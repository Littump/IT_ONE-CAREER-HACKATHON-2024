import { useNavigate, useParams } from "react-router-dom";
import BackLink from "@/ui/BackLink.tsx";
import { Button, Input, Typography } from "@material-tailwind/react";
import { Field, Form, Formik } from "formik";
import DateInput from "@/ui/DateInput.tsx";
import { OperationProps } from "@/modules/Operations/types/operation.ts";
import Dropdown from "@/ui/Dropdown.tsx";

const EditOperationForm = () => {
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  const handleSubmit = () => {
    navigate("/operations/" + id);
  };

  const operation: OperationProps = {
    kind_operation: "income",
    kind: "salary",
    description: "Зарплата банкира",
    value: 200000,
    date: "22-01-2023",
    period_value: 6,
    period_kind: "week",
    id: 4,
  };
  const { description, value, date, period_value, period_kind } = operation;

  const isPeriodic = !!period_kind;

  const split_date = date.split("-");

  const start_date = new Date(
    +split_date[2],
    +split_date[1] - 1,
    +split_date[0],
  );

  const period_kind_list = ["day", "week", "month", "year"];

  const initialValues = {
    value: value,
    description: description,
    date: {
      startDate: start_date,
      endDate: start_date,
    },
    kind: period_kind ? period_kind : "day",
    period_value: period_value ? period_value : 0,
  };
  return (
    <div className="flex flex-col gap-6">
      <div className="flex w-full text-center">
        <BackLink to={"/operations/" + id} />
        <Typography variant="h6" className="w-2/3 mx-auto">
          Редактировать операцию
        </Typography>
      </div>
      <Formik onSubmit={handleSubmit} initialValues={initialValues}>
        {({ values, setFieldValue }) => {
          return (
            <Form className="flex gap-6 flex-col">
              <Input label="Сумма" value={values.value} />
              <Input label="Описание" value={values.description} />
              <DateInput
                placeholder="Дата окончания"
                value={values.date}
                setFieldValue={setFieldValue}
                name="date"
              />
              {isPeriodic && (
                <div className="flex gap-4 w-full items-center">
                  <Typography
                    variant="paragraph"
                    className="w-1/2 font-semibold text-blue-gray-700"
                  >
                    Повторять каждые
                  </Typography>
                  <Field
                    min={1}
                    name="period_value"
                    type="number"
                    className="text-blue-gray-700 w-20 border outline-0 focus:border-black border-blue-gray-200 rounded-lg py-2 px-4"
                  />
                  <Dropdown
                    className="w-28"
                    items={period_kind_list}
                    onClick={(el) => setFieldValue("kind", el)}
                  >
                    {values.kind ? values.kind : "частота"}
                  </Dropdown>
                </div>
              )}
              <Button type="submit">Редактировать</Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default EditOperationForm;
