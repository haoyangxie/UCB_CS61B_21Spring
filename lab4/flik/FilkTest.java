package flik;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilkTest {
    @Test
    public void Test() {
        int a = 66;
        int b = 66;
        boolean result = (a == b);
        assertTrue(result);

        boolean flikResult = Flik.isSameNumber(a, b);
        assertTrue(flikResult);
    }


}
