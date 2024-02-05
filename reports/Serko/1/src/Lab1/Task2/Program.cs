﻿
int len = Convert.ToInt32(args[0]);
int width = Convert.ToInt32(args[1]);
int index = 2;
double[,] matrix = new double[len, width];
for (int i = 0; i < len; i++)
{
    for (int j = 0; j < width; j++)
    {
        matrix[i, j] = Convert.ToDouble(args[index]);
        index++;
    }
}

randomPerturbations(matrix);
 void randomPerturbations(double[,] matrix)

{
  
    printMatrix(matrix, "Old");

    Random number = new Random();
    for (int i = 0; i < len; i++)
    {
        for (int j = 0; j < width; j++)
        {
            int ri = number.Next(len);
            int rj = number.Next(width);
            (matrix[i, j], matrix[ri, rj]) = (matrix[ri, rj], matrix[i, j]);
          }

    }
    printMatrix(matrix, "New");




}
 void printMatrix(double[,] matrix, string type)
{
    
    Console.WriteLine($"{type} matrix");
 
    for (int i = 0; i < len; i++)
    {
        for (int j = 0; j < width; j++)
        {
            

            Console.Write($"{matrix[i, j]} \t");

        }
        Console.WriteLine();

    }

}


