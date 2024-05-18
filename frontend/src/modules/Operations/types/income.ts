import { PeriodKindType } from "@/modules/Operations/types/expense.ts";

export enum INCOME_EMOJIS {
  salary = "🏢",
  partTime = "💼",
  business = "👨‍💼",
  passive = "🏝️",
  gifts = "🎁",
  investment = "💹",
}
export type IncomeType = keyof typeof INCOME_EMOJIS;

export interface IIncome {
  description: string;
  id: number;
  kind: IncomeType;
  value: number;
  date: string;
}

export interface IIncomePeriodic {
  description: string;
  id: number;
  kind: IncomeType;
  value: number;
  start_day: string;
  period_kind: PeriodKindType;
  period_value: number;
}
