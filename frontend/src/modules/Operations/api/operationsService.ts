import axios from "axios";
import API_URL from "@/config/api.ts";
import { CurrencyType } from "@/types/currency.ts";
import {
  IOperation,
  IOperationPeriodic,
} from "@/modules/Operations/types/operation.ts";
import { AddDto, AddPeriodicDto } from "@/modules/Operations/types/add.dto.ts";
import {
  IExpense,
  IExpensePeriodic,
} from "@/modules/Operations/types/expense.ts";
import { IIncome, IIncomePeriodic } from "@/modules/Operations/types/income.ts";

class operationsService {
  async getOperations(currency: CurrencyType) {
    return axios.get<IOperation[]>(
      `${API_URL}operations?currency=${currency.toUpperCase()}`,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
  async getExpense(id: number) {
    return axios.get<IExpense>(`${API_URL}expenses/${id}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  }
  async getIncome(id: number) {
    return axios.get<IIncome>(`${API_URL}incomes/${id}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  }
  async getIncomePeriodic(id: number) {
    return axios.get<IIncomePeriodic>(`${API_URL}incomes_period/${id}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  }
  async getExpensePeriodic(id: number) {
    return axios.get<IExpensePeriodic>(`${API_URL}expenses_period/${id}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  }
  async getOperationsPeriodic(currency: CurrencyType) {
    return axios.get<IOperationPeriodic[]>(
      `${API_URL}operations_period?currency=${currency.toUpperCase()}`,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
  async addExpense(body: AddDto, currency: CurrencyType) {
    return axios.post(
      `${API_URL}expenses?currency=${currency.toUpperCase()}`,
      {
        ...body,
        value: body.value.toString(),
      },
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
  async addIncome(body: AddDto, currency: CurrencyType) {
    return axios.post(
      `${API_URL}incomes?currency=${currency.toUpperCase()}`,
      { ...body, value: body.value.toString() },
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
  async addExpensePeriodic(body: AddPeriodicDto, currency: CurrencyType) {
    return axios.post(
      `${API_URL}expenses_period?currency=${currency.toUpperCase()}`,
      { ...body, value: body.value.toString() },
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
  async addIncomePeriodic(body: AddPeriodicDto, currency: CurrencyType) {
    return axios.post(
      `${API_URL}incomes_period?currency=${currency.toUpperCase()}`,
      { ...body, value: body.value.toString() },
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      },
    );
  }
}

export default new operationsService();
