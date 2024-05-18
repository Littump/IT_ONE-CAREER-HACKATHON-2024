import { Typography } from "@material-tailwind/react";
import { NavLink } from "react-router-dom";
import { useTranslation } from "react-i18next";
import Emoji from "@/ui/Emoji.tsx";
import {
  EXPENSE_EMOJIS,
  ExpenseType,
} from "@/modules/Operations/types/expense.ts";
import {
  INCOME_EMOJIS,
  IncomeType,
} from "@/modules/Operations/types/income.ts";

const ChooseOperationItem = ({
  type,
  kind,
  periodic,
}: {
  type: ExpenseType | IncomeType;
  periodic: "periodic" | "oneTime";
  kind: "expenses" | "incomes";
}) => {
  const { t } = useTranslation();
  return (
    <NavLink
      to={`/addOperation/${periodic}/${kind}/${type}`}
      className="flex transition hover:bg-gray-200 py-4 px-2 items-center justify-between border-b border-gray-300"
    >
      <div className="w-9/12 flex gap-4 justify-start items-center">
        <Emoji>
          {kind === "expenses" ? EXPENSE_EMOJIS[type] : INCOME_EMOJIS[type]}
        </Emoji>
        <div>
          <Typography variant="h5" className="font-semibold">
            {t(type)}
          </Typography>
        </div>
      </div>
      <span>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          strokeWidth={1.5}
          stroke="currentColor"
          className="w-6 h-6"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="m8.25 4.5 7.5 7.5-7.5 7.5"
          />
        </svg>
      </span>
    </NavLink>
  );
};

export default ChooseOperationItem;
