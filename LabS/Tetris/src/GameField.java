import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Tetris
 * Created by fpm.kazachin on 19.11.2018 8:42
 */

public class GameField extends JPanel implements ActionListener
{
    private final int SIZE = 320;
    private final int PIXEL_SIZE = 16;
    private final int TOTAL_PIXELS = 400;
    private final int MAX_SQUARES = 5;
    private Image square;
    private Image back;
    private Image gameover;
    private int[] squaresX;
    private int[] squaresY;
    private ArrayList<int[]> arrayX;
    private ArrayList<int[]> arrayY;
    private int NUM = 0;

    private int countSquares;
    private Timer timer;
    private boolean left;
    private boolean right;
    private boolean getEnd;
    private boolean gameOver;
    private int count = 0;
    private int globalCount = 0;
    private int checkY = 304;

    public GameField()
    {

        arrayX = new ArrayList<>();
        arrayY = new ArrayList<>();
        squaresX = new int[MAX_SQUARES];
        squaresY = new int[MAX_SQUARES];

        ImageIcon ImageIconSquare = new ImageIcon("square.png");
        square = ImageIconSquare.getImage();
        ImageIcon ImageIconBack = new ImageIcon("back.png");
        back = ImageIconBack.getImage();
        ImageIcon ImageIconGameover = new ImageIcon("gameover.png");
        gameover = ImageIconGameover.getImage();

        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initGame()
    {
        globalCount++;
        System.out.println("GLOBAL_COUNT" + globalCount);
        countSquares = 5;
        //160+16+16
        for (int i = 0; i < countSquares; i++)
        {
            squaresX[i] = 176 - i * PIXEL_SIZE;
            squaresY[i] = 48;
        }
        timer = new Timer(300, this);
        timer.start();
    }

    public void move()
    {
        for (int i = 0; i < countSquares; i++)
        {
            squaresY[i] += 16;
        }
        if (left)
        {
            //if(squaresX[countSquares]!=16 ||squaresX[0] != 16)
            for (int i = 0; i < countSquares; i++)
            {
                squaresX[i] -= 16;
            }
        }
        if (right)
        {
            //if(squaresX[0]!=304 || squaresX[countSquares]!=304)
            for (int i = 0; i < countSquares; i++)
            {
                squaresX[i] += 16;
            }
        }
        right = false;
        left = false;
        System.out.println(Arrays.toString(squaresY));
    }
    public void check()
    {
        for (int count = 0; count < arrayY.size(); count++)
        {
            for (int i = 0; i < countSquares; i++)
            {
                for (int j = 0; j < countSquares; j++)
                {
                    if (squaresY[i] == arrayY.get(count)[j] - 16 && squaresX[i] == arrayX.get(count)[j])
                        getEnd = true;
                }
            }
        }
        for (int i = 0; i < countSquares; i++)
        {
            if (squaresY[i] == 36)
                gameOver = true;
        }
        if (getEnd == true)
        {
            for (int i = 0; i < countSquares; i++)
            {
                squaresY[i] -= 16;
            }
        }
        if (squaresY[0] == 288 || squaresY[countSquares - 1] == 288)
        {
            getEnd = true;
        }
        int count;
        for (int y = checkY; y >= 48; )
        {
            count = 0;
            for (int i = 0; i < arrayY.size(); i++)
            {
                for (int j = 0; j < countSquares; j++)
                {
                    if (arrayY.get(i)[j] == y)
                        count++;
                }
            }
            if (count == 20)
            {
                for (int i = 0; i < arrayY.size(); i++)
                {

                    int temp[] = new int[countSquares];
                    for (int j = 0; j < countSquares; j++)
                    {
                        temp[j] = arrayY.get(i)[j] + 16;
                    }
                    arrayY.set(i, temp);
                }
                checkY-=16;
            }
            y -= 16;
        }
    }
     public void turn(int k)
     {
         System.out.println("ДО\n" + "X: " + Arrays.toString(squaresX) + " Y: " + Arrays.toString(squaresY));
         int count = countSquares / 2;
         if (globalCount % 2 == 1)
             for (int i = 0; i < countSquares; i++)
             {
                 if (i < countSquares / 2)
                 {
                     if (k % 2 == 0)
                     {
                         squaresX[i] -= count * 16;
                         squaresY[i] += count * 16;
                         count--;
                     } else
                     {
                         squaresX[i] += count * 16;
                         squaresY[i] -= count * 16;
                         count--;
                     }
                 }
                 if (i > countSquares / 2)
                 {
                     if (k % 2 == 0)
                     {
                         count++;
                         squaresX[i] += count * 16;
                         squaresY[i] -= count * 16;
                     } else
                     {
                         count++;
                         squaresX[i] -= count * 16;
                         squaresY[i] += count * 16;
                     }
                 }
             }
         if (globalCount % 2 == 0)
         {

             if(k%2==0)
             {
                 squaresX[0] -= 32;
                 squaresY[0] -= 32;

                 squaresX[1] -= 32;

                 squaresX[3] += 32;

                 squaresX[4] += 64;
                 //squaresY[4] += 16 ;
             }
             else
             {
                 squaresX[0] += 32;
                 squaresY[0] += 32;

                 squaresX[1] += 32;

                 squaresX[3] -= 32;

                 squaresX[4] -= 64;
             }

         }
         int tmp = 0;


     }
         //System.out.println("ПОСЛЕ\n"+"X: "+ Arrays.toString(squaresX)+" Y: "+ Arrays.toString(squaresY));
    public void addNewFigure()
    {

        arrayY.add(Arrays.copyOf(squaresY, squaresY.length));
        arrayX.add(Arrays.copyOf(squaresX, squaresX.length));
        for (int i = 0; i < countSquares; i++)
        {
            if (squaresY[i] == 48)
                gameOver = true;
        }


        if (globalCount % 2 == 0)
        {
            for (int i = 0; i < countSquares; i++)
            {
                squaresX[i] = 176 - i * PIXEL_SIZE;
                squaresY[i] = 48;
            }
        }
        if (globalCount % 2 == 1)
        {
            squaresX[0] = 176;
            squaresY[0] = 64;
            for (int i = 1; i < countSquares; i++)
            {
                squaresX[i] = 176 - (i - 1) * PIXEL_SIZE;
                squaresY[i] = 48;
            }
        }

        count = 0;
        getEnd = false;
        globalCount++;
        System.out.println("GLOBAL_COUNT" + globalCount);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
            if (getEnd == false && gameOver == false)
            {
                check();
                move();
            } else if(gameOver == false)
                addNewFigure();
            repaint();
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(gameOver == false)
        {
            g.drawImage(back, 0, 0, this);
            for (int i = 0; i < countSquares; i++)
            {
                g.drawImage(square, squaresX[i], squaresY[i], this);
            }

            for (int i = 0; i < arrayY.size(); i++)
            {
                for (int j = 0; j < countSquares; j++)
                {
                    g.drawImage(square, arrayX.get(i)[j], arrayY.get(i)[j], this);
                }
            }
        }
        else g.drawImage(gameover, 0, 0, this);
    }
    class FieldKeyListener extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT)
            {
                left = true;
                right = false;
            }
            if (key == KeyEvent.VK_RIGHT )
            {
                right = true;
                left = false;
            }
            if(key == KeyEvent.VK_SPACE)
            {
                turn(count);
                count++;
            }
        }
    }
}

