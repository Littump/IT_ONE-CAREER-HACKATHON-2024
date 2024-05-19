import { useQuery } from "@tanstack/react-query";
import { CurrencyType } from "@/types/currency.ts";
import operationsService from "./operationsService.ts";

export const useGetOperations = (currency: CurrencyType) =>
  useQuery({
    queryKey: ["operations"],
    queryFn: () => operationsService.getOperations(currency),
  });
