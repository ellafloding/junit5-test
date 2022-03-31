import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {

    @Test
    void maximumValueTest(){
        assertEquals(5, Math.max(3, 5));
    }

    @Test
    void minimumValueTest(){
        assertEquals(3, Math.min(3, 5));
    }

}
