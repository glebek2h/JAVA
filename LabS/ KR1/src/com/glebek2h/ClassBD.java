package com.glebek2h;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * KR1
 * Created by fpm.kazachin on 21.11.2018 22:08
 */

public class ClassBD
{
    Map<String, Student> map;

    public ClassBD()
    {
        map = new HashMap<>()
        {
            @Override
            public String toString()
            {
                StringBuilder string = new StringBuilder();
                map.forEach((k, v) -> string.append("key: ").append(k).append(" value:").append(v));
                return string.toString();
            }
        };
    }

    public void removeUncorrectSurname()
    {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext())
        {
            String key = it.next();
            if (map.get(key).getSurname().matches(".*[1-9].*"))
                it.remove();
        }
    }
    public void removeUncorrectMark()
    {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext())
        {
            String key = it.next();
            if (map.get(key).getAverageMark().matches(".*[!/\"№;%:?*()_+].*"))
                it.remove();
        }
    }

    @Override
    public String toString()
    {
        return map.toString();
    }

    public void read(String path) throws Exception
    {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext())
        {
            Student student = new Student().readStudent(path, scanner);
            map.put(student.getId(), student);
        }
    }

    public void write(String path) throws Exception
    {

        Iterator<String> it = map.keySet().iterator();
        FileWriter fileWriter = new FileWriter(new File(path));
        while (it.hasNext())
        {
            String key = it.next();
            fileWriter.write(map.get(key).toString());
        }
        fileWriter.close();
    }
}
