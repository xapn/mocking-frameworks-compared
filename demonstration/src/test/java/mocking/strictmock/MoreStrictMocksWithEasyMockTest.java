/**
 * 
 */
package mocking.strictmock;

import static org.easymock.EasyMock.createStrictControl;
import static org.easymock.EasyMock.expect;
import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use several strict mocks with EasyMock.
 * 
 * @author Xavier Pigeon
 */
public class MoreStrictMocksWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoreStrictMocksWithEasyMockTest.class);

    private SystemUnderTest systemUnderTest;

    private ToBeMocked mock1;

    private ToBeMocked mock2;

    private IMocksControl mocksControl;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mocksControl = createStrictControl();
        mock1 = mocksControl.createMock(ToBeMocked.class);
        mock2 = mocksControl.createMock(ToBeMocked.class);
    }

    @Test
    public void should_call_expected_methods_in_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock1.method("expected call #1"));
                LOGGER.debug(mock2.method("expected call #2"));
                LOGGER.debug(mock1.method("expected call #3"));
                LOGGER.debug(mock2.method("expected call #4"));
            }
        });
        // Mock expectations
        expect(mock1.method("expected call #1")).andReturn("response #1");
        expect(mock2.method("expected call #2")).andReturn("response #2");
        expect(mock1.method("expected call #3")).andReturn("response #3");
        expect(mock2.method("expected call #4")).andReturn("response #4");
        mocksControl.replay();

        // WHEN
        systemUnderTest.process();

        // THEN
        mocksControl.verify();
    }

    @Test(expected = AssertionError.class)
    public void should_call_expected_methods_in_any_order() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock1.method("expected call #1"));
                LOGGER.debug(mock1.method("expected call #3"));
                LOGGER.debug(mock2.method("expected call #2"));
                LOGGER.debug(mock2.method("expected call #4"));
            }
        });
        // Mock expectations
        expect(mock1.method("expected call #1")).andReturn("response #1");
        expect(mock2.method("expected call #2")).andReturn("response #2");
        expect(mock1.method("expected call #3")).andReturn("response #3");
        expect(mock2.method("expected call #4")).andReturn("response #4");
        mocksControl.replay();

        // WHEN
        systemUnderTest.process();

        // THEN
        mocksControl.verify();
    }
}