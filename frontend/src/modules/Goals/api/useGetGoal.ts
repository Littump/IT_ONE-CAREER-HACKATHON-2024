import goalService from "./goalService.ts";
import { useQuery } from "@tanstack/react-query";
import { CurrencyType } from "@/types/currency.ts";

export const useGetGoal = (currency: CurrencyType, id: number) =>
  useQuery({
    queryKey: ["goal" + id],
    queryFn: () => goalService.getGoal(id, currency),
  });
