/**
 *
 */
package mocking.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    String method(Boolean argument);

    String method(byte argument);

    String method(Byte argument);

    String method(char argument);

    String method(Character argument);

    String method(double argument);

    String method(Double argument);

    String method(float argument);

    String method(Float argument);

    String method(int argument);

    String method(Integer argument);

    String method(long argument);

    String method(Long argument);

    String method(short argument);

    String method(Short argument);

    String method(String argument);

    <T> String method(Collection<T> argument);

    <T> String method(List<T> argument);

    <K, V> String method(Map<K, V> argument);

    <T> String method(Set<T> set);

    <T> void voidMethod(T argument);

    <T> String method(T argument);

    String method(String argument, String... arguments);

    <T> String methodThrowingException(T argument) throws Exception;

    String methodThrowingException() throws Exception;

    String methodThrowingException(String argument) throws Exception;
}