# 빌드 스테이지
FROM node:20-alpine AS build

WORKDIR /app

COPY frontend/package*.json ./
RUN npm install

COPY frontend/ ./
RUN npm run build

# 런타임 스테이지
FROM nginx:alpine

COPY --from=build /app/build /usr/share/nginx/html

# Optional: nginx 설정 커스터마이징 (필요 시)
# COPY docker/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]