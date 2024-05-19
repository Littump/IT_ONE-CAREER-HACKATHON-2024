export enum EXPENSE_EMOJIS {
  FOOD = "🥪",
  HOUSE = "🏡",
  PERSONAL = "☕",
  TRANSPORT = "🚌",
  HEALTH = "💊",
  EDUCATION = "🎓",
  ENTERTAINMENTS = "🎠",
}
export type ExpenseType = keyof typeof EXPENSE_EMOJIS;

export type PeriodKindType = "DAY" | "WEEK" | "MONTH" | "YEAR";

export interface IExpense {
  description: string;
  id: number;
  kind: ExpenseType;
  value: number;
  date: string;
}

export interface IExpensePeriodic {
  description: string;
  id: number;
  kind: ExpenseType;
  value: number;
  start_day: string;
  period_kind: PeriodKindType;
  period_value: number;
}
