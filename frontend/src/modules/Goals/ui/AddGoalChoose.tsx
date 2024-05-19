import { GoalType } from "@/modules/Goals/types/goal.ts";
import { Typography } from "@material-tailwind/react";
import ChooseGoalItem from "@/modules/Goals/ui/ChooseGoalItem.tsx";
import BackLink from "@/ui/BackLink.tsx";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";

const AddGoalChoose = () => {
  const types: GoalType[] = [
    "TRAVEL",
    "EGG",
    "GIFT",
    "EMERGENCY",
    "CUSHION",
    "GOAL",
  ];

  const { t } = useTypedTranslation();
  return (
    <div className="flex flex-col gap-6">
      <BackLink to="/" />
      <Typography variant="h4" className="font-semibold">
        {t("what-purpose-you-to-save")}
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
