import { ReactNode } from "react";
interface Props {
  children: ReactNode;
  size?: "sm" | "lg";
}
const Emoji = ({ children, size = "sm" }: Props) => {
  return (
    <span
      className={`text-4xl ${
        size == "sm" ? "w-14 h-14" : "w-20 h-20 text-5xl"
      } border-2 border-gray-200 rounded-full flex justify-center items-center`}
    >
      {children}
    </span>
  );
};

export default Emoji;
