import { NavLink } from "react-router-dom";
import { ReactNode } from "react";
import { IconButton } from "@material-tailwind/react";
import { useTranslation } from "react-i18next";
import useGetLanguage from "@/helpers/useGetLanguage.ts";
export interface IRoute {
  to: string;
  text: string;
  icon: ReactNode;
}

export default function Navbar() {
  const { i18n } = useTranslation();

  const changeLanguage = (language: "en" | "ru") => {
    i18n.changeLanguage(language);
  };

  const lan = useGetLanguage();

  return (
    <nav className="mx-auto bg-white z-40 fixed top-0 px-4 py-2  lg:py-4 shadow-none w-full h-14">
      <div className=" mx-auto flex items-center justify-between text-blue-gray-900">
        <div className="flex gap-2 items-center">
          <NavLink to="/profile">
            <IconButton variant="text">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth={1.5}
                stroke="currentColor"
                className="w-7 h-7"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"
                />
              </svg>
            </IconButton>
          </NavLink>
          <div className="flex gap-2">
            <button
              className={`py-1 px-2 bg-gray-200 rounded-xl hover:bg-green-400 hover:text-white transition ${
                lan === "en" ? "bg-green-400 text-white" : ""
              }`}
              onClick={() => changeLanguage("en")}
            >
              en
            </button>
            <button
              className={`py-1 px-2 bg-gray-200 rounded-xl hover:bg-green-400 hover:text-white transition ${
                lan === "ru" ? "bg-green-400 text-white" : ""
              }`}
              onClick={() => changeLanguage("ru")}
            >
              ru
            </button>
          </div>
        </div>
        <div className="flex gap-2">
          <NavLink to="/info">
            <IconButton variant="text">
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
                  d="M12 21a9.004 9.004 0 0 0 8.716-6.747M12 21a9.004 9.004 0 0 1-8.716-6.747M12 21c2.485 0 4.5-4.03 4.5-9S14.485 3 12 3m0 18c-2.485 0-4.5-4.03-4.5-9S9.515 3 12 3m0 0a8.997 8.997 0 0 1 7.843 4.582M12 3a8.997 8.997 0 0 0-7.843 4.582m15.686 0A11.953 11.953 0 0 1 12 10.5c-2.998 0-5.74-1.1-7.843-2.918m15.686 0A8.959 8.959 0 0 1 21 12c0 .778-.099 1.533-.284 2.253m0 0A17.919 17.919 0 0 1 12 16.5c-3.162 0-6.133-.815-8.716-2.247m0 0A9.015 9.015 0 0 1 3 12c0-1.605.42-3.113 1.157-4.418"
                />
              </svg>
            </IconButton>
          </NavLink>
          <NavLink to="/">
            <IconButton variant="text">
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
            </IconButton>
          </NavLink>
        </div>
      </div>
    </nav>
  );
}
