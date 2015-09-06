/**
 * 
 */
package mocking.model;

/**
 * The object class to be tested.
 * 
 * @author Xavier Pigeon
 */
public class SystemUnderTest {

    private HowItBehaves howItBehaves;

    public SystemUnderTest(HowItBehaves howItBehaves) {
        this.howItBehaves = howItBehaves;
    }

    /**
     * The method to be tested.
     */
    public void process() {
        howItBehaves.whereItCallsDependencies();
    }
}