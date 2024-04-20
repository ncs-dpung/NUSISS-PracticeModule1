FROM node:20 AS builder

# Install the Angular CLI globally
RUN npm install -g @angular/cli

# Set the working directory in the container
WORKDIR /app

ADD ./package.json .

# Install project dependencies
RUN npm install

# Copy the Angular project files into the container
ADD ./src ./src

ADD ./*.json ./

# Build the Angular application for production
RUN ng build

## Use the Nginx base image for serving the built Angular app
FROM nginx:alpine

# # Copy the Nginx configuration file
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy the built Angular app from the builder stage to the Nginx directory
COPY --from=builder /app/dist/advanced-inventory-management-system/browser /usr/share/nginx/html/aims

EXPOSE 80

