import Datepicker, { DateValueType } from "react-tailwindcss-datepicker";
import { useTranslation } from "react-i18next";

interface Props {
  name: string;
  value: DateValueType;
  setFieldValue: (name: string, val: DateValueType) => void;
  placeholder?: string;
  success?: boolean;
  error?: boolean;
}

function DateInput({
  name,
  value,
  setFieldValue,
  placeholder,
  success,
  error,
}: Props) {
  const { i18n } = useTranslation();
  return (
    <Datepicker
      classNames={{
        input: () =>
          `border ${
            success
              ? "border-green-400"
              : error
                ? "border-red-500"
                : "border-blue-gray-200 "
          } focus:border-green-500 w-full py-2 px-4 rounded-lg text-blue-gray-700 text-sm`,
      }}
      i18n={i18n.language}
      primaryColor={"green"}
      useRange={false}
      asSingle={true}
      placeholder={placeholder}
      displayFormat={"DD/MM/YYYY"}
      value={value}
      popoverDirection={"down"}
      onChange={(value) => setFieldValue(name, value)}
    />
  );
}

export default DateInput;
