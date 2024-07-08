package online.pdp.spring_advanced;

import java.util.concurrent.TimeUnit;

public class Calculator {

    public int sum(int a, int b) {
        return a + b;
    }

    public int div(int a, int b) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return a / b;
    }

}
