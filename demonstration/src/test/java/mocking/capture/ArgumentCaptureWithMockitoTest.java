/**
 *
 */
package mocking.capture;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mocking.capturing.ArgumentWrapper;
import mocking.matching.Argument;
import mocking.model.ToBeMocked;
import mocking.model.capturing.HowItBehaves;
import mocking.model.capturing.SystemUnderTest;

/**
 * This test case shows how to use argument captures with Mockito.
 *
 * @author Xavier Pigeon
 */
public class ArgumentCaptureWithMockitoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArgumentCaptureWithMockitoTest.class);

    private ToBeMocked mock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock = Mockito.mock(ToBeMocked.class);
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

                LOGGER.debug(mock.method(argument));
            }
        });
        // Mock expectations
        when(mock.method(any(Argument.class))).thenReturn("response");

        // WHEN
        systemUnderTest
                .process(new ArgumentWrapper<Argument>(LocalDate.now(), new Argument("wrapped argument", ZERO, 0)));

        // THEN
        ArgumentCaptor<Argument> argumentCaptor = ArgumentCaptor.forClass(Argument.class);
        verify(mock).method(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getRatio()).isEqualTo(TEN);
        assertThat(argumentCaptor.getValue().getQuantity()).isEqualTo(100);
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_call_an_expected_method_receiving_captured_boolean() {
        // GIVEN
        SystemUnderTest<Boolean> systemUnderTest = new SystemUnderTest<Boolean>(new HowItBehaves<Boolean>() {

            @Override
            public void whereItCallsDependencies(ArgumentWrapper<Boolean> argumentWrapper) {
                Boolean argument = argumentWrapper.getArgument();

                // Some calculations
                argument = !argument;

                LOGGER.debug(mock.method(argument));
            }
        });
        // Mock expectations
        when(mock.method((Boolean) anyBoolean())).thenReturn("response");

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Boolean>(LocalDate.now(), false));

        // THEN
        ArgumentCaptor<Boolean> argumentCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(mock).method(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isTrue();
        verifyNoMoreInteractions(mock);
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

                LOGGER.debug(mock.method(argument));
            }
        });
        // Mock expectations
        when(mock.method((Byte) anyByte())).thenReturn("response");

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Byte>(LocalDate.now(), Byte.MIN_VALUE));

        // THEN
        ArgumentCaptor<Byte> argumentCaptor = ArgumentCaptor.forClass(Byte.class);
        verify(mock).method(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(Byte.MAX_VALUE);
        verifyNoMoreInteractions(mock);
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

                LOGGER.debug(mock.method(argument));
            }
        });
        // Mock expectations
        when(mock.method((Character) anyChar())).thenReturn("response");

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Character>(LocalDate.now(), 'a'));

        // THEN
        ArgumentCaptor<Character> argumentCaptor = ArgumentCaptor.forClass(Character.class);
        verify(mock).method(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo('z');
        verifyNoMoreInteractions(mock);
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

                LOGGER.debug(mock.method(argument));
            }
        });
        // Mock expectations
        when(mock.method((Double) anyDouble())).thenReturn("response");

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Double>(LocalDate.now(), 11d));

        // THEN
        ArgumentCaptor<Double> argumentCaptor = ArgumentCaptor.forClass(Double.class);
        verify(mock).method(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(111d);
        verifyNoMoreInteractions(mock);
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

                LOGGER.debug(mock.method(argument));
            }
        });
        // Mock expectations
        when(mock.method((Float) anyFloat())).thenReturn("response");

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Float>(LocalDate.now(), 11f));

        // THEN
        ArgumentCaptor<Float> argumentCaptor = ArgumentCaptor.forClass(Float.class);
        verify(mock).method(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(111f);
        verifyNoMoreInteractions(mock);
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

                LOGGER.debug(mock.method(argument));
            }
        });
        // Mock expectations
        when(mock.method((Integer) anyInt())).thenReturn("response");

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Integer>(LocalDate.now(), 11));

        // THEN
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(mock).method(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(111);
        verifyNoMoreInteractions(mock);
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

                LOGGER.debug(mock.method(argument));
            }
        });
        // Mock expectations
        when(mock.method((Long) anyLong())).thenReturn("response");

        // WHEN
        systemUnderTest.process(new ArgumentWrapper<Long>(LocalDate.now(), 11l));

        // THEN
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(mock).method(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(111);
        verifyNoMoreInteractions(mock);
    }
}