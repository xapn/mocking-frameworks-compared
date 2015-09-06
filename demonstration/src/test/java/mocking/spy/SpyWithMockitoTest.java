/**
 * 
 */
package mocking.spy;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import mocking.model.legacy.DefaultBehaviorWithLegacyCode;
import mocking.model.legacy.HowItBehaves;
import mocking.model.legacy.SystemUnderTest;

import org.junit.Test;

/**
 * This test case shows how to use spies with Mockito.
 * 
 * @author Xavier Pigeon
 */
public class SpyWithMockitoTest {

    @Test
    public void should_call_spied_methods() {
        // GIVEN
        HowItBehaves spy = spy(new DefaultBehaviorWithLegacyCode());
        SystemUnderTest systemUnderTest = new SystemUnderTest(spy);
        // Spy expectations
        when(spy.legacyMethodToBeMocked()).thenReturn("response from the spy");

        // WHEN
        systemUnderTest.processWithLegacyCode();

        // THEN
        // Optionally, you can verify.
        verify(spy).methodToBeTested();
        verify(spy).legacyMethodToBeMocked();
    }
}