/**
 * Tetris
 * Created by fpm.kazachin on 03.12.2018 9:28
 */

public class LetterL
{
    private int[] x;
    private int[] y;
    private final int SIZE = 5;
    public LetterL()
    {
        x = new int[SIZE];
        y = new int[SIZE];
        x[0] = 176;
        y[0] = 48;

        x[1] = 160;
        y[1] = 48;

        x[2] = 144;
        y[2] = 48;

        x[3] = 144;
        y[3] = 64;

        x[4] = 144;
        y[4] = 80;
    }
    public void turn(int k)
    {
        if(k == 1)
        {
            y[0] += 32;
            x[0] -= 32;

            x[1] -= 16;
            y[1] += 16;

            x[3] -= 16;
            y[3] -= 16;


            x[4] -= 32;
            y[4] -= 32;
        }
        if(k == 2)
        {
            y[0] -= 32;
            x[0] -= 32;

            x[1] -= 16;
            y[1] -= 16;

            x[3] += 16;
            y[3] -= 16;

            x[4] += 32;
            y[4] -= 32;
        }
        if(k == 3)
        {
            y[0] -= 32;
            x[0] += 32;

            x[1] += 16;
            y[1] -= 16;

            x[3] += 16;
            y[3] += 16;

            x[4] += 32;
            y[4] += 32;
        }
        if(k == 4)
        {
            y[0] += 32;
            x[0] += 32;

            x[1] += 16;
            y[1] += 16;

            x[3] -= 16;
            y[3] += 16;

            x[4] -= 32;
            y[4] += 32;
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
