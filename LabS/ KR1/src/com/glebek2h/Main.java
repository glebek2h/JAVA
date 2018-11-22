package com.glebek2h;

import com.glebek2h.ClassBD;

/**
 * KR1
 * Created by fpm.kazachin on 21.11.2018 22:09
 */

/*
Задача
В  текстовом файле test1.txt находятся данные о студентах : фамилия, имя, отчество, средний бал,  идентификационный номер
(с буквой Е, е -это запись в экпоненциальном виде).
Каждая запись о студенте - в отдельной строке.
​
Разработать классы (com.glebek2h.Student,  com.glebek2h.ClassBD),  разработать итерфейс InterfBD с одним методом и реализовать метод в классе.
Класс com.glebek2h.Student - содержит информацию об одном студенте, класс com.glebek2h.ClassBD - содержит стандартные коллекции
(для MAP, ключ- идентификационный номер).

в классах должны быть  методы :
​1)​Перегруженный метод toString
​2)​Метод чтения данных из текстового файла
​3)​Метод удаляющий записи с некорректными данными  в среднем балле ( если присутствуют символы:  !/"№;%:?*()_+)
​​и записывающий  в результирующий текстовый файл rezult1.txt
​4)​Метод удаляющий записи с некорректными данными: (фамилия с цифрами)
​​и записывающий  в результирующий текстовый файл rezult2.txt
 */
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            ClassBD classBD = new ClassBD();
            classBD.read("input.txt");
            System.out.println(classBD);
            classBD.removeUncorrect();
            System.out.println(":)");
            System.out.println(classBD);
            classBD.write("rezult2.txt");
        } catch (Exception e)
        {
            System.out.println("Something wrong)");
        }
    }
}
