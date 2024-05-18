import useGetCurrency from "@/helpers/useGetCurrency.ts";
import { GOAL_EMOJIS, IGoal, Transfer } from "@/modules/Goals/types/goal.ts";
import BackLink from "@/ui/BackLink.tsx";
import {
  Button,
  IconButton,
  Input,
  Typography,
} from "@material-tailwind/react";
import Emoji from "@/ui/Emoji.tsx";
import { useState } from "react";
import { NavLink } from "react-router-dom";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
import { Form, Formik } from "formik";

const TransferC = ({ date, value }: Transfer) => {
  const { t } = useTypedTranslation();
  const currency = useGetCurrency();
  return (
    <div className="flex gap-4 items-center  w-full border-b border-gray-200 pb-4">
      <div className="w-20 md:w-1/12">
        <Emoji size="sm">
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
              d="M19.5 13.5 12 21m0 0-7.5-7.5M12 21V3"
            />
          </svg>
        </Emoji>
      </div>
      <div className="flex flex-col w-8/12 ">
        <Typography variant="paragraph" className="text-lg">
          {t("replenishment")}
        </Typography>
        <Typography variant="paragraph">{date}</Typography>
      </div>
      <div className="w-24 md:w-2/12 flex justify-end">
        <Typography variant="paragraph" className="font-semibold">
          {" "}
          +{value} {currency}
        </Typography>
      </div>
      <button type="button" className="w-10 hover:text-red-500 transition">
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
            d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"
          />
        </svg>
      </button>
    </div>
  );
};

const GoalItemBig = () => {
  const currency = useGetCurrency();
  const [showAllTransfers, setShowAllTransfers] = useState(false);
  const [isAddMoney, setIsAddMoney] = useState(false);
  const goal: IGoal = {
    kind: "goal",
    description: "Книга",
    value: 2000,
    goal_value: 2000,
    achieved: true,
    deadline: "22-01-2023",
    id: 4,
  };
  const transfers: Transfer[] = [
    { date: "22-01-2024", value: 500, id: 1 },
    { date: "22-01-2024", value: 220, id: 2 },
    { date: "22-01-2024", value: 300, id: 3 },
    { date: "22-01-2024", value: 100, id: 4 },
    { date: "22-01-2024", value: 500, id: 5 },
    { date: "22-01-2024", value: 600, id: 6 },
  ];

  const handleAddMoney = (value: number) => {
    setIsAddMoney(false);
    console.log(value);
  };

  const { t } = useTypedTranslation();
  const { kind, description, value, goal_value, achieved, deadline } = goal;
  return (
    <div className="flex flex-col gap-8">
      <div className="grid grid-cols-3 w-full text-center">
        <BackLink to="/" />
        <Typography variant="h6">{t("your-goal")}</Typography>
        <div className="flex gap-6 ml-auto items-center ">
          <NavLink to="editGoal" className="hover:text-green-600 transition">
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
                d="M9.594 3.94c.09-.542.56-.94 1.11-.94h2.593c.55 0 1.02.398 1.11.94l.213 1.281c.063.374.313.686.645.87.074.04.147.083.22.127.325.196.72.257 1.075.124l1.217-.456a1.125 1.125 0 0 1 1.37.49l1.296 2.247a1.125 1.125 0 0 1-.26 1.431l-1.003.827c-.293.241-.438.613-.43.992a7.723 7.723 0 0 1 0 .255c-.008.378.137.75.43.991l1.004.827c.424.35.534.955.26 1.43l-1.298 2.247a1.125 1.125 0 0 1-1.369.491l-1.217-.456c-.355-.133-.75-.072-1.076.124a6.47 6.47 0 0 1-.22.128c-.331.183-.581.495-.644.869l-.213 1.281c-.09.543-.56.94-1.11.94h-2.594c-.55 0-1.019-.398-1.11-.94l-.213-1.281c-.062-.374-.312-.686-.644-.87a6.52 6.52 0 0 1-.22-.127c-.325-.196-.72-.257-1.076-.124l-1.217.456a1.125 1.125 0 0 1-1.369-.49l-1.297-2.247a1.125 1.125 0 0 1 .26-1.431l1.004-.827c.292-.24.437-.613.43-.991a6.932 6.932 0 0 1 0-.255c.007-.38-.138-.751-.43-.992l-1.004-.827a1.125 1.125 0 0 1-.26-1.43l1.297-2.247a1.125 1.125 0 0 1 1.37-.491l1.216.456c.356.133.751.072 1.076-.124.072-.044.146-.086.22-.128.332-.183.582-.495.644-.869l.214-1.28Z"
              />
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"
              />
            </svg>
          </NavLink>
          <button className="hover:text-red-500 transition">
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
                d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"
              />
            </svg>
          </button>
        </div>
      </div>
      <div className="flex items-center gap-4 ">
        <Emoji size="lg">{kind && GOAL_EMOJIS[kind]} </Emoji>
        <div>
          <Typography variant="h4" className="flex gap-4">
            {kind && t(kind)}{" "}
            {achieved && (
              <span className="text-green-600">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth={1.5}
                  stroke="currentColor"
                  className="w-8 h-8"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M9 12.75 11.25 15 15 9.75M21 12c0 1.268-.63 2.39-1.593 3.068a3.745 3.745 0 0 1-1.043 3.296 3.745 3.745 0 0 1-3.296 1.043A3.745 3.745 0 0 1 12 21c-1.268 0-2.39-.63-3.068-1.593a3.746 3.746 0 0 1-3.296-1.043 3.745 3.745 0 0 1-1.043-3.296A3.745 3.745 0 0 1 3 12c0-1.268.63-2.39 1.593-3.068a3.745 3.745 0 0 1 1.043-3.296 3.746 3.746 0 0 1 3.296-1.043A3.746 3.746 0 0 1 12 3c1.268 0 2.39.63 3.068 1.593a3.746 3.746 0 0 1 3.296 1.043 3.746 3.746 0 0 1 1.043 3.296A3.745 3.745 0 0 1 21 12Z"
                  />
                </svg>
              </span>
            )}
          </Typography>
          <Typography variant="paragraph">{description}</Typography>
        </div>
      </div>
      <div>
        <Typography variant="paragraph">{t("current-balance")}</Typography>
        <Typography variant="h4">
          {value} {currency}
        </Typography>
        <Typography variant="paragraph" className="text-blue-gray-700">
          * {t("on-delete-sum-returns-on-balance")}
        </Typography>
      </div>
      <Formik
        onSubmit={(values) => handleAddMoney(values.value)}
        initialValues={{ value: 0 }}
      >
        {({ values, handleChange, touched, errors }) => {
          return (
            <Form>
              {!isAddMoney ? (
                <Button
                  className="bg-blue-gray-50 text-black font-semibold w-full transition"
                  onClick={() => setIsAddMoney(true)}
                >
                  {t("top-up-balance")}
                </Button>
              ) : (
                <div className="flex gap-2">
                  <Input
                    name="value"
                    onChange={handleChange}
                    value={values.value}
                    error={!!(touched.value && errors.value)}
                    success={!!(touched.value && !errors.value)}
                    label={t("goal-sum")}
                  />

                  <IconButton type="submit">
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
                        d="M19.5 13.5 12 21m0 0-7.5-7.5M12 21V3"
                      />
                    </svg>
                  </IconButton>
                </div>
              )}
            </Form>
          );
        }}
      </Formik>
      <div className="border border-blue-gray-200 text-blue-gray-700 rounded-xl py-4 px-6 flex flex-col gap-4">
        <div className="flex justify-between border-b items-center border-blue-gray-200 pb-4">
          <Typography variant="paragraph">{t("goal-solo")}</Typography>{" "}
          <Typography variant="h6" className="text-black">
            {goal_value} {currency}
          </Typography>
        </div>
        <div className="flex justify-between items-center">
          <Typography variant="paragraph">{t("end-date")}</Typography>{" "}
          <Typography variant="h6" className="text-black">
            {deadline.replace(/-/g, ".")}
          </Typography>
        </div>
      </div>
      <div className="flex flex-col gap-4">
        <Typography variant="h5">{t("last-actions")}</Typography>{" "}
        <div className="flex flex-col gap-4 mt-4">
          {transfers
            .slice(0, showAllTransfers ? transfers.length : 2)
            .map((el) => (
              <TransferC key={el.id} {...el} />
            ))}
          <button
            className="text-blue-gray-700 underline"
            onClick={() => setShowAllTransfers((prev) => !prev)}
          >
            {showAllTransfers ? t("hide") : t("show-all")}
          </button>
        </div>
      </div>
    </div>
  );
};

export default GoalItemBig;
