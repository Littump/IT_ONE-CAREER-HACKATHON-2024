import { useQuery } from "@tanstack/react-query";
import operationsService from "./operationsService.ts";

export const useGetExpense = (id: number) =>
  useQuery({
    queryKey: ["expense" + id],
    queryFn: () => {
      return operationsService.getExpense(id);
    },
  });
export const useGetIncome = (id: number) =>
  useQuery({
    queryKey: ["income" + id],
    queryFn: () => {
      return operationsService.getIncome(id);
    },
  });
export const useGetExpensePeriodic = (id: number) =>
  useQuery({
    queryKey: ["expense-period" + id],
    queryFn: () => {
      return operationsService.getExpensePeriodic(id);
    },
  });
export const useGetIncomePeriodic = (id: number) =>
  useQuery({
    queryKey: ["income-periodic" + id],
    queryFn: () => {
      return operationsService.getIncomePeriodic(id);
    },
  });
