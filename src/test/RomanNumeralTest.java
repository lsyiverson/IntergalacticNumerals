package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import main.RomanNumeral;

import org.junit.Before;
import org.junit.Test;

public class RomanNumeralTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testArabicToRoman() {
        RomanNumeral roman1 = new RomanNumeral(3999);
        assertEquals("MMMCMXCIX", roman1.stringValue());

        RomanNumeral roman2 = new RomanNumeral(1);
        assertEquals("I", roman2.stringValue());

        RomanNumeral roman3 = new RomanNumeral(3255);
        assertEquals("MMMCCLV", roman3.stringValue());

        RomanNumeral roman4 = new RomanNumeral(3000);
        assertEquals("MMM", roman4.stringValue());

        RomanNumeral roman5 = new RomanNumeral(2000);
        assertEquals("MM", roman5.stringValue());

        RomanNumeral roman6 = new RomanNumeral(1300);
        assertEquals("MCCC", roman6.stringValue());

        RomanNumeral roman7 = new RomanNumeral(1000);
        assertEquals("M", roman7.stringValue());

        RomanNumeral roman8 = new RomanNumeral(800);
        assertEquals("DCCC", roman8.stringValue());

        RomanNumeral roman9 = new RomanNumeral(400);
        assertEquals("CD", roman9.stringValue());

        RomanNumeral roman10 = new RomanNumeral(480);
        assertEquals("CDLXXX", roman10.stringValue());

        RomanNumeral roman11 = new RomanNumeral(88);
        assertEquals("LXXXVIII", roman11.stringValue());

        RomanNumeral roman12 = new RomanNumeral(89);
        assertEquals("LXXXIX", roman12.stringValue());

        RomanNumeral roman13 = new RomanNumeral(22);
        assertEquals("XXII", roman13.stringValue());

        RomanNumeral roman14 = new RomanNumeral(3333);
        assertEquals("MMMCCCXXXIII", roman14.stringValue());

        // Check if RomanNumeral can throw NumberFormatException when value is
        // out of range
        try {
            RomanNumeral roman15 = new RomanNumeral(0);
            fail("Not throw NumberFormatException");
        } catch (NumberFormatException e) {
        }

        try {
            RomanNumeral roman16 = new RomanNumeral(4000);
            fail("Not throw NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }

    @Test
    public void testRomanToArabic() {
        RomanNumeral roman1 = new RomanNumeral("MMMCMXCIX");
        assertEquals(3999, roman1.intValue());

        RomanNumeral roman2 = new RomanNumeral("I");
        assertEquals(1, roman2.intValue());

        RomanNumeral roman3 = new RomanNumeral("IX");
        assertEquals(9, roman3.intValue());

        RomanNumeral roman4 = new RomanNumeral("XXXIX");
        assertEquals(39, roman4.intValue());

        RomanNumeral roman5 = new RomanNumeral("LXXXIII");
        assertEquals(83, roman5.intValue());

        RomanNumeral roman6 = new RomanNumeral("CL");
        assertEquals(150, roman6.intValue());

        RomanNumeral roman7 = new RomanNumeral("DCCLXXVII");
        assertEquals(777, roman7.intValue());

        RomanNumeral roman8 = new RomanNumeral("CMXCIX");
        assertEquals(999, roman8.intValue());

        RomanNumeral roman9 = new RomanNumeral("MIX");
        assertEquals(1009, roman9.intValue());

        RomanNumeral roman10 = new RomanNumeral("MCMXIII");
        assertEquals(1913, roman10.intValue());

        RomanNumeral roman11 = new RomanNumeral("MCMXVIII");
        assertEquals(1918, roman11.intValue());

        RomanNumeral roman12 = new RomanNumeral("MMDCCC");
        assertEquals(2800, roman12.intValue());

        RomanNumeral roman13 = new RomanNumeral("MMDCCCXC");
        assertEquals(2890, roman13.intValue());
        
        try {
            RomanNumeral roman14 = new RomanNumeral("MMXDLX");
            fail("Not throw NumberFormatException");
        } catch (NumberFormatException e) {
        }

        try {
            RomanNumeral roman15 = new RomanNumeral("MMMM");
            fail("Not throw NumberFormatException");
        } catch (NumberFormatException e) {
        }

        try {
            RomanNumeral roman16 = new RomanNumeral("MMMDM");
            fail("Not throw NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
}
