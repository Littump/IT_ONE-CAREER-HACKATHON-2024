export enum GOAL_EMOJIS {
  travel = "✈️",
  cushion = "🏝️",
  emergency = "🏠",
  gift = "🎁",
  egg = "💰",
  goal = "🤔",
}
export type GoalType = keyof typeof GOAL_EMOJIS;

export interface IGoal {
  description: string;
  id: number;
  kind: GoalType;
  value: number;
  goal_value: number;
  deadline: string;
  achieved: boolean;
}

export interface Transfer {
  date: string;
  value: number;
  id: number;
}
