# Программа сортировки нескольких файлов слиянием.
  Написана в рамках тестового задания.
## Краткая инструкция по запуску
### Требования
  1. Java 17
  2. Maven 3.8.1

### Библиотеки
1.Lombok
```
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.26</version>
            <scope>compile</scope>
        </dependency>
```

### 1. Склонировать репозиторий в нужную папку:   
```
git@github.com:soulasphyxia/files-merge-sorter.git
```
### 2.  Собрать проект с помощью Maven
```
mvn clean install
```
### 3. Запустить программу через созданный архив в директории `/target` с указанием параметров для запуска:
```
java -jar files-merge-sorter-1.0-SNAPSHOT.jar -i out.txt in1.txt in2.txt
```
Данный вызов запустит программу сортировки слиянием для целых чисел по возрастанию для файлов in1.txt, in2.txt в выходной файл out.txt


## Особенности реализации

### 1. Подход - Eternal Sort.
 Алгоритм сортировки реализовывался с помощью подхода, применяемого при сортировки  внешним слиянием(External Sort), который используется при сортировке N файлов,
 не всегда помещающихся в оперативную память. В этом алгоритме файлы предварительно сортируются, а затем с помощью структуры данных minHeap(в Java - PriorityQueue) сливаются в один файл.

### 2. Сортировка по убыванию
Для сортировки по убыванию, также как и при сортирорвки по возрастанию, необходимо, чтобы файлы были предварительно отсортированы в соответсвующей последовательности.

### 3. Сортировка целых чисел с присутствием строк
Если при сортировке целых чисел в файле присутствуют строки, содержащие строковые символы, которые не могут быть конвертированы в целое число, то они будут проигнорированы.
При сортировке строк, числа будут конвертированы в строки и сортироваться по правилам сортировки строк.

### 4. Строки, содержащие пробельные символы
Строки, состоящие только из пробельных символов игнорируются, в противном случае берется первая последовательность символов до ближайшего пробельного символа.

### 5. Ошибка в предварительной сортировке
Если присутствует ошибка в предварительной сортировке, то файлы будут отсортированы настолько, насколько это возможно(до встречи первой ошибки). В выходном файле будут находиться частично отсортированные данные, но алгоритм не гарантирует никакой закономерности в порядке данных в выходном файле после встречи ошибки.

