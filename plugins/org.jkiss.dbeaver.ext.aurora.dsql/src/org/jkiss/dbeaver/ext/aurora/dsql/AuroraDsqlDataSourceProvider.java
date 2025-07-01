/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2025 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ext.aurora.dsql;

import org.jkiss.code.NotNull;
import org.jkiss.code.Nullable;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.ext.aurora.dsql.model.AuroraDsqlDataSource;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSourceProvider;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.utils.CommonUtils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Amazon Aurora DSQL data source provider
 */
public class AuroraDsqlDataSourceProvider extends JDBCDataSourceProvider {

    private static final Log log = Log.getLog(AuroraDsqlDataSourceProvider.class);

    public AuroraDsqlDataSourceProvider() {
    }

    @Override
    public long getFeatures() {
        return FEATURE_SCHEMAS | FEATURE_CATALOGS;
    }

    @Override
    public String getConnectionURL(DBPConnectionConfiguration connectionInfo) {
        StringBuilder url = new StringBuilder(128);
        url.append("jdbc:postgresql://");
        if (connectionInfo.getHostName() != null) {
            url.append(connectionInfo.getHostName());
        }
        if (connectionInfo.getHostPort() != null && !connectionInfo.getHostPort().isEmpty()) {
            url.append(":").append(connectionInfo.getHostPort());
        } else {
            url.append(":").append(AuroraDsqlConstants.DEFAULT_PORT);
        }
        if (connectionInfo.getDatabaseName() != null) {
            url.append("/").append(connectionInfo.getDatabaseName());
        } else {
            url.append("/").append(AuroraDsqlConstants.DEFAULT_DATABASE);
        }
        return url.toString();
    }

    @NotNull
    @Override
    public DBPDataSource openDataSource(@NotNull DBRProgressMonitor monitor, @NotNull DBPDataSourceContainer container)
        throws DBException {
        return new AuroraDsqlDataSource(monitor, container);
    }

    @Override
    protected Connection openConnection(@NotNull DBRProgressMonitor monitor, @NotNull DBPDataSourceContainer dataSourceContainer, @NotNull String purpose) throws DBException, SQLException {
        DBPConnectionConfiguration connectionInfo = dataSourceContainer.getActualConnectionConfiguration();
        
        // Get the JDBC driver
        Driver driver = getJdbcDriver(dataSourceContainer);
        
        // Prepare connection properties
        Properties connectProps = getAllConnectionProperties(monitor, dataSourceContainer, purpose, connectionInfo);
        
        // Configure Aurora DSQL specific properties
        AuroraDsqlAuthHelper.configureConnectionProperties(connectProps, connectionInfo);
        
        // Handle Aurora DSQL authentication
        if (AuroraDsqlAuthHelper.shouldUseAwsAuth(connectionInfo)) {
            String username = connectionInfo.getUserName();
            String hostname = connectionInfo.getHostName();
            String region = connectionInfo.getProviderProperty(AuroraDsqlConstants.PROP_AWS_REGION);
            
            if (CommonUtils.isEmpty(region)) {
                log.warn("AWS region not specified for Aurora DSQL connection. Authentication may fail.");
            }
            
            if (!CommonUtils.isEmpty(username) && !CommonUtils.isEmpty(hostname) && !CommonUtils.isEmpty(region)) {
                try {
                    boolean isAdmin = AuroraDsqlAuthHelper.isAdminUser(username);
                    String authToken = AuroraDsqlAuthHelper.generateAuthToken(hostname, region, username, isAdmin);
                    
                    if (authToken != null) {
                        connectProps.setProperty("password", authToken);
                        log.debug("Using AWS authentication token for Aurora DSQL connection");
                    } else {
                        log.debug("AWS authentication token generation not available, using standard authentication");
                    }
                } catch (Exception e) {
                    log.warn("Failed to generate AWS authentication token, falling back to standard authentication", e);
                }
            }
        }
        
        // Create connection
        String url = getConnectionURL(connectionInfo);
        log.debug("Connecting to Aurora DSQL: " + url);
        
        return driver.connect(url, connectProps);
    }

}
