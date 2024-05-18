export enum EXPENSE_COLORS {
  products = "#DB7F67",
  transport = "#DBBEA1",
  jkh = "#A37B73",
  medicine = "#3F292B",
  other = "#D34F73",
}

export type ExpenseType = keyof typeof EXPENSE_COLORS;

export interface IExpense {
  type: ExpenseType;
  value: number;
}
