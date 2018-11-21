package com.glebek2h;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * KR1
 * Created by fpm.kazachin on 21.11.2018 22:08
 */

public class ClassBD
{
    Map<Double, Student> map;

    public ClassBD()
    {
        map = new HashMap<Double,Student>();
    }

    @Override
    public String toString()
    {
        return "com.glebek2h.ClassBD{" +
                "map=" + map +
                '}';
    }
    public void read(String path) throws Exception
    {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext())
        {
            Student student = new Student().readStudent(path,scanner);
            map.put(student.getId(), student);
        }
    }
}
