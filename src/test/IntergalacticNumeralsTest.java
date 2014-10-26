package test;

import main.IntergalacticNumeralTranslator;

import org.junit.Before;
import org.junit.Test;

public class IntergalacticNumeralsTest {
    IntergalacticNumeralTranslator translator;

    @Before
    public void setUp() throws Exception {
        translator = IntergalacticNumeralTranslator.init();
    }

    @Test
    public void test() {
        translator.handleInput("glob is I");
        translator.handleInput("prok is V");
        translator.handleInput("pish is X");
        translator.handleInput("tegj is L");
        translator.handleInput("glob glob Silver is 34 Credits");
        translator.handleInput("glob prok Gold is 57800 Credits");
        translator.handleInput("pish pish Iron is 3910 Credits");
        translator.handleInput("how much is pish tegj glob glob ?");
        translator.handleInput("how many Credits is glob prok Silver ?");
        translator.handleInput("how many Credits is glob prok Gold ?");
        translator.handleInput("how many Credits is glob prok Iron ?");
        translator
                .handleInput("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    }
}
