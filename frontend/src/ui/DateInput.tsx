import Datepicker, { DateValueType } from "react-tailwindcss-datepicker";

interface Props {
  name: string;
  value: DateValueType;
  setFieldValue: (name: string, val: DateValueType) => void;
  placeholder?: string;
}

function DateInput({ name, value, setFieldValue, placeholder }: Props) {
  return (
    <Datepicker
      classNames={{
        input: () =>
          "border border-blue-gray-200 focus:border-green-500 w-full py-2 px-4 rounded-xl",
      }}
      i18n={"ru"}
      primaryColor={"green"}
      useRange={false}
      asSingle={true}
      placeholder={placeholder}
      displayFormat={"DD/MM/YYYY"}
      value={value}
      onChange={(value) => setFieldValue(name, value)}
    />
  );
}

export default DateInput;
