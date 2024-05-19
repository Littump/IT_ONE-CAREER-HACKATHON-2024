import { Typography } from "@material-tailwind/react";
import useGetCurrency from "@/helpers/useGetCurrency.ts";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import { useGetMe } from "@/modules/Balance/api/useGetMe.ts";

const Balance = () => {
  const currency = useGetCurrency();
  const { t } = useTypedTranslation();
  const { data, isPending } = useGetMe(currency);

  const now = new Date();
  const hours = now.getHours();
  if (!data || isPending) return;
  const hello =
    (hours >= 18
      ? t("good-evening")
      : hours >= 12
        ? t("good-day")
        : t("good-morning")) +
    " " +
    data.data.name;
  const sum = data.data.balance;

  return (
    <div>
      <Typography variant="h3" className="font-semibold">
        {hello}
      </Typography>
      <Typography variant="paragraph" className="text-gray-600">
        {t("you-have")}{" "}
        <span className="text-gray-900">
          {sum} {currency}
        </span>{" "}
        {t("on-your-balance")}
      </Typography>
    </div>
  );
};

export default Balance;
