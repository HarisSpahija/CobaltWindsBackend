FROM mcr.microsoft.com/azure-sql-edge

# create directory within SQL container for database files
RUN mkdir -p /opt/mssql-scripts

# copy the database files from host to container
COPY sql/ /opt/mssql-scripts

# set environment variables
ENV SA_PASSWORD=Test123456
ENV ACCEPT_EULA=Y

RUN ( /opt/mssql/bin/sqlservr --accept-eula & ) | grep -q "Service Broker manager has started" \
    && /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "Test123456" -i /opt/mssql-scripts/create_db.sql \
    && pkill sqlservr