import ReactApexChart from "react-apexcharts";
import { ApexOptions } from "apexcharts";
import {
  EXPENSE_COLORS,
  IExpense,
} from "@/modules/ExpensesGraph/types/expenses.ts";
import useGetCurrency from "@/helpers/useGetCurrency.ts";

interface Props {
  expenses: IExpense[];
}

const Chart = ({ expenses }: Props) => {
  const colors = expenses.map((el) => EXPENSE_COLORS[el.type]);
  const labels = expenses.map((el) => el.type);
  const series = expenses.map((el) => el.value);

  const currency = useGetCurrency();

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
              formatter: function () {
                return series.reduce((acc, num) => acc + num, 0) + currency;
              },
            },
          },
        },
      },
    },
  };

  return (
    <ReactApexChart
      options={options}
      series={series}
      type="donut"
      height={250}
    />
  );
};

export default Chart;
