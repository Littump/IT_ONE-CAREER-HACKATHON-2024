import { GOAL_EMOJIS, IGoal } from "@/modules/Goals/types/goal.ts";
import { Typography } from "@material-tailwind/react";
import useGetCurrency from "@/helpers/useGetCurrency.ts";
import { NavLink } from "react-router-dom";
import { useTranslation } from "react-i18next";

const GoalItemMin = ({ type, description, value, id }: IGoal) => {
  const { t } = useTranslation();
  const currency = useGetCurrency();
  return (
    <NavLink
      to={`/goals/${id}`}
      className="flex transition hover:bg-gray-200 py-4 px-2 items-center justify-between border-b border-gray-300"
    >
      <div className="w-9/12 flex gap-4 justify-start items-center">
        <span className="text-4xl w-14 h-14 border-2 border-gray-200 rounded-full flex justify-center items-center">
          {GOAL_EMOJIS[type]}
        </span>
        <div>
          <Typography variant="h6" className="font-semibold">
            {t(type)}
          </Typography>
          <Typography variant="paragraph" className="text-blue-gray-700">
            {description}
          </Typography>
        </div>
      </div>
      <Typography
        variant="h6"
        className="font-semibold ml-auto text-end w-3/12"
      >
        {value} {currency}
      </Typography>
    </NavLink>
  );
};

export default GoalItemMin;
