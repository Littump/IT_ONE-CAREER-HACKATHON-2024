import { Typography } from "@material-tailwind/react";
import useGetCurrency from "@/helpers/useGetCurrency.ts";

const Balance = () => {
  const now = new Date();
  const hours = now.getHours();
  const hello =
    hours >= 18
      ? "Добрый вечер!"
      : hours >= 12
        ? "Добрый день!"
        : "Доброе утро!";
  const sum = 123;
  const currency = useGetCurrency();
  return (
    <div>
      <Typography variant="h3" className="font-semibold">
        {hello}
      </Typography>
      <Typography variant="paragraph" className="text-gray-600">
        У вас{" "}
        <span className="text-gray-900">
          {sum}
          {currency}
        </span>{" "}
        на балансе
      </Typography>
    </div>
  );
};

export default Balance;
