import { Button, Input, Typography } from "@material-tailwind/react";
import BackLink from "@/ui/BackLink.tsx";
import { useNavigate } from "react-router-dom";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import * as yup from "yup";
import YupPassword from "yup-password";
import { Form, Formik } from "formik";
import { useRegistration } from "@/modules/Login/api/useRegistration.ts";
import { useEffect } from "react";
YupPassword(yup);
const validationSchema = yup.object().shape({
  firstName: yup.string().required("required field").min(3, "invalid value"),
  lastName: yup.string().required("required field").min(3, "invalid value"),
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

const Registration = () => {
  const navigate = useNavigate();
  const { t } = useTypedTranslation();
  const { mutate, isPending, isSuccess } = useRegistration();
  const initialValues = {
    firstName: "",
    lastName: "",
    username: "",
    password: "",
  };

  useEffect(() => {
    if (isSuccess && !isPending) navigate("/login");
  }, [isSuccess, isPending]);

  return (
    <div className="flex md:mt-10 md:text-center flex-col w-full min-h-[100vh] gap-6 px-6 py-6 max-w-xl mx-auto">
      <BackLink to="/intro"></BackLink>
      <Typography variant="h3" className="font-semibold">
        {t("tell-us-about-yourself")}
      </Typography>
      <Typography variant="paragraph" className="text-blue-gray-700">
        {t("fill-name-and-surname-below")}
      </Typography>
      <Formik
        initialValues={initialValues}
        onSubmit={({ firstName, lastName, username, password }) =>
          mutate({ name: firstName + " " + lastName, username, password })
        }
        validationSchema={validationSchema}
      >
        {({ values, handleChange, touched, errors }) => {
          return (
            <Form className="mt-6 flex flex-col gap-6">
              <Input
                name="firstName"
                onChange={handleChange}
                value={values.firstName}
                error={!!(touched.firstName && errors.firstName)}
                success={!!(touched.firstName && !errors.firstName)}
                label={t("first-name")}
              />{" "}
              <Input
                name="lastName"
                onChange={handleChange}
                value={values.lastName}
                error={!!(touched.lastName && errors.lastName)}
                success={!!(touched.lastName && !errors.lastName)}
                label={t("last-name")}
              />{" "}
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
                {t("next")}
              </Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default Registration;
