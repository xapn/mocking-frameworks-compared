/**
 * 
 */
package mocking.model;

/**
 * This interface stands for any dependency component the object under test
 * should depends on.
 * 
 * @author Xavier Pigeon
 */
public interface ToBeMocked {

    void voidMethod();

    void voidMethod(String argument);

    void voidMethodThrowingException() throws Exception;

    void voidMethodThrowingException(String argument) throws Exception;

    String method();

    String method(String argument);

    String methodThrowingException() throws Exception;

    String methodThrowingException(String argument) throws Exception;
}