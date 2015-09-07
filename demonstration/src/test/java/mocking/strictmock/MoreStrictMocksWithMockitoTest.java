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
 * This test case shows how to use several strict mocks with Mockito.
 * 
 * @author Xavier Pigeon
 */
public class MoreStrictMocksWithMockitoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoreStrictMocksWithMockitoTest.class);

    private SystemUnderTest systemUnderTest;

    private ToBeMocked mock1;

    private ToBeMocked mock2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock1 = mock(ToBeMocked.class);
        mock2 = mock(ToBeMocked.class);
    }

    @Test
    public void should_call_expected_methods_in_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock1.method("expected call #1"));
                LOGGER.debug(mock2.method("expected call #2"));
                LOGGER.debug(mock1.method("expected call #3"));
                LOGGER.debug(mock2.method("expected call #4"));
            }
        });
        // Mock expectations
        when(mock1.method("expected call #1")).thenReturn("response #1");
        when(mock2.method("expected call #2")).thenReturn("response #2");
        when(mock1.method("expected call #3")).thenReturn("response #3");
        when(mock2.method("expected call #4")).thenReturn("response #4");

        // WHEN
        systemUnderTest.process();

        // THEN
        InOrder inOrder = inOrder(mock1, mock2);
        inOrder.verify(mock1).method("expected call #1");
        inOrder.verify(mock2).method("expected call #2");
        inOrder.verify(mock1).method("expected call #3");
        inOrder.verify(mock2).method("expected call #4");
        inOrder.verifyNoMoreInteractions();
    }

    @Test(expected = AssertionError.class)
    public void should_call_expected_methods_in_any_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock1.method("expected call #1"));
                LOGGER.debug(mock1.method("expected call #3"));
                LOGGER.debug(mock2.method("expected call #2"));
                LOGGER.debug(mock2.method("expected call #4"));
            }
        });
        // Mock expectations
        when(mock1.method("expected call #1")).thenReturn("response #1");
        when(mock2.method("expected call #2")).thenReturn("response #2");
        when(mock1.method("expected call #3")).thenReturn("response #3");
        when(mock2.method("expected call #4")).thenReturn("response #4");

        // WHEN
        systemUnderTest.process();

        // THEN
        InOrder inOrder = inOrder(mock1, mock2);
        inOrder.verify(mock1).method("expected call #1");
        inOrder.verify(mock2).method("expected call #2");
        inOrder.verify(mock1).method("expected call #3");
        inOrder.verify(mock2).method("expected call #4");
        inOrder.verifyNoMoreInteractions();
    }
}