import { Button, Input, Typography } from "@material-tailwind/react";
import BackLink from "@/ui/BackLink.tsx";
import { useNavigate } from "react-router-dom";
const Login = () => {
  const navigate = useNavigate();
  const handleSubmit = () => {
    navigate("/");
  };
  return (
    <div className="flex flex-col w-full min-h-[100vh] gap-6 px-6 py-6 max-w-xl mx-auto">
      <BackLink to="/intro"></BackLink>
      <Typography variant="h3" className="font-semibold">
        Рады снова вас видеть!
      </Typography>
      <Typography variant="paragraph" className="text-blue-gray-700">
        Введите данные ниже и наслаждайтесь использованием приложения:)
      </Typography>
      <div className="mt-6 flex flex-col gap-6">
        <Input size="lg" label="Почта" />
        <Input size="lg" label="Пароль" type="password" />
      </div>
      <Button
        onClick={handleSubmit}
        size="lg"
        type="submit"
        className="mt-auto"
      >
        Войти
      </Button>
    </div>
  );
};

export default Login;
