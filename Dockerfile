FROM mysql:8.0.36

# Copy your SQL initialization scripts to a directory in the container
COPY ./db /docker-entrypoint-initdb.d/

# Environment variables for MySQL configuration
ENV MYSQL_ROOT_PASSWORD=pwd123
ENV MYSQL_DATABASE=inventory_db
ENV MYSQL_USER=user123
ENV MYSQL_PASSWORD=pwd123

# Expose the MySQL port
EXPOSE 3306
