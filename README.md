# Тестовое

Задание на позицию стажера

Для запуска приложения воспользуйтесь командой `java -jar app.jar`
в корневой папке проекта

Для отправки запросов воспользуйтесь POSTMAN или аналогичной программой

## API

Поддерживается только один формат запроса

#### __POST__ `/`

В теле запроса необходимо указать строку.
В ответе будет json с результатом работы программы.

Пример запроса:

```json
{
  "str": "cbbaaa"
}
```

Пример ответа:

```json
{
  "answer": {"a": 3, "b": 2, "c": 1}
}
```

В случае ошибки будет возварщен статус 400 BadRequest и сообщение об ошибке
```json
{
  "error": "Str parameter not found"
}
```