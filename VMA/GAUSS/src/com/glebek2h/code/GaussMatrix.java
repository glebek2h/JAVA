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
                if(matrix[i][j] < 0)
                    System.out.print(matrix[i][j] + " ");
                else System.out.print(matrix[i][j] + "  ");
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
        // ищет строку с первым элементом единицей для наилучшего применения алгоритма
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
                for (int columns = count; columns < m; columns++)
                {
                    matrix[rows][columns] -= matrix[count][columns] * ratio;
                }
                printMatrix();
            }
            count++;
        }
        /*
        ----
        --Сокращает каждую строчку после приведения к ступенчатому виду--
        ----
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
        */

        double[] answer = new double[m - 1];
        double chislitel;
        for (int i = 0; i < m - 1; i++)
            answer[i] = 0;
        int eshkere = m - 2;
        if(eshkere+1==n)
        {
            System.out.println("Answer:");
            for (int i = n - 1; i >= 0 && eshkere >= 0; i--)
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
                System.out.print("x" + i + " = " + answer[i] + " ; ");
            }
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

    public void LUmatrixs() // 1 0 0
    // 0 1 0
    // 0 0 1
    {

        int count = 0, a = 0;
        GaussMatrix gaussMatrix = new GaussMatrix(n, m * 2  );
        for (int rows = 0; rows < n; rows++) {
            count = 0;
            for (int columns = 0; columns < m * 2 - 1; columns++) {
                if (columns < m)
                    gaussMatrix.matrix[rows][columns] = matrix[rows][columns];
                else {
                    if (count == a) {
                        gaussMatrix.matrix[rows][columns] = 1;

                    } else {
                        gaussMatrix.matrix[rows][columns] = 0;
                    }
                    count++;

                }
            }
            a++;
        }
        System.out.println("Добавляем единичную матрицу:");
        gaussMatrix.printMatrix();
        gaussMatrix.methodGaussa();

        GaussMatrix Umatrix = new GaussMatrix(n, m);
        GaussMatrix L0matrix = new GaussMatrix(n, m);
        for (int i = 0, rows = 0; i < n; i++, rows++) {
            for (int j = 0, columns = 0; j < m * 2; j++) {
                if (j < m)
                    Umatrix.matrix[i][j] = gaussMatrix.matrix[i][j];
                else {
                    L0matrix.matrix[rows][columns] = gaussMatrix.matrix[i][j];
                    columns++;
                }
            }
        }
        System.out.println("U-matrix :");
        Umatrix.printMatrix();

        System.out.println("L0-matrix(обратная к L- матрице,которую ещё нужно найти) :");
        L0matrix.printMatrix();

        System.out.println("Находим L:");
        int count2 = 0, a2 = 0;
        GaussMatrix gaussMatrix2 = new GaussMatrix(n, m * 2);
        for (int rows = 0; rows < n; rows++) {
            count2 = 0;
            for (int columns = 0; columns < m * 2; columns++) {
                if (columns < m)
                    gaussMatrix2.matrix[rows][columns] = L0matrix.matrix[rows][columns];
                else {
                    if (count2 == a2) {
                        gaussMatrix2.matrix[rows][columns] = 1;

                    } else {
                        gaussMatrix2.matrix[rows][columns] = 0;
                    }
                    count2++;

                }
            }
            a2++;
        }
        gaussMatrix2.methodGaussa();
        GaussMatrix Lmatrix = new GaussMatrix(n, m);
        for (int i = 0, rows = 0; i < n; i++, rows++) {
            for (int j = 0, columns = 0; j < m * 2; j++) {
                if (j >= m)
                {
                    Lmatrix.matrix[rows][columns] = gaussMatrix2.matrix[i][j];
                    columns++;
                }
            }
        }
        System.out.println("L-матрица!");
        Lmatrix.printMatrix();
    }


    public static void main(String[] args)
    {
        GaussMatrix gaussMatrix = new GaussMatrix(3, 3);
        int[] arr = { 1, 2, 3, 4, 3,
                      4, 5, 6, 3, 3,
                      7, 8,9, 4, 5,
                      3, 4, 5, 5, 6 };
        int[] arr2 = {  1, 2, 3,
                        4, 5, 6,
                        7,8, 9  };
        gaussMatrix.setMatrix(arr2);

        System.out.println("matrix:");
        gaussMatrix.printMatrix();

        //System.out.println("methodGaussa:");
        //gaussMatrix.methodGaussa();

        gaussMatrix.LUmatrixs();
        //System.out.println("methodGaussaJordana:");
        //gaussMatrix.methodGaussaJordana();


    }
}