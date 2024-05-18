import { useNavigate, useParams } from "react-router-dom";
import BackLink from "@/ui/BackLink.tsx";
import { Button, Input, Typography } from "@material-tailwind/react";
import { Form, Formik } from "formik";
import DateInput from "@/ui/DateInput.tsx";

const EditGoalForm = () => {
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  const handleSubmit = () => {
    navigate("/goals/" + id);
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
    <div className="flex flex-col gap-6">
      <div className="flex w-full text-center">
        <BackLink to={"/goals/" + id} />
        <Typography variant="h6" className="w-1/2 mx-auto">
          Редактировать цель
        </Typography>
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
              <Button type="submit">Редактировать</Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default EditGoalForm;
