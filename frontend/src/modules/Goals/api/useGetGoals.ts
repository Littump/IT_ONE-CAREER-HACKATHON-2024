import goalService from "./goalService.ts";
import { useQuery } from "@tanstack/react-query";
import { CurrencyType } from "@/types/currency.ts";

export const useGetGoals = (currency: CurrencyType) =>
  useQuery({
    queryKey: ["goals"],
    queryFn: () => goalService.getGoals(currency),
  });
