import { useNavigate, useParams } from "react-router-dom";
import BackLink from "@/ui/BackLink.tsx";
import { Button, Input, Spinner, Typography } from "@material-tailwind/react";
import { Form, Formik } from "formik";
import DateInput from "@/ui/DateInput.tsx";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import * as yup from "yup";
import { useGetGoal } from "@/modules/Goals/api/useGetGoal.ts";
import useGetCurrency from "@/helpers/useGetCurrency.ts";
import { usePatchGoal } from "@/modules/Goals/api/usePatchGoal.ts";
import { useEffect } from "react";

const validationsSchema = yup.object().shape({
  value: yup.string().required("required field").min(1, "invalid value"),
  description: yup
    .string()
    .required("required field")
    .min(1, "invalid description"),
  date: yup.object().shape({
    startDate: yup.date().required("required field"),
    endDate: yup.date().required("required field"),
  }),
});

const EditGoalForm = () => {
  const navigate = useNavigate();
  const currency = useGetCurrency();
  const { id } = useParams<{ id: string }>();
  const { data, isPending } = useGetGoal(currency, id ? +id : 1);
  const patchGoal = usePatchGoal(id ? +id : 1, currency);
  const { t } = useTypedTranslation();

  useEffect(() => {
    if (!patchGoal.isPending && patchGoal.isSuccess) navigate("goals/" + id);
  }, [patchGoal]);

  if (!data || isPending) return <Spinner />;

  const initialValues = {
    value: data.data.goalValue,
    description: data.data.description,
    date: {
      startDate: new Date(data.data.deadline),
      endDate: new Date(data.data.deadline),
    },
  };
  return (
    <div className="flex flex-col gap-6 mt-10 max-w-2xl mx-auto">
      <div className="flex w-full text-center">
        <BackLink to={"/goals/" + id} />
        <Typography variant="h6" className="w-1/2 mx-auto">
          {t("edit-goal")}
        </Typography>
      </div>
      <Formik
        onSubmit={({ value, description, date }) => {
          patchGoal.mutate({
            goal_value: value,
            description,
            deadline: date.startDate.toString(),
            kind: data.data.kind,
          });
        }}
        initialValues={initialValues}
        validationSchema={validationsSchema}
      >
        {({ values, setFieldValue, handleChange, touched, errors }) => {
          return (
            <Form className="flex gap-6 flex-col mb-10">
              <DateInput
                success={!!(touched.date && !errors.date)}
                error={!!(touched.date && errors.date)}
                placeholder={t("end-date")}
                value={values.date}
                setFieldValue={setFieldValue}
                name="date"
              />
              <Input
                name="value"
                type="number"
                min={0}
                onChange={handleChange}
                value={values.value}
                success={!!(touched.value && !errors.value)}
                error={!!(touched.value && errors.value)}
                label={t("goal-sum")}
              />
              <Input
                name="description"
                onChange={handleChange}
                value={values.description}
                error={!!(touched.description && errors.description)}
                success={!!(touched.description && !errors.description)}
                label={t("description")}
              />
              <Button type="submit" loading={patchGoal.isPending}>
                {t("add")}
              </Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default EditGoalForm;
