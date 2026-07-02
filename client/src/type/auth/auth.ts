export interface SignInRequest {
  email: string;
  password: string;
}

export interface User {
  id: string;
  username: string;
  fullName: string;
  role: "ADMIN" | "CUSTOMER" | "TELLER";
}

export interface SignInResponse {
  accessToken: string;
  user: User;
}
