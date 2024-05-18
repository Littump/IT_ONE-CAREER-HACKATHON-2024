import { Button, Input, Typography } from "@material-tailwind/react";
import BackLink from "@/ui/BackLink.tsx";
import { useNavigate } from "react-router-dom";
const Registration = () => {
  const navigate = useNavigate();
  const handleSubmit = () => {
    navigate("/login");
  };
  return (
    <div className="flex flex-col w-full min-h-[100vh] gap-6 px-6 py-6 max-w-xl mx-auto">
      <BackLink to="/intro"></BackLink>
      <Typography variant="h3" className="font-semibold">
        Расскажите нам о себе!
      </Typography>
      <Typography variant="paragraph" className="text-blue-gray-700">
        Пожалуйста, введите свои имя и фамилию ниже
      </Typography>
      <div className="mt-6 flex flex-col gap-6">
        <Input size="lg" label="Имя" />
        <Input size="lg" label="Фамилия" />
        <Input size="lg" label="Почта" />
        <Input size="lg" label="Пароль" type="password" />
      </div>
      <Button
        onClick={handleSubmit}
        size="lg"
        type="submit"
        className="mt-auto"
      >
        Дальше
      </Button>
    </div>
  );
};

export default Registration;
