import useGetCurrency from "@/helpers/useGetCurrency.ts";
import { Button, Input, Spinner, Typography } from "@material-tailwind/react";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import Dropdown from "@/ui/Dropdown.tsx";
import * as yup from "yup";
import { CurrencyType } from "@/types/currency.ts";
import { Form, Formik } from "formik";
import { usePatchMe } from "@/modules/Profile/api/usePatchMe.ts";
import { useGetMe } from "@/modules/Profile/api/useGetMe.ts";
import { queryClient } from "@/main.tsx";

const validationSchema = yup.object().shape({
  name: yup.string().required("required field").min(3, "invalid value"),
  username: yup
    .string()
    .email("incorrect email")
    .required("required field")
    .min(3, "invalid value"),
});

const Profile = () => {
  const currency = useGetCurrency();

  const currency_list: CurrencyType[] = ["rub", "usd", "cny", "eur"];
  const { t } = useTypedTranslation();
  const { mutate, isPending } = usePatchMe();
  const { data } = useGetMe(currency);
  if (!data) return <Spinner></Spinner>;
  const initialValues: {
    name: string;
    username: string;
    currency: CurrencyType;
  } = {
    name: data.data.name,
    username: data.data.username,
    currency: currency,
  };
  return (
    <div className="flex flex-col gap-7">
      <Typography variant="h6">{t("your-profile")}</Typography>
      <Formik
        initialValues={initialValues}
        onSubmit={({ name, username, currency }) => {
          localStorage.setItem("currency", currency);
          queryClient.invalidateQueries({ queryKey: ["goals"] });
          queryClient.invalidateQueries({ queryKey: ["operations"] });
          mutate({ name, username });
        }}
        validationSchema={validationSchema}
      >
        {({ values, setFieldValue, handleChange, touched, errors }) => {
          return (
            <Form className="mt-6 flex flex-col gap-6">
              <Input
                name="name"
                onChange={handleChange}
                value={values.name}
                error={!!(touched.name && errors.name)}
                success={!!(touched.name && !errors.name)}
                label={`${t("first-name")} ${t("last-name")}`}
              />{" "}
              <Input
                name="username"
                onChange={handleChange}
                value={values.username}
                error={!!(touched.username && errors.username)}
                success={!!(touched.username && !errors.username)}
                label={t("email")}
              />
              <div className="flex flex-col gap-2">
                <Dropdown
                  onClick={(el) => setFieldValue("currency", el)}
                  items={currency_list}
                  className="w-full"
                >
                  {values.currency}
                </Dropdown>
                <Typography
                  variant="paragraph"
                  className="text-sm text-blue-gray-700"
                >
                  {t("preferred-currency")}
                </Typography>
              </div>
              <Button
                loading={isPending}
                size="lg"
                type="submit"
                className="mt-auto md:mt-0 text-center flex justify-center"
              >
                {t("edit")}
              </Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default Profile;
