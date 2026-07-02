import genericHttpClient from "../../lib/axious/genericHttpClient";
import type { SignInRequest, SignInResponse } from "../../type/auth/auth";
import { saveToken } from "../../utils/token";

async function signin(
  email: string,
  password: string,
): Promise<SignInResponse> {
  const signInRequest: SignInRequest = {
    email,
    password,
  };

  const res = genericHttpClient.post<SignInResponse, SignInRequest>(
    "auth/signin",
    signInRequest,
  );
  console.log((await res).data);
  // set token
  await saveToken((await res).data.accessToken);
  // redirect
  console.log((await res).data);
  return (await res).data;
}
export const authFeatures = {
  signin,
};
