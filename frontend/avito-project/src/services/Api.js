import axios from 'axios';

export async function fetchUserSegments(userId) {
  const response = await instance.get(`/segments/${userId}`);
  return response.data;
}

export async function fetchBaselineMatrices() {
  const response = await instance.get('/baseline_matrices');
  return response.data;
}

export async function fetchDiscountMatrices() {
  const response = await instance.get('/discount_matrices');
  return response.data;
}

export async function fetchDiscountMatrixForSegment(segmentId) {
  const response = await instance.get(`/discount_matrices?segment=${segmentId}`);
  return response.data;
}

export async function fetchPrice(baselineMatrixId, locationId, microcategoryId) {
  const response = await instance.get(`/price/${baselineMatrixId}?location=${locationId}&category=${microcategoryId}`);
  return response.data;
}

export async function fetchMicrocategories() {
  const response = await instance.get('/microcategories');
  return response.data;
}

export async function fetchLocations() {
  const response = await instance.get('/locations');
  return response.data;
}

export async function fetchChildMicrocategories(microcategoryId) {
  const response = await instance.get(`/microcategories/${microcategoryId}`);
  return response.data;
}

export async function fetchChildLocations(locationId) {
  const response = await instance.get(`/locations/${locationId}`);
  return response.data;
}

export async function addDiscountMatrix(matrixId, body) {
  const response = await instance.post(`/discount_matrices/${matrixId}`, body);
  return response.data;
}

export async function createDiscountMatrix(body) {
  const response = await instance.post('/discount_matrices', body);
  return response.data;
}

export async function addBaselineMatrix(matrixId, body) {
  const response = await instance.post(`/baseline_matrices/${matrixId}`, body);
  return response.data;
}

export async function updateDiscountMatrix(matrixId, body) {
  const response = await instance.patch(`/discount_matrices/${matrixId}`, body);
  return response.data;
}

export async function updateBaselineMatrix(matrixId, body) {
  const response = await instance.patch(`/baseline_matrices/${matrixId}`, body);
  return response.data;
}

export async function deleteDiscountMatrix(matrixId, body) {
  const response = await instance.delete(`/discount_matrices/${matrixId}`, { data: body });
  return response.data;
}
