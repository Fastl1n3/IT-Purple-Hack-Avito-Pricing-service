import axios from 'axios';

export async function fetchUserSegments(userId) {
  const response = await axios.get(`/segments/${userId}`);
  return response.data;
}

export async function fetchBaselineMatrices() {
  const response = await axios.get('/baseline_matrices');
  return response.data;
}

export async function fetchDiscountMatrices() {
  const response = await axios.get('/discount_matrices');
  return response.data;
}

export async function fetchDiscountMatrixForSegment(segmentId) {
  const response = await axios.get(`/discount_matrices?segment=${segmentId}`);
  return response.data;
}

export async function fetchPrice(baselineMatrixId, locationId, microcategoryId) {
  const response = await axios.get(`/price/${baselineMatrixId}?location=${locationId}&category=${microcategoryId}`);
  return response.data;
}

export async function fetchMicrocategories() {
  const response = await axios.get('/microcategories');
  return response.data;
}

export async function fetchLocations() {
  const response = await axios.get('/locations');
  return response.data;
}

export async function fetchChildMicrocategories(microcategoryId) {
  const response = await axios.get(`/microcategories/${microcategoryId}`);
  return response.data;
}

export async function fetchChildLocations(locationId) {
  const response = await axios.get(`/locations/${locationId}`);
  return response.data;
}

export async function addDiscountMatrix(matrixId, body) {
  const response = await axios.post(`/discount_matrices/${matrixId}`, body);
  return response.data;
}

export async function createDiscountMatrix(body) {
  const response = await axios.post('/discount_matrices', body);
  return response.data;
}

export async function addBaselineMatrix(matrixId, body) {
  const response = await axios.post(`/baseline_matrices/${matrixId}`, body);
  return response.data;
}

export async function updateDiscountMatrix(matrixId, body) {
  const response = await axios.patch(`/discount_matrices/${matrixId}`, body);
  return response.data;
}

export async function updateBaselineMatrix(matrixId, body) {
  const response = await axios.patch(`/baseline_matrices/${matrixId}`, body);
  return response.data;
}

export async function deleteDiscountMatrix(matrixId, body) {
  const response = await axios.delete(`/discount_matrices/${matrixId}`, { data: body });
  return response.data;
}
