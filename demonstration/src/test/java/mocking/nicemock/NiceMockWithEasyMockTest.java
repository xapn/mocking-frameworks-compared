/**
 *
 */
package mocking.nicemock;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.niceMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use nice mocks with EasyMock.
 *
 * @author Xavier Pigeon
 */
public class NiceMockWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NiceMockWithEasyMockTest.class);

    private SystemUnderTest systemUnderTest;

    private ToBeMocked mock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock = niceMock(ToBeMocked.class);
    }

    @Test
    public void should_call_an_expected_method_in_addition_to_an_unexpected_one() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("unexpected call"));
            }
        });
        // Mock expectations
        expect(mock.method("expected call")).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
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
        expect(mock.method("expected call")).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }

    @Test
    public void should_call_expected_methods_in_any_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call #2"));
                LOGGER.debug(mock.method("expected call #3"));
                LOGGER.debug(mock.method("expected call #1"));
            }
        });
        // Mock expectations
        expect(mock.method("expected call #1")).andReturn("response #1");
        expect(mock.method("expected call #2")).andReturn("response #2");
        expect(mock.method("expected call #3")).andReturn("response #3");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }
}