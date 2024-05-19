import useGetCurrency from "@/helpers/useGetCurrency.ts";
import BackLink from "@/ui/BackLink.tsx";
import { Spinner, Typography } from "@material-tailwind/react";
import Emoji from "@/ui/Emoji.tsx";
import { useParams } from "react-router-dom";
import { INCOME_EMOJIS } from "@/modules/Operations/types/income.ts";
import {
  EXPENSE_EMOJIS,
  ExpenseType,
  PeriodKindType,
} from "@/modules/Operations/types/expense.ts";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import {
  useGetExpense,
  useGetExpensePeriodic,
  useGetIncome,
  useGetIncomePeriodic,
} from "@/modules/Operations/api/useGetOperation.ts";
import { KindOperation } from "@/modules/Operations/types/operation.ts";

interface res {
  description: string;
  id: number;
  kind: ExpenseType;
  value: number;
  startDay?: string;
  periodKind?: PeriodKindType;
  periodValue?: number;
  date?: "";
}

const OperationItemBig = () => {
  const currency = useGetCurrency();
  const { id, periodic, kind } = useParams<{
    kind: KindOperation;
    periodic: "periodic" | "oneTime";
    id: string;
  }>();
  const { t } = useTypedTranslation();
  let operation;

  if (kind == "expense" && periodic == "oneTime")
    operation = useGetExpense(id ? +id : 1);
  else if (kind == "income" && periodic == "oneTime")
    operation = useGetIncome(id ? +id : 1);
  else if (kind == "expense" && periodic == "periodic")
    operation = useGetExpensePeriodic(id ? +id : 1);
  else operation = useGetIncomePeriodic(id ? +id : 1);
  if (!operation.data || operation.isPending) return <Spinner />;
  const data = operation.data.data as res;
  const {
    kind: type,
    value,
    description,
    periodKind,
    periodValue,
    startDay,
    date: dateData,
  } = data;
  console.log(data);
  const date = dateData ? dateData : startDay ? startDay : "01-01-2024";
  const isPeriodic = periodic === "periodic";
  return (
    <div className="flex flex-col gap-8">
      <div className="grid grid-cols-3 w-full text-center">
        <BackLink to="/" />
        <Typography variant="h6">{t("operation")}</Typography>
      </div>
      <div className="flex items-center gap-4 ">
        <Emoji size="lg">
          {
            // @ts-ignore
            kind === "expense" ? EXPENSE_EMOJIS[type] : INCOME_EMOJIS[type]
          }
        </Emoji>
        <div>
          <Typography variant="h4" className="flex gap-4">
            {type && t(type)}{" "}
          </Typography>
          <Typography variant="paragraph">{description}</Typography>
        </div>
      </div>
      <div className="border border-gray-300 rounded-xl py-4 px-6 flex flex-col gap-4">
        <div className="flex justify-between border-b items-center border-gray-300 pb-4">
          <Typography variant="paragraph" className="text-gray-700">
            {t("goal-sum")}
          </Typography>{" "}
          <Typography variant="h6">
            {value} {currency}
          </Typography>
        </div>
        <div
          className={`flex justify-between items-center ${
            isPeriodic && "border-b border-gray-300 pb-4"
          }`}
        >
          <Typography variant="paragraph" className="text-gray-700">
            {isPeriodic ? t("start-date") : t("date-of-debiting/replenishment")}
          </Typography>{" "}
          <Typography variant="h6">{date.replace(/-/g, ".")}</Typography>
        </div>
        {isPeriodic && (
          <div className="flex justify-between items-center">
            <Typography variant="paragraph" className="text-gray-700">
              {kind === "expense"
                ? t("frequency-of-expense")
                : t("frequency-of-income")}
            </Typography>
            <Typography variant="h6">
              {t("repeat-every")}{" "}
              {periodValue + " " + (periodKind && t(periodKind))}
            </Typography>
          </div>
        )}
      </div>
    </div>
  );
};

export default OperationItemBig;
