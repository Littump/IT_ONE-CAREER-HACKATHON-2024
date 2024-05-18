import {
  Card,
  CardBody,
  CardHeader,
  Typography,
} from "@material-tailwind/react";
import Chart from "./Chart.tsx";
import { IExpense } from "@/modules/ExpensesGraph/types/expenses.ts";
import Details from "@/modules/ExpensesGraph/ui/Details.tsx";

const ExpensesGraph = () => {
  const expenses: IExpense[] = [
    { type: "products", value: 1200 },
    { type: "jkh", value: 600 },
    { type: "medicine", value: 900 },
    { type: "transport", value: 900 },
    { type: "other", value: 2200 },
  ];

  return (
    <Card>
      <CardHeader
        floated={false}
        shadow={false}
        color="transparent"
        className="flex flex-col gap-2 rounded-none  justify-center items-center"
      >
        <Typography variant="h3" color="blue-gray">
          Расходы за месяц
        </Typography>
      </CardHeader>
      <CardBody className=" flex justify-center items-center gap-4 flex-col px-2">
        <Chart expenses={expenses} />
        <Details expenses={expenses} />
      </CardBody>
    </Card>
  );
};

export default ExpensesGraph;
