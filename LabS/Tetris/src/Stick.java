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
                if (k % 2 == 0)
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
                if (k % 2 == 0)
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
}
