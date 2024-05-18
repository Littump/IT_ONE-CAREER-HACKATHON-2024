export enum EXPENSE_EMOJIS {
  food = "🥪",
  house = "🏡",
  personal = "☕",
  transport = "🚌",
  healthcare = "💊",
  education = "🎓",
  entertainment = "🎠",
}
export type ExpenseType = keyof typeof EXPENSE_EMOJIS;

export type PeriodKindType = "day" | "week" | "month" | "year";

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
