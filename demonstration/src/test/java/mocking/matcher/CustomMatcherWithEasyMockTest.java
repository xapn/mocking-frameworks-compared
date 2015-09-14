/**
 * 
 */
package mocking.matcher;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static mocking.matching.EasyMockArgumentMatcher.eqArgument;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import mocking.matching.Argument;
import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use custom matchers with EasyMock.
 * 
 * @author Xavier Pigeon
 */
public class CustomMatcherWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMatcherWithEasyMockTest.class);

    private SystemUnderTest systemUnderTest;

    private ToBeMocked mock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock = createMock(ToBeMocked.class);
    }

    @Test
    public void should_call_an_expected_method_using_a_custom_argument_matcher() {
        // GIVEN
        Argument expectedArgument = new Argument("expected argument", TEN, 100);
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method(new Argument("actual argument", TEN, 100)));
            }
        });
        // Mock expectations
        expect(mock.method(eqArgument(expectedArgument))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }

    @Test(expected = AssertionError.class)
    public void should_not_call_an_expected_method_using_a_custom_argument_matcher() {
        // GIVEN
        Argument expectedArgument = new Argument("expected argument", TEN, 100);
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method(new Argument("actual argument", ONE, 100)));
            }
        });
        // Mock expectations
        expect(mock.method(eqArgument(expectedArgument))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }
}