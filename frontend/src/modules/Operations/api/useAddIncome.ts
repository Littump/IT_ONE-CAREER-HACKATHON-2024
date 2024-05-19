import { useMutation } from "@tanstack/react-query";
import { queryClient } from "@/main.tsx";
import { CurrencyType } from "@/types/currency.ts";
import operationsService from "@/modules/Operations/api/operationsService.ts";
import { AddDto } from "@/modules/Operations/types/add.dto.ts";

export const useAddIncome = (currency: CurrencyType) =>
  useMutation({
    mutationFn: (body: AddDto) => operationsService.addIncome(body, currency),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["operations"] });
      queryClient.invalidateQueries({ queryKey: ["balance"] });
    },
  });
