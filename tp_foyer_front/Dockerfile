# Use an official Node.js image to build the project
FROM node:16 as build-stage

# Set the working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the Angular project
RUN npm run build --prod

# Use a lightweight web server to serve the built Angular files
FROM nginx:alpine as production-stage
COPY --from=build-stage /app/dist/tp_foyer_front /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
