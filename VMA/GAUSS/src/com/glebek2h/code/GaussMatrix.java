package com.glebek2h.code;

import java.math.BigDecimal;
import java.util.Arrays;

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
    public double[][] getMatrix() { return matrix; }
    public static void printMatrix(double [][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {

                System.out.printf("%.4f",matrix[i][j]);
                System.out.print("   ");

            }
            System.out.println();
        }
        System.out.println("-----------");
    }
    public boolean setMatrix(double[] arr)
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
    public static void swaprows(int q, int w,double [][] matrix)
    {
        for (int i = 0; i < matrix[0].length; i++)
        {
            double tmp = matrix[q][i];
            matrix[q][i] = matrix[w][i];
            matrix[w][i] = tmp;
        }
    }
    public static double selectTheMainInColumn(int count,double [][] matrix)
    {
        double max = matrix[count][count];
        int i = 0;
        for (int k = count; k < matrix.length; k++)
        {
            if (Math.abs(matrix[k][count]) >= max)
            {
                max = Math.abs(matrix[k][count]);
                i = k;
            }
        }
        swaprows(count, i, matrix);

        max = matrix[count][count];
        for (int j = 0; j < matrix[0].length; j++)
        {
            matrix[count][j] /= max;
        }
        if (count != i)
            max = -max;
        return max;
    }
    public double[] methodGaussa()
    {
        double[][] gaussMatrix = matrix;
        int count = 0;
        double detA = 1;
        for (int t = 1; t <= n; t++)
        {
            detA *= selectTheMainInColumn(count, gaussMatrix);
            for (int rows = t; rows < n; rows++)
            {
                double ratio = gaussMatrix[rows][count];
                for (int columns = count; columns < m; columns++)
                    gaussMatrix[rows][columns] -= gaussMatrix[count][columns] * ratio;
                printMatrix(gaussMatrix);
            }
            count++;
        }
        printMatrix(gaussMatrix);
        double[] answer = new double[m - 1];
        answer[m - 2] = gaussMatrix[n - 1][m - 1];// 4 4 5
        double sum;
        for (int i = n - 2; i > -1; i--)
        {
            sum = 0;
            for (int j = i + 1; j < n; j++)
            {
                sum += gaussMatrix[i][j] * answer[j];
            }
            answer[i] = gaussMatrix[i][m - 1] - sum;
        }
        System.out.println("Answer:");
        for (int i = 0; i <= m - 2; i++)
        {
            System.out.print("x" + i + " = " );System.out.printf("%.6f" ,answer[i]); System.out.print( " ; ");
        }
        System.out.println("\n-----------");
        System.out.println("det A: " + '\n' + detA);
        System.out.println("-----------");
        return answer;
    }
    public static double[] reverseMove(GaussMatrix G)
    {
        double[] answer = new double[G.m - 1];
        answer[G.m - 2] = G.matrix[G.n - 1][G.m - 1];// 4 4 5
        double sum;
        for (int i = G.n - 2; i > -1; i--)
        {
            sum = 0;
            for (int j = i + 1; j < G.n; j++)
            {
                sum += G.matrix[i][j] * answer[j];
            }
            answer[i] = G.matrix[i][G.m - 1] - sum;
        }
        return answer;
    }
    public void residualVector(double [] answer)
    {
        double [] resuidal = new double [m -1];
        int count = 0;
        double sum = 0;
        for (int i = 0; i < n ; i++)
        {
            sum = 0;
            for (int j = 0; j  < answer.length ; j ++)
            {
                sum +=matrix[i][j]*answer[j];
            }
            resuidal[count] = matrix[i][m-1] - sum;
            count++;
        }
        System.out.println("Вектор невязки А(х*) - b :");
        for (int i = 0; i < resuidal.length; i++)
        {
            System.out.print("r" + i + " = " );System.out.printf("%e" ,resuidal[i]); System.out.print( " ; ");
        }
        double max = Math.abs(resuidal[0]);
        for (int i = 1; i < resuidal.length ; i++)
        {
            if(Math.abs(resuidal[i])>max)
                max = Math.abs(resuidal[i]);
        }
        System.out.println("Норма вектора невязки :" + max);
        System.out.println("\n-----------");
    }
    /*public void residualMatrix(GaussMatrix Matrix)
    {
        System.out.println("A^-1 :");
        printMatrix(Avminus1.matrix);
        System.out.println("R = A*A^-1 - Е :");
        multiplyMatrixMinysE(A.matrix, Avminus1.matrix);
        System.out.println("-----------");

        double normA = 0,sum;
        for (int i = 0; i < A.n; i++)
        {
            sum = 0;
            for (int j = 0; j < A.m; j++)
                sum += Math.abs(A.matrix[i][j]);
            if (sum >= normA)
                normA = sum;
        }
        double normAvminys1 = 0;
        for (int i = 0; i < Avminus1.n; i++)
        {
            sum = 0;
            for (int j = 0; j < Avminus1.m; j++)
                sum += Math.abs(Avminus1.matrix[i][j]);
                if (sum >= normAvminys1)
                    normAvminys1 = sum;
        }
        System.out.println("Число обусловленности матрицы А = ||A|| * ||A^-1|| :" + '\n' + normA*normAvminys1);
        System.out.println("-----------");
    }*/
    public static void resuidalMatrix(GaussMatrix initialMatrix)
    {
        GaussMatrix A = new GaussMatrix(initialMatrix.n, initialMatrix.m - 1);
        for (int i = 0; i < initialMatrix.n; i++)
        {
            for (int j = 0; j < initialMatrix.m - 1; j++)
            {
                A.matrix[i][j] = initialMatrix.matrix[i][j];
            }
        }
        System.out.println("A :");
        printMatrix(A.matrix);

       // double[][] column = { {1,0,0,0,0}, {0,1,0,0,0},{0,0,1,0,0},{0,0,0,1,0},{0,0,0,0,1}};
        double [][]column = {{1,0,0},{0,1,0},{0,0,1}};

        GaussMatrix [] gaussMatrices = new GaussMatrix[column.length];
        for (int i = 0; i < gaussMatrices.length; i++)
            gaussMatrices[i] = new GaussMatrix(A.n, A.m + 1);
        for (int count = 0; count < gaussMatrices.length; count++)
        {
            for (int i = 0; i < gaussMatrices.length ; i++)
            {
                for (int j = 0; j < gaussMatrices.length + 1; j++)
                {
                    if(j < gaussMatrices.length)
                    gaussMatrices[count].matrix[i][j] = initialMatrix.matrix[i][j];
                    else
                    {
                        for (int k = 0; k < column.length ; k++)
                        {
                            gaussMatrices[count].matrix[k][j] = column[k][count];
                        }
                    }
                }
            }
            gaussMatrices[count].methodGaussa();
            printMatrix(gaussMatrices[count].matrix);
        }

        double[][] inverseA = new double[A.n][A.n];
        for (int count = 0; count < gaussMatrices.length; count++)
        {
            double [] vector = reverseMove(gaussMatrices[count]);
            for (int row = 0; row < gaussMatrices.length; row++)
            {
                inverseA[row][count] = vector[row];
            }
        }
        System.out.println("A^-1 :");
        printMatrix(inverseA);


        System.out.println();
        System.out.println("R = A*A^-1 - Е :");
        double[][] R = multiplyMatrixMinysE(A.matrix,inverseA);
        System.out.println("------------------");

        double normA = 0,sum;
        for (int i = 0; i < A.n; i++)
        {
            sum = 0;
            for (int j = 0; j < A.m; j++)
                sum += Math.abs(A.matrix[i][j]);
            if (sum >= normA)
                normA = sum;
        }
        double normAvminys1 = 0;
        for (int i = 0; i < A.n; i++)
        {
            sum = 0;
            for (int j = 0; j < A.m; j++)
                sum += Math.abs(inverseA[i][j]);
            if (sum >= normAvminys1)
                normAvminys1 = sum;
        }
        System.out.println("Число обусловленности матрицы А = ||A|| * ||A^-1|| :" + '\n' + normA*normAvminys1);
        System.out.println("-----------");
        double normR = 0;
        for (int i = 0; i < A.n; i++)
        {
            sum = 0;
            for (int j = 0; j < A.n; j++)
                sum += Math.abs(R[i][j]);
            if (sum >= normR)
                normR = sum;
        }
        System.out.println("Норма матрицы R:" + normR);

    }
    public static double normMatrix(double[][] matrix)
    {
        double sum = 0, norm = 0;
        for (int i = 0; i < matrix.length; i++)
        {
            sum = 0;
            for (int j = 0; j < matrix[0].length; j++)
            {
                sum += Math.abs(matrix[i][j]);
            }
            if (sum > norm)
            {
                norm = sum;
            }
        }
        return norm;
    }
    public static double[][] multiplyMatrixMinysE(double [][] left,double [][] right)
    {
        double [][] result = new double[left.length][right[0].length];
        if(left[0].length==right.length)
        {

            for (int i = 0; i < left.length; i++)
            {
                for (int j = 0; j <right[0].length ; j++)
                {
                    for (int k = 0; k < left[0].length; k++)
                    {
                        //result[i][j] += left[i][k] * right[k][j];
                        result[i][j] += new BigDecimal(left[i][k]).multiply(new BigDecimal(right[k][j])).doubleValue();
                    }
                }
            }
            for (int i = 0; i < left.length; i++)
            {
                for (int j = 0; j <right[0].length ; j++)
                {
                    if(i==j)
                        result[i][j] -= 1;
                    System.out.printf("%e",result[i][j]);System.out.print("   ");
                }
                System.out.println();
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        GaussMatrix gaussMatrix = new GaussMatrix(3, 4);
        double[] arr =
                {
                        0.8918, 0.0000, -0.2330, 0.1638, 0.2730, 1.7545,
                        -0.0546, 0.5824, 0.0000, -0.1110, 0.0364, 2.3278,
                        0.0182, -0.1638, 1.0556, 0.0200, 0.0637, -3.2742,
                        0.0546, 0.0000, -0.1329, 1.0556, 0.0000, -9.0472,
                        0.0364, -0.0546, 0.2639, -0.0218, 0.7644, 3.9239,
                };
        double [] arr2 = {1,2,3,4,2,1,-1,-2,3,-1,5,1};


        gaussMatrix.setMatrix(arr2);
        GaussMatrix initialMatrix = new GaussMatrix(3, 4);
        initialMatrix.setMatrix(arr2);

        System.out.println("matrix:");
        printMatrix(gaussMatrix.getMatrix());
        //printMatrix(initialMatrix.getMatrix());

        double[] answer = gaussMatrix.methodGaussa();
        initialMatrix.residualVector(answer);
        resuidalMatrix(initialMatrix);

        /*double[] A =
                {
                        0.8918, 0.0000, 0.0000, 0.0000, 0.0000,1.7545,
                        -0.0546, 0.5824, 0.0000, 0.0000, 0.0000,2.3278,
                        0.0000, -0.1638, 1.0556, 0.0200, 0.0000,-3.2742,
                        0.0000, 0.0000, -0.1329, 1.0556, 0.0000,-9.0472,
                        0.0000, 0.0000, 0.0000, -0.0218, 0.7644,3.9239,
                };
        double[] b = {1.7545, 2.3278, -3.2742, -9.0472, 3.9239,};
        GaussMatrix gaussMatrix = new GaussMatrix(5, 6);

        gaussMatrix.setMatrix(A);
        GaussMatrix initialMatrix = new GaussMatrix(5, 6);
        initialMatrix.setMatrix(A);

        System.out.println("matrix:");
        printMatrix(gaussMatrix.getMatrix());
        //printMatrix(initialMatrix.getMatrix());

        double [] answer = gaussMatrix.methodGaussa();
        resuidalMatrix(initialMatrix);*/

        // System.out.println("\n\n\n\n\n");

    }
}