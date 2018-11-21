package com.glebek2h;

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
    private double averageMark;
    private double id;

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
    public boolean isNeedDeleteStudent()
    {
        Pattern pattern = Pattern.compile("[^A-Z|a-z|А-Я|а-я]");
        Matcher matcher = pattern.matcher(surname);
        if(matcher.find())
           return false;
        return true;
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
