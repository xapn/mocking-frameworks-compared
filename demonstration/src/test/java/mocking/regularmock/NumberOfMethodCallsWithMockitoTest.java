/**
 *
 */
package mocking.regularmock;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

/**
 * This test case shows how to control the number of method calls with Mockito.
 *
 * @author Xavier Pigeon
 */
public class NumberOfMethodCallsWithMockitoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberOfMethodCallsWithMockitoTest.class);

    private SystemUnderTest systemUnderTest;

    private ToBeMocked mock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock = mock(ToBeMocked.class);
    }

    @Test
    public void should_call_an_expected_method_a_given_number_of_times() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        when(mock.method("expected call")).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock, times(3)).method("expected call");
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_call_an_expected_method_at_least_once() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        when(mock.method("expected call")).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock, atLeastOnce()).method("expected call");
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_call_an_expected_method_at_least_a_given_number_of_times() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        when(mock.method("expected call")).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock, atLeast(2)).method("expected call");
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_call_an_expected_method_at_most_a_given_number_of_times() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        when(mock.method("expected call")).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock, atMost(4)).method("expected call");
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_call_an_expected_method_between_x_and_y_times() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        when(mock.method("expected call")).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock, atLeast(2)).method("expected call");
        verify(mock, atMost(5)).method("expected call");
        verifyNoMoreInteractions(mock);
    }
}