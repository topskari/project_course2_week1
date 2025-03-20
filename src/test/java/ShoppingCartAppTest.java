import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartAppTest {
    @Test
    void testCalculateCost() {
        ShoppingCartApp app = new ShoppingCartApp();

        assertEquals(150.0, app.calculateCost(50.0, 3), 0.0);
    }


}
