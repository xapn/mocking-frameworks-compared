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

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to inject mocks as dependencies into the SUT with
 * EasyMock.
 * 
 * @author Xavier Pigeon
 */
@RunWith(EasyMockRunner.class)
public class InjectionWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InjectionWithEasyMockTest.class);

    @TestSubject
    private SystemUnderTest systemUnderTest = new SystemUnderTest();

    @Mock(fieldName = "dependency")
    private ToBeMocked dependencyMocked;

    @Mock(fieldName = "firstClassDependency", type = MockType.STRICT)
    private ToBeMocked firstClassDependencyMocked;

    @Mock(fieldName = "secondClassDependency", type = MockType.DEFAULT)
    private ToBeMocked secondClassDependencyMocked;

    @Mock(fieldName = "thirdClassDependency", type = MockType.NICE)
    private ToBeMocked thirdClassDependencyMocked;

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
                LOGGER.debug(firstClassDependencyMocked.method("expected call"));
                LOGGER.debug(secondClassDependencyMocked.method("expected call"));
                LOGGER.debug(thirdClassDependencyMocked.method("expected call"));
            }
        });
    }

    @Test
    public void should_call_expected_methods() {
        // GIVEN
        // Mock expectations
        expect(dependencyMocked.method("expected call")).andReturn("response from mock of dependency");
        expect(firstClassDependencyMocked.method("expected call")).andReturn(
                "response from mock of first-class dependency");
        expect(secondClassDependencyMocked.method("expected call")).andReturn(
                "response from mock of second-class dependency");
        expect(thirdClassDependencyMocked.method("expected call")).andReturn(
                "response from mock of third-class dependency");
        replay(dependencyMocked, firstClassDependencyMocked, secondClassDependencyMocked, thirdClassDependencyMocked);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(dependencyMocked, firstClassDependencyMocked, secondClassDependencyMocked, thirdClassDependencyMocked);
    }
}