/**
 * 
 */
package mocking.model;

/**
 * Interface for anonymous classes calling the mocked methods.
 * 
 * @author Xavier Pigeon
 */
@FunctionalInterface
public interface HowItBehaves {

    /**
     * Call the methods from mocks.
     */
    void whereItCallsDependencies();
}
