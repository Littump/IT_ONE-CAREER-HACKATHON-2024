import { Typography } from "@material-tailwind/react";
import info1 from "@/assets/info_money.jpg";
import info2 from "@/assets/info_languages.jpg";
import { useTypedTranslation } from "@/helpers/useTypedTranslation.ts";

export default function Info() {
  const { t } = useTypedTranslation();

  return (
    <div className="flex flex-col my-4">
      <Typography variant="h4">{t("more-about-savemon")}</Typography>
      <div className="flex gap-4 mb-4 mt-8 items-center border-b pb-4 border-gray-400">
        <img src={info1} alt="" className="w-[56px] h-[76px] rounded-3xl" />
        <div className="flex flex-col w-9/12">
          <Typography variant="h6">{t("use-your-currency")}</Typography>
          <Typography variant="paragraph" className="text-gray-700">
            {t("dollars-rubles-yuan")}
          </Typography>
        </div>
      </div>
      <div className="flex gap-4 my-4 items-center border-b pb-4 border-gray-400">
        <img src={info2} alt="" className="w-[56px] h-[76px] rounded-3xl" />
        <div className="flex flex-col w-8/12">
          <Typography variant="h6">{t("use-app-on-your-language")}</Typography>
          <Typography variant="paragraph" className="text-gray-700">
            {t("russian-english")}
          </Typography>
        </div>
      </div>
      <Typography variant="h6" className="mt-6">
        {t("Its-not-just-app")}
      </Typography>
      <div className="mt-6 text-gray-600 text-sm">
        {t("savemon-description")}
      </div>
    </div>
  );
}
