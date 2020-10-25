package exceptions;

public class MyArraySizeException extends IllegalArgumentException {

    public MyArraySizeException(String sda) {
        super(sda);
    }
}
