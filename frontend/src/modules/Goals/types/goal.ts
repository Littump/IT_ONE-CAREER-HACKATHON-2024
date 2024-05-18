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
  type: GoalType;
  value: number;
  goal_value: number;
  date: string;
  is_achievement: boolean;
}

export interface Transfer {
  date: string;
  value: number;
  id: number;
}
