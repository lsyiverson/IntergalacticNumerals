package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import main.IntergalacticNumeralTranslator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntergalacticNumeralTranslatorTest {

    private IntergalacticNumeralTranslator mTranslator;

    @Before
    public void setUp() throws Exception {
        mTranslator = IntergalacticNumeralTranslator.init();

        mTranslator.handleInput("glob is I");
        mTranslator.handleInput("prok is V");
        mTranslator.handleInput("pish is X");
        mTranslator.handleInput("tegj is L");

        mTranslator.handleInput("glob glob Silver is 34 Credits");
        mTranslator.handleInput("glob prok Gold is 57800 Credits");
        mTranslator.handleInput("pish pish Iron is 3910 Credits");
    }

    @Test
    public void testHandleInput() {
        HashMap<String, String> intergNumMap = getIntergToRomanMap();
        Assert.assertNotNull(intergNumMap);
        Assert.assertEquals("I", intergNumMap.get("glob"));
        Assert.assertEquals("V", intergNumMap.get("prok"));
        Assert.assertEquals("X", intergNumMap.get("pish"));
        Assert.assertEquals("L", intergNumMap.get("tegj"));

        HashMap<String, Double> unitPriceMap = getUnitPrice();
        Assert.assertNotNull(unitPriceMap);
        Assert.assertEquals(17, unitPriceMap.get("Silver"), 0.0001);
        Assert.assertEquals(14450, unitPriceMap.get("Gold"), 0.0001);
        Assert.assertEquals(195.5, unitPriceMap.get("Iron"), 0.0001);
    }

    private HashMap<String, String> getIntergToRomanMap() {
        Class<IntergalacticNumeralTranslator> clazz = IntergalacticNumeralTranslator.class;
        HashMap<String, String> map = null;
        try {
            Field intergToRomanField = clazz.getDeclaredField("mIntergToRomanMap");
            intergToRomanField.setAccessible(true);
            map = (HashMap<String, String>)intergToRomanField.get(mTranslator);
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    private HashMap<String, Double> getUnitPrice() {
        Class<IntergalacticNumeralTranslator> clazz = IntergalacticNumeralTranslator.class;
        HashMap<String, Double> map = null;
        try {
            Field intergToRomanField = clazz.getDeclaredField("mMetalUnitPrice");
            intergToRomanField.setAccessible(true);
            map = (HashMap<String, Double>)intergToRomanField.get(mTranslator);
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    @Test
    public void testIsSetIntergalacticToRoman() throws NoSuchMethodException,SecurityException,IllegalAccessException,IllegalArgumentException,InvocationTargetException{
        Class<IntergalacticNumeralTranslator> clazz = IntergalacticNumeralTranslator.class;
        Method method;
        method = clazz.getDeclaredMethod("isSetIntergalacticToRoman", String.class);
        method.setAccessible(true);

        boolean ret1 = (boolean)method.invoke(mTranslator, "tegj is L");
        Assert.assertTrue(ret1);

        boolean ret2 = (boolean)method.invoke(mTranslator, "prok glob is L");
        Assert.assertFalse(ret2);

        boolean ret3 = (boolean)method.invoke(mTranslator, "prok is T");
        Assert.assertFalse(ret3);

        boolean ret4 = (boolean)method.invoke(mTranslator, "prok glob is N");
        Assert.assertFalse(ret4);

        boolean ret5 = (boolean)method.invoke(mTranslator, "prok is IX");
        Assert.assertFalse(ret5);
    }

    @Test
    public void testIsConvertIntergToArabic() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<IntergalacticNumeralTranslator> clazz = IntergalacticNumeralTranslator.class;
        Method method;
        method = clazz.getDeclaredMethod("isConvertIntergToArabic", String.class);
        method.setAccessible(true);

        boolean ret1 = (boolean)method.invoke(mTranslator, "how much is pish tegj glob glob ?");
        Assert.assertTrue(ret1);

        boolean ret2 = (boolean)method.invoke(mTranslator, "how much is glob pish tegj ?");
        Assert.assertFalse(ret2);

        boolean ret3 = (boolean)method.invoke(mTranslator, "how many is pish tegj glob glob ?");
        Assert.assertFalse(ret3);

        boolean ret4 = (boolean)method.invoke(mTranslator, "how many is pish tegj glob glob ?");
        Assert.assertFalse(ret4);

        boolean ret5 = (boolean)method.invoke(mTranslator, "The quick brown fox jumps over the lazy dog");
        Assert.assertFalse(ret5);
    }

    @Test
    public void testIsSetMetalsPrice() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<IntergalacticNumeralTranslator> clazz = IntergalacticNumeralTranslator.class;
        Method method;
        method = clazz.getDeclaredMethod("isSetMetalsPrice", String.class);
        method.setAccessible(true);

        boolean ret1 = (boolean)method.invoke(mTranslator, "glob glob Silver is 34 Credits");
        Assert.assertTrue(ret1);

        boolean ret2 = (boolean)method.invoke(mTranslator, "glob regd Silver is 34 Credits");
        Assert.assertFalse(ret2);

        boolean ret3 = (boolean)method.invoke(mTranslator, "glob regd Silver is pjk Credits");
        Assert.assertFalse(ret3);

        boolean ret4 = (boolean)method.invoke(mTranslator, "The quick brown fox jumps over the lazy dog");
        Assert.assertFalse(ret4);
    }

    @Test
    public void testCalculateTotalPriceOfMetal() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<IntergalacticNumeralTranslator> clazz = IntergalacticNumeralTranslator.class;
        Method method;
        method = clazz.getDeclaredMethod("calculateTotalPriceOfMetal", String.class);
        method.setAccessible(true);

        boolean ret1 = (boolean)method.invoke(mTranslator, "how many Credits is glob prok Silver ?");
        Assert.assertTrue(ret1);

        boolean ret2 = (boolean)method.invoke(mTranslator, "how many Credits is glob prok Copper ?");
        Assert.assertFalse(ret2);

        boolean ret3 = (boolean)method.invoke(mTranslator, "how many Credits is glob tegj Silver ?");
        Assert.assertFalse(ret3);
    }

}
