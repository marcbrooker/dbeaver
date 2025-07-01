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
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.utils.CommonUtils;

import java.util.Properties;

/**
 * Aurora DSQL authentication helper
 */
public class AuroraDsqlAuthHelper {
    
    private static final Log log = Log.getLog(AuroraDsqlAuthHelper.class);

    /**
     * Generate Aurora DSQL authentication token using AWS SDK
     */
    @Nullable
    public static String generateAuthToken(
        @NotNull String hostname, 
        @NotNull String region, 
        @NotNull String username,
        boolean isAdmin
    ) throws DBException {
        return AuroraDsqlAwsIntegration.generateAuthToken(hostname, region, username, isAdmin);
    }

    /**
     * Configure connection properties for Aurora DSQL
     */
    public static void configureConnectionProperties(
        @NotNull Properties props, 
        @NotNull DBPConnectionConfiguration connectionInfo
    ) {
        // Set required SSL properties for Aurora DSQL
        props.setProperty(AuroraDsqlConstants.PROP_SSL_MODE, AuroraDsqlConstants.PROP_SSL_MODE_VALUE);
        props.setProperty(AuroraDsqlConstants.PROP_SSL_FACTORY, AuroraDsqlConstants.PROP_SSL_FACTORY_VALUE);
        props.setProperty(AuroraDsqlConstants.PROP_SSL_NEGOTIATION, AuroraDsqlConstants.PROP_SSL_NEGOTIATION_VALUE);
        
        // Configure AWS region if provided
        String region = connectionInfo.getProviderProperty(AuroraDsqlConstants.PROP_AWS_REGION);
        if (!CommonUtils.isEmpty(region)) {
            props.setProperty(AuroraDsqlConstants.PROP_AWS_REGION, region);
        }
        
        // Configure AWS credentials if provided
        String accessKey = connectionInfo.getProviderProperty(AuroraDsqlConstants.PROP_AWS_ACCESS_KEY);
        if (!CommonUtils.isEmpty(accessKey)) {
            props.setProperty(AuroraDsqlConstants.PROP_AWS_ACCESS_KEY, accessKey);
        }
        
        String secretKey = connectionInfo.getProviderProperty(AuroraDsqlConstants.PROP_AWS_SECRET_KEY);
        if (!CommonUtils.isEmpty(secretKey)) {
            props.setProperty(AuroraDsqlConstants.PROP_AWS_SECRET_KEY, secretKey);
        }
        
        String sessionToken = connectionInfo.getProviderProperty(AuroraDsqlConstants.PROP_AWS_SESSION_TOKEN);
        if (!CommonUtils.isEmpty(sessionToken)) {
            props.setProperty(AuroraDsqlConstants.PROP_AWS_SESSION_TOKEN, sessionToken);
        }
        
        String profile = connectionInfo.getProviderProperty(AuroraDsqlConstants.PROP_AWS_PROFILE);
        if (!CommonUtils.isEmpty(profile)) {
            props.setProperty(AuroraDsqlConstants.PROP_AWS_PROFILE, profile);
        }
    }

    /**
     * Check if AWS authentication should be used
     */
    public static boolean shouldUseAwsAuth(@NotNull DBPConnectionConfiguration connectionInfo) {
        String useAwsAuth = connectionInfo.getProviderProperty(AuroraDsqlConstants.PROP_USE_AWS_AUTH);
        return CommonUtils.getBoolean(useAwsAuth, true); // Default to true for Aurora DSQL
    }

    /**
     * Check if user is admin user
     */
    public static boolean isAdminUser(@NotNull String username) {
        return AuroraDsqlConstants.PROP_ADMIN_USER.equalsIgnoreCase(username);
    }

}
