package com.exercises.ex8;

public class SimpleNumbers {
    private int x;
    public static boolean isSimple(int value)
    {
        int count = 0;
        for(int i = 2;i < value-1;i++)
        {
            if(value%i==0)
                count++;
        }
        if (count == 0 )
            return true;
        else return false;
    }
}
