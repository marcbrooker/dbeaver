# DBeaver Amazon Aurora DSQL Plugin

This plugin provides support for Amazon Aurora DSQL in DBeaver with proper AWS IAM authentication.

## Features

- PostgreSQL-compatible SQL interface
- AWS IAM authentication support with token generation
- Serverless database connectivity
- Standard PostgreSQL features with Aurora DSQL limitations
- SSL/TLS encryption with certificate verification

## Connection Setup

Aurora DSQL uses PostgreSQL-compatible drivers and protocols with AWS-specific authentication.

### Connection Properties

- **Host**: Your Aurora DSQL cluster endpoint (e.g., `abc123.dsql.us-east-1.on.aws`)
- **Port**: 5432 (default PostgreSQL port)
- **Database**: `postgres` (default) or your specific database name
- **Username**: Your Aurora DSQL username (`admin` for admin users)

### AWS Authentication Properties

The plugin supports AWS IAM authentication through the following properties:

- **Use AWS Authentication**: Enable/disable AWS IAM token authentication (default: true)
- **AWS Region**: Required - the AWS region where your Aurora DSQL cluster is located
- **AWS Profile**: Optional - AWS credentials profile name
- **AWS Access Key ID**: Optional - explicit AWS access key (if not using profile/IAM role)
- **AWS Secret Access Key**: Optional - explicit AWS secret key (if not using profile/IAM role)
- **AWS Session Token**: Optional - for temporary credentials

### Authentication Methods

1. **AWS Profile**: Use named profile from `~/.aws/credentials`
2. **Environment Variables**: `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_REGION`
3. **IAM Role**: For EC2 instances or containers with attached IAM roles
4. **Explicit Credentials**: Direct input of access key and secret key

### SSL Configuration

Aurora DSQL requires secure connections with the following settings (automatically configured):
- `sslmode=verify-full`
- `sslfactory=org.postgresql.ssl.DefaultJavaSSLFactory`
- `sslNegotiation=direct`

## Authentication Flow

1. Plugin detects if AWS authentication is enabled
2. Generates temporary authentication token using AWS SDK
3. Uses token as password for PostgreSQL connection
4. Tokens are automatically refreshed as needed

### Admin vs Non-Admin Users

- **Admin users**: Use username `admin` and get admin-level authentication tokens
- **Non-admin users**: Use specific username and get standard authentication tokens
- Non-admin users typically work with custom schemas (e.g., `myschema`)

## Limitations

Aurora DSQL is a serverless, PostgreSQL-compatible database with some limitations:

- No support for extensions
- Limited trigger support
- No materialized views
- No table partitioning
- No table inheritance
- IAM-based access control instead of PostgreSQL roles
- No native client tools support
- No foreign data wrappers
- No custom aggregates or operator families

## Implementation Details

### Key Classes

- `AuroraDsqlDataSourceProvider`: Main data source provider with AWS authentication
- `AuroraDsqlDataSource`: Data source implementation extending PostgreSQL
- `AuroraDsqlServerType`: Server type with Aurora DSQL-specific feature flags
- `AuroraDsqlAuthHelper`: Authentication helper for AWS token generation
- `AuroraDsqlAwsIntegration`: AWS SDK integration layer

### AWS SDK Integration

The plugin is designed to integrate with the AWS SDK for Java:
- `software.amazon.awssdk.services.dsql.DsqlUtilities`
- `software.amazon.awssdk.services.dsql.model.GenerateAuthTokenRequest`
- `software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider`

When AWS SDK dependencies are available, the plugin will automatically use them for token generation.

## Development

The plugin follows DBeaver's architecture patterns and extends the PostgreSQL plugin for maximum compatibility while adding Aurora DSQL-specific authentication and limitations.

### Building

The plugin integrates with DBeaver's build system and will be included when building the complete DBeaver application.

### Testing

To test the plugin:
1. Set up an Aurora DSQL cluster in AWS
2. Configure AWS credentials
3. Create a connection using the "Amazon Aurora DSQL" connection type
4. Verify authentication and basic SQL operations

## Example Connection

```
Host: mydsqlcluster.dsql.us-east-1.on.aws
Port: 5432
Database: postgres
Username: admin (or your specific username)
AWS Region: us-east-1
Use AWS Authentication: true
```

The plugin will automatically generate authentication tokens and establish secure connections to your Aurora DSQL cluster.
