import { useMutation } from "@tanstack/react-query";
import { queryClient } from "@/main.tsx";
import goalService from "@/modules/Goals/api/goalService.ts";

export const useDeleteGoal = () =>
  useMutation({
    mutationFn: (id: number) => goalService.deleteGoal(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["incomes"] });
    },
  });
