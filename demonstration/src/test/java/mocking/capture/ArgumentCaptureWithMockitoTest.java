/**
 * 
 */
package mocking.capture;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import mocking.capturing.ArgumentWrapper;
import mocking.matching.Argument;
import mocking.model.ToBeMocked;
import mocking.model.capturing.HowItBehaves;
import mocking.model.capturing.SystemUnderTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        systemUnderTest.process(new ArgumentWrapper<Argument>(LocalDate.now(),
                new Argument("wrapped argument", ZERO, 0)));

        // THEN
        ArgumentCaptor<Argument> argumentCaptor = ArgumentCaptor.forClass(Argument.class);
        verify(mock).method(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getRatio()).isEqualTo(TEN);
        assertThat(argumentCaptor.getValue().getQuantity()).isEqualTo(100);
        verifyNoMoreInteractions(mock);
    }
}