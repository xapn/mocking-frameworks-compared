/**
 * 
 */
package mocking.spy;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import mocking.model.legacy.DefaultBehaviorWithLegacyCode;
import mocking.model.legacy.HowItBehaves;
import mocking.model.legacy.SystemUnderTest;

import org.junit.Before;
import org.junit.Test;

/**
 * This test case shows how to use partial mocks with EasyMock.
 * 
 * @author Xavier Pigeon
 */
public class PartialMockWithEasyMockTest {

    private SystemUnderTest systemUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}

    @Test
    public void should_call_spied_method() {
        // GIVEN
        HowItBehaves mock = mock(HowItBehaves.class);
        HowItBehaves howItBehaves = new DefaultBehaviorWithLegacyCode();
        systemUnderTest = new SystemUnderTest(mock);
        // Mock expectations
        expect(mock.methodToBeTested()).andDelegateTo(howItBehaves);
        expect(mock.legacyMethodToBeMocked()).andReturn("response from the partial mock");
        replay(mock);

        // WHEN
        systemUnderTest.processWithLegacyCode();

        // THEN
        verify(mock);
    }
}