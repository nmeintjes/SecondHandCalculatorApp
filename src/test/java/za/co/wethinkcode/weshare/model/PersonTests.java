package za.co.wethinkcode.weshare.model;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.weshare.TestData;
import za.co.wethinkcode.weshare.app.model.Item;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTests {
    @Test
    public void newPersonDoesNotHaveId() {
        Item p = TestData.me();
        assertNull(p.getId());
    }

    @Test
    public void personWithNoEmailFails() {
        assertThrows(NullPointerException.class, () -> new Item(null));
    }

    @Test
    public void pseudonymFromEmailAddress() {
        Item p = TestData.me();
        assertEquals("Me", p.getName());
    }

    @Test
    public void pseudonymFromNonEmailAddress() {
        Item p = new Item("me, myself and I");
        assertEquals("Me, myself and I", p.getName());
    }
}
