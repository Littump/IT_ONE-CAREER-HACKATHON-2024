import { Typography } from "@material-tailwind/react";
import BackLink from "@/ui/BackLink.tsx";
import Emoji from "@/ui/Emoji.tsx";
import { NavLink } from "react-router-dom";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";

const AddOperationChooseIsPeriodic = () => {
  const { t } = useTypedTranslation();
  return (
    <div className="flex flex-col gap-6">
      <BackLink to="/" />
      <Typography variant="h4" className="font-semibold">
        {t("which-operation-type-you-want-to-add")}
      </Typography>
      <div>
        <NavLink
          to={`/addOperation/periodic`}
          className="flex transition hover:bg-gray-200 py-4 px-2 items-center justify-between border-b border-gray-300"
        >
          <div className="w-9/12 flex gap-4 justify-start items-center">
            <Emoji>⏲️</Emoji>
            <div>
              <Typography variant="h5" className="font-semibold">
                {t("periodic")}
              </Typography>
            </div>
          </div>
          <span>
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
                d="m8.25 4.5 7.5 7.5-7.5 7.5"
              />
            </svg>
          </span>
        </NavLink>
        <NavLink
          to={`/addOperation/oneTime`}
          className="flex transition hover:bg-gray-200 py-4 px-2 items-center justify-between border-b border-gray-300"
        >
          <div className="w-9/12 flex gap-4 justify-start items-center">
            <Emoji>🚨</Emoji>
            <div>
              <Typography variant="h5" className="font-semibold">
                {t("one-time")}
              </Typography>
            </div>
          </div>
          <span>
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
                d="m8.25 4.5 7.5 7.5-7.5 7.5"
              />
            </svg>
          </span>
        </NavLink>
      </div>
    </div>
  );
};

export default AddOperationChooseIsPeriodic;
