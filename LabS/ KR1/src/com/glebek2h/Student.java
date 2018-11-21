package com.glebek2h;

import java.util.Scanner;

/**
 * KR1
 * Created by fpm.kazachin on 21.11.2018 22:08
 */

public class Student
{
    private String surname;
    private String name;
    private String patronymic;
    private double averageMark;
    private double id;

    @Override
    public String toString()
    {
        return "com.glebek2h.Student{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", averageMark=" + averageMark +
                ", id=" + id +
                '}';
    }

    public double getId()
    {
        return id;
    }

    public Student readStudent(String path, Scanner scanner) throws Exception
    {
        String[] strings = scanner.nextLine().split(" ");
        surname = strings[0];
        name = strings[1];
        patronymic = strings[2];
        averageMark = Double.parseDouble(strings[3]);
        id = Double.parseDouble(strings[4]);
        return this;
    }
}
