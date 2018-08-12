package com.company;

import java.security.MessageDigest;

public class Main {
    public static int leapYearCount(int year) {
        int count = 0;
        while(year>0)
        {
            if((year%4==0&&(year%100)!=0)||(year%400==0))
                count++;
            year--;
        }
        return count;
    }
    public static void main(String[] args)
    {
        System.out.println(leapYearCount(100));

    }

}