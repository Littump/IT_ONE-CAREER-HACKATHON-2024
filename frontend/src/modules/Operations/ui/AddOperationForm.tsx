import { useNavigate, useParams } from "react-router-dom";
import BackLink from "@/ui/BackLink.tsx";
import { Button, Input, Typography } from "@material-tailwind/react";
import Emoji from "@/ui/Emoji.tsx";
import { Field, Form, Formik } from "formik";
import DateInput from "@/ui/DateInput.tsx";
import {
  EXPENSE_EMOJIS,
  ExpenseType,
  PeriodKindType,
} from "@/modules/Operations/types/expense.ts";
import {
  INCOME_EMOJIS,
  IncomeType,
} from "@/modules/Operations/types/income.ts";
import Dropdown from "@/ui/Dropdown.tsx";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import * as yup from "yup";
import { useEffect } from "react";
import { useAddExpense } from "@/modules/Operations/api/useAddExpense.ts";
import useGetCurrency from "@/helpers/useGetCurrency.ts";
import { useAddIncome } from "@/modules/Operations/api/useAddIncome.ts";
import { useAddExpensePeriodic } from "@/modules/Operations/api/useAddExpensePeriodic.ts";
import { useAddIncomePeriodic } from "@/modules/Operations/api/useAddIncomePeriodic.ts";

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

const AddOperationForm = () => {
  const currency = useGetCurrency();
  const { periodic, type, kind } = useParams<{
    type: ExpenseType | IncomeType;
    kind: "expenses" | "incomes";
    periodic: "periodic" | "oneTime";
  }>();
  const isPeriodic = periodic === "periodic";
  const navigate = useNavigate();
  const { t } = useTypedTranslation();
  const addExpense = useAddExpense(currency);
  const addIncome = useAddIncome(currency);
  const addExpensePeriodic = useAddExpensePeriodic(currency);
  const addIncomePeriodic = useAddIncomePeriodic(currency);
  const loadingAll =
    addExpense.isPending ||
    addExpensePeriodic.isPending ||
    addIncome.isPending ||
    addIncomePeriodic.isPending;
  useEffect(() => {
    if (
      !loadingAll &&
      (addExpense.isSuccess ||
        addIncome.isSuccess ||
        addIncomePeriodic.isSuccess ||
        addExpensePeriodic.isSuccess)
    )
      navigate("/");
  }, [addExpense, addIncome, addExpensePeriodic, addIncomePeriodic]);

  const period_kind_list: PeriodKindType[] = ["DAY", "WEEK", "MONTH", "YEAR"];

  const initialValues = {
    value: 0,
    description: "",
    date: {
      startDate: null,
      endDate: null,
    },
    kind: "DAY",
    period_value: 1,
  };
  if (!type || !kind || !periodic) return;
  return (
    <div className="flex flex-col gap-6 md:max-w-xl mx-auto ">
      <div className="flex  w-full text-center">
        <BackLink to={"/addOperation/" + periodic} />
        <Typography variant="h6" className="w-2/3 mx-auto">
          {t("add")} {kind === "expenses" ? t("expense") : t("income")}
        </Typography>
      </div>
      <div className="flex items-center gap-4">
        <Emoji size="lg">
          {type &&
            // @ts-ignore
            (kind === "expenses" ? EXPENSE_EMOJIS[type] : INCOME_EMOJIS[type])}
        </Emoji>
        <Typography variant="h4">{type && t(type)}</Typography>
      </div>
      <Formik
        onSubmit={(values) => {
          const main = {
            kind: type,
            date:
              values.date.startDate != null
                ? // @ts-ignore
                  values.date.startDate.toString()
                : "01-01-2020",
            value: values.value,
            description: values.description,
          };
          const period_kind = values.kind as PeriodKindType;

          const mainPeriodic = {
            kind: type,
            start_day:
              values.date.startDate != null
                ? // @ts-ignore
                  values.date.startDate.toString()
                : "01-01-2020",
            value: values.value,
            description: values.description,
            period_value: values.period_value,
            period_kind,
          };
          if (kind == "expenses" && periodic == "oneTime") {
            addExpense.mutate(main);
          } else if (kind == "incomes" && periodic == "oneTime") {
            addIncome.mutate(main);
          } else if (kind == "expenses" && periodic == "periodic") {
            addExpensePeriodic.mutate(mainPeriodic);
          } else if (kind == "incomes" && periodic == "periodic") {
            addIncomePeriodic.mutate(mainPeriodic);
          }
          console.log(kind, periodic);
        }}
        validationSchema={validationsSchema}
        initialValues={initialValues}
      >
        {({ values, handleChange, setFieldValue, touched, errors }) => {
          return (
            <Form className="flex gap-6 flex-col">
              <DateInput
                success={!!(touched.date && !errors.date)}
                error={!!(touched.date && errors.date)}
                placeholder={t("date-of-debiting/replenishment")}
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
                success={!!(touched.description && !errors.description)}
                error={!!(touched.description && errors.description)}
                label={t("description")}
              />
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
              <Button
                type="submit"
                className="flex justify-center"
                loading={loadingAll}
              >
                {t("add")}
              </Button>
            </Form>
          );
        }}
      </Formik>
    </div>
  );
};

export default AddOperationForm;
