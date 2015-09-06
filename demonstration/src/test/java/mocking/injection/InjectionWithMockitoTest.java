/**
 * 
 */
package mocking.injection;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import mocking.model.HowItBehaves;
import mocking.model.ToBeMocked;
import mocking.model.injection.SystemUnderTest;
import mocking.model.legacy.DefaultBehaviorWithLegacyCode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to inject mocks as dependencies with Mockito.
 * 
 * @author Xavier Pigeon
 */
@RunWith(MockitoJUnitRunner.class)
public class InjectionWithMockitoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InjectionWithMockitoTest.class);

    @InjectMocks
    private SystemUnderTest systemUnderTest;

    @Mock
    private ToBeMocked dependencyMocked;

    @Spy
    private DefaultBehaviorWithLegacyCode dependencyWithLegacyCodeSpied;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        LOGGER.debug("SUT: {}", systemUnderTest);

        systemUnderTest.setHowItBehaves(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(dependencyMocked.method("expected call"));
                LOGGER.debug(dependencyWithLegacyCodeSpied.legacyMethodToBeMocked());
            }
        });
    }

    @Test
    public void should_call_expected_methods() {
        // GIVEN
        // Mock expectations
        when(dependencyMocked.method("expected call")).thenReturn("response from the dependencyMocked");
        when(dependencyWithLegacyCodeSpied.legacyMethodToBeMocked()).thenReturn("response from the dependencyWithLegacyCodeSpied");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(dependencyMocked).method("expected call");
        verify(dependencyWithLegacyCodeSpied).legacyMethodToBeMocked();
    }
}