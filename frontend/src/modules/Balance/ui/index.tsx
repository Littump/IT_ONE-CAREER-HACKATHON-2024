import { Typography } from "@material-tailwind/react";
import useGetCurrency from "@/helpers/useGetCurrency.ts";
import {useTypedTranslation} from "@/helpers/useTypedTranslation.ts";

const Balance = () => {
  const now = new Date();
  const hours = now.getHours();
  const {t} = useTypedTranslation()
  const hello =
    hours >= 18
      ? t("good-evening")
      : hours >= 12
        ? t("good-day")
        :t("good-morning");
  const sum = 123;
  const currency = useGetCurrency();
  return (
    <div>
      <Typography variant="h3" className="font-semibold">
        {hello}
      </Typography>
      <Typography variant="paragraph" className="text-gray-600">
          {t("you-have")}{" "}
        <span className="text-gray-900">
          {sum}
          {currency}
        </span>{" "}
          {t("on-your-balance")}
      </Typography>
    </div>
  );
};

export default Balance;
