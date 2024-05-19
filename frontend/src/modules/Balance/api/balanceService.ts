import axios from "axios";
import API_URL from "@/config/api.ts";
import { CurrencyType } from "@/types/currency.ts";
import { IUser } from "@/modules/Balance/types/user.ts";

class balanceService {
  async getMe(currency: CurrencyType) {
    return axios.get<IUser>(
      `${API_URL}profile?currency=${currency.toUpperCase()}`,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
}

export default new balanceService();
