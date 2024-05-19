import { CurrencyType } from "@/types/currency.ts";

function useGetCurrency(): CurrencyType {
  const currency = localStorage.getItem("currency") as CurrencyType;
  return currency ? currency : "rub";
}

export default useGetCurrency;
