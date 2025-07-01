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
package org.jkiss.dbeaver.ext.aurora.dsql.model;

import org.jkiss.code.NotNull;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.ext.postgresql.model.PostgreDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;

/**
 * Amazon Aurora DSQL data source
 */
public class AuroraDsqlDataSource extends PostgreDataSource {

    public AuroraDsqlDataSource(DBRProgressMonitor monitor, DBPDataSourceContainer container) throws DBException {
        super(monitor, container, "aurora_dsql");
    }

    @Override
    public boolean isServerVersionAtLeast(int major, int minor) {
        // Aurora DSQL is based on PostgreSQL 15.x compatibility
        if (major < 15) {
            return true;
        } else if (major == 15) {
            return minor <= 0; // Assume 15.0 compatibility
        } else {
            return false; // Don't assume newer features are available
        }
    }

    @NotNull
    @Override
    public String getServerTypeName() {
        return "Amazon Aurora DSQL";
    }

}
