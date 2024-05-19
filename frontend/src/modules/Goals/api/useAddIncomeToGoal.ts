import { useMutation } from "@tanstack/react-query";
import { queryClient } from "@/main.tsx";
import goalService from "@/modules/Goals/api/goalService.ts";
import { CurrencyType } from "@/types/currency.ts";

export const useAddIncomeToGoal = (id: number, currency: CurrencyType) =>
  useMutation({
    mutationFn: (val: number) => goalService.addIncomeToGoal(val, id, currency),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["incomes" + id] });
      queryClient.invalidateQueries({ queryKey: ["goal" + id] });
    },
  });
