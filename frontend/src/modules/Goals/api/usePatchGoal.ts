import { useMutation } from "@tanstack/react-query";
import { queryClient } from "@/main.tsx";
import { PatchDto } from "@/modules/Goals/types/patch.dto.ts";
import goalService from "@/modules/Goals/api/goalService.ts";
import { CurrencyType } from "@/types/currency.ts";

export const usePatchGoal = (id: number, currency: CurrencyType) =>
  useMutation({
    mutationFn: (body: PatchDto) => goalService.patchGoal(body, id, currency),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["goal" + id] });
    },
  });
