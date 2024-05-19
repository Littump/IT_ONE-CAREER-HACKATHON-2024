import { NavLink } from "react-router-dom";
import { ReactNode } from "react";
import { Typography } from "@material-tailwind/react";
import {useTypedTranslation} from "@/helpers/useTypedTranslation.ts";
export interface IRoute {
  to: string;
  text: string;
  icon: ReactNode;
}


export default function Footer() {

  const {t} = useTypedTranslation()

  const routes: IRoute[] = [
    {
      to: "/",
      text: t("home"),
      icon: (
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
                d="m2.25 12 8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"
            />
          </svg>
      ),
    },
    {
      to: "/profile",
      text: t("profile"),
      icon: (
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
                d="M10.5 6h9.75M10.5 6a1.5 1.5 0 1 1-3 0m3 0a1.5 1.5 0 1 0-3 0M3.75 6H7.5m3 12h9.75m-9.75 0a1.5 1.5 0 0 1-3 0m3 0a1.5 1.5 0 0 0-3 0m-3.75 0H7.5m9-6h3.75m-3.75 0a1.5 1.5 0 0 1-3 0m3 0a1.5 1.5 0 0 0-3 0m-9.75 0h9.75"
            />
          </svg>
      ),
    },
  ];

  return (
    <footer className="bg-white px-8 pt-4">
      <div className="flex flex-row flex-wrap items-center justify-center gap-y-6 gap-x-12 bg-white text-center md:justify-between">
        <ul className="flex items-center justify-center w-full gap-8">
          {routes.map((el) => (
            <li key={el.to}>
              <NavLink to={el.to}>
                {" "}
                <Typography
                  color="blue-gray"
                  className="font-normal transition-colors hover:text-blue-500 focus:text-blue-500"
                >
                  {el.text}
                </Typography>
              </NavLink>
            </li>
          ))}
        </ul>
      </div>

      <hr className="my-4 border-blue-gray-50" />
      <Typography color="blue-gray" className="text-center font-normal">
        &copy; 2024 SaveMon
      </Typography>
    </footer>
  );
}
