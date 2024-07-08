package online.pdp.spring_advanced;

import java.util.Arrays;

public class Math {


    public int sum(int... args) {
        if ( args == null )
            throw new IllegalArgumentException("Arguments can not be null");
        return Arrays.stream(args)
                .reduce(Integer :: sum)
                .orElse(0);
    }

    public int sub(int a, int b) { // done
        return a - b;
    }

    public int add(int a, int b) { // done
        return a + b;
    }

    public int div(int a, int b) {
        if ( b == 0 )
            throw new IllegalArgumentException("Divider can not be zero");
        return a / b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int pow(int number, int power) {
        int result = 1;
        for ( int i = 0; i < power; i++ )
            result = result * number;
        return result;
    }
}
