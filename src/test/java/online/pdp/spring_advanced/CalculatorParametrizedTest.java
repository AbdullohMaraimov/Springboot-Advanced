package online.pdp.spring_advanced;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorParametrizedTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest(name = "{displayName} => {index} : [{arguments}]")
    @ValueSource(strings = {"PDP", "JAVA", "FIND"})
    @DisplayName("Test metod for parametrized test")
    void testMethod(String message) {
        System.out.println(message);
        assertEquals(message, message.toUpperCase());
    }





    @ParameterizedTest(name = "{displayName} => {index} : [{arguments}]")
    @CsvSource({
            "1, 2, 3",
            "22, 11, 33",
            "4, 3, 7"
    })

    void sum(int a, int b, int expected) {
        assertEquals(expected, calculator.sum(a, b));
    }





    @ParameterizedTest(name = "{displayName} => {index} : [{arguments}]")
    @CsvSource(value = {
            "a, b, sum",
            "1, 2, 3",
            "22, 11, 33",
            "4, 3, 7"
    }, useHeadersInDisplayName = true)

    void sum_with_header(int a, int b, int expected) {
        assertEquals(expected, calculator.sum(a, b));
    }






    @ParameterizedTest(name = "{displayName} => {index} : [{arguments}]")
    @CsvFileSource(resources = "/source.csv", useHeadersInDisplayName = true)
    void sum_cvs_file_source(int a, int b, int expected) {
        assertEquals(expected, calculator.sum(a, b));
    }






    @ParameterizedTest(name = "{displayName} => {index} : [{arguments}]")
    @MethodSource("sumSource")
    void sum(SumArgument arg) {
        assertEquals(arg.expected, calculator.sum(arg.a, arg.b));
    }

    static Stream<SumArgument> sumSource() {
        return Stream.of(
                new SumArgument(2, 3, 5),
                new SumArgument(1, -3, -2),
                new SumArgument(1, 3, 4),
                new SumArgument(11, 3, 14)
        );
    }

    public static class SumArgument {
        int a;
        int b;
        int expected;

        public SumArgument(int a, int b, int expected) {
            this.a = a;
            this.b = b;
            this.expected = expected;
        }

        @Override
        public String toString() {
            return "SumArgument{" +
                    "a=" + a +
                    ", b=" + b +
                    ", expected=" + expected +
                    '}';
        }
    }







    @Test
    void div() {
    }
}