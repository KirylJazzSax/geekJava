package utils;

import exceptions.MyArrayDataException;
import exceptions.MyArraySizeException;

public class MatrixUtil {
    private static int arrLength  = 4;

    public static int matrixSum(String[][] matrix) throws MyArraySizeException, MyArrayDataException {
        guardMatrixSize(matrix);

        int sum = 0;
        int[][] intArr;

        intArr = parseMatrix(matrix);
        for (int i = 0; i < arrLength; i++) {
            for (int j = 0; j < arrLength; j++) {
                sum += intArr[i][j];
            }
        }

        return sum;
    }

    private static void guardMatrixSize(String[][] matrix) {
        if (matrix.length != arrLength) {
            throw new MyArraySizeException("Matrix length should be " + arrLength);
        }

        for (String[] arr: matrix) {
            if (arr.length != arrLength) {
                throw new MyArraySizeException("Array length inside matrix should be " + arrLength);
            }
        }
    }

    private static int[][] parseMatrix(String[][] matrix) {
        int[][] intMatrix = new int[arrLength][arrLength];

        for (int i = 0; i < arrLength; i++) {
            for (int j = 0; j < arrLength; j++) {
                try {
                    intMatrix[i][j] = Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Array data not valid, should be integer type of integers");
                }
            }
        }

        return intMatrix;
    }

    public static int getArrLength() {
        return arrLength;
    }

    public static void setArrLength(int arrLength) {
        MatrixUtil.arrLength = arrLength;
    }
}
