import { GoalType } from "@/modules/Goals/types/goal.ts";

export interface PatchDto {
  goal_value: number;
  deadline: string;
  description: string;
  kind: GoalType;
}
