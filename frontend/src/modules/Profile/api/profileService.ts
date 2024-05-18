import axios from "axios";
import API_URL from "@/config/api.ts";
import { CurrencyType } from "@/types/currency.ts";
import { IUser } from "@/modules/Profile/types/user.ts";
import { PatchDto } from "@/modules/Profile/types/patch.dto.ts";

class profileService {
  async getMe(currency: CurrencyType) {
    return axios.get(`${API_URL}profile?currency=${currency.toUpperCase()}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  }
  async patchMe(body: PatchDto) {
    return axios.patch<IUser>(`${API_URL}users/me`, body, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  }
}

export default new profileService();
