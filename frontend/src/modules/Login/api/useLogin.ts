import { useMutation } from "@tanstack/react-query";
import loginService from "./loginService.ts";
import {Logindto} from "@/modules/Login/types/dto.ts";

export const useLogin = () =>
  useMutation({
    mutationFn: (body: Logindto) => loginService.login(body),
  });
