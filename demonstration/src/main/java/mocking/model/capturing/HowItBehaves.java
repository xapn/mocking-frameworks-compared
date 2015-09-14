/**
 * 
 */
package mocking.model.capturing;

import mocking.capturing.ArgumentWrapper;

/**
 * Interface for anonymous classes calling the mocked methods.
 * 
 * @author Xavier Pigeon
 */
@FunctionalInterface
public interface HowItBehaves<T> {

    /**
     * Call the methods from mocks.
     * 
     * @param argumentWrapper
     *            the wrapper of the argument passed to a mock
     */
    void whereItCallsDependencies(ArgumentWrapper<T> argumentWrapper);
}