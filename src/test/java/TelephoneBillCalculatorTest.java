import com.phonecompany.billing.TelephoneBillCalculator;
import com.phonecompany.billing.TelephoneBillCalculatorImpl;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelephoneBillCalculatorTest {

    private final TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();

    @Test
    public void testCalculate() throws Exception {
        String phoneLog = Files.readString(Paths.get("src/main/resources/billing.csv"));
        BigDecimal expected = new BigDecimal("108.0000");
        assertEquals(expected, calculator.calculate(phoneLog));
    }

    @Test
    public void testCalculate2() throws Exception {
        String phoneLog = Files.readString(Paths.get("src/main/resources/billing2.csv"));
        BigDecimal expected = new BigDecimal("33.1000");
        assertEquals(expected, calculator.calculate(phoneLog));
    }

    @Test
    public void testCalculate3() throws Exception {
        String phoneLog = Files.readString(Paths.get("src/main/resources/billing3.csv"));
        BigDecimal expected = new BigDecimal("49.8000");
        assertEquals(expected, calculator.calculate(phoneLog));
    }

}
