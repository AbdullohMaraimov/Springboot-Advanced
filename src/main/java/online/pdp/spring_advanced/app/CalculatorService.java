package online.pdp.spring_advanced.app;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double add(double a, double b) {
        return a + b;
    }

    public double div(double a, double b) {
        return a / b;
    }

    public double sub(double a, double b) {
        return a - b;
    }

    public double mul(double a, double b) {
        return a * b;
    }

}
