/**
 * 
 */
package mocking.model.capturing;

import mocking.capturing.ArgumentWrapper;

/**
 * The object class to be tested.
 * 
 * @author Xavier Pigeon
 */
public class SystemUnderTest<T> {

    private HowItBehaves<T> howItBehaves;

    public SystemUnderTest() {}

    public SystemUnderTest(HowItBehaves<T> howItBehaves) {
        this.howItBehaves = howItBehaves;
    }

    /**
     * The method to be tested.
     */
    public void process(ArgumentWrapper<T> argumentWrapper) {
        howItBehaves.whereItCallsDependencies(argumentWrapper);
    }

    public void setHowItBehaves(HowItBehaves<T> howItBehaves) {
        this.howItBehaves = howItBehaves;
    }
}