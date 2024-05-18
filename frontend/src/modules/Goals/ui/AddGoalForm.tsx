import { useNavigate, useParams } from "react-router-dom";
import BackLink from "@/ui/BackLink.tsx";
import { Button, Input, Typography } from "@material-tailwind/react";
import Emoji from "@/ui/Emoji.tsx";
import { GOAL_EMOJIS, GoalType } from "@/modules/Goals/types/goal.ts";
import { Form, Formik } from "formik";
import { useTranslation } from "react-i18next";
import DateInput from "@/ui/DateInput.tsx";

const AddGoalForm = () => {
  const { type } = useParams<{ type: GoalType }>();

  const navigate = useNavigate();
  const { t } = useTranslation();

  const handleSubmit = () => {
    navigate("/");
  };

  const initialValues = {
    value: 0,
    description: "",
    date: {
      startDate: null,
      endDate: null,
    },
  };
  return (
    <div className="flex flex-col gap-6 md:max-w-xl mx-auto">
      <div className="grid grid-cols-3 w-full text-center">
        <BackLink to="/addGoal" />
        <Typography variant="h6">Создать цель</Typography>
      </div>
      <div className="flex items-center gap-4">
        <Emoji size="lg">{type && GOAL_EMOJIS[type]}</Emoji>
        <Typography variant="h4">{type && t(type)}</Typography>
      </div>
      <Formik onSubmit={handleSubmit} initialValues={initialValues}>
        {({ values, setFieldValue }) => {
          return (
            <Form className="flex gap-6 flex-col">
              <Input label="Сумма" />
              <Input label="Описание" />
              <DateInput
                placeholder="Дата окончания"
                value={values.date}
                setFieldValue={setFieldValue}
                name="date"
              />
              <Button type="submit">Добавить</Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default AddGoalForm;
