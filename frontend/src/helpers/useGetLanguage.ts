import { useTranslation } from "react-i18next";

function useGetLanguage() {
  const { i18n } = useTranslation();
  return i18n.language;
}

export default useGetLanguage;
