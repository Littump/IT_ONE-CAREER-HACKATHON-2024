import { Button, Typography } from "@material-tailwind/react";
import { NavLink } from "react-router-dom";
import { useState } from "react";
import {
  IOperation,
  IOperationPeriodic,
} from "@/modules/Operations/types/operation.ts";
import OperationItemMin from "@/modules/Operations/ui/OperationItemMin.tsx";
import AddOperationItem from "@/modules/Operations/ui/AddOperationItem.tsx";

interface Props {
  isFull?: boolean;
}

const Operations = ({ isFull = true }: Props) => {
  const [showAll, setShowAll] = useState(!isFull);
  const [showPeriodic, setShowPeriodic] = useState(!isFull);

  const operations: IOperation[] = [
    {
      kind_operation: "expense",
      kind: "food",
      date: "22-01-2024",
      value: 1500,
      description: "макидональдс",
      id: 1,
    },
    {
      kind_operation: "expense",
      kind: "education",
      date: "22-01-2024",
      value: 30000,
      description: "спбгу",
      id: 2,
    },
    {
      kind_operation: "income",
      kind: "business",
      date: "22-01-2024",
      value: 100,
      description: "шаурмечная принесла рыбов",
      id: 3,
    },
    {
      kind_operation: "income",
      kind: "gifts",
      date: "22-01-2024",
      value: 5000,
      description: "подарили денежку",
      id: 4,
    },
    {
      kind_operation: "income",
      kind: "salary",
      date: "22-01-2024",
      value: 30000,
      description: "зп",
      id: 4,
    },
  ];

  const operationsPeriodic: IOperationPeriodic[] = [
    {
      kind_operation: "expense",
      period_kind: "day",
      period_value: 4,
      kind: "food",
      start_day: "22-01-2024",
      value: 1500,
      description: "макидональдс",
      id: 1,
    },
    {
      kind_operation: "expense",
      kind: "education",
      period_kind: "day",
      period_value: 4,
      start_day: "22-01-2024",
      value: 30000,
      description: "спбгу",
      id: 2,
    },
    {
      kind_operation: "income",
      kind: "business",
      period_kind: "day",
      period_value: 4,
      start_day: "22-01-2024",
      value: 100,
      description: "шаурмечная принесла рыбов",
      id: 3,
    },
    {
      kind_operation: "income",
      kind: "gifts",
      period_kind: "day",
      period_value: 4,
      start_day: "22-01-2024",
      value: 5000,
      description: "подарили денежку",
      id: 4,
    },
    {
      kind_operation: "income",
      kind: "salary",
      period_kind: "day",
      period_value: 4,
      start_day: "22-01-2024",
      value: 30000,
      description: "зп",
      id: 4,
    },
  ];

  return (
    <div className="rounded-2xl py-6 px-4 border border-gray-300">
      {isFull ? (
        <>
          <div className="flex justify-between items-center">
            <Typography variant="h5" className="font-semibold">
              Операции
            </Typography>
            <NavLink
              to="/operations"
              className="bg-gray-100 hover:bg-gray-200 transition rounded-full p-2 "
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth={1.5}
                stroke="currentColor"
                className="w-6 h-6"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M17.25 8.25 21 12m0 0-3.75 3.75M21 12H3"
                />
              </svg>
            </NavLink>
          </div>
          <NavLink to="/addOperation">
            <Button className="rounded-full my-2" size="sm">
              Добавить
            </Button>
          </NavLink>
          <button
            onClick={() => setShowAll((prev) => !prev)}
            className="text-blue-gray-700 underline text-sm ml-4"
          >
            {showAll ? "Скрыть" : "Показать все"}
          </button>
        </>
      ) : (
        <Typography variant="h5">Ваши операции</Typography>
      )}
      <div className="my-4 flex flex-col ">
        {operations.slice(0, showAll ? operations.length : 2).map((el) => (
          <OperationItemMin key={el.value + el.description} {...el} />
        ))}
      </div>
      <AddOperationItem />
      {!showPeriodic ? (
        <button
          onClick={() => setShowPeriodic(true)}
          className="py-4 px-2 w-full text-blue-gray-700 text-md underline mx-auto"
        >
          Показать периодические
        </button>
      ) : (
        <div className="my-4 flex flex-col">
          <Typography variant="h5" className="font-semibold mt-6 mb-4">
            Периодические операции
          </Typography>
          {operationsPeriodic.map((el) => (
            <OperationItemMin
              key={el.value + el.description}
              {...el}
              date={el.start_day}
            />
          ))}
          <button
            onClick={() => setShowPeriodic(false)}
            className="py-4 px-2 w-full text-blue-gray-700 text-md underline mx-auto"
          >
            Скрыть
          </button>
        </div>
      )}
    </div>
  );
};

export default Operations;
