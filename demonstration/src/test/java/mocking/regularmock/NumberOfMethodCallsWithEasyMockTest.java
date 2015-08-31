/**
 *
 */
package mocking.regularmock;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

/**
 * This test case shows how to control the number of method calls with EasyMock.
 *
 * @author Xavier Pigeon
 */
public class NumberOfMethodCallsWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberOfMethodCallsWithEasyMockTest.class);

    private SystemUnderTest systemUnderTest;

    private ToBeMocked mock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock = createMock(ToBeMocked.class);
    }

    @Test
    public void should_call_an_expected_method_a_given_number_of_times() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        expect(mock.method("expected call")).andReturn("response").times(3);
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }

    @Test
    public void should_call_an_expected_method_at_least_once() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        expect(mock.method("expected call")).andReturn("response").atLeastOnce();
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }

    @Test
    public void should_call_an_expected_method_between_x_and_y_times() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
                LOGGER.debug(mock.method("expected call"));
            }
        });
        // Mock expectations
        expect(mock.method("expected call")).andReturn("response").times(2, 5);
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }

    @Test
    public void should_call_an_expected_method_any_times() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                for (int count = 0; count < 3; count++) {
                    LOGGER.debug(mock.method("expected call"));
                }
            }
        });
        // Mock expectations
        expect(mock.method("expected call")).andReturn("response").anyTimes();
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }
}