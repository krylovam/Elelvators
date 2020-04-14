# Elevators
### Лабораторная работа №5 по курсу "Java", выполненная Крыловой Марией, студентом группы 18ПИ-1
Данная работа представляет моделирование работы лифтов в консоли.
#### Потоки
Проект является многопоточной программой, где один из потоков отвечает за генерацию запросов, а другой за обработку запрсов и 
управление лифтами.
#### Классы
Приложение состоит из нескольких основных классов, немного подробнее о каждом из них:
* **Request** - класс, отвечающий за генерацию одного запроса. Каждый из запросов состоит из двух чисел(Откуда-Куда), получаемых случайно и направления движения.
* **Generator** - класс, отчающий за работу потока генерации запросов. На каждой итерации цикла, создаётся новый запрос, добавляется в общую очередь запросов и приостанавливается на случайное время.
* **Direction **- перечисление, необходимое для описания состояния лифта и запроса.
* **Elevator** - класс, представляющий сущность "Лифт", в котором хранятся основные характеристики(направление движение, текущий этаж и т.д) и оделируется движение лифта
в соответствие с запросами, которые лифт обрабатывает.
* **Controller** - класс, отвечающий за поток обработки запросов, в нём каждый из запросов передаётся в управление подходящему лифту.
* **Main** - основной класс, в котором создаются и запускаются потоки, а так же хранится общая база текущих, еще не обработанных запросов.

#### Описание порядка обработки запросов:
Если лифт находится в сотоянии **"WAIT"**, т.е у него нет текущего запроса, то он принимает запрос, находящийся последним в очереди(хранящийся дольше всех).
Если лифт находится в состоянии **"GO TO REQUEST"**, то он не принимает новых запросов.
Если лифт находится в состоянии **"UP"** или** "DOWN**", то на каждом из проезжаемых ис этажей, он проверяет наличие на данном этаже запросов с таким же направлением.
Если такие запросы есть, то он их обрабатывает и,если нужно, изменяет значение поля "floorDestination".
На каждой итерации цикла все лифты выводят в консоль,своё положение, состояние и куда они движутся в данный момент.

Таким образом, все запросы в результате оказываются обработанными.


