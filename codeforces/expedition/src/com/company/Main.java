package com.company;

public class Main
{
    private static void printArr(int [] arrCount)
    {
        System.out.println();
        for (int i = 0; i < arrCount.length; i++)
        {
            System.out.print(arrCount[i] + " ");
        }
    }
    private static void bubbleSort(int [] types)
    {
        for (int i = 0; i < types.length; i++)
        {
            for (int j = types.length - 1 ; j > i; j--)
            {
                if(types[i] > types[j])
                {
                    int tmp = types [i];
                    types[i] = types[j];
                    types[j]=tmp;
                }
            }

        }
    }
    private static int[] deletePovt(int [] types)
    {

        int count = 1, i = 0,k = 0,s = 1;
        for (int j = 0; j < types.length; j++)
        {
            if (types[i] != types[j])
            {
                count++;//хранит кол-во различных типов
                i = j;
            }
        }// 1 1 1 1 2 2 2 5 5 7
        i = 0;
        int[] resultArr = new int[count];
        resultArr[0]=types[0];
        for (int j = 0; j < types.length; j++)
        {
            if (types[i] != types[j])
           {
               resultArr[s]=types[j];
               s++;
               i=j;
           }
       }
       return resultArr;
    }
    public static void main(String[] args)
    {
        int n = 4;
        int m = 10;
        int k = 0;
        int[] types = {1, 5, 2, 1, 1, 1, 2, 5, 7, 2};
        int[] arrCount = new int [m];
        bubbleSort(types);
        printArr(types);
        for (int i = 0; i < types.length; i++)
        {
            for (int j = 0; j < types.length; j++)
            {
                if(types[i]==types[j])
                {
                    arrCount[k]++;
                }
            }
            k++;
        }
        printArr(arrCount);

        int[] newTypes = deletePovt(types);
        printArr(newTypes);
        int[] newCount = deletePovt(arrCount);
        printArr(newCount);

    }
}
