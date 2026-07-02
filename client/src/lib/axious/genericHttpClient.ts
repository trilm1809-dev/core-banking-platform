import type { AxiosRequestConfig } from "axios";
import api from "./axios";
import type { ApiResponse } from "../../type/genericRepose";

class genericHttpClient {
  private unwrapResponse<ResponseType>(
    responseToProcess: ApiResponse<ResponseType>,
  ): ApiResponse<ResponseType> {
    if (!responseToProcess.isSuccess) {
      throw new Error();
    }

    return responseToProcess;
  }

  async get<ResponseType>(
    url: string,
    config?: AxiosRequestConfig,
  ): Promise<ApiResponse<ResponseType>> {
    const response = await api.get<ApiResponse<ResponseType>>(url, config);
    return this.unwrapResponse(response.data);
  }

  async post<ResponseType, RequestType>(
    url: string,
    body?: RequestType,
    config?: AxiosRequestConfig,
  ): Promise<ApiResponse<ResponseType>> {
    const response = await api.post<ApiResponse<ResponseType>>(
      url,
      body,
      config,
    );
    return this.unwrapResponse(response.data);
  }

  async put<ResponseType, RequestType>(
    url: string,
    body?: RequestType,
    config?: AxiosRequestConfig,
  ): Promise<ApiResponse<ResponseType>> {
    const response = await api.put<ApiResponse<ResponseType>>(
      url,
      body,
      config,
    );
    return this.unwrapResponse(response.data);
  }
  async patch<ResponseType, RequestType>(
    url: string,
    body?: RequestType,
    config?: AxiosRequestConfig,
  ): Promise<ApiResponse<ResponseType>> {
    const response = await api.patch<ApiResponse<ResponseType>>(
      url,
      body,
      config,
    );
    return this.unwrapResponse(response.data);
  }
  async delete<ResponseType, RequestType>(
    url: string,
    body?: RequestType,
    config?: AxiosRequestConfig,
  ): Promise<ApiResponse<ResponseType>> {
    const response = await api.delete<ApiResponse<ResponseType>>(url, {
      ...config,
      data: body,
    });
    return this.unwrapResponse(response.data);
  }
}

export default new genericHttpClient();
