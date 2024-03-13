import axios from 'axios';

export async function fetchUserSegments(userId) {
  try {
    const response = await axios.get(`/segments/${userId}`);
    return response.data;
  } catch (error) {
    console.error('Ошибка при загрузке сегментов пользователя:', error);
    throw error;
  }
}

export async function fetchBaselineMatrices() {
  try {
    const response = await axios.get('/baseline_matrices');
    return response.data;
  } catch (error) {
    console.error('Ошибка при загрузке базовых матриц:', error);
    throw error;
  }
}

export async function fetchDiscountMatrices() {
  try {
    const response = await axios.get('/discount_matrices');
    return response.data;
  } catch (error) {
    console.error('Ошибка при загрузке скидочных матриц:', error);
    throw error;
  }
}

export async function fetchDiscountMatrixForSegment(segmentId) {
  try {
    const response = await axios.get(`/discount_matrices?segment=${segmentId}`);
    return response.data;
  } catch (error) {
    console.error('Ошибка при загрузке скидочной матрицы для сегмента:', error);
    throw error;
  }
}

export async function fetchPrice(baselineMatrixId, locationId, microcategoryId) {
  try {
    const response = await axios.get(`/price/${baselineMatrixId}?location=${locationId}&category=${microcategoryId}`);
    return response.data;
  } catch (error) {
    console.error('Ошибка при получении цены:', error);
    throw error;
  }
}

export async function fetchMicrocategories() {
  try {
    const response = await axios.get('/microcategories');
    return response.data;
  } catch (error) {
    console.error('Ошибка при загрузке микрокатегорий:', error);
    throw error;
  }
}

export async function fetchLocations() {
  try {
    const response = await axios.get('/locations');
    return response.data;
  } catch (error) {
    console.error('Ошибка при загрузке локаций:', error);
    throw error;
  }
}

export async function fetchChildMicrocategories(microcategoryId) {
  try {
    const response = await axios.get(`/microcategories/${microcategoryId}`);
    return response.data;
  } catch (error) {
    console.error('Ошибка при загрузке дочерних микрокатегорий:', error);
    throw error;
  }
}

export async function fetchChildLocations(locationId) {
  try {
    const response = await axios.get(`/locations/${locationId}`);
    return response.data;
  } catch (error) {
    console.error('Ошибка при загрузке дочерних локаций:', error);
    throw error;
  }
}

export async function addDiscountMatrix(matrixId, body) {
  try {
    const response = await axios.post(`/discount_matrices/${matrixId}`, body);
    return response.data;
  } catch (error) {
    console.error('Ошибка при добавлении записи в скидочную матрицу:', error);
    throw error;
  }
}

export async function createDiscountMatrix(body) {
  try {
    const response = await axios.post('/discount_matrices', body);
    return response.data;
  } catch (error) {
    console.error('Ошибка при создании скидочной матрицы:', error);
    throw error;
  }
}

export async function addBaselineMatrix(matrixId, body) {
  try {
    const response = await axios.post(`/baseline_matrices/${matrixId}`, body);
    return response.data;
  } catch (error) {
    console.error('Ошибка при добавлении записи в базовую матрицу:', error);
    throw error;
  }
}

export async function updateDiscountMatrix(matrixId, body) {
  try {
    const response = await axios.patch(`/discount_matrices/${matrixId}`, body);
    return response.data;
  } catch (error) {
    console.error('Ошибка при обновлении цены в скидочной матрице:', error);
    throw error;
  }
}

export async function updateBaselineMatrix(matrixId, body) {
  try {
    const response = await axios.patch(`/baseline_matrices/${matrixId}`, body);
    return response.data;
  } catch (error) {
    console.error('Ошибка при обновлении цены в базовой матрице:', error);
    throw error;
  }
}

export async function deleteDiscountMatrix(matrixId, body) {
  try {
    const response = await axios.delete(`/discount_matrices/${matrixId}`, { data: body });
    return response.data;
  } catch (error) {
    console.error('Ошибка при удалении записи из скидочной матрицы:', error);
    throw error;
  }
}
