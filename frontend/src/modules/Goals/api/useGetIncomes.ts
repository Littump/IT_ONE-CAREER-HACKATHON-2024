import goalService from "./goalService.ts";
import { useQuery } from "@tanstack/react-query";
import { CurrencyType } from "@/types/currency.ts";

export const useGetIncomes = (currency: CurrencyType, id: number) =>
  useQuery({
    queryKey: ["incomes" + id],
    queryFn: () => goalService.getIncomes(id, currency),
  });
