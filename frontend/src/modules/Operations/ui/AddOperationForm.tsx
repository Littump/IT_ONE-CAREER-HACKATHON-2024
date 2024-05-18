import { useNavigate, useParams } from "react-router-dom";
import BackLink from "@/ui/BackLink.tsx";
import { Button, Input, Typography } from "@material-tailwind/react";
import Emoji from "@/ui/Emoji.tsx";
import { Field, Form, Formik } from "formik";
import DateInput from "@/ui/DateInput.tsx";
import {
  EXPENSE_EMOJIS,
  ExpenseType,
} from "@/modules/Operations/types/expense.ts";
import {
  INCOME_EMOJIS,
  IncomeType,
} from "@/modules/Operations/types/income.ts";
import Dropdown from "@/ui/Dropdown.tsx";
import {useTypedTranslation} from "@/helpers/useTypedTranslation.ts";

const AddOperationForm = () => {
  const { periodic, type, kind } = useParams<{
    type: ExpenseType | IncomeType;
    kind: "expenses" | "incomes";
    periodic: "periodic" | "oneTime";
  }>();
  const isPeriodic = periodic === "periodic";
  const navigate = useNavigate();
  const { t } = useTypedTranslation();

  const handleSubmit = () => {
    navigate("/");
  };

  const period_kind_list = ["day", "week", "month", "year"];

  const initialValues = {
    value: 0,
    description: "",
    date: {
      startDate: null,
      endDate: null,
    },
    kind: "day",
    period_value: 1,
  };
  return (
    <div className="flex flex-col gap-6 md:max-w-xl mx-auto ">
      <div className="flex  w-full text-center">
        <BackLink to={"/addOperation/" + kind} />
        <Typography variant="h6" className="w-2/3 mx-auto">
          {t("add")} {kind === "expenses" ? t("expense") : t("income")}
        </Typography>
      </div>
      <div className="flex items-center gap-4">
        <Emoji size="lg">
          {type &&
            (kind === "expenses" ? EXPENSE_EMOJIS[type] : INCOME_EMOJIS[type])}
        </Emoji>
        <Typography variant="h4">{type && t(type)}</Typography>
      </div>
      <Formik onSubmit={handleSubmit} initialValues={initialValues}>
        {({ values, setFieldValue }) => {
          return (
            <Form className="flex gap-6 flex-col">
              <DateInput
                  placeholder={t("date-of-debiting/replenishment")}
                  value={values.date}
                  setFieldValue={setFieldValue}
                  name="date"
              />
              <Input label={t("goal-sum")} />
              <Input label={t("description")} />
              {isPeriodic && (
                <div className="flex gap-2 w-full items-center">
                  <Typography
                    variant="paragraph"
                    className="w-1/2 text-sm font-semibold pl-4 text-blue-gray-700"
                  >
                    {t("repeat-every")}
                  </Typography>
                  <Field
                    name="period_value"
                    type="number"
                    min={1}
                    max={28}
                    className="text-blue-gray-700 w-14 md:w-28 border outline-0 focus:border-black border-blue-gray-200 rounded-lg py-2 px-4"
                  />
                  <Dropdown
                    className="w-28 md:w-40"
                    items={period_kind_list}
                    onClick={(el) => setFieldValue("kind", el)}
                  >
                    {values.kind ? values.kind : t("replenishment-frequency")}
                  </Dropdown>
                </div>
              )}
              <Button type="submit">{t("add")}</Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default AddOperationForm;
