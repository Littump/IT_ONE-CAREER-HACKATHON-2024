import { useNavigate, useParams } from "react-router-dom";
import BackLink from "@/ui/BackLink.tsx";
import { Button, Input, Typography } from "@material-tailwind/react";
import Emoji from "@/ui/Emoji.tsx";
import { GOAL_EMOJIS, GoalType } from "@/modules/Goals/types/goal.ts";
import { Form, Formik } from "formik";
import DateInput from "@/ui/DateInput.tsx";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import * as yup from "yup";

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

const AddGoalForm = () => {
  const { type } = useParams<{ type: GoalType }>();

  const navigate = useNavigate();
  const { t } = useTypedTranslation();

  const handleSubmit = () => {
    navigate("/");
  };

  const initialValues = {
    value: 0,
    description: "",
    date: {
      startDate: new Date(),
      endDate: new Date(),
    },
  };
  return (
    <div className="flex flex-col gap-6 md:max-w-xl mx-auto">
      <div className="grid grid-cols-3 w-full text-center">
        <BackLink to="/addGoal" />
        <Typography variant="h6">{t("create-goal")}</Typography>
      </div>
      <div className="flex items-center gap-4">
        <Emoji size="lg">{type && GOAL_EMOJIS[type]}</Emoji>
        <Typography variant="h4">{type && t(type)}</Typography>
      </div>
      <Formik
        validationSchema={validationsSchema}
        onSubmit={handleSubmit}
        initialValues={initialValues}
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
              <Button type="submit">{t("add")}</Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default AddGoalForm;
