Задание

Необходимо создать Android приложение для отображения списка фильмов и просмотра информации о них. За основу взята информация с сайта КиноПоиск.

Приложение должно состоять из двух экранов:
Список фильмов с фильтром;
Подробная информация о фильме.

Язык разработки: Java или Kotlin
Среда разработки: Android Studio
Минимальная версия Android: 5.1
Паттерн проектирования: MVP 
Ориентация экрана: вертикальная, горизонтальная
Библиотеки: для сетевых запросов используйте библиотеку Retrofit, не использовать RxJava

Источник данных

Список фильмов необходимо получить в формате JSON, выполнив GET запрос: https://s3-eu-west-1.amazonaws.com/sequeniatesttask/films.json

Экраны

Каждый экран необходимо реализовать с использованием Fragment’ов (то есть приложение должно иметь только одно Activity). 
Список фильмов с фильтром

На экране в списке отобразить заголовок Жанры. Далее - список жанров, который необходимо собрать из информации о фильмах (поле genres).
После списка жанров идет заголовок Фильмы, после которого следует список фильмов по 2 в ряд. Фильмы отсортировать по локализованному имени (поле localized_name) по алфавиту. 
