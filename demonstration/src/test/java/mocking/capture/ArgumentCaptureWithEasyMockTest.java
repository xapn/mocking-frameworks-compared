/**
 * 
 */
package mocking.capture;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.newCapture;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.time.LocalDate;

import mocking.capturing.ArgumentWrapper;
import mocking.matching.Argument;
import mocking.model.ToBeMocked;
import mocking.model.capturing.HowItBehaves;
import mocking.model.capturing.SystemUnderTest;

import org.easymock.Capture;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use argument captures with EasyMock.
 * 
 * @author Xavier Pigeon
 */
public class ArgumentCaptureWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArgumentCaptureWithEasyMockTest.class);

    private ToBeMocked mock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock = createMock(ToBeMocked.class);
    }

    @Test
    public void should_call_an_expected_method_receiving_captured_argument() {
        // GIVEN
        SystemUnderTest<Argument> systemUnderTest = new SystemUnderTest<Argument>(new HowItBehaves<Argument>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Argument> argumentWrapper) {
                Argument argument = argumentWrapper.getArgument();

                // Some calculations
                argument.setRatio(TEN);
                argument.setQuantity(100);

                LOGGER.debug(mock.method(argumentWrapper.getArgument()));
            }
        });
        // Mock expectations
        Capture<Argument> captured = newCapture();
        expect(mock.method(capture(captured))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Argument>(LocalDate.now(),
                new Argument("wrapped argument", ZERO, 0)));

        // THEN
        verify(mock);
        assertThat(captured.getValue().getRatio()).isGreaterThan(ZERO);
        assertThat(captured.getValue().getQuantity()).isGreaterThan(0);
    }
}