import { useQuery } from "@tanstack/react-query";
import { CurrencyType } from "@/types/currency.ts";
import balanceService from "./balanceService.ts";

export const useGetMe = (currency: CurrencyType) =>
  useQuery({
    queryKey: ["balance"],
    queryFn: () => balanceService.getMe(currency),
  });
