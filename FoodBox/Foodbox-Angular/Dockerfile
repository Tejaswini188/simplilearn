# Stage 1: Compile and Build angular codebase

# Use official node image as the base image
FROM node:10.16.3-alpine

# Set the working directory
WORKDIR /app

# Add the source code to app
COPY package.json .

# Install all the dependencies
RUN npm install

COPY . .

#RUN npm run build
#CMD npm run start
# Generate the build of the application
# RUN npm run build

# #Stage 2: Serve app with nginx server

# # Use official nginx image as the base image
# FROM nginx:latest

# # Copy the build output to replace the default nginx contents.
# COPY --from=node /app/dist/Foodbox-Angular /usr/share/nginx/html