/**
 * 
 */
package mocking.model.legacy;

/**
 * Interface for anonymous classes whose the methods can be spied, ie partially
 * stubbed.
 * 
 * @author Xavier Pigeon
 */
public interface HowItBehaves {

    /**
     * Method to be tested.
     */
    String methodToBeTested();

    /**
     * Legacy code.</br> This code is impossible to test, thus this method must
     * not be called while testing!
     */
    String legacyMethodToBeMocked();
}