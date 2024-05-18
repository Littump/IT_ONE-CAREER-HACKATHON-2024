import { Typography } from "@material-tailwind/react";
import info1 from "@/assets/info_money.jpg";
import info2 from "@/assets/info_languages.jpg";

export default function Info() {
  return (
    <div className="flex flex-col my-4">
      <Typography variant="h4">Ещё о проекте SaveMon</Typography>
      <div className="flex gap-4 mb-4 mt-8 items-center border-b pb-4 border-gray-400">
        <img src={info1} alt="" className="w-[56px] h-[76px] rounded-3xl" />
        <div className="flex flex-col w-9/12">
          <Typography variant="h6">Используйте любимую валюту</Typography>
          <Typography variant="paragraph" className="text-gray-700">
            доллары, рубли, юани
          </Typography>
        </div>
      </div>
      <div className="flex gap-4 my-4 items-center border-b pb-4 border-gray-400">
        <img src={info2} alt="" className="w-[56px] h-[76px] rounded-3xl" />
        <div className="flex flex-col w-8/12">
          <Typography variant="h6">Переведите приложение</Typography>
          <Typography variant="paragraph" className="text-gray-700">
            Если вам так удобнее
          </Typography>
        </div>
      </div>
      <Typography variant="h6" className="mt-6">
        Это не просто приложение для подсчета доходов и расходов!
      </Typography>
      <div className="mt-6 text-gray-600 text-sm">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet ipsa
        nulla rem, repellat rerum saepe! Assumenda doloribus eveniet, itaque
        nisi non possimus quaerat quo similique voluptates voluptatum. Autem
        iusto, quod. <br /> <br /> Lorem ipsum dolor sit amet, consectetur
        adipisicing elit. Cum cumque impedit minus. Expedita quas tenetur vero?
        Beatae culpa quae quasi?
        <br />
        <br />
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, unde!
      </div>
    </div>
  );
}
