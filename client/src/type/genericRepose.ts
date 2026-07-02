export interface ApiResponse<T> {
  isSuccess: boolean;
  errorMessage: string | null;
  data: T;
}
