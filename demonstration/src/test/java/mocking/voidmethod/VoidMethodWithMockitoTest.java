/**
 *
 */
package mocking.voidmethod;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to stub a void method with Mockito.
 *
 * @author Xavier Pigeon
 */
public class VoidMethodWithMockitoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoidMethodWithMockitoTest.class);

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
    public void should_call_an_expected_void_method() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                mock.voidMethod("expected call");
            }
        });
        // Mock expectations
        doNothing().when(mock).voidMethod("expected call");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).voidMethod("expected call");
    }

    @Test
    public void should_call_an_expected_void_method_throwing_exception() throws Exception {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                try {
                    mock.voidMethodThrowingException("expected call");
                } catch (Exception e) {
                    LOGGER.debug(e.getMessage());
                }
            }
        });
        // Mock expectations
        doThrow(Exception.class).when(mock).voidMethodThrowingException("expected call");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).voidMethodThrowingException("expected call");
    }
}