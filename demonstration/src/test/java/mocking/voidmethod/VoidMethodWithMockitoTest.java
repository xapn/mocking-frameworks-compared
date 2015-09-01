/**
 *
 */
package mocking.voidmethod;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * This test case shows how to stub a void method with Mockito.
 *
 * @author Xavier Pigeon
 */
public class VoidMethodWithMockitoTest {

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
}