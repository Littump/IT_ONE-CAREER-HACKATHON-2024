import { Button, Typography } from "@material-tailwind/react";
import { NavLink } from "react-router-dom";
import { IGoal } from "@/modules/Goals/types/goal.ts";
import GoalItemMin from "@/modules/Goals/ui/GoalItemMin.tsx";
import AddGoalItem from "@/modules/Goals/ui/AddGoalItem.tsx";
import { useState } from "react";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";

interface Props {
  isFull?: boolean;
}

const Goals = ({ isFull = true }: Props) => {
  const [showAll, setShowAll] = useState(!isFull);

  const { t } = useTypedTranslation();
  const goals: IGoal[] = [];
  return (
    <div className="rounded-2xl py-6 px-4 border border-gray-300">
      {isFull ? (
        <>
          <div className="flex justify-between items-center">
            <Typography variant="h5" className="font-semibold">
              {t("goals")}
            </Typography>
            <NavLink
              to="/goals"
              className="bg-gray-100 hover:bg-gray-200 transition rounded-full p-2 "
            >
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
                  d="M17.25 8.25 21 12m0 0-3.75 3.75M21 12H3"
                />
              </svg>
            </NavLink>
          </div>
          <NavLink to="/addGoal">
            <Button className="rounded-full my-2" size="sm">
              {t("add")}
            </Button>
          </NavLink>
          <button
            onClick={() => setShowAll((prev) => !prev)}
            className="text-blue-gray-700 underline text-sm ml-4"
          >
            {showAll ? t("hide") : t("show-all")}
          </button>
        </>
      ) : (
        <Typography variant="h5">{t("your-goals")}</Typography>
      )}
      <div className="my-4 flex flex-col ">
        {goals.slice(0, showAll ? goals.length : 2).map((el) => (
          <GoalItemMin key={el.description} {...el} />
        ))}
      </div>
      <AddGoalItem />
    </div>
  );
};

export default Goals;
