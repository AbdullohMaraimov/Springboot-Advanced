package online.pdp.spring_advanced;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MathTest {

    Math math;

    @BeforeEach
    void setUp() {
        math = new Math();
    }

    @AfterEach
    void tearDown() {
        math = null;
    }

    // ----------------------------------------------

    @ParameterizedTest
    @CsvSource({
            "1, 1, 0",
            "12, 2, 10",
            "99, 9, 90",
            "111, 11, 100",
            "-1, -1, 0"
    })
    void sub(int a, int b, int expected) {
        assertEquals(expected, math.sub(a, b));
    }



    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "4, 6, 10",
            "11, 22, 33",
            "-1, 1, 0"
    })
    void add(int a, int b, int expected) {
        assertEquals(expected, math.add(a, b));
    }



    @Test
    void div() {
        assertEquals(2, math.div(4, 2));
        assertEquals(1, math.div(4, 4));
        assertEquals(11, math.div(121, 11));
        assertThrows(IllegalArgumentException.class, () -> math.div(1, 0));
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/source.csv", useHeadersInDisplayName = true)
    void mul(int a, int b, int expected) {
        assertEquals(expected, math.mul(a, b));
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/source2.csv", useHeadersInDisplayName = true)
    void pow(int num, int pow, int expected) {
        assertEquals(expected, math.pow(num, pow));
    }



    @ParameterizedTest
    @MethodSource("sumSource")
    void sum(SumParam param) {
        assertEquals(param.expected, math.sum(param.a, param.b, param.c, param.d));
    }

    static Stream<SumParam> sumSource() {
        return Stream.of(
                new SumParam(1, 2, 3, 4, 10),
                new SumParam(12, 21, -3, 4, 34),
                new SumParam(-1, -2, -3, -4, -10),
                new SumParam(11, 22, 33, 44, 110),
                new SumParam(-21, 2, -3, 4, -18)
        );
    }

    public static class SumParam{
        int a;
        int b;
        int c;
        int d;
        int expected;

        public SumParam(int a, int b, int c, int d, int expected) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.expected = expected;
        }
    }

}








