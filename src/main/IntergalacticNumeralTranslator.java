package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntergalacticNumeralTranslator {
    /**
     * The error tips
     */
    private static final String ERROR_INFO = "I have no idea what you are talking about";

    /**
     * The translator instance
     */
    private static IntergalacticNumeralTranslator mTranslatorInstance;

    private HashMap<String, String> mIntergToRomanMap;
    private HashMap<String, Double> mMetalUnitPrice;

    private IntergalacticNumeralTranslator() {
        mIntergToRomanMap = new HashMap<String, String>();
        mMetalUnitPrice = new HashMap<String, Double>();
    }

    /**
     * Init the Intergalactic Numeral Translator
     * 
     * @return the instance of the translator
     */
    public static IntergalacticNumeralTranslator init() {
        if (mTranslatorInstance == null) {
            mTranslatorInstance = new IntergalacticNumeralTranslator();
        }

        return mTranslatorInstance;
    }

    /**
     * Handle the input data, if the data can not be handle, it will out
     * "I have no idea what you are talking about"
     * 
     * @param input
     *            The input data from user
     */
    public void handleInput(String input) {
        if (isSetIntergalacticToRoman(input)) {
            setupIntergToRomanMap(input);
        } else if (isSetMetalsPrice(input)) {
            calculateMetalPrice(input);
        } else if (isConvertIntergToArabic(input)) {
            // Do nothing, convert is done in isConvertIntergToArabic()
        } else if (calculateTotalPriceOfMetal(input)) {
            // Do nothing, calculate is done in calculateTotalPriceOfMetal()
        } else {
            System.out.println(ERROR_INFO);
        }
    }

    /**
     * Check if the sentence is setting Intergalactic numeral to Roman numeral
     * 
     * @param s
     *            the sentence
     * @return <tt>true</tt> if the sentence actually is setting Intergalactic
     *         numeral to Roman Numeral
     */
    private boolean isSetIntergalacticToRoman(String s) {
        Pattern p = Pattern.compile("^[a-zA-Z]+ is [MDCLXVI]$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(s);
        return matcher.find();
    }
    
    /**
     * Check if the sentence is convert from Intergalactic Numeral to Arabic
     * Numeral
     * 
     * @param s
     *            the sentence
     * @return <tt>true</tt> if the sentence is convert number from Integalactic
     *         to Arabic
     */
    private boolean isConvertIntergToArabic(String s) {
        // Create the regex to match the sentence
        String intergNumerals = getAllIntergNumeral();

        Pattern p = Pattern.compile("^how much is (?<intergNum>("
                + intergNumerals + ")( (" + intergNumerals + "))*)",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(s);

        if (!matcher.find()) {
            return false;
        } else {
            String intergNum = matcher.group("intergNum");
            int arabicNum = convertIntergToArabic(intergNum);
            System.out.println(intergNum + " is " + arabicNum);
            return true;
        }
    }

    /**
     * Setup the map from Intergalactic to Roman;
     * 
     * @param s
     *            The input string
     */
    private void setupIntergToRomanMap(String s) {
        // get the words in sentence
        Pattern p = Pattern.compile(
                "^(?<arabic>[a-zA-Z]+) is (?<roman>[MDCLXVI])$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(s);
        if (matcher.find()) {
            String arabic = matcher.group("arabic");
            String roman = matcher.group("roman");

            mIntergToRomanMap.put(arabic, roman);
        }
    }

    /**
     * Check if the sentence is set the price of metals
     * 
     * @param s
     *            the sentence
     * @return <tt>true</tt> if the sentence is set the price of metals
     */
    private boolean isSetMetalsPrice(String s) {
        // Create the regex to match the sentence
        String intergNumerals = getAllIntergNumeral();
        Pattern p = Pattern.compile("^((" + intergNumerals
                        + ") )+[a-zA-Z]+ is \\d+ Credit(s)?$",
                        Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(s);
        return matcher.find();
    }

    /**
     * Calculate the price of the metal mentioned in sentence
     * 
     * @param s
     *            the sentence
     */
    private void calculateMetalPrice(String s) {
        String amount, name, total;
        // Create the regex to match the sentence
        String intergNumerals = getAllIntergNumeral();
        Pattern p = Pattern.compile("^(?<amount>(" + intergNumerals + ")( ("
                + intergNumerals
                + "))*) (?<name>[a-zA-Z]+) is (?<total>\\d+) Credit(s)?$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(s);
        if (matcher.find()) {
            amount = matcher.group("amount");
            name = matcher.group("name");
            total = matcher.group("total");

            int metalAmount = convertIntergToArabic(amount);
            int metalTotalPrice = Integer.parseInt(total);
            double unitPrice = (double) metalTotalPrice / metalAmount;

            mMetalUnitPrice.put(name, unitPrice);
        }
    }

    /**
     * Convert Intergalactic Numeral to Arabic Numeral in order to calculate the
     * metal price
     * 
     * @param intergNum
     *            Intergalactic Numeral
     * @return Arabic Numeral
     */
    private int convertIntergToArabic(String intergNum) {
        String[] nums = intergNum.split(" ");
        // Use Roman Numerals to replace Intergalactic Numerals
        for (int i = 0; i < nums.length; i++) {
            nums[i] = mIntergToRomanMap.get(nums[i]);
        }
        StringBuilder romanNumBuilder = new StringBuilder();
        for (String n : nums) {
            romanNumBuilder.append(n);
        }
        // Convert Roman Numeral to Arabic Numeral
        int arabicNum = RomanNumeral.parseToInt(romanNumBuilder.toString());
        return arabicNum;
    }

    /**
     * Get the saved Intergalactic numeral, split by '|'
     * 
     * @return all of the known Intergalactic numeral
     */
    private String getAllIntergNumeral() {
        Iterator<Entry<String, String>> iter = mIntergToRomanMap.entrySet()
                .iterator();
        StringBuilder builder = new StringBuilder();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iter
                    .next();
            if (builder.length() != 0) {
                builder.append("|");
            }
            builder.append(entry.getKey());
        }
        // Create the Intergalactic numeral regex to match the sentence
        return builder.toString();
    }

    /**
     * Get all metals name which price is known.
     * 
     * @return all metals name, split by '|'
     */
    private String getAllMetals() {
        Iterator<Entry<String, Double>> iter = mMetalUnitPrice.entrySet()
                .iterator();
        StringBuilder builder = new StringBuilder();
        while (iter.hasNext()) {
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>) iter
                    .next();
            if (builder.length() != 0) {
                builder.append("|");
            }
            builder.append(entry.getKey());
        }
        // Create the metals regex to match the sentence
        return builder.toString();
    }

    private boolean calculateTotalPriceOfMetal(String s) {
        // Create the Intergalactic numeral regex to match the sentence
        String intergNumerals = getAllIntergNumeral();

        String metals = getAllMetals();

        Pattern p = Pattern.compile("^how many Credits is (?<intergNum>("
                + intergNumerals + ")( (" + intergNumerals + "))*) (?<metal>"
                + metals + ")", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(s);
        if (!matcher.find()) {
            return false;
        } else {
            String intergNum = matcher.group("intergNum");
            String metal = matcher.group("metal");
            int amount = convertIntergToArabic(intergNum);
            double price = mMetalUnitPrice.get(metal);
            int total = (int) (amount * price);
            System.out.println(intergNum + " " + metal + " is " + total
                    + " Credits");
        }
        return true;
    }
}
