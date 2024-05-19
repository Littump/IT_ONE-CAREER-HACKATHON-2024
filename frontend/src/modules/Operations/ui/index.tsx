import { Button, Spinner, Typography } from "@material-tailwind/react";
import { NavLink } from "react-router-dom";
import { useState } from "react";
import OperationItemMin from "@/modules/Operations/ui/OperationItemMin.tsx";
import AddOperationItem from "@/modules/Operations/ui/AddOperationItem.tsx";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import { useGetOperations } from "@/modules/Operations/api/useGetOperations.ts";
import useGetCurrency from "@/helpers/useGetCurrency.ts";
import { useGetOperationsPeriodic } from "@/modules/Operations/api/useGetOperationsPeriodic.ts";

interface Props {
  isFull?: boolean;
}

const Operations = ({ isFull = true }: Props) => {
  const [showAll, setShowAll] = useState(!isFull);
  const [showPeriodic, setShowPeriodic] = useState(!isFull);
  const currency = useGetCurrency();
  const { t } = useTypedTranslation();

  const { data, isPending } = useGetOperations(currency);
  const { data: periodicData, isPending: isPeriodicPending } =
    useGetOperationsPeriodic(currency);
  if (!data || !periodicData || isPeriodicPending || isPending)
    return <Spinner />;
  return (
    <div className="rounded-2xl py-6 px-4 border border-gray-300">
      {isFull ? (
        <>
          <div className="flex justify-between items-center">
            <Typography variant="h5" className="font-semibold">
              {t("operations")}
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
              {t("add")}
            </Button>
          </NavLink>
          <button
            onClick={() => setShowAll((prev) => !prev)}
            className="text-blue-gray-700 underline text-sm ml-4"
          >
            {showAll ? t("hide") : t("show-all")}
          </button>
        </>
      ) : (
        <Typography variant="h5">{t("your-operations")}</Typography>
      )}
      <div className="my-4 flex flex-col ">
        {data.data.slice(0, showAll ? data.data.length : 2).map((el) => (
          <OperationItemMin key={el.value + el.description} {...el} />
        ))}
      </div>
      <AddOperationItem />
      {!showPeriodic ? (
        <button
          onClick={() => setShowPeriodic(true)}
          className="py-4 px-2 w-full text-blue-gray-700 text-md underline mx-auto"
        >
          {t("show-periodic")}
        </button>
      ) : (
        <div className="my-4 flex flex-col">
          <Typography variant="h5" className="font-semibold mt-6 mb-4">
            {t("periodic-operations")}
          </Typography>
          {periodicData.data.map((el) => (
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
            {t("hide")}
          </button>
        </div>
      )}
    </div>
  );
};

export default Operations;
