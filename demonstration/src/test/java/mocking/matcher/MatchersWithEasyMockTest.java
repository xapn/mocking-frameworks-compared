/**
 * 
 */
package mocking.matcher;

import static org.easymock.EasyMock.anyBoolean;
import static org.easymock.EasyMock.anyByte;
import static org.easymock.EasyMock.anyChar;
import static org.easymock.EasyMock.anyDouble;
import static org.easymock.EasyMock.anyFloat;
import static org.easymock.EasyMock.anyInt;
import static org.easymock.EasyMock.anyLong;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.anyShort;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.contains;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.endsWith;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.find;
import static org.easymock.EasyMock.geq;
import static org.easymock.EasyMock.gt;
import static org.easymock.EasyMock.leq;
import static org.easymock.EasyMock.lt;
import static org.easymock.EasyMock.matches;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.startsWith;
import static org.easymock.EasyMock.verify;

import java.time.Duration;

import mocking.model.HowItBehaves;
import mocking.model.SystemUnderTest;
import mocking.model.ToBeMocked;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test case shows how to use matchers with EasyMock.
 * 
 * @author Xavier Pigeon
 */
public class MatchersWithEasyMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchersWithEasyMockTest.class);

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
        expect(mock.method(anyBoolean())).andReturn("response");
        expect(mock.method(anyByte())).andReturn("response");
        expect(mock.method(anyChar())).andReturn("response");
        expect(mock.method(anyDouble())).andReturn("response");
        expect(mock.method(anyFloat())).andReturn("response");
        expect(mock.method(anyInt())).andReturn("response");
        expect(mock.method(anyLong())).andReturn("response");
        expect(mock.method(anyObject(Duration.class))).andReturn("response");
        expect(mock.method(anyShort())).andReturn("response");
        expect(mock.method(anyString())).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
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
                LOGGER.debug(mock.method("56791fzpkegj"));
                LOGGER.debug(mock.method("fzpkegj56791"));
            }
        });
        // Mock expectations
        expect(mock.method(startsWith("prefix"))).andReturn("response");
        expect(mock.method(contains("substring"))).andReturn("response");
        expect(mock.method(endsWith("suffix"))).andReturn("response");
        expect(mock.method(matches("[1-9a-z]*"))).andReturn("response");
        expect(mock.method(find("[1-9A-Z]*"))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }

    @Test
    public void should_call_expected_methods_receiving_numbers() {
        // GIVEN
        systemUnderTest = new SystemUnderTest(new HowItBehaves() {

            @Override
            public void whereItCallsDependencies() {
                LOGGER.debug(mock.method(50d));
                LOGGER.debug(mock.method(150d));
                LOGGER.debug(mock.method(100d));
                LOGGER.debug(mock.method(100d));
                LOGGER.debug(mock.method(105d));
            }
        });
        // Mock expectations
        expect(mock.method(lt(100d))).andReturn("response");
        expect(mock.method(gt(100d))).andReturn("response");
        expect(mock.method(leq(100d))).andReturn("response");
        expect(mock.method(geq(100d))).andReturn("response");
        expect(mock.method(eq(100d, 10d))).andReturn("response");
        replay(mock);

        // WHEN
        systemUnderTest.process();

        // THEN
        verify(mock);
    }
}