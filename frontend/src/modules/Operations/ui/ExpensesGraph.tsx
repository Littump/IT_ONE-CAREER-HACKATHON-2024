import { ApexOptions } from "apexcharts";
import ReactApexChart from "react-apexcharts";
import { IGoal } from "@/modules/Goals/types/goal.ts";
import { Typography } from "@material-tailwind/react";
import useGetCurrency from "@/helpers/useGetCurrency.ts";

const ExpensesGraph = () => {
  const currency = useGetCurrency();
  const goals: IGoal[] = [
    {
      type: "gift",
      description: "Подарок маме",
      value: 500,
      goal_value: 15000,
      is_achievement: false,
      date: "22-01-2023",
      id: 1,
    },
    {
      type: "egg",
      description: "На всякий",
      value: 20000,
      goal_value: 100000,
      is_achievement: false,
      date: "22-01-2023",
      id: 2,
    },
    {
      type: "travel",
      description: "Анапа",
      value: 2500,
      goal_value: 30000,
      is_achievement: false,
      date: "22-01-2023",
      id: 3,
    },
    {
      type: "goal",
      description: "Книга",
      value: 2000,
      goal_value: 2000,
      is_achievement: true,
      date: "22-01-2023",
      id: 4,
    },
  ];
  const countAchieved = goals.reduce((sum, el) => sum + +el.is_achievement, 0);
  const sumOfValue = goals.reduce((sum, el) => sum + +el.value, 0);
  const sumOfGoalValue = goals.reduce((sum, el) => sum + +el.goal_value, 0);
  const colors = ["#cad2c5", "#008000"];
  const labels = ["Осталось достичь", "Достигнуто"];
  const series = [goals.length - countAchieved, countAchieved];

  const options: ApexOptions = {
    chart: { type: "donut" },
    legend: { show: false },
    dataLabels: { enabled: false },
    tooltip: {
      custom: function ({ seriesIndex }) {
        return (
          `<div class="bg-black px-4 py-2">` + labels[seriesIndex] + `</div>`
        );
      },
    },
    fill: { colors },
    stroke: { width: 0 },
    plotOptions: {
      pie: {
        expandOnClick: false,
        donut: {
          size: "70%",
          labels: {
            show: true,
            name: { show: false },
            value: {
              fontSize: "32px",
            },
            total: {
              show: true,
              showAlways: true,
            },
          },
        },
      },
    },
  };

  return (
    <div>
      <div className="border border-gray-300 rounded-xl py-4 px-6 text-center flex flex-col gap-4">
        <Typography variant="h3">Достигнутых целей</Typography>
        <div className="grid grid-cols-2 gap-4">
          <div>
            <ReactApexChart
              options={options}
              series={series}
              type="donut"
              height={250}
            />
          </div>
          <div className="text-start flex flex-col gap-2">
            <span>
              <Typography variant="paragraph">Всего пополнено:</Typography>
              <Typography variant="h5">
                {sumOfValue} {currency}
              </Typography>
            </span>
            <span>
              <Typography variant="paragraph">Всего Нужно:</Typography>
              <Typography variant="h5">
                {sumOfGoalValue} {currency}
              </Typography>
            </span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExpensesGraph;
