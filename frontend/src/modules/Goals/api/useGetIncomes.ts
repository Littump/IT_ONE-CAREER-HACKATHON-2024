import goalService from "./goalService.ts";
import { useQuery } from "@tanstack/react-query";

export const useGetIncomes = (id: number) =>
  useQuery({
    queryKey: ["incomes" + id],
    queryFn: () => goalService.getIncomesToGoal(id),
  });
