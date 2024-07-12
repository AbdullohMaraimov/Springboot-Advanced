package online.pdp.spring_advanced.app;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.Set;
import java.util.StringJoiner;

@ShellComponent
@RequiredArgsConstructor
public class CalculatorCommand {

    private final CalculatorService calculatorService;

    @ShellMethod(value = "Add method command", key = "add")
    public double addTwoNumbers(
            @ShellOption(value = "-n", help = "This is first argument") double a,
            @ShellOption(value = "-n", defaultValue = "1") double b) {
        return calculatorService.add(a, b);
    }

    @ShellMethod(value = "Sum of array")
    public double sum(@ShellOption(arity = 3) double[] nums) {
        return Arrays.stream(nums).sum();
    }

    @ShellMethod
    public double sub(double a, double b) {
        return calculatorService.sub(a, b);
    }

    @ShellMethod
    public double div(
            @ShellOption(value = "-n") double a,
            @ShellOption(value = "-m", help = "Can not be negative") @Positive double b) {
        return calculatorService.div(a, b);
    }

    @ExceptionResolver({ParameterValidationException.class})
    CommandHandlingResult errorHandler(ParameterValidationException e) {
        Set<ConstraintViolation<Object>> constraintViolations = e.getConstraintViolations();

        StringJoiner joiner = new StringJoiner(".\n", "", "\n");

        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            String path = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            joiner.add(path + ":" + message);
        }
        return CommandHandlingResult.of(joiner.toString(), 400);
    }

    @ShellMethod
    public double mul(double a, double b) {
        return calculatorService.mul(a, b);
    }

}
