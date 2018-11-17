package com.company;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 Разбить первую строку на лексемы (используя разделители из  второй строки), определить в ней целые числа
 Числа записать в новый отдельные массив. Над числами  из массива выполнить операцию (^, модуль),  в произвольной форме,
 введённую в третьей строке. Среди лексем не являющихся числами, найти лексемы состоящие только из знаков препинания.
 Найти число Р. Добавить  в строку случайное число, добавить его после числа Р или перед первым целым числом строки.
 Последнее целое число < числа Р - удалить из строки.  Все результаты вывести.
 */
public class Text
{
    private String text;

    public static void checkThirdLineOperation(String line,int marker)
    {
        CharSequence charSequence1 = "abs";
        if (line.contains(charSequence1))
            marker = 1;
        CharSequence charSequence2 = "sqrt";
        if (line.contains(charSequence2))
            marker = 2;
        CharSequence charSequence3 = "pow";
        if (line.contains(charSequence3))
            marker = 3;
    }
    public static int[] firstLineFunction(String firstLine,String result,int P,FileWriter fileWriter) throws Exception//result - разделители второй строки
    {
        int count = 0;
        StringTokenizer stringTokenizer = new StringTokenizer(firstLine,result);
        while(stringTokenizer.hasMoreTokens())
        {
            String retval = stringTokenizer.nextToken();
            if ( retval.matches("^-?[1-9][0-9]$"))//проверка на десятичное число
            {
                count++;
            }
        }
        int[] numbersArray = new int[count + 1];
        int i = 0;
        //System.out.println("Числа 1-ой строки :");
        fileWriter.write("\nЧисла 1-ой строки :");
        for (String retval : firstLine.split("[" + result + "]+"))
        {
            if (retval.matches("^-?[1-9][0-9]*$"))//проверка на десятичное число
            {
                numbersArray[i] = Integer.parseInt(retval);
                //System.out.print(numbersArray[i] + " ");
                fileWriter.write(numbersArray[i] + " ");
                i++;
            }
        }
        for (i = numbersArray.length - 1; i > 0 ; i--)
        {
            if(numbersArray[i]<P)
            {
                String string = Integer.toString(numbersArray[i]);
                firstLine = firstLine.replace(string, "");
                break;
            }
        }
       // System.out.println("\nУдалили последний элемент < P");
        fileWriter.write("\nУдалили последний элемент < P :");
        //System.out.println(firstLine);
        fileWriter.write(firstLine);
        return numbersArray;
    }
    public static void operationToNumberArray(int[] numbersArray,int marker,FileWriter fileWriter) throws Exception
    {
        //System.out.println("Числа 1-ой строки,после применения операции 3-ей строки :");
        fileWriter.write("\nЧисла 1-ой строки,после применения операции 3-ей строки :");
        for (int i = 0; i < numbersArray.length; i++)
        {
            if (marker == 1)
                numbersArray[i] = Math.abs(numbersArray[i]);
            if (marker == 2)
            {
                numbersArray[i] = Math.abs(numbersArray[i]);
                numbersArray[i] = (int)Math.sqrt(numbersArray[i]);
            }
            if (marker == 3)
                numbersArray[i] = (int)Math.pow(numbersArray[i],10);
            //System.out.print(numbersArray[i]+ " ");
            fileWriter.write(numbersArray[i]+ " ");
        }
    }
    public static String AddRandAfterP(String string,int P,FileWriter fileWriter) throws Exception//добавляет случайное число после найденного Р
    {
        String p = Integer.toString(P);
        int count = 0, index = 0, j = 0;
        for (int i = 0; i < string.length(); i++)
        {
            j = 0;
            while (string.charAt(i) == p.charAt(j))
            {
                j++;
                i++;
                index = i;
                if (j == p.length())
                {
                    //System.out.println("Добавили случайное число после Р:");
                    fileWriter.write("Добавили случайное число после Р:");
                    StringBuffer stringBuffer = new StringBuffer(string);
                    Random random = new Random();
                    string = stringBuffer.insert(index, random.nextInt(25)).toString();
                   // System.out.println(string);
                    fileWriter.write(string);
                    return string;
                }
            }
        }
        return string;

    }
    public static boolean isPunctuationWord(String s,String strings,int a,int b)//является ли слово словом из знаков
    {
        String word = s.subSequence(a,b).toString();//Слово между пробелами
        int count = 0;
        for (int i = 0; i < word.length(); i++)
        {
            for (int j = 0; j < strings.length(); j++)
            {
                if (word.charAt(i) == strings.charAt(j))
                {
                    count++;
                }
            }
        }
        if(count == word.length())
            return true;
        return false;
    }
    public static String bestFunction(String s)
    {
        String[] strings = {",",":",";",".","!"," "};
        String result = new String();
        int count;
        for (int i = 0; i < strings.length ; i++)
        {
            count = 0;
            for (int j = 0; j < s.length(); j++)
            {
                if (strings[i].charAt(0) == s.charAt(j))
                    count++;
            }
            if (count == 0)
                result += strings[i];
        }

        return result;
    }
    public static void coutPunctuationWords(String line,String s,FileWriter fileWriter)throws Exception
    {
        //System.out.println("\nСлова первой строки,состоящие только из знаков препинания :");
        fileWriter.write("\nСлова первой строки,состоящие только из знаков препинания :");
        int firstSpace = 0, secondSpace;
        int count = 0;
        for (int i = 0; i < line.length(); i++)
        {
            if(line.charAt(i)==' ')
            {
                if(count == 0)
                {
                    firstSpace = i;
                    count++;
                }
                else
                {
                    secondSpace = i;
                    if(isPunctuationWord(line,bestFunction(s),firstSpace+1,secondSpace))
                    {
                        fileWriter.write("[" + line.subSequence(firstSpace + 1, secondSpace).toString() + "]" + " ");
                        //System.out.print("[" + line.subSequence(firstSpace + 1, secondSpace).toString() + "]" + " ");
                    }
                    firstSpace = secondSpace;
                }
            }
        }
    }
    public void readFromFile(int P)
    {
        try
        {
            String s = new String();
            int count = 0;
            BufferedReader bufferedReader = new BufferedReader(new FileReader("files//Input.txt"));
            FileReader fileReader2 = new FileReader("files//Input.txt");
            FileWriter fileWriter = new FileWriter("files//Output.txt");
            Scanner scanner = new Scanner(fileReader2);
            String firstLine = new String();
            int marker = 1;
            while (bufferedReader.read()!=-1)//пока сканер не дойдёт до конца файла
            {
                System.out.println(bufferedReader.readLine());
            }
            System.out.println("-------------------------------------------------------");
            while (scanner.hasNext())//пока сканер не дойдёт до конца файла
            {
                text = scanner.nextLine();
               // text = AddRandAfterP(text, P,fileWriter);
                if (count == 0)
                    firstLine = AddRandAfterP(text, P,fileWriter);
                if (count == 1)
                    s = text;
                if (count == 2)
                    checkThirdLineOperation(text, marker);
                count++;
            }
            int [] numbersArray = firstLineFunction(firstLine,s,P,fileWriter);//получаем массив чисел первой строки и удалили последнее число <P
            operationToNumberArray(numbersArray,marker,fileWriter);//применяем операцию из 3-ей строки к массиву чисел
            coutPunctuationWords(firstLine,s,fileWriter);
            bufferedReader.close();
            fileReader2.close();
            fileWriter.close();
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error ¯\\_(ツ)_/¯");
        }
    }
}
