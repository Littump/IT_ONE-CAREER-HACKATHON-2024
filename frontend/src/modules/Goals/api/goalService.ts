import axios from "axios";
import API_URL from "@/config/api.ts";
import { CurrencyType } from "@/types/currency.ts";
import { AddDto } from "@/modules/Goals/types/add.dto.ts";
import { PatchDto } from "@/modules/Goals/types/patch.dto.ts";
import { IGoal, Transfer } from "@/modules/Goals/types/goal.ts";

class goalService {
  async getGoals(currency: CurrencyType) {
    return axios.get<IGoal[]>(
      `${API_URL}goals?currency=${currency.toUpperCase()}`,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
  async getGoal(id: number, currency: CurrencyType) {
    return axios.get<IGoal>(
      `${API_URL}goals/${id}?currency=${currency.toUpperCase()}`,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }

  async patchGoal(body: PatchDto, id: number, currency: CurrencyType) {
    return axios.patch(
      `${API_URL}goals/${id}?currency=${currency.toUpperCase()}`,
      body,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
  async addGoal(body: AddDto, currency: CurrencyType) {
    return axios.post(
      `${API_URL}goals?currency=${currency.toUpperCase()}`,
      { ...body, kind: body.kind.toUpperCase() },
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
  async addIncomeToGoal(value: number, id: number, currency: CurrencyType) {
    return axios.post(
      `${API_URL}goals/${id}/incomes?currency=${currency.toUpperCase()}`,
      { value },
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
  async deleteIncomeToGoal(id: number, goalId: number) {
    return axios.delete(`${API_URL}goals/${id}/incomes/${goalId}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  }
  async getIncomesToGoal(id: number) {
    return axios.get<Transfer[]>(`${API_URL}goals/${id}/incomes`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  }
  async deleteGoal(id: number) {
    return axios.delete(`${API_URL}goals/${id}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  }
}

export default new goalService();
