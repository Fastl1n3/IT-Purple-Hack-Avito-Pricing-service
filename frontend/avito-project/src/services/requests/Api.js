import axios from "axios";

/**
 * Получение сегментов пользователя по его id
 * @param {number} userId - id пользователя
 * @returns {Promise<Object>} Объект с информацией о сегментах пользователя
 */
export async function fetchUserSegments(userId) {
    try {
      const response = await axios.get(`/segments/${userId}`);
      return response.data;
    } catch (error) {
      console.error('Ошибка при загрузке сегментов пользователя:', error);
      throw error;
    }
}

/**
 * Получение цены для указанных базовой матрицы, локации и микрокатегории
 * @param {number} baselineMatrixId - id базовой матрицы
 * @param {number} locationId - id локации
 * @param {number} microcategoryId - id микрокатегории
 * @returns {Promise<Object>} Объект с информацией о цене
 */
export async function fetchPrice(baselineMatrixId, locationId, microcategoryId) {
    try {
      const response = await axios.get(`/price/${baselineMatrixId}?location=${locationId}&category=${microcategoryId}`);
      return response.data;
    } catch (error) {
      console.error('Ошибка при получении цены:', error);
      throw error;
    }
}

/**
 * Получение верхних 3 уровней микрокатегорий
 * @returns {Promise<Array>} Массив объектов верхних 3 уровней микрокатегорий
 */
export async function fetchTopMicrocategories() {
    try {
      const response = await axios.get('/microcategories');
      return response.data.values;
    } catch (error) {
      console.error('Ошибка при загрузке верхних уровней микрокатегорий:', error);
      throw error;
    }
}

/**
 * Получение следующих 3 уровней микрокатегорий для указанной микрокатегории
 * @param {number} microcategoryId - id микрокатегории
 * @returns {Promise<Object>} Объект с информацией о следующих 3 уровнях микрокатегорий
 */
export async function fetchNextMicrocategories(microcategoryId) {
    try {
      const response = await axios.get(`/microcategories/${microcategoryId}`);
      return response.data;
    } catch (error) {
      console.error('Ошибка при загрузке следующих уровней микрокатегорий:', error);
      throw error;
    }
}

/**
 * Получение верхнего уровня локаций
 * @returns {Promise<Array>} Массив объектов верхнего уровня локаций
 */
export async function fetchTopLocations() {
    try {
      const response = await axios.get('/locations');
      return response.data.values;
    } catch (error) {
      console.error('Ошибка при загрузке верхнего уровня локаций:', error);
      throw error;
    }
}

/**
 * Получение следующего уровня локации для указанной локации
 * @param {number} locationId - id локации
 * @returns {Promise<Object>} Объект с информацией о следующем уровне локации
 */
export async function fetchNextLocation(locationId) {
    try {
      const response = await axios.get(`/locations/${locationId}`);
      return response.data;
    } catch (error) {
      console.error('Ошибка при загрузке следующего уровня локации:', error);
      throw error;
    }
}
  
  
  