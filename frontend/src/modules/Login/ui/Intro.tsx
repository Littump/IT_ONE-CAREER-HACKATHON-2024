import { Button, Typography } from "@material-tailwind/react";
import img from "@/assets/loginPhoto.jpg";
import { NavLink } from "react-router-dom";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";
const Intro = () => {
  const { t } = useTypedTranslation();
  return (
    <div className="flex flex-col w-full md:flex-row md:mt-20 gap-14 md:gap-7 md:items-center px-4 py-16 max-w-4xl my-auto mx-auto">
      <div className="md:w-1/2">
        <img src={img} alt="" className="w-full" />
      </div>
      <div className="px-4 flex flex-col gap-4 md:w-1/2">
        <Typography variant="h2" className="font-semibold">
          {t("reach-your-goals")}
        </Typography>
        <Typography variant="paragraph" className="text-blue-gray-700">
          {t("SaveMon-your-personal-assistant")}
        </Typography>
        <div className="flex gap-4 w-full my-6">
          <NavLink to="/login" className="w-1/3">
            <Button variant="text" className="w-full">
              {t("login")}
            </Button>
          </NavLink>
          <NavLink to="/registration" className="w-2/3">
            <Button className="w-full">{t("lets-go")}</Button>
          </NavLink>
        </div>
      </div>
    </div>
  );
};

export default Intro;
