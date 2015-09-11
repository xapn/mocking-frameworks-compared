/**
 * 
 */
package mocking.matcher;

import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.AdditionalMatchers.find;
import static org.mockito.AdditionalMatchers.geq;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.AdditionalMatchers.leq;
import static org.mockito.AdditionalMatchers.lt;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyShort;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Matchers.matches;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.Duration;

import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use argument matchers with Mockito.
 * 
 * @author Xavier Pigeon
 */
public class MatchersWithMockitoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchersWithMockitoTest.class);

    private SystemUnderTest systemUnderTest;

    private ToBeMocked mock;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mock = mock(ToBeMocked.class);
    }

    @Test
    public void should_call_expected_methods_receiving_any_value_of_any_type() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method(true));
                LOGGER.debug(mock.method((byte) 123));
                LOGGER.debug(mock.method('a'));
                LOGGER.debug(mock.method((double) 123));
                LOGGER.debug(mock.method((float) 123));
                LOGGER.debug(mock.method((int) 123));
                LOGGER.debug(mock.method((long) 123));
                LOGGER.debug(mock.method(Duration.ofMillis(500)));
                LOGGER.debug(mock.method((short) 123));
                LOGGER.debug(mock.method("any string"));
            }
        });
        // Mock expectations
        when(mock.method(anyBoolean())).thenReturn("response");
        when(mock.method(anyByte())).thenReturn("response");
        when(mock.method(anyChar())).thenReturn("response");
        when(mock.method(anyDouble())).thenReturn("response");
        when(mock.method(anyFloat())).thenReturn("response");
        when(mock.method(anyInt())).thenReturn("response");
        when(mock.method(anyLong())).thenReturn("response");
        when(mock.method(any(Duration.class))).thenReturn("response");
        when(mock.method(anyShort())).thenReturn("response");
        when(mock.method(anyString())).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).method(anyBoolean());
        verify(mock).method(anyByte());
        verify(mock).method(anyChar());
        verify(mock).method(anyDouble());
        verify(mock).method(anyFloat());
        verify(mock).method(anyInt());
        verify(mock).method(anyLong());
        verify(mock).method(any(Duration.class));
        verify(mock).method(anyShort());
        verify(mock).method(anyString());
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_call_expected_methods_receiving_some_kinds_of_string() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method("prefix the argument is expected to start with"));
                LOGGER.debug(mock.method("the argument should contain the expected substring somewhere"));
                LOGGER.debug(mock.method("the argument should end with the expected suffix"));
                LOGGER.debug(mock.method("_prefix _sub-string_ suffix_"));
                LOGGER.debug(mock.method("506791fzpkegj"));
                LOGGER.debug(mock.method("FREGORG506791"));
            }
        });
        // Mock expectations
        when(mock.method(startsWith("prefix"))).thenReturn("response");
        when(mock.method(contains("substring"))).thenReturn("response");
        when(mock.method(endsWith("suffix"))).thenReturn("response");
        when(mock.method(and(and(startsWith("_prefix"), endsWith("suffix_")), contains("_sub-string_"))))
                .thenReturn("response");
        when(mock.method(matches("^[0-9]*[a-z]*$"))).thenReturn("response");
        when(mock.method(find("^[A-Z]*[0-9]*$"))).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).method(startsWith("prefix"));
        verify(mock).method(contains("substring"));
        verify(mock).method(endsWith("suffix"));
        verify(mock).method(and(and(startsWith("_prefix"), endsWith("suffix_")), contains("_sub-string_")));
        verify(mock).method(matches("^[0-9]*[a-z]*$"));
        verify(mock).method(find("^[A-Z]*[0-9]*$"));
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_call_expected_methods_receiving_numbers_1() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method(50d));
                LOGGER.debug(mock.method(1000d));
                LOGGER.debug(mock.method(505d));
            }
        });
        // Mock expectations
        when(mock.method(lt(100d))).thenReturn("response");
        when(mock.method(geq(1000d))).thenReturn("response");
        when(mock.method(eq(500d, 10d))).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).method(lt(100d));
        verify(mock).method(geq(1000d));
        verify(mock).method(eq(500d, 10d));
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void should_call_expected_methods_receiving_numbers_2() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method(100d));
                LOGGER.debug(mock.method(1001d));
            }
        });
        // Mock expectations
        when(mock.method(leq(100d))).thenReturn("response");
        when(mock.method(gt(1000d))).thenReturn("response");

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock).method(leq(100d));
        verify(mock).method(gt(1000d));
        verifyNoMoreInteractions(mock);
    }
}