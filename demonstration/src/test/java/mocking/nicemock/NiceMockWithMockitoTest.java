/**
 *
 */
package mocking.nicemock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use nice mocks with Mockito.
 *
 * @author Xavier Pigeon
 */
public class NiceMockWithMockitoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NiceMockWithMockitoTest.class);

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
    public void should_call_an_expected_method_in_addition_to_an_unexpected_one() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("unexpected call"));
            }
        });
        // Mock expectations
        when(mock.method("expected call")).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).method("expected call");
    }

    @Test(expected = AssertionError.class)
    public void should_omit_to_call_an_expected_method() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                // LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        when(mock.method("expected call")).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).method("expected call");
    }

    @Test
    public void should_call_expected_methods_in_any_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call #2"));
                LOGGER.debug(mock.method("expected call #3"));
                LOGGER.debug(mock.method("expected call #1"));
            }
        });
        // Mock expectations
        when(mock.method("expected call #1")).thenReturn("response #1");
        when(mock.method("expected call #2")).thenReturn("response #2");
        when(mock.method("expected call #3")).thenReturn("response #3");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).method("expected call #1");
        verify(mock).method("expected call #2");
        verify(mock).method("expected call #3");
    }
}