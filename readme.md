### TaskManager

#### Версия Java 17.
#### Версия MySQL 8.0

Для работы с базой использовался Spring Data JPA

Log4j логирует в консоль при выборе сотрудника и масяца

#### Для запуска:

- внутри test.jar в файле application.properties изменить параметры источника данных spring.datasource
- в MySQL выполнить скрипты для создания базы данных и наполнения тестовыми данными
- запустить test.jar

#### Для проверки локазации можно запустить программу со следующими параметрами JVM:

java -Duser.language=en -Duser.country=US -jar TaskManager.jar

java -Duser.language=ru -Duser.country=RU -jar TaskManager.jar