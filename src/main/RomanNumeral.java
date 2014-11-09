package main;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RomanNumeral {

    /**
     * A constant holding the minimum value an {@code RomanNumeral} can have, 1.
     */
    public static final int MIN_VALUE = 1;

    /**
     * A constant holding the maximum value an {@code RomanNumeral} can have,
     * 3999.
     */
    public static final int MAX_VALUE = 3999;

    /**
     * Parse the Roman Numeral string to int
     * @param s
     * @return
     * @throws NumberFormatException
     */
    public static int parseToInt(String s) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("null");
        }
        if (!isVaildFormat(s)) {
            throw new NumberFormatException(s + " is not a vaild Roman Numeral");
        }

        int result = romanToArabic(s);

        if (result < MIN_VALUE || result > MAX_VALUE) {
            throw new NumberFormatException("The number is out of range");
        }
        return result;
    }

    final static int [] DigitArabic = {
        1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };
    
    final static String [] DigitRoman = {
        "M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"
    };

    /**
     * Parse a Arabic numeral to Roman numeral
     * 
     * @param arabic
     *            The Arabic numeral in {@code int}
     * @return The Roman numeral in {@code String}
     */
    private String arabicToRoman(int number) throws NumberFormatException {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new NumberFormatException("The number is out of range");
        }
        
        StringBuilder result = new StringBuilder();
        
        // Use the existed Roman and Arabic
        for (int i = 0; i < DigitArabic.length;) {
            if (number >= DigitArabic[i]) {
                result.append(DigitRoman[i]);
                number -= DigitArabic[i];
            } else {
                i++;
            }

            if (number == 0) {
                break;
            }
        }

        return result.toString();
    }
    
    /**
     * Convert Roman Numeral to Arabic Numeral
     * 
     * @param s
     *            Roman Numeral in {@code string}
     * @return
     * @throws NumberFormatException
     */
    private static int romanToArabic(String s) throws NumberFormatException {
        if (!isVaildFormat(s)) {
            throw new NumberFormatException(
                    "This string is not a vaild Roman Numeral");
        }
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put(Character.valueOf('I'), Integer.valueOf(1));
        map.put(Character.valueOf('V'), Integer.valueOf(5));
        map.put(Character.valueOf('X'), Integer.valueOf(10));
        map.put(Character.valueOf('L'), Integer.valueOf(50));
        map.put(Character.valueOf('C'), Integer.valueOf(100));
        map.put(Character.valueOf('D'), Integer.valueOf(500));
        map.put(Character.valueOf('M'), Integer.valueOf(1000));

        char[] num = s.toCharArray();
        int sum = map.get(num[0]).intValue();
        for (int i = 0; i < num.length - 1; i++) {
            // If num[i] >= num[i+1], Add the two nums
            int front = map.get(num[i]).intValue();
            int behind = map.get(num[i + 1]).intValue();
            if (front >= behind) {
                sum += behind;
            }
            // If the num[i+1] > num[i], it means the sum need add num[i+1] -
            // num[i]. Besides that, the num[i] is added in the last loop, so
            // the num[i] need minus from the sum.
            else {
                sum = (sum - front) + (behind - front);
            }
        }
        return sum;
    }
    
    private static boolean isVaildFormat(String s) {
        Pattern p = Pattern
                .compile("^M{0,3}(?:D?C{0,3}|C[MD])(?:L?X{0,3}|X[CL])(?:V?I{0,3}|I[XV])$");
        Matcher matcher = p.matcher(s);
        return matcher.find();
    }
    
    public int intValue() {
        return value;
    }

    public String stringValue() {
        return arabicToRoman(value);
    }

    /**
     * The value of the {@code RomanNumeral}.
     * 
     * @serial
     */
    private final int value;

    public RomanNumeral(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new NumberFormatException("The number is out of range");
        }
        this.value = value;
    }

    public RomanNumeral(String s) throws NumberFormatException {
        if (!isVaildFormat(s)) {
            throw new NumberFormatException(
                    "This string is not a vaild Roman Numeral");
        }
        this.value = romanToArabic(s);
    }
}
