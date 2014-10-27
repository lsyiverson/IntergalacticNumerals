
package test;

import main.IntergalacticNumeralTranslator;

import org.junit.Before;
import org.junit.Test;

public class IntergalacticNumeralsTest {
    IntergalacticNumeralTranslator mTranslator;

    @Before
    public void setUp() throws Exception {
        mTranslator = IntergalacticNumeralTranslator.init();
    }

    @Test
    public void test() {
        mTranslator.handleInput("glob is I");
        mTranslator.handleInput("prok is V");
        mTranslator.handleInput("pish is X");
        mTranslator.handleInput("tegj is L");
        mTranslator.handleInput("glob glob Silver is 34 Credits");
        mTranslator.handleInput("glob prok Gold is 57800 Credits");
        mTranslator.handleInput("pish pish Iron is 3910 Credits");
        mTranslator.handleInput("how much is pish tegj glob glob ?");
        mTranslator.handleInput("how many Credits is glob prok Silver ?");
        mTranslator.handleInput("how many Credits is glob prok Gold ?");
        mTranslator.handleInput("how many Credits is glob prok Iron ?");
        mTranslator
        .handleInput("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    }
}
