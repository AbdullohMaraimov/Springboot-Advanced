package online.pdp.spring_advanced;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for calculator")
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    Calculator calculator;
    private static final Logger logger = Logger.getLogger(CalculatorTest.class.getName());

    @BeforeEach
    void setUp() {
       // logger.info("...........Before each is working...............");
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
       // logger.info("............After each is working ................");
    }

    @BeforeAll
    static void setUpAll() {
       // logger.info("...........Before All is working...............");
    }

    @AfterAll
    static void tearDownAll() {
       // logger.info("............After All is working ................");
    }

// -----------------------------------------------------------------------------------------

    @Test
    @Order(4)
    @DisplayName("Test for sum of two numbers")
    void testForSum() {
        int actual = calculator.sum(2, 3);
        assertEquals(5, actual, "Expected value and actual values dont match");
    }

    @Test
    @Order(1)
    @DisplayName("Test for division of two numbers")
    void testForDiv() throws InterruptedException {
        int actual = calculator.div(10, 2);
        assertEquals(5, actual, "Expected value and actual values dont match");

    }

    @Test
    @Order(2)
    @DisplayName("Test for div that throws exception")
    void testForDiv_throws_Exception() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> calculator.div(10, 0));
        e.printStackTrace();
    }

    @Test
    @Order(3)
    @DisplayName("Test for div that throws timeout exception")
    void testForDiv_throws_timeout_Exception() {
        assertTimeout(Duration.ofMillis(1500), () -> calculator.div(4, 2));
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void shouldWorkOnWindowsOnly() {}
}
