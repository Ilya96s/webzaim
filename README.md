# Сервис, рассчитывающий сквозную строку платежей

# Описание проекта:
* Сквозная строка платежей – это объединение всех строк с платежами за разные даты. Если между кредитами есть промежуток, то он заполняется символом «X» Если в один месяц пересекаются платежи по разным кредитам, то в результирующую строку ставиться символ с большим кол-вом дней просрочки.
* Данные на вход:

| Строка платежей | Дата первого платежа |
|-----------------|----------------------|
| 111111          | 01/01/2020  |
| 32А10           | 01/03/2020  |
| 111             | 01/10/2020  |

* 32A10 – каждый символ в строке  - это код платежа.
  Крайний правый символ «0» - это первый платеж, который имеет дату платежа 01/03/2020
  Следующий символ «1» - это второй платеж с датой 01/04/2020
  Последний символ справа на лево «3» - имеет дату 01/07/2020

**Справочник кодов для платежей:**
1. 1 - платеж вовремя
2. 0 - данные не поступили
3. А - просрочка 7 дней
4. 2 - просрочка от 29 до 39 дней
5. 3 - просрочка свыше 39 дней
6. Х - отсутствие данных

* При запуске запускатся миграция в БД.
* В Миграции создается одна таблица с кредитами, с полями: Строка платежей, Дата первого платежа.
* При запуске приложение расчитывает сквозную строку платежей по данным из таблицы кредитов и вывести результат в консоль.



# Стек технологий:
* Java 17
* Spring Boot 3.1.2
* Spring Data JPA 3.1.2
* PostgreSQL 14
* Liquibase 4.20
* Lombok 1.18.28
* Apache Maven 3.8.6

# Требования к окружению:
* Java 17
* Maven 3.8.6
* PostgreSQL 14

# Запуск проекта с помощью терминала:
1. В PostgreSQL создать базу данных webzaim
```shell
jdbc:postgresql://127.0.0.1:5432/webzaim
```
2. Запустить проект
```shell
mvn spring-boot run
```

# Запуск проекта с помощью docker-compose:
1. Клонируйте проект
```shell
gi clone https://github.com/Ilya96s/webzaim
```
2. Перейдите в папку проекта
```shell
cd webzaim
```
3. Создайте образ проекта
```shell
docker-compose build
```
4. Выполните команду
```shell
docker-compose up
```

# Контакты
Telegram: **@Ilya96s**

