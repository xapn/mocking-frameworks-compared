/**
 * 
 */
package mocking.matcher;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static mocking.matching.MockitoArgumentMatcher.eqArgument;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import mocking.matching.Argument;
import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use custom argument matchers with EasyMock.
 * 
 * @author Xavier Pigeon
 */
public class CustomMatcherWithMockitoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMatcherWithMockitoTest.class);

    private SystemUnderTest systemUnderTest;

    private ToBeMocked mock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock = Mockito.mock(ToBeMocked.class);
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
        when(mock.method(eqArgument(expectedArgument))).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).method(eqArgument(expectedArgument));
        verifyNoMoreInteractions(mock);
    }

    @Test(expected = AssertionError.class)
    public void should_not_call_an_expected_method_using_a_custom_argument_matcher() {
        // GIVEN
        Argument expectedArgument = new Argument("expected argument", TEN, 100);
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method(new Argument("actual argument", ONE, 10)));
            }
        });
        // Mock expectations
        when(mock.method(eqArgument(expectedArgument))).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).method(eqArgument(expectedArgument));
        verifyNoMoreInteractions(mock);
    }
}