/**
 *
 */
package mocking.regularmock;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
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
 * This test case shows how to use regular mocks with EasyMock.
 *
 * @author Xavier Pigeon
 */
public class RegularMockWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegularMockWithEasyMockTest.class);

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
    public void should_call_an_expected_method() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call"));
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
    public void should_call_expected_methods_without_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("expected call #5"));
                LOGGER.debug(mock.method("expected call #1"));
                LOGGER.debug(mock.method("expected call #3"));
                LOGGER.debug(mock.method("expected call #2"));
                LOGGER.debug(mock.method("expected call #4"));
            }
        });
        // Mock expectations
        expect(mock.method("expected call #1")).andReturn("response #1");
        expect(mock.method("expected call #2")).andReturn("response #2");
        expect(mock.method("expected call #3")).andReturn("response #3");
        expect(mock.method("expected call #4")).andReturn("response #4");
        expect(mock.method("expected call #5")).andReturn("response #5");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
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
        expect(mock.method("expected call #1")).andReturn("response #1");
        expect(mock.method("expected call #2")).andReturn("response #2");
        expect(mock.method("expected call #3")).andReturn("response #3");
        expect(mock.method("expected call #4")).andReturn("response #4");
        expect(mock.method("expected call #5")).andReturn("response #5");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }
}