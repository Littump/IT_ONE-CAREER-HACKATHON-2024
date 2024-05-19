import { useMutation } from "@tanstack/react-query";
import loginService from "./loginService.ts";
import { RegistrationDto } from "@/modules/Login/types/dto.ts";

export const useRegistration = () =>
  useMutation({
    mutationFn: (body: RegistrationDto) => loginService.registration(body),
  });
