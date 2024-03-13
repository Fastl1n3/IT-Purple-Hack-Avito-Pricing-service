FROM node:21.7.1-alpine3.18

WORKDIR /frontend/avito-project

RUN apk add --no-cache libc6-compat
RUN npm install --global pm2

COPY ./avito-project/package*.json ./

RUN npm install --production

COPY ./avito-project/ ./

RUN npm run build

#COPY --from=builder /app/build ./build

EXPOSE 3000

CMD ["pm2-runtime", "npm", "--", "start"]