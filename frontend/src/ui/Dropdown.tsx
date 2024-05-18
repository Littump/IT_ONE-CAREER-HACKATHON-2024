import {
  Menu,
  MenuHandler,
  MenuItem,
  MenuList,
} from "@material-tailwind/react";

interface Props {
  children: string | JSX.Element;
  items: string[];
  className?: string | null;
  onClick: (text: string) => void;
}

const Dropdown = ({ children, items, className, onClick }: Props) => {
  return (
    <Menu>
      <MenuHandler>
        <button
          type="button"
          className={
            "relative border  border-blue-gray-200 rounded-lg py-2 pl-4 pr-8 font-semibold text-blue-gray-700 " +
            className
          }
        >
          {children}
          <span className="absolute right-2 top-3">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="w-4 h-4"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="m19.5 8.25-7.5 7.5-7.5-7.5"
              />
            </svg>
          </span>
        </button>
      </MenuHandler>
      <MenuList>
        {items.map((el, index) => (
          <MenuItem key={el + index} onClick={() => onClick(el)}>
            {el}
          </MenuItem>
        ))}
      </MenuList>
    </Menu>
  );
};

export default Dropdown;
