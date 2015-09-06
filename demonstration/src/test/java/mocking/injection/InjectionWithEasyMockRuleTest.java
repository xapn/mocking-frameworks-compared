/**
 * 
 */
package mocking.injection;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import mocking.model.HowItBehaves;
import mocking.model.ToBeMocked;
import mocking.model.injection.SystemUnderTest;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to inject mocks as dependencies into the SUT with
 * EasyMock.
 * 
 * @author Xavier Pigeon
 */
public class InjectionWithEasyMockRuleTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InjectionWithEasyMockRuleTest.class);

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @TestSubject
    private SystemUnderTest systemUnderTest = new SystemUnderTest();

    @Mock(fieldName = "dependency")
    private ToBeMocked dependencyMocked;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        LOGGER.debug("SUT: {}", systemUnderTest.toString());

        systemUnderTest.setHowItBehaves(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(dependencyMocked.method("expected call"));
            }
        });
    }

    @Test
    public void should_call_expected_methods() {
        // GIVEN
        // Mock expectations
        expect(dependencyMocked.method("expected call")).andReturn("response from mock of dependency");
        replay(dependencyMocked);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(dependencyMocked);
    }
}