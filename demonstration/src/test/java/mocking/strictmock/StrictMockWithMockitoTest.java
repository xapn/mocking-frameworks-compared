/**
 *
 */
package mocking.strictmock;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use strict mocks with Mockito.
 *
 * @author Xavier Pigeon
 */
public class StrictMockWithMockitoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrictMockWithMockitoTest.class);

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
    public void should_call_an_expected_method() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        when(mock.method("expected call")).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).method("expected call");
        inOrder.verifyNoMoreInteractions();
    }

    @Test(expected = AssertionError.class)
    public void should_call_an_unexpected_method() {
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
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).method("expected call");
        inOrder.verifyNoMoreInteractions();
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
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).method("expected call");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void should_call_expected_methods_in_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call #1"));
                LOGGER.debug(mock.method("expected call #2"));
                LOGGER.debug(mock.method("expected call #3"));
                LOGGER.debug(mock.method("expected call #4"));
                LOGGER.debug(mock.method("expected call #5"));
            }
        });
        // Mock expectations
        when(mock.method("expected call #1")).thenReturn("response #1");
        when(mock.method("expected call #2")).thenReturn("response #2");
        when(mock.method("expected call #3")).thenReturn("response #3");
        when(mock.method("expected call #4")).thenReturn("response #4");
        when(mock.method("expected call #5")).thenReturn("response #5");

        // WHEN
        systemUnderTest.process();

        // THEN
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).method("expected call #1");
        inOrder.verify(mock).method("expected call #2");
        inOrder.verify(mock).method("expected call #3");
        inOrder.verify(mock).method("expected call #4");
        inOrder.verify(mock).method("expected call #5");
        inOrder.verifyNoMoreInteractions();
    }

    @Test(expected = AssertionError.class)
    public void should_call_expected_methods_in_any_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call #1"));
                LOGGER.debug(mock.method("expected call #2"));
                LOGGER.debug(mock.method("expected call #5"));
                LOGGER.debug(mock.method("expected call #3"));
                LOGGER.debug(mock.method("expected call #4"));
            }
        });
        // Mock expectations
        when(mock.method("expected call #1")).thenReturn("response #1");
        when(mock.method("expected call #2")).thenReturn("response #2");
        when(mock.method("expected call #3")).thenReturn("response #3");
        when(mock.method("expected call #4")).thenReturn("response #4");
        when(mock.method("expected call #5")).thenReturn("response #5");

        // WHEN
        systemUnderTest.process();

        // THEN
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).method("expected call #1");
        inOrder.verify(mock).method("expected call #2");
        inOrder.verify(mock).method("expected call #3");
        inOrder.verify(mock).method("expected call #4");
        inOrder.verify(mock).method("expected call #5");
        inOrder.verifyNoMoreInteractions();
    }
}