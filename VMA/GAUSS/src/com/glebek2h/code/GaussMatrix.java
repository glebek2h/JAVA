package com.glebek2h.code;

public class GaussMatrix
{
    private double[][] matrix;
    private int n, m;

    GaussMatrix(int rows, int columns)
    {
        n = rows;
        m = columns;
        matrix = new double[n][m];
    }

    GaussMatrix(GaussMatrix gaussMatrix)
    {
        n = gaussMatrix.n;
        m = gaussMatrix.m;
        matrix = new double[n][m];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                matrix[i][j] = gaussMatrix.matrix[i][j];
            }
        }
    }

    public void printMatrix()
    {

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }

    public boolean setMatrix(int[] arr)
    {
        if (arr.length != (n * m))
            return false;
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                matrix[i][j] = arr[count];
                count++;
            }
        }
        return true;
    }

    public int checkOne()
    {
        for (int i = 0; i < n; i++)
        {
            if (matrix[i][0] == 1)
            {
                return i;
            }
        }
        return 99;
    }

    public int checkNoZero()
    {
        for (int i = 0; i < n; i++)
        {
            if (matrix[i][0] != 0)
            {
                return i;
            }
        }
        return 99;
    }

    public void swaprows(int q, int w)
    {
        for (int i = 0; i < m; i++)
        {
            double tmp = matrix[q][i];
            matrix[q][i] = matrix[w][i];
            matrix[w][i] = tmp;
        }
    }

    public void methodGaussa()
    {
        int k = checkOne();
        if (k != 99)
            swaprows(k, 0);
        printMatrix();
        int count = 0;
        for (int t = 1; t < n; t++)
        {
            for (int rows = t; rows < n; rows++)
            {
                double ratio = matrix[rows][count] / matrix[count][count];
                for (int columns = count; columns <= n; columns++)
                {
                    matrix[rows][columns] -= matrix[count][columns] * ratio;
                }
                printMatrix();
            }
            count++;
        }
        int u = 0;
        for (int i = 0; i < n; i++)
        {
            double p = matrix[u][u];
            for (int j = 0; j < m; j++)
            {
                matrix[i][j] /= p;
            }
            u++;
        }
        printMatrix();
        System.out.println("Answer:");
        double[] answer = new double[m - 1];
        double chislitel;
        for (int i = 0; i < m - 1; i++)
            answer[i] = 0;
        int eshkere = m - 2;
        for (int i = n - 1; i >= 0 ; i--)
        {
            if (eshkere == m - 2)
            {
                answer[eshkere] = matrix[i][m - 1] / matrix[i][m - 2];
                eshkere--;
            } else
            {
                chislitel = matrix[i][m - 1];
                for (int j = m - 2; j >= 0; j--)
                {
                    chislitel -= matrix[i][j] * answer[j];
                }
                answer[eshkere] = chislitel / matrix[i][eshkere];
                eshkere--;
            }
        }
        for (int i = 0; i <= m - 2; i++)
        {
            System.out.print("x"+i+" = "+answer[i]+" ; ");
        }
    }

        public void methodGaussaJordana ()
        {
            int k = checkNoZero();
            if (k != 99)
                swaprows(k, n - 1);
            int count = m - 1;
            int count2 = n - 1;
            for (int t = n - 1; t >= 1; t--)
            {
                for (int rows = t; rows >= 1; rows--)
                {
                    double ratio = matrix[rows - 1][count] / matrix[count2][count];
                    for (int columns = count; columns > 0; columns--)
                    {
                        matrix[rows - 1][columns] -= matrix[count2][columns] * ratio;
                    }
                    printMatrix();
                }
                count2--;
                count--;
            }
            double p = matrix[n - 1][m - 1];
            for (int j = 0; j < m; j++)
            {
                matrix[n - 1][j] /= p;
            }
            printMatrix();

        }


    public static void main(String[] args)
    {
        GaussMatrix gaussMatrix = new GaussMatrix(4, 5);
        int[] arr = { 2, 5, 4, 1, 20,
                      1, 3, 2, 1, 11,
                      2, 10,9, 7, 40,
                      3, 8, 9, 2, 37 };
        int[] arr2 = {   8, 7, 3,  18
                        -7,-4,-4, -11
                        -6, 5,-4 ,-15  };
        gaussMatrix.setMatrix(arr);
        System.out.println("matrix:");
        gaussMatrix.printMatrix();
        System.out.println("methodGaussa:");
        gaussMatrix.methodGaussa();
        /*System.out.println("methodGaussaJordana:");
        gaussMatrix.methodGaussaJordana();*/


    }
}
