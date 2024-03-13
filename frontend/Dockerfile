# Используем за основу образ Node.js
FROM node:21.7.1 AS builder

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем файлы package.json и package-lock.json и устанавливаем зависимости
COPY package*.json ./
RUN npm install

# Копируем все остальные файлы в рабочую директорию
COPY ./ ./

# Собираем приложение
RUN npm run build

# Создаем production-образ
FROM node:21.7.1-alpine

# Копируем собранные файлы из предыдущего образа
COPY --from=builder /app/build ./build

# Определяем порт, который будет слушать приложение
EXPOSE 3000

# Команда для запуска приложения
CMD ["npm", "start"]
