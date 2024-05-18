import { GoalType } from "@/modules/Goals/types/goal.ts";
import { Typography } from "@material-tailwind/react";
import ChooseGoalItem from "@/modules/Goals/ui/ChooseGoalItem.tsx";
import BackLink from "@/ui/BackLink.tsx";

const AddGoalChoose = () => {
  const types: GoalType[] = [
    "travel",
    "egg",
    "gift",
    "emergency",
    "cushion",
    "goal",
  ];
  return (
    <div className="flex flex-col gap-6">
      <BackLink to="/" />
      <Typography variant="h4" className="font-semibold">
        С какой целью вы хотите накопить?
      </Typography>
      <div>
        {types.map((el) => (
          <ChooseGoalItem type={el} key={el} />
        ))}
      </div>
    </div>
  );
};

export default AddGoalChoose;
