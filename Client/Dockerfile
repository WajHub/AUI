# Stage 1
FROM node:23-alpine AS build

WORKDIR /dist/src/app

COPY package.json ./

RUN npm install

COPY . .

RUN npm run build --prod

# Stage 2
FROM nginx:1.27.2 AS ngi

COPY --from=build /dist/src/app/dist/client/browser /usr/share/nginx/html

COPY /nginx.conf  /etc/nginx/conf.d/default.conf

EXPOSE 80
