import { ApexOptions } from "apexcharts";
import ReactApexChart from "react-apexcharts";
import { IGoal } from "@/modules/Goals/types/goal.ts";
import { Typography } from "@material-tailwind/react";
import useGetCurrency from "@/helpers/useGetCurrency.ts";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";

const GoalGraph = () => {
  const { t } = useTypedTranslation();
  const currency = useGetCurrency();
  const goals: IGoal[] = [
    {
      kind: "gift",
      description: "Подарок маме",
      value: 500,
      goal_value: 15000,
      achieved: false,
      deadline: "22-01-2023",
      id: 1,
    },
  ];
  const countAchieved = goals.reduce((sum, el) => sum + +el.achieved, 0);
  const sumOfValue = goals.reduce((sum, el) => sum + +el.value, 0);
  const sumOfGoalValue = goals.reduce((sum, el) => sum + +el.goal_value, 0);
  const colors = ["#cad2c5", "#008000"];
  const labels = [t("remains-to-achieve"), t("achieved")];
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
        <Typography variant="h3">{t("achieved-goals")}</Typography>
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
              <Typography variant="paragraph">{t("total-income")}:</Typography>
              <Typography variant="h5">
                {sumOfValue} {currency}
              </Typography>
            </span>
            <span>
              <Typography variant="paragraph">{t("total-need")}:</Typography>
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

export default GoalGraph;
