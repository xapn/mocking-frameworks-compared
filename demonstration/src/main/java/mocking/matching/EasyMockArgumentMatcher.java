/**
 * 
 */
package mocking.matching;

import static org.easymock.EasyMock.reportMatcher;

import org.easymock.IArgumentMatcher;

/**
 * Custom argument matcher for {@link Argument}.
 * 
 * @author Xavier Pigeon
 */
public class EasyMockArgumentMatcher implements IArgumentMatcher {

    private Argument expected;

    /**
     * Default constructor.
     * 
     * @param expected
     *            the expected value
     */
    private EasyMockArgumentMatcher(Argument expected) {
        this.expected = expected;
    }

    /**
     * Call this method in a test class to use the argument matcher
     * {@link EasyMockArgumentMatcher}.
     * 
     * @param expected
     *            the expected value
     * @return {@code null}
     */
    public static Argument eqArgument(Argument expected) {
        reportMatcher(new EasyMockArgumentMatcher(expected));
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(Object actual) {
        boolean hasMatched = false;

        if (actual instanceof Argument) {
            Argument actualArgument = (Argument) actual;

            if (expected.getQuantity().equals(actualArgument.getQuantity())
                    && expected.getRatio().equals(actualArgument.getRatio())) {
                hasMatched = true;
            }
        }

        return hasMatched;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void appendTo(StringBuffer buffer) {
        buffer.append("eqArgument(");
        buffer.append(expected.getClass().getName());
        buffer.append(" with value \"");
        buffer.append(expected.toString());
        buffer.append("\"");
    }
}