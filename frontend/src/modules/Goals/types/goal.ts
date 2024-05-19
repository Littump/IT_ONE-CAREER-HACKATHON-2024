export enum GOAL_EMOJIS {
  TRAVEL = "✈️",
  CUSHION = "🏝️",
  EMERGENCY = "🏠",
  GIFT = "🎁",
  EGG = "💰",
  GOAL = "🤔",
}
export type GoalType = keyof typeof GOAL_EMOJIS;

export interface IGoal {
  description: string;
  id: number;
  kind: GoalType;
  value: number;
  goalValue: number;
  deadline: string;
  achieved: boolean;
}

export interface Transfer {
  date: string;
  value: number;
  id: number;
}
