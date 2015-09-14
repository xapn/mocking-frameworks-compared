/**
 * 
 */
package mocking.matching;

import static org.mockito.Matchers.argThat;

import org.mockito.ArgumentMatcher;

/**
 * Custom argument matcher for {@link Argument} with Mockito.
 * 
 * @author Xavier Pigeon
 */
public class MockitoArgumentMatcher extends ArgumentMatcher<Argument> {

    private Argument expected;

    private MockitoArgumentMatcher(Argument expected) {
        super();
        this.expected = expected;
    }

    public static Argument eqArgument(Argument expected) {
        return argThat(new MockitoArgumentMatcher(expected));
    }

    @Override
    public boolean matches(Object argument) {
        boolean hasMatched = false;

        if (argument instanceof Argument) {
            Argument actual = (Argument) argument;

            if (expected.getQuantity().equals(actual.getQuantity()) && expected.getRatio().equals(actual.getRatio())) {
                hasMatched = true;
            }
        }

        return hasMatched;
    }
}