import axios from "axios";
import API_URL from "@/config/api.ts";
import { Logindto, RegistrationDto } from "@/modules/Login/types/dto.ts";

class loginService {
  async login(body: Logindto) {
    return axios.post(`${API_URL}auth/token/login`, body);
  }
  async registration(body: RegistrationDto) {
    return axios.post(`${API_URL}users`, body);
  }
  async getMe() {
    return axios.get(`${API_URL}users`, {
      headers: {
        Authorization: "Token " + localStorage.getItem("token"),
      },
    });
  }
}

export default new loginService();
