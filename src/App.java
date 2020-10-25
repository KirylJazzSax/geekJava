import exceptions.MyArrayDataException;
import exceptions.MyArraySizeException;
import utils.MatrixUtil;

import java.lang.reflect.Array;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        String[][] matrix = {
                {"11", "22", "33", "44"},
                {"55", "66", "77", "44"},
                {"99", "10", "12", "13"},
                {"14", "15", "16", "17"}
        };
        
        int sum = 0;
        
        try {
            sum = MatrixUtil.matrixSum(matrix);
            System.out.println(sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }
    }
}
