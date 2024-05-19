import { Typography } from "@material-tailwind/react";
import useGetCurrency from "@/helpers/useGetCurrency.ts";
import { NavLink } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { EXPENSE_EMOJIS } from "@/modules/Operations/types/expense.ts";
import { INCOME_EMOJIS } from "@/modules/Operations/types/income.ts";
import { OperationProps } from "@/modules/Operations/types/operation.ts";

const OperationItemMin = ({
  kind_operation,
  kind,
  id,
  description,
  value,
  period_kind,
  period_value,
}: OperationProps) => {
  const { t } = useTranslation();
  const currency = useGetCurrency();
  return (
    <NavLink
      to={`/operations/${id}`}
      className="flex transition hover:bg-gray-200 py-4 px-2 items-center justify-between border-b border-gray-300"
    >
      <div className="w-9/12 flex gap-4 justify-start items-center">
        <span className="text-4xl w-14 h-14 border-2 border-gray-200 rounded-full flex justify-center items-center">
          {kind_operation === "expense"
            ? EXPENSE_EMOJIS[kind]
            : INCOME_EMOJIS[kind]}
        </span>
        <div>
          <Typography variant="h6" className="font-semibold">
            {t(kind)}
          </Typography>

          {period_kind && period_value ? (
            <Typography variant="paragraph" className="text-blue-gray-700">
              раз в {period_value} {period_kind}
            </Typography>
          ) : (
            <Typography variant="paragraph" className="text-blue-gray-700">
              {description}
            </Typography>
          )}
        </div>
      </div>
      <Typography
        variant="h6"
        className="font-semibold ml-auto text-end w-3/12"
      >
        {(kind_operation === "income" ? "+" : "-") + value + " " + currency}
      </Typography>
    </NavLink>
  );
};

export default OperationItemMin;
