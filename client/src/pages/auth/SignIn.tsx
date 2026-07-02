import { useState } from "react";
import { Button, Stack, TextField, Typography } from "@mui/material";
import { authFeatures } from "../../features/auth/authFeatures";
import { useNavigate } from "react-router-dom";

export const SignIn = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleSignIn = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    await authFeatures.signin(username, password);
    navigate("/");
  };

  return (
    <>
      <form onSubmit={handleSignIn}>
        <Stack spacing={2} sx={{ width: 350, margin: "100px auto" }}>
          <Typography variant="h4">Digital Banking</Typography>

          <TextField
            label="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            fullWidth
          />

          <TextField
            label="Password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            fullWidth
          />

          <Button type="submit" variant="contained" fullWidth>
            Login
          </Button>
        </Stack>
      </form>
    </>
  );
};
