package com.exercises.ex1;

import java.util.Scanner;
import com.exercises.ex8.SimpleNumbers;
public class Numbers {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        //if(x%2==0)
         //   System.out.println("Чётное число");
        //else System.out.println("Нечётное число");
        if(SimpleNumbers.isSimple(x) == true)
            System.out.println("Простое число");
        else System.out.println("Не простое число");

    }
}
