import { BrowserRouter, Route, Routes } from "react-router-dom";
import { SignIn } from "../pages/auth/SignIn";
import { Home } from "../pages/Home";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />

        <Route>
          <Route path="/login" element={<SignIn />} />
          {/* <Route path="/forgot-password" element={<ForgotPasswordPage />} /> */}
        </Route>

        {/* 404 */}
        <Route path="*" element={<h1>404 - Page Not Found</h1>} />
      </Routes>
    </BrowserRouter>
  );
}
