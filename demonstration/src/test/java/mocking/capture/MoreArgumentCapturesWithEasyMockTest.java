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

import java.math.BigDecimal;
import java.time.LocalDate;

import mocking.capturing.ArgumentWrapper;
import mocking.matching.Argument;
import mocking.model.ToBeMocked;
import mocking.model.capturing.HowItBehaves;
import mocking.model.capturing.SystemUnderTest;

import org.easymock.Capture;
import org.easymock.CaptureType;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use argument captures with EasyMock.
 *
 * @author Xavier Pigeon
 */
public class MoreArgumentCapturesWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoreArgumentCapturesWithEasyMockTest.class);

    private SystemUnderTest<Argument> systemUnderTest;

    private ToBeMocked mock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock = createMock(ToBeMocked.class);
    }

    /**
     * This is the default behavior.
     */
    @Test
    public void should_call_an_expected_method_capturing_the_argument_at_the_last_call() {
        // GIVEN
        systemUnderTest = new SystemUnderTest<Argument>(new HowItBehaves<Argument>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Argument> argumentWrapper) {
                Argument argument = argumentWrapper.getArgument();

                // Some calculations
                for (int count = 0; count < 3; count++) {
                    argument.setRatio(argument.getRatio().add(TEN));
                    argument.setQuantity(argument.getQuantity() + 100);
                    LOGGER.debug(mock.method(new Argument(argument)));
                }
            }
        });
        // Mock expectations
        Capture<Argument> captured = newCapture(CaptureType.LAST);
        expect(mock.method(capture(captured))).andReturn("response").atLeastOnce();
        replay(mock);

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Argument>(LocalDate.now(),
                new Argument("wrapped argument", ZERO, 0)));

        // THEN
        verify(mock);
        assertThat(captured.getValue().getRatio()).isEqualTo(new BigDecimal("30"));
        assertThat(captured.getValue().getQuantity()).isEqualTo(300);
    }
}