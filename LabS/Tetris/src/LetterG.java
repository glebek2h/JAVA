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
        if(k%2==0)
        {
            x[0] -= 32;
            y[0] -= 32;

            x[1] -= 32;

            x[3] += 32;

            x[4] += 64;
        }
        else
        {
            x[0] += 32;
            y[0] += 32;

            x[1] += 32;

            x[3] -= 32;

            x[4] -= 64;
        }
    }
    public void move(boolean left,boolean right)
    {
        for (int i = 0; i < SIZE; i++)
        {
            y[i] += 16;
        }
        if (left)
        {
            for (int i = 0; i < SIZE; i++)
            {
                x[i] -= 16;
            }
        }
        if (right)
        {
            for (int i = 0; i < SIZE; i++)
            {
                x[i] += 16;
            }
        }
    }

    public int[] getY()
    {
        return y;
    }

    public int[] getX()
    {

        return x;
    }
}
