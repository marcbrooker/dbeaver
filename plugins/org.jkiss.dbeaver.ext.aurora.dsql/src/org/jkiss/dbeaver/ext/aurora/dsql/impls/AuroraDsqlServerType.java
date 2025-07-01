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
package org.jkiss.dbeaver.ext.aurora.dsql.impls;

import org.jkiss.dbeaver.ext.postgresql.model.PostgreDataSource;
import org.jkiss.dbeaver.ext.postgresql.model.impls.PostgreServerExtensionBase;

/**
 * Amazon Aurora DSQL server type implementation
 */
public class AuroraDsqlServerType extends PostgreServerExtensionBase {
    public static final String TYPE_ID = "aurora_dsql";

    public AuroraDsqlServerType(PostgreDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getServerTypeName() {
        return "Amazon Aurora DSQL";
    }

    @Override
    public boolean supportsEntityMetadataInResults() {
        return true;
    }

    @Override
    public boolean supportsPGConstraintExpressionColumn() {
        return true; // Aurora DSQL supports modern PostgreSQL features
    }

    @Override
    public boolean supportsHasOidsColumn() {
        return false; // Aurora DSQL doesn't support OIDs (modern PostgreSQL)
    }

    @Override
    public boolean supportsRowLevelSecurity() {
        return false; // Aurora DSQL doesn't support RLS in initial version
    }

    @Override
    public boolean supportsDatabaseSize() {
        return false; // Aurora DSQL is serverless, size queries may not be meaningful
    }

    @Override
    public boolean supportsBackslashStringEscape() {
        return false; // Modern PostgreSQL behavior
    }

    @Override
    public boolean supportsDisablingAllTriggers() {
        return false; // Aurora DSQL has limited trigger support
    }

    @Override
    public boolean supportsGeneratedColumns() {
        return true; // Aurora DSQL supports generated columns
    }

    @Override
    public boolean supportsKeyAndIndexRename() {
        return true; // Modern PostgreSQL feature
    }

    @Override
    public boolean supportsAlterUserChangePassword() {
        return false; // Aurora DSQL uses IAM authentication
    }

    @Override
    public boolean supportsCopyFromStdIn() {
        return false; // Aurora DSQL doesn't support COPY FROM STDIN
    }

    @Override
    public boolean supportsEventTriggers() {
        return false; // Aurora DSQL doesn't support event triggers
    }

    @Override
    public boolean supportsAlterStorageStrategy() {
        return false; // Aurora DSQL manages storage automatically
    }

    @Override
    public boolean supportsStorageModifier() {
        return false; // Aurora DSQL manages storage automatically
    }

    @Override
    public boolean supportsTriggers() {
        return false; // Aurora DSQL has limited trigger support initially
    }

    @Override
    public boolean supportsForeignKeys() {
        return true; // Aurora DSQL supports foreign keys
    }

    @Override
    public boolean supportsIndexes() {
        return true; // Aurora DSQL supports indexes
    }

    @Override
    public boolean supportsMaterializedViews() {
        return false; // Aurora DSQL doesn't support materialized views initially
    }

    @Override
    public boolean supportsPartitions() {
        return false; // Aurora DSQL doesn't support table partitioning initially
    }

    @Override
    public boolean supportsInheritance() {
        return false; // Aurora DSQL doesn't support table inheritance
    }

    @Override
    public boolean supportsExtensions() {
        return false; // Aurora DSQL doesn't support PostgreSQL extensions
    }

    @Override
    public boolean supportsRoles() {
        return false; // Aurora DSQL uses IAM for access control
    }

    @Override
    public boolean supportsSessionActivity() {
        return false; // Aurora DSQL doesn't expose session information
    }

    @Override
    public boolean supportsLocks() {
        return false; // Aurora DSQL doesn't expose lock information
    }

    @Override
    public boolean supportsForeignServers() {
        return false; // Aurora DSQL doesn't support foreign data wrappers
    }

    @Override
    public boolean supportsAggregates() {
        return false; // Aurora DSQL doesn't support custom aggregates
    }

    @Override
    public boolean supportsCollations() {
        return true; // Aurora DSQL supports collations
    }

    @Override
    public boolean supportsTablespaces() {
        return false; // Aurora DSQL manages storage automatically
    }

    @Override
    public boolean supportsSequences() {
        return true; // Aurora DSQL supports sequences
    }

    @Override
    public boolean supportsNativeClient() {
        return false; // Aurora DSQL is cloud-only, no native client tools
    }

    @Override
    public boolean supportsClientInfo() {
        return true; // Aurora DSQL supports client info
    }

    @Override
    public boolean supportsOpFamily() {
        return false; // Aurora DSQL doesn't support operator families
    }

    @Override
    public boolean supportsEncodings() {
        return true; // Aurora DSQL supports character encodings
    }

    @Override
    public boolean supportsLanguages() {
        return false; // Aurora DSQL doesn't support procedural languages
    }

    @Override
    public boolean supportsDependencies() {
        return false; // Aurora DSQL doesn't expose dependency information
    }

    @Override
    public boolean supportsRules() {
        return false; // Aurora DSQL doesn't support rules
    }

}
