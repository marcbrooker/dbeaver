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

/**
 * Amazon Aurora DSQL constants
 */
public class AuroraDsqlConstants {

    public static final String DRIVER_ID = "aurora_dsql";
    public static final String SERVER_TYPE_ID = "aurora_dsql";
    
    // Default connection properties
    public static final int DEFAULT_PORT = 5432;
    public static final String DEFAULT_DATABASE = "postgres";
    
    // Aurora DSQL specific SSL properties (required)
    public static final String PROP_SSL_MODE = "sslmode";
    public static final String PROP_SSL_MODE_VALUE = "verify-full";
    public static final String PROP_SSL_FACTORY = "sslfactory";
    public static final String PROP_SSL_FACTORY_VALUE = "org.postgresql.ssl.DefaultJavaSSLFactory";
    public static final String PROP_SSL_NEGOTIATION = "sslNegotiation";
    public static final String PROP_SSL_NEGOTIATION_VALUE = "direct";
    
    // AWS specific properties
    public static final String PROP_AWS_REGION = "aws.region";
    public static final String PROP_AWS_ACCESS_KEY = "aws.accessKeyId";
    public static final String PROP_AWS_SECRET_KEY = "aws.secretAccessKey";
    public static final String PROP_AWS_SESSION_TOKEN = "aws.sessionToken";
    public static final String PROP_AWS_PROFILE = "aws.profile";
    
    // Aurora DSQL authentication
    public static final String PROP_USE_AWS_AUTH = "useAwsAuth";
    public static final String PROP_ADMIN_USER = "admin";
    public static final String PROP_TOKEN_EXPIRATION = "tokenExpiration";
    
    // Default token expiration (15 minutes)
    public static final int DEFAULT_TOKEN_EXPIRATION_SECONDS = 900;
    
}
