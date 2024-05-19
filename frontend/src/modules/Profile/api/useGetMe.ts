import profileService from "./profileService.ts";
import { useQuery } from "@tanstack/react-query";
import { CurrencyType } from "@/types/currency.ts";

export const useGetMe = (currency: CurrencyType) =>
  useQuery({
    queryKey: ["me"],
    queryFn: () => profileService.getMe(currency),
  });
