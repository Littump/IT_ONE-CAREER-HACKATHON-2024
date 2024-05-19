import { useQuery } from "@tanstack/react-query";
import { CurrencyType } from "@/types/currency.ts";
import operationsService from "./operationsService.ts";

export const useGetOperationsPeriodic = (currency: CurrencyType) =>
  useQuery({
    queryKey: ["operations_periodic"],
    queryFn: () => operationsService.getOperationsPeriodic(currency),
  });
