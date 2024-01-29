FROM node:20 AS builder

# Install the Angular CLI globally
RUN npm install -g @angular/cli

# Set the working directory in the container
WORKDIR /app

# Copy the Angular project files into the container
COPY . .

# Install project dependencies
RUN npm install

# Build the Angular application for production
RUN ng build

# Use the Nginx base image for serving the built Angular app
# FROM nginx:1.21

# Copy the Nginx configuration file
# COPY nginx.conf /etc/nginx/nginx.conf

# Copy the built Angular app from the builder stage to the Nginx directory
# COPY --from=builder /app/dist/advanced-inventory-management-system /usr/share/nginx/html
