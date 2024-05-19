import { useMutation } from "@tanstack/react-query";
import { queryClient } from "@/main.tsx";
import profileService from "@/modules/Profile/api/profileService.ts";
import { PatchDto } from "@/modules/Profile/types/patch.dto.ts";

export const usePatchGoal = () =>
  useMutation({
    mutationFn: (body: PatchDto) => profileService.patchMe(body),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["me"] });
    },
  });
