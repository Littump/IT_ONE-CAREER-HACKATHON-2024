import useGetCurrency from "@/helpers/useGetCurrency.ts";
import {
  Button,
  Input,
  Menu,
  MenuHandler,
  MenuItem,
  MenuList,
  Typography,
} from "@material-tailwind/react";
import { IUser } from "@/modules/Profile/user.ts";

const Profile = () => {
  const currency = useGetCurrency();
  const user: IUser = {
    name: "Вася пупкин",
    username: "Angel_demon1234",
  };
  return (
    <div className="flex flex-col gap-7">
      <Typography variant="h5">Ваши данные</Typography>
      <Input label={"Имя пользователя"} value={user.username} />
      <Input label={"Имя Фамилия"} value={user.name} />
      <Menu>
        <MenuHandler>
          <div className="border border-blue-gray-200 text-blue-gray-700 text-sm rounded-xl py-3 px-4">
            Предпочтительная валюта: {currency}
          </div>
        </MenuHandler>
        <MenuList className="w-11/12">
          <MenuItem>RU - Р</MenuItem>
          <MenuItem>USD - $</MenuItem>
          <MenuItem>Юани - %</MenuItem>
          <MenuItem>Биткоины - !</MenuItem>
        </MenuList>
      </Menu>
      <Button>Изменить</Button>
    </div>
  );
};

export default Profile;
