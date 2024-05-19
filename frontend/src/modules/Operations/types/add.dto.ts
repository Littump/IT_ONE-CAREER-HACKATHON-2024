import {
  ExpenseType,
  PeriodKindType,
} from "@/modules/Operations/types/expense.ts";
import { IncomeType } from "@/modules/Operations/types/income.ts";

export interface AddDto {
  value: number;
  kind: ExpenseType | IncomeType;
  description: string;
  date: string;
}
export interface AddPeriodicDto {
  value: number;
  kind: ExpenseType | IncomeType;
  description: string;
  start_day: string;
  period_value: number;
  period_kind: PeriodKindType;
}
