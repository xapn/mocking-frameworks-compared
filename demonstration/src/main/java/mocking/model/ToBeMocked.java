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

    String method(boolean argument);

    String method(byte argument);

    String method(char argument);

    String method(double argument);

    String method(float argument);

    String method(int argument);

    String method(long argument);

    <T> String method(T argument);

    String method(short argument);

    String method(String argument);

    String methodThrowingException() throws Exception;

    String methodThrowingException(String argument) throws Exception;
}