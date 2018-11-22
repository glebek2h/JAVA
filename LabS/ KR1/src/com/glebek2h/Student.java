package com.glebek2h;

import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * KR1
 * Created by fpm.kazachin on 21.11.2018 22:08
 */

public class Student
{
    private String surname;
    private String name;
    private String patronymic;
    private String averageMark;
    private String id;

    @Override
    public String toString()
    {
        return "{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", averageMark=" + averageMark +
                ", id=" + id +
                '}'+'\n';
    }
    public String getId()
    {
        return id;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getAverageMark()
    {
        return averageMark;
    }

    public Student readStudent(String path, Scanner scanner) throws Exception
    {
        String[] strings = scanner.nextLine().split(" ");
        surname = strings[0];
        name = strings[1];
        patronymic = strings[2];
        averageMark = strings[3];
        id = strings[4];
        return this;
    }
}
