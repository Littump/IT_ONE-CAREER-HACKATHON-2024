### Сайт: [savemon.ru](https://savemon.ru)

### Демонстрация работы:
https://drive.google.com/file/d/1dLkKpOLkBhO6LkXJ8nhUFHZ4IEyqCBNt/view?usp=sharing

### Презентация:
https://drive.google.com/drive/folders/1d-4J0odyogJy3N6_UECy37EqAmm7VsVt?usp=sharing

---

## Как поднять проект

1. **Собрать образы локально**
   - Клоним и переходим в папку infra-local:
     ```
     git clone https://github.com/Littump/IT_ONE-CAREER-HACKATHON-2024.git
     cd infra-local
     ```
   - Поднимаем docker-compose
     ```
     docker compose up -d
     ```
2. **Спушить образы с docker.hub**
   - Клоним и переходим в папку infra:
     ```
     git clone https://github.com/Littump/IT_ONE-CAREER-HACKATHON-2024.git
     cd infra
     ```
   - Поднимаем docker-compose
     ```
     docker compose up -d
     ```
---

## Правила разработки
Техническая документация, или правила разработки

1. **Работа с ветками:**
    - Запрещено писать код в ветках STABLE и PRESTABLE. Нужно отводить новую ветку от PRESTABLE и начинать в ней разработку:

    ```
    git checkout PRESTABLE
    git pull
    git checkout -b new_feature
    ```

    - После того, как работа окончена - заходите на github и делайте Pull Request

2. **Работа с большими данными (файлами):**
    Нехорошо закидывать огромные файлы csv на git. Git - это больше про код, а не про данные. И там есть ограничение (вроде 300 мб). А если мы туда будем кидать csv по 50+ мб, то скоро это память закончится. Надо искать какое-то решение, как с такими файлами обходиться. Предлагается:

    - Эти файлики добавить в .gitignore, чтобы при пуше они не закидывались на гит
    - В папке создавать файл data_name.url, а в нем указывать ссылку на любое хранилище, откуда надо этот файл data_name.csv можно скачать и закинуть вместо этого data_name.url. Файл data_name.url с ссылкой внутри уже можно (нужно) лить на гит
