import { useMutation } from "@tanstack/react-query";
import { queryClient } from "@/main.tsx";
import { AddDto } from "@/modules/Goals/types/add.dto.ts";
import goalService from "@/modules/Goals/api/goalService.ts";
import { CurrencyType } from "@/types/currency.ts";

export const useAddGoal = (currency: CurrencyType) =>
  useMutation({
    mutationFn: (body: AddDto) => goalService.addGoal(body, currency),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["goals"] });
    },
  });
