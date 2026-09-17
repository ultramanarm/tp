package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_emptyRemark_success() {
        assertEquals("", new Remark("").value);
    }

    @Test
    public void constructor_text_preservesValue() {
        String text = "  Likes coffee, tea & cake!  ";
        assertEquals(text, new Remark(text).value);
    }

    @Test
    public void equals() {
        Remark remark = new Remark("Likes coffee");

        assertTrue(remark.equals(new Remark("Likes coffee")));
        assertTrue(remark.equals(remark));
        assertFalse(remark.equals(null));
        assertFalse(remark.equals("Likes coffee"));
        assertFalse(remark.equals(new Remark("Likes tea")));
        assertFalse(remark.equals(new Remark("")));
        assertTrue(new Remark("").equals(new Remark("")));
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        assertEquals(new Remark("Likes coffee").hashCode(), new Remark("Likes coffee").hashCode());
    }

    @Test
    public void toStringMethod() {
        assertEquals("Likes coffee", new Remark("Likes coffee").toString());
        assertEquals("", new Remark("").toString());
    }
}
