/**
 * 
 */
package mocking.model.legacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default behavior containing legacy code.
 * 
 * @author Xavier Pigeon
 */
public class DefaultBehaviorWithLegacyCode implements HowItBehaves {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBehaviorWithLegacyCode.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String methodToBeTested() {
        LOGGER.debug("The code to be tested.");

        return "Ok";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String legacyMethodToBeMocked() {
        LOGGER.warn("Legacy code: this code is impossible to test!");

        return null;
    }
}