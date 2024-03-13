import axios from 'axios';

/**
 * Получения базовых матриц (без содержимого)
 * @returns {Promise<Array>} Массив базовых матриц
 */
export async function fetchBaselineMatrices() {
    try {
      const response = await axios.get('/baseline_matrices');

      return response.data.matrices;
    } catch (error) {
      console.error('Ошибка при загрузке базовых матриц:', error);
      throw error;
    }
}

/**
 * Добавление записи в базовую матрицу
 * @param {number} matrixId - id базовой матрицы
 * @param {number} microcategoryId - id микрокатегории
 * @param {number} locationId - id локации
 * @param {number} price - цена
 * @returns {Promise<Object>} Объект с информацией о добавленной записи в базовую матрицу
 */
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

/**
 * Обновление цены в базовой матрице
 * @param {number} matrixId - id базовой матрицы
 * @param {number} locationId - id локации
 * @param {number} microcategoryId - id микрокатегории
 * @param {number} price - новая цена
 * @returns {Promise<Object>} Объект с информацией об обновленной цене в базовой матрице
 */
export async function updateBaselineMatrixPrice(matrixId, locationId, microcategoryId, price) {
    try {
      const response = await axios.patch(`/baseline_matrices/${matrixId}`, {
        location_id: locationId,
        microcategory_id: microcategoryId,
        price: price
      });
  
      return response.data;
    } catch (error) {
      console.error('Ошибка при обновлении цены в базовой матрице:', error);
      throw error;
    }
}

/**
 * Удаление записи в базовой матрице
 * @param {number} matrixId - id базовой матрицы
 * @param {number} locationId - id локации
 * @param {number} microcategoryId - id микрокатегории
 * @returns {Promise<Object>} Объект с информацией об удаленной записи в базовой матрице
 */
export async function deleteRecordFromBaselineMatrix(matrixId, locationId, microcategoryId) {
  try {
    const response = await axios.delete(`/baseline_matrices/${matrixId}`, {
      data: {
        location_id: locationId,
        microcategory_id: microcategoryId
      }
    });

    return response.data;
  } catch (error) {
    console.error('Ошибка при удалении записи в базовой матрице:', error);
    throw error;
  }
}