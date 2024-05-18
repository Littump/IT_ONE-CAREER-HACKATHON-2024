function useGetCurrency() {
  // const data = queryClient.getQueryData(["currency"]);
  /*  if (data.current_currency == "rubles") return "Р";
  else if (data.current_currency == "yuane") return "u";
  else if (data.current_currency == "dollars") return "$";*/
  return "₽";
}

export default useGetCurrency;
