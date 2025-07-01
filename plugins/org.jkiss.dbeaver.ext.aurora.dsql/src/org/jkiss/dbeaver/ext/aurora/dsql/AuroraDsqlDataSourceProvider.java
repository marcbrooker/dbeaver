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
import org.jkiss.dbeaver.ext.aurora.dsql.model.AuroraDsqlDataSource;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSourceProvider;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;

/**
 * Amazon Aurora DSQL data source provider
 */
public class AuroraDsqlDataSourceProvider extends JDBCDataSourceProvider {

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
        }
        if (connectionInfo.getDatabaseName() != null) {
            url.append("/").append(connectionInfo.getDatabaseName());
        }
        return url.toString();
    }

    @NotNull
    @Override
    public DBPDataSource openDataSource(@NotNull DBRProgressMonitor monitor, @NotNull DBPDataSourceContainer container)
        throws DBException {
        return new AuroraDsqlDataSource(monitor, container);
    }

}
