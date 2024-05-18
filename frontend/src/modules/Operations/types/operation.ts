import {
  ExpenseType,
  PeriodKindType,
} from "@/modules/Operations/types/expense.ts";
import { IncomeType } from "@/modules/Operations/types/income.ts";

export type KindOperation = "expense" | "income";

export type IOperation =
  | {
      kind_operation: "expense";
      description: string;
      id: number;
      kind: ExpenseType;
      value: number;
      date: string;
    }
  | {
      kind_operation: "income";
      description: string;
      id: number;
      kind: IncomeType;
      value: number;
      date: string;
    };

export type IOperationPeriodic =
  | {
      kind_operation: "expense";
      description: string;
      id: number;
      kind: ExpenseType;
      value: number;
      start_day: string;
      period_kind: PeriodKindType;
      period_value: number;
    }
  | {
      kind_operation: "income";
      description: string;
      id: number;
      kind: IncomeType;
      value: number;
      start_day: string;
      period_kind: PeriodKindType;
      period_value: number;
    };

export interface OperationProps {
  kind_operation: KindOperation;
  description: string;
  id: number;
  kind: ExpenseType | IncomeType;
  value: number;
  date: string;
  period_kind?: PeriodKindType;
  period_value?: number;
}
