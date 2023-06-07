export interface ApiError {
  name: String
  message: String
}

export interface ApiHttpResponse<T> {
  error: ApiError | ApiError[]
  data: T
}

export interface ValidationError {
  field: String
  result: Boolean
}

export function isApiError(error: ApiError | ApiError[]): error is ApiError {
  return 'name' in error && 'message' in error
}

export function isFieldValidationError(error: ApiError | ApiError[]): error is ApiError[] {
  return !isApiError(error) && error.length > 0 && isApiError(error[0])
}
