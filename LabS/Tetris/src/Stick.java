import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tetris
 * Created by fpm.kazachin on 01.12.2018 23:11
 */

public class Stick
{
    private int[] x;
    private int[] y;
    private final int SIZE = 5;
    public Stick()
    {
        x = new int[SIZE];
        y = new int[SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            x[i] = 176 - i * 16;
            y[i] = 48;
        }
    }
     public void turn(int k)
    {
        int count = SIZE / 2;
        for (int i = 0; i < 5; i++)
        {
            if (i < SIZE / 2)
            {
                if (k % 2 == 1)
                {
                    x[i] -= count * 16;
                    y[i] += count * 16;
                    count--;
                } else
                {
                    x[i] += count * 16;
                    y[i] -= count * 16;
                    count--;
                }
            }
            if (i > SIZE / 2)
            {
                if (k % 2 == 1)
                {
                    count++;
                    x[i] += count * 16;
                    y[i] -= count * 16;
                } else
                {
                    count++;
                    x[i] -= count * 16;
                    y[i] += count * 16;
                }
            }
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
        if (down && y[0] != 304 && y[y.length - 1] != 304)
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
