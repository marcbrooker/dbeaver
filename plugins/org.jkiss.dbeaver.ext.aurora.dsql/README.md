# DBeaver Amazon Aurora DSQL Plugin

This plugin provides support for Amazon Aurora DSQL in DBeaver.

## Features

- PostgreSQL-compatible SQL interface
- AWS IAM authentication support
- Serverless database connectivity
- Standard PostgreSQL features with Aurora DSQL limitations

## Connection

Aurora DSQL uses PostgreSQL-compatible drivers and protocols. The plugin extends the PostgreSQL plugin with Aurora DSQL-specific features and limitations.

### Connection Properties

- **Host**: Your Aurora DSQL cluster endpoint
- **Port**: 5432 (default PostgreSQL port)
- **Database**: Database name
- **Authentication**: AWS IAM or standard PostgreSQL authentication

## Limitations

Aurora DSQL is a serverless, PostgreSQL-compatible database with some limitations compared to full PostgreSQL:

- No support for extensions
- Limited trigger support
- No materialized views
- No table partitioning
- No table inheritance
- IAM-based access control instead of PostgreSQL roles
- No native client tools support

## Implementation

This plugin extends the DBeaver PostgreSQL plugin (`org.jkiss.dbeaver.ext.postgresql`) and customizes behavior for Aurora DSQL's specific capabilities and limitations.

### Key Classes

- `AuroraDsqlDataSourceProvider`: Main data source provider
- `AuroraDsqlDataSource`: Data source implementation extending PostgreSQL
- `AuroraDsqlServerType`: Server type implementation with Aurora DSQL-specific feature flags

## Development

The plugin follows the same pattern as other PostgreSQL-compatible database plugins in DBeaver, inheriting most functionality from the PostgreSQL plugin while overriding specific behaviors for Aurora DSQL.
