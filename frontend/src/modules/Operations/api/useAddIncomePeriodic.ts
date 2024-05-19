import { useMutation } from "@tanstack/react-query";
import { queryClient } from "@/main.tsx";
import { CurrencyType } from "@/types/currency.ts";
import operationsService from "@/modules/Operations/api/operationsService.ts";
import { AddPeriodicDto } from "@/modules/Operations/types/add.dto.ts";

export const useAddIncomePeriodic = (currency: CurrencyType) =>
  useMutation({
    mutationFn: (body: AddPeriodicDto) =>
      operationsService.addIncomePeriodic(body, currency),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["operations_periodic"] });
    },
  });
