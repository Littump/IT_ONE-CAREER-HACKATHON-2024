import { Typography } from "@material-tailwind/react";
import { NavLink } from "react-router-dom";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";

const AddOperationItem = () => {
  const { t } = useTypedTranslation();
  return (
    <NavLink
      to="/addOperation"
      className="flex text-blue-gray-700 px-2 hover:text-black transition cursor-pointer items-center justify-between border-b pb-4 border-gray-300"
    >
      <div className="w-10/12 flex gap-4 justify-start items-center">
        <span className="text-4xl w-14 h-14 border-2 border-gray-200 rounded-full flex justify-center items-center">
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
              d="M12 4.5v15m7.5-7.5h-15"
            />
          </svg>
        </span>
        <div>
          <Typography variant="h6" className="font-semibold ">
            {t("new-operation")}
          </Typography>
        </div>
      </div>
    </NavLink>
  );
};

export default AddOperationItem;
