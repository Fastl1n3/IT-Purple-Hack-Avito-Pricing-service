import axios from 'axios';

/**
 * Функция для получения скидочных матриц (без содержимого)
 * @returns {Promise<Array>} Массив скидочных матриц
 */
export async function fetchDiscountMatrices() {
    try {
      const response = await axios.get('/discount_matrices');
      
      return response.data.matrices;
    } catch (error) {
      console.error('Ошибка при загрузке скидочных матриц:', error);
      throw error;
    }
}

/**
 * Функция для добавления записи в скидочную матрицу
 * @param {number} matrixId ID скидочной матрицы
 * @param {number} microcategoryId ID микрокатегории
 * @param {number} locationId ID локации
 * @param {number} price Цена
 * @returns {Promise<Object>} Ответ от сервера
 */
export async function addRecordToDiscountMatrix(matrixId, microcategoryId, locationId, price) {
  try {
    const response = await axios.post(`/discount_matrices/${matrixId}`, {
      microcategory_id: microcategoryId,
      location_id: locationId,
      price: price
    });

    return response.data;
  } catch (error) {
    console.error('Ошибка при добавлении записи в скидочную матрицу:', error);
    throw error;
  }
}

/**
 * Функция для создания скидочной матрицы
 * @param {string} matrixName Название скидочной матрицы
 * @returns {Promise<Object>} Ответ от сервера
 */
export async function createDiscountMatrix(matrixName) {
  try {
    const response = await axios.post('/discount_matrices', {
      matrix_name: matrixName
    });

    return response.data;
  } catch (error) {
    console.error('Ошибка при создании скидочной матрицы:', error);
    throw error;
  }
}

/**
 * Функция для обновления цены в скидочной матрице
 * @param {number} matrixId ID скидочной матрицы
 * @param {number} microcategoryId ID микрокатегории
 * @param {number} locationId ID локации
 * @param {number} price Новая цена
 * @returns {Promise<Object>} Ответ от сервера
 */
export async function updatePriceInDiscountMatrix(matrixId, microcategoryId, locationId, price) {
  try {
    const response = await axios.patch(`/discount_matrices/${matrixId}`, {
      microcategory_id: microcategoryId,
      location_id: locationId,
      price: price
    });

    return response.data;
  } catch (error) {
    console.error('Ошибка при обновлении цены в скидочной матрице:', error);
    throw error;
  }
}

/**
 * Функция для удаления записи из скидочной матрицы
 * @param {number} matrixId ID скидочной матрицы
 * @param {number} microcategoryId ID микрокатегории
 * @param {number} locationId ID локации
 * @returns {Promise<Object>} Ответ от сервера
 */
export async function deleteRecordFromDiscountMatrix(matrixId, microcategoryId, locationId) {
  try {
    const response = await axios.delete(`/discount_matrices/${matrixId}`, {
      data: {
        microcategory_id: microcategoryId,
        location_id: locationId
      }
    });

    return response.data;
  } catch (error) {
    console.error('Ошибка при удалении записи из скидочной матрицы:', error);
    throw error;
  }
}