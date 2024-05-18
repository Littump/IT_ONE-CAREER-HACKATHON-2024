import {
  EXPENSE_COLORS,
  IExpense,
} from "@/modules/ExpensesGraph/types/expenses.ts";
import { css } from "@emotion/css";
import useGetCurrency from "@/helpers/useGetCurrency.ts";
interface Props {
  expenses: IExpense[];
}

const Details = ({ expenses }: Props) => {
  const colors = expenses.map((el) => EXPENSE_COLORS[el.type]);
  const labels = expenses.map((el) => el.type);
  const series = expenses.map((el) => el.value);
  const currency = useGetCurrency();
  return (
    <div className="flex flex-wrap gap-x-1 gap-y-2">
      {series.map((val, id) => {
        const lineClass = css({
          backgroundColor: colors[id],
        });
        return (
          <div
            key={labels[id]}
            className={`py-1 px-4 rounded-full text-sm text-white ${lineClass} `}
          >
            {labels[id]} - {val} {currency}
          </div>
        );
      })}
    </div>
  );
};

export default Details;
