import { useMutation } from "@tanstack/react-query";
import { queryClient } from "@/main.tsx";
import goalService from "@/modules/Goals/api/goalService.ts";

export const useDeleteIncome = (id: number, goalId: number) =>
  useMutation({
    mutationFn: () => goalService.deleteIncomeToGoal(id, goalId),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["incomes" + id] });
    },
  });
