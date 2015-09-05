/**
 *
 */
package mocking.regularmock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;

/**
 * This test case shows how to use regular mocks with Mockito.
 *
 * @author Xavier Pigeon
 */
public class RegularMockWithMockitoTest {

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
                mock.method("expected call");
            }
        });
        // Mock expectations
        when(mock.method("expected call")).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        // By default, Mockito checks if a given method (with given arguments)
        // was called once and only once.
        verify(mock).method("expected call");
        // verifyNoMoreInteractions() allows to ensure that no more interaction
        // (except the already verified ones) was performed with the mock(s).
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_call_expected_methods_without_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                mock.method("expected call #5");
                mock.method("expected call #1");
                mock.method("expected call #3");
                mock.method("expected call #2");
                mock.method("expected call #4");
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
        verify(mock).method("expected call #1");
        verify(mock).method("expected call #2");
        verify(mock).method("expected call #3");
        verify(mock).method("expected call #4");
        verify(mock).method("expected call #5");
        verifyNoMoreInteractions(mock);
    }

    @Test(expected = AssertionError.class)
    public void should_omit_to_call_at_least_one_of_expected_methods() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                mock.method("expected call #5");
                mock.method("expected call #1");
                // mock.method("expected call #3");
                mock.method("expected call #2");
                mock.method("expected call #4");
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
        verify(mock).method("expected call #1");
        verify(mock).method("expected call #2");
        verify(mock).method("expected call #3");
        verify(mock).method("expected call #4");
        verify(mock).method("expected call #5");
        verifyNoMoreInteractions(mock);
    }
}