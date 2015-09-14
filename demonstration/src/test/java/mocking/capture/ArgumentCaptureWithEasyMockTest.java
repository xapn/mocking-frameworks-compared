/**
 * 
 */
package mocking.capture;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.captureBoolean;
import static org.easymock.EasyMock.captureByte;
import static org.easymock.EasyMock.captureChar;
import static org.easymock.EasyMock.captureDouble;
import static org.easymock.EasyMock.captureFloat;
import static org.easymock.EasyMock.captureInt;
import static org.easymock.EasyMock.captureLong;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
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
        mock = mock(ToBeMocked.class);
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

    @Test
    public void should_call_an_expected_method_receiving_captured_boolean() {
        // GIVEN
        SystemUnderTest<Boolean> systemUnderTest = new SystemUnderTest<Boolean>(new HowItBehaves<Boolean>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Boolean> argumentWrapper) {
                Boolean flag = argumentWrapper.getArgument();

                // Some calculations
                flag = !flag;

                LOGGER.debug(mock.method(flag.booleanValue()));
            }
        });
        // Mock expectations
        Capture<Boolean> captured = newCapture();
        expect(mock.method(captureBoolean(captured))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Boolean>(LocalDate.now(), false));

        // THEN
        verify(mock);
        assertThat(captured.getValue()).isTrue();
    }

    @Test
    public void should_call_an_expected_method_receiving_captured_byte() {
        // GIVEN
        SystemUnderTest<Byte> systemUnderTest = new SystemUnderTest<Byte>(new HowItBehaves<Byte>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Byte> argumentWrapper) {
                Byte argument = argumentWrapper.getArgument();

                // Some calculations
                argument = Byte.MAX_VALUE;

                LOGGER.debug(mock.method(argument.byteValue()));
            }
        });
        // Mock expectations
        Capture<Byte> captured = newCapture();
        expect(mock.method(captureByte(captured))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Byte>(LocalDate.now(), Byte.MIN_VALUE));

        // THEN
        verify(mock);
        assertThat(captured.getValue()).isEqualTo(Byte.MAX_VALUE);
    }

    @Test
    public void should_call_an_expected_method_receiving_captured_character() {
        // GIVEN
        SystemUnderTest<Character> systemUnderTest = new SystemUnderTest<Character>(new HowItBehaves<Character>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Character> argumentWrapper) {
                Character argument = argumentWrapper.getArgument();

                // Some calculations
                argument = 'z';

                LOGGER.debug(mock.method(argument.charValue()));
            }
        });
        // Mock expectations
        Capture<Character> captured = newCapture();
        expect(mock.method(captureChar(captured))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Character>(LocalDate.now(), 'a'));

        // THEN
        verify(mock);
        assertThat(captured.getValue()).isEqualTo('z');
    }

    @Test
    public void should_call_an_expected_method_receiving_captured_double() {
        // GIVEN
        SystemUnderTest<Double> systemUnderTest = new SystemUnderTest<Double>(new HowItBehaves<Double>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Double> argumentWrapper) {
                Double argument = argumentWrapper.getArgument();

                // Some calculations
                argument += 100;

                LOGGER.debug(mock.method(argument.doubleValue()));
            }
        });
        // Mock expectations
        Capture<Double> captured = newCapture();
        expect(mock.method(captureDouble(captured))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Double>(LocalDate.now(), 11d));

        // THEN
        verify(mock);
        assertThat(captured.getValue()).isEqualTo(111d);
    }

    @Test
    public void should_call_an_expected_method_receiving_captured_float() {
        // GIVEN
        SystemUnderTest<Float> systemUnderTest = new SystemUnderTest<Float>(new HowItBehaves<Float>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Float> argumentWrapper) {
                Float argument = argumentWrapper.getArgument();

                // Some calculations
                argument += 100;

                LOGGER.debug(mock.method(argument.floatValue()));
            }
        });
        // Mock expectations
        Capture<Float> captured = newCapture();
        expect(mock.method(captureFloat(captured))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Float>(LocalDate.now(), 11f));

        // THEN
        verify(mock);
        assertThat(captured.getValue()).isEqualTo(111f);
    }

    @Test
    public void should_call_an_expected_method_receiving_captured_integer() {
        // GIVEN
        SystemUnderTest<Integer> systemUnderTest = new SystemUnderTest<Integer>(new HowItBehaves<Integer>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Integer> argumentWrapper) {
                Integer argument = argumentWrapper.getArgument();

                // Some calculations
                argument += 100;

                LOGGER.debug(mock.method(argument.intValue()));
            }
        });
        // Mock expectations
        Capture<Integer> captured = newCapture();
        expect(mock.method(captureInt(captured))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Integer>(LocalDate.now(), 11));

        // THEN
        verify(mock);
        assertThat(captured.getValue()).isEqualTo(111);
    }

    @Test
    public void should_call_an_expected_method_receiving_captured_long() {
        // GIVEN
        SystemUnderTest<Long> systemUnderTest = new SystemUnderTest<Long>(new HowItBehaves<Long>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Long> argumentWrapper) {
                Long argument = argumentWrapper.getArgument();

                // Some calculations
                argument += 100;

                LOGGER.debug(mock.method(argument.longValue()));
            }
        });
        // Mock expectations
        Capture<Long> captured = newCapture();
        expect(mock.method(captureLong(captured))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Long>(LocalDate.now(), 11l));

        // THEN
        verify(mock);
        assertThat(captured.getValue()).isEqualTo(111);
    }
}