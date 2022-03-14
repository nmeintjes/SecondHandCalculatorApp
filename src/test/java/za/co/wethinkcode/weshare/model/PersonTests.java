package za.co.wethinkcode.weshare.model;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.weshare.TestData;
import za.co.wethinkcode.weshare.app.model.Phone;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTests {
    @Test
    public void newPersonDoesNotHaveId() {
        Phone p = TestData.me();
        assertNull(p.getId());
    }

    @Test
    public void personWithNoEmailFails() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void pseudonymFromEmailAddress() {
        Phone p = TestData.me();
        assertEquals("Me", p.getName());
    }

    @Test
    public void pseudonymFromNonEmailAddress() {
        Phone p = new Phone("me, myself and I");
        assertEquals("Me, myself and I", p.getName());
    }
}
