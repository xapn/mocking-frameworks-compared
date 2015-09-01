/**
 *
 */
package mocking.voidmethod;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expectLastCall;
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
 * This test case shows how to stub a void method with EasyMock.
 *
 * @author Xavier Pigeon
 */
public class VoidMethodWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoidMethodWithEasyMockTest.class);

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
    public void should_call_an_expected_void_method() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                mock.voidMethod("expected call");
            }
        });
        // Mock expectations
        mock.voidMethod("expected call");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }

    @Test
    public void should_call_an_expected_void_method_throwing_exception() throws Exception {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                try {
                    mock.voidMethodThrowingException("expected call");
                } catch (Exception e) {
                    LOGGER.debug(e.getMessage());
                }
            }
        });
        // Mock expectations
        mock.voidMethodThrowingException("expected call");
        expectLastCall().andThrow(new Exception("This void method throws an exception!"));
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }
}