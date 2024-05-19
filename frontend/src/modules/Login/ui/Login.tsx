import { Button, Input, Typography } from "@material-tailwind/react";
import BackLink from "@/ui/BackLink.tsx";
import { useNavigate } from "react-router-dom";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import { useLogin } from "@/modules/Login/api/useLogin.ts";
import { Form, Formik } from "formik";
import { useEffect } from "react";
import YupPassword from "yup-password";
import * as yup from "yup";
YupPassword(yup);
const validationSchema = yup.object().shape({
  username: yup
    .string()
    .email("incorrect email")
    .required("required field")
    .min(3, "invalid value"),
  password: yup
    .string()
    .typeError("Должно быть строкой")
    .required("Обязательное поле")
    .min(2, "Слишком простой"),
});

const Login = () => {
  const navigate = useNavigate();
  const { mutate, isPending, isSuccess, data } = useLogin();

  const { t } = useTypedTranslation();
  const initialValues = {
    username: "",
    password: "",
  };
  console.log(data);
  useEffect(() => {
    if (isSuccess && !isPending) {
      localStorage.setItem("token", data.data);
      navigate("/");
    }
  }, [isSuccess, isPending]);

  return (
    <div className="flex flex-col md:text-center md:mt-20  w-full min-h-[100vh] gap-6 px-6 py-6 max-w-xl mx-auto">
      <BackLink to="/intro"></BackLink>
      <Typography variant="h3" className="font-semibold">
        {t("happy-to-see-you-again")}
      </Typography>
      <Typography variant="paragraph" className="text-blue-gray-700">
        {t("fill-info-below-and-enjoy")}
      </Typography>
      <Formik
        initialValues={initialValues}
        onSubmit={({ username, password }) => mutate({ username, password })}
        validationSchema={validationSchema}
      >
        {({ values, handleChange, touched, errors }) => {
          return (
            <Form className="mt-6 flex flex-col gap-6">
              <Input
                name="username"
                onChange={handleChange}
                value={values.username}
                error={!!(touched.username && errors.username)}
                success={!!(touched.username && !errors.username)}
                label={t("email")}
              />
              <Input
                type="password"
                name="password"
                onChange={handleChange}
                value={values.password}
                error={!!(touched.password && errors.password)}
                success={!!(touched.password && !errors.password)}
                label={t("password")}
              />
              <Button
                loading={isPending}
                size="lg"
                type="submit"
                className="mt-auto md:mt-0 text-center flex justify-center"
              >
                {t("login")}
              </Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default Login;
