/**
 * 
 */
package mocking.model.legacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object class to be tested.
 * 
 * @author Xavier Pigeon
 */
public class SystemUnderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemUnderTest.class);

    private HowItBehaves howItBehaves;

    public SystemUnderTest() {
        howItBehaves = new DefaultBehaviorWithLegacyCode();
    }

    public SystemUnderTest(HowItBehaves howItBehaves) {
        this.howItBehaves = howItBehaves;
    }

    /**
     * The method to be tested.
     */
    public void processWithLegacyCode() {
        partOfTheMethodToBeTested();

        LOGGER.debug("Result of legacy code: {}", itUsesLegacyCode());
    }

    private void partOfTheMethodToBeTested() {
        howItBehaves.methodToBeTested();
    }

    /**
     * Legacy code.</br> This code is impossible to test, thus this method must
     * not be called while testing!
     * 
     * @return a <code>boolean</code>
     */
    public String itUsesLegacyCode() {
        String resultFromLegacyMethod = howItBehaves.legacyMethodToBeMocked();
        LOGGER.debug("Result from legacy method: {}", resultFromLegacyMethod);

        if (!resultFromLegacyMethod.equals("An exception occurs if the real method is called and the test fails!")) {
            LOGGER.debug("If this message is displayed, then the spy has stubbed the legacy method code.");
            return resultFromLegacyMethod;
        } else {
            return "incorrect result from legacy code";
        }
    }
}