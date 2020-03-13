import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static String[][] matrix;
    static int nPaths = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // rows
        int m = scanner.nextInt(); // cols

        initializeMatrix(n,m);
        printMatrix();

        findPath(0, 0); // starting from the beginning of the matrix -> looking for n-1,m-1
        System.out.println(nPaths);
    }

    private static void findPath(int row, int col) {
        if (inMatrix(row, col)) { // continue if index is inside the matrix
            if (isExit(row, col)) { // exit if row == n-1 and col == m-1
                nPaths++;
            }else if(isPassable(row,col)){
                matrix[row][col] = "*"; // marks as path
                findPath(row+1,col); // moves down
                findPath(row-1,col); // moves up
                findPath(row,col-1); // moves left
                findPath(row,col+1); // moves right
                matrix[row][col] = "-"; // unmarks path when recursion finished
            }
        }
    }

    private static boolean isPassable(int row, int col) { // checks if the directions is passable - not passable when going backwards
        return matrix[row][col].equals("-");
    }


    private static boolean inMatrix(int row, int col) {
        if (row < 0 || row > matrix.length - 1) {
            return false;
        }
        if (col < 0 || col > matrix[row].length - 1) {
            return false;
        }

        return true;
    }

    private static boolean isExit(int row, int col) {
        return row == matrix.length - 1 && col == matrix[row].length - 1;
    }

    private static void printMatrix() {
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    private static void initializeMatrix(int n, int m) {
        matrix = new String[n][];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new String[m];
            Arrays.fill(matrix[i], "-");
        }
    }
}
