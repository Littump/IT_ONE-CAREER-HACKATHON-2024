import i18n from "i18next";
import Backend from "i18next-http-backend";
import LanguageDetector from "i18next-browser-languagedetector";
import { initReactI18next } from "react-i18next";
import rut from "@/../public/locales/ru/translation.json";
import ent from "@/../public/locales/en/translation.json";

const resources = {
  ru: {
    translation: rut,
  },
  en: {
    translation: ent,
  },
};

i18n
  // Подключение бэкенда i18next
  .use(Backend)
  // Автоматическое определение языка
  .use(LanguageDetector)
  // модуль инициализации
  .use(initReactI18next)
  .init({
    // Стандартный язык
    fallbackLng: "ru",
    debug: true,
    interpolation: {
      escapeValue: false,
    },
    resources,
  });

export default i18n;
