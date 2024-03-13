import axios from 'axios';

import { fetchBaselineMatrices } from '../services/Api.js';

// Получение базовых матриц (без содержимого)
export async function getBaselineMatrices() {
  try {
    const response = await fetchBaselineMatrices();
    const { matrices } = response;

    if (matrices.length === 0) {
      return [];
    }

    // Преобразуем полученные данные в нужную структуру
    const formattedMatrices = matrices.map(matrix => ({
      id: matrix.matrix_id,
      segmentId: matrix.segment_id,
      name: matrix.matrix_name
    }));

    return formattedMatrices;
  } catch (error) {
    console.error('Ошибка при получении базовых матриц:', error);
    throw error;
  }
}

// Пример использования функции
/*
getBaselineMatrices()
  .then(baselineMatrices => {
    console.log('Базовые матрицы:', baselineMatrices);
  })
  .catch(error => {
    console.error('Ошибка:', error);
  });
*/

// Добавление записи в базовую матрицу
export async function addRecordToBaselineMatrix(matrixId, microcategoryId, locationId, price) {
  try {
    const response = await axios.post(`/baseline_matrices/${matrixId}`, {
      microcategory_id: microcategoryId,
      location_id: locationId,
      price: price
    });

    return response.data;
  } catch (error) {
    console.error('Ошибка при добавлении записи в базовую матрицу:', error);
    throw error;
  }
}

// Пример использования функции
/*
const matrixId = 1;
const microcategoryId = 10;
const locationId = 202;
const price = 1000;

addRecordToBaselineMatrix(matrixId, microcategoryId, locationId, price)
  .then(response => {
    console.log('Ответ сервера:', response);
  })
  .catch(error => {
    console.error('Ошибка:', error);
  });
*/

// Обновление цены в базовой матрице
export async function updatePriceInBaselineMatrix(matrixId, microcategoryId, locationId, newPrice) {
  try {
    const response = await axios.patch(`/baseline_matrices/${matrixId}`, {
      microcategory_id: microcategoryId,
      location_id: locationId,
      price: newPrice
    });

    return response.data;
  } catch (error) {
    console.error('Ошибка при обновлении цены в базовой матрице:', error);
    throw error;
  }
}

// Пример использования функции
/*
const matrixId = 1;
const microcategoryId = 10;
const locationId = 202;
const newPrice = 1200;

updatePriceInBaselineMatrix(matrixId, microcategoryId, locationId, newPrice)
  .then(response => {
    console.log('Ответ сервера:', response);
  })
  .catch(error => {
    console.error('Ошибка:', error);
  });
*/
