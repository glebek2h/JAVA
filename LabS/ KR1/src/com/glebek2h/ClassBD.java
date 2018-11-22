package com.glebek2h;

import java.io.File;
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
        map = new HashMap<String, Student>()
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
    public void removeUncorrect()
    {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (map.get(key).getSurname().matches(".*[1-9].*"))
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
            Student student = new Student().readStudent(path,scanner);
            map.put(student.getId(), student);
        }
    }
}
