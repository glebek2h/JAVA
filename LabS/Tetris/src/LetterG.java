import java.util.ArrayList;

/**
 * Tetris
 * Created by fpm.kazachin on 03.12.2018 9:00
 */

public class LetterG
{
    private int[] x;
    private int[] y;
    private final int SIZE = 5;
    public LetterG()
    {
        x = new int[SIZE];
        y = new int[SIZE];
        x[0] = 176;
        y[0] = 64;
        for (int i = 1; i < SIZE; i++)
        {
            x[i] = 176 - (i - 1) * 16;
            y[i] = 48;
        }
    }
    public void turn(int k)
    {
        if(k == 1)
        {
            x[0] -= 32;

            x[1] -= 16;
            y[1] += 16;

            x[3] += 16;
            y[3] -= 16;


            x[4] += 32;
            y[4] -= 32;
        }
        if(k == 2)
        {
            y[0] -= 32;

            x[1] -= 16;
            y[1] -= 16;

            x[3] += 16;
            y[3] += 16;

            x[4] += 32;
            y[4] += 32;
        }
        if (k==3)
        {
            x[0]+= 32;

            x[1] +=16;
            y[1] -=16;

            x[3] -= 16;
            y[3] += 16;

            x[4] -= 32;
            y[4] += 32;
        }
        if(k == 4)
        {
            y[0] += 32;

            x[1] += 16;
            y[1] += 16;

            x[3] -= 16;
            y[3] -= 16;

            x[4] -= 32;
            y[4] -= 32;
        }
    }
    public void move(boolean left,boolean right,boolean down)
    {
        for (int i = 0; i < SIZE; i++)
        {
            y[i] += 16;
        }
        if (left && x[0] != 0 && x[x.length - 1] != 0)
        {
            for (int i = 0; i < SIZE; i++)
            {
                x[i] -= 16;
            }
        }
        if (right && x[0] != 304 && x[x.length - 1] != 304)
        {
            for (int i = 0; i < SIZE; i++)
            {
                x[i] += 16;
            }
        }
        if(down)
            for (int i = 0; i < SIZE; i++)
            {
                y[i] += 16;
            }

    }
    public static ArrayList<Integer> toArayList(int[] arr)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++)
        {
            arrayList.add(arr[i]);
        }
        return arrayList;
    }

    public ArrayList<Integer> getY()
    {
        return toArayList(y);
    }

    public ArrayList<Integer> getX()
    {

        return toArayList(x);
    }
}
