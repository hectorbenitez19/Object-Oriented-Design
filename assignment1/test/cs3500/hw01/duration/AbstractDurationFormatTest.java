package cs3500.hw01.duration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the format method of {@link Duration}s. Add your tests to this class to assure that
 * your format method works properly
 */
public abstract class AbstractDurationFormatTest {

  @Test
  public void formatExample1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds",
        hms(4, 0, 9)
            .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample2() {
    assertEquals("4:05:17",
        hms(4, 5, 17).format("%h:%M:%S"));
  }


  @Test
  public void testSecDurationFormat1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds",
        sec(14409)
            .format("%h hours, %m minutes, and %s seconds"));
  }


  @Test
  public void testSecDurationFormat2() {
    assertEquals("4:05:17",
        sec(14717).format("%h:%M:%S"));
  }


  @Test
  public void testDurationPadded() {
    assertEquals("10 hours, 10 minutes, and 29 seconds",
        sec(36629)
            .format("%H hours, %M minutes, and %S seconds"));
  }

  @Test
  public void testDurationPaddedWithZero() {
    assertEquals("05 hours, 07 minutes, and 09 seconds",
        sec(18429)
            .format("%H hours, %M minutes, and %S seconds"));
  }


  @Test
  public void testNoPaddedSpecifiers2() {
    assertEquals("10 hours, 10 minutes, and 29 seconds",
        sec(36629)
            .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void testTotalDurationFormat() {
    assertEquals("total duration is 36629 seconds",
        sec(36629)
            .format("total duration is %t seconds"));
  }


  @Test
  public void testMinuteToHourConversionFormat() {
    assertEquals("2 hours, 0 minutes, and 0 seconds",
        hms(1, 60, 0)
            .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void testFormatSpecifierPrecedenceEven() {
    assertEquals("%h hours, 0 minutes, and 0 seconds",
        hms(1, 60, 0)
            .format("%%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void testFormatSpecifierPrecedenceOdd() {
    assertEquals("%2 hours, 0 minutes, and 0 seconds",
        hms(1, 60, 0)
            .format("%%%h hours, %m minutes, and %s seconds"));
  }


  @Test
  public void testFormatSpecifierPrecedence() {
    assertEquals("%%h hours, 0 minutes, and 0 seconds",
        hms(1, 60, 0)
            .format("%%%%h hours, %m minutes, and %s seconds"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testformatTemplateNull() {
    assertEquals("%2 hours, 0 minutes, and 0 seconds",
        hms(1, 60, 0)
            .format(null));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpecifier() {
    assertEquals("10 minutes",
        sec(36629)
            .format("%k minutes"));
  }

  @Test
  public void testEmptyTemplate() {
    assertEquals("",
        sec(36629)
            .format(""));
  }

  @Test
  public void testNoSpecifiers() {
    assertEquals("no hours, no minutes, and no seconds",
        sec(36629)
            .format("no hours, no minutes, and no seconds"));
  }

  @Test
  public void testSpecifierEdgeCase() {
    assertEquals("%",
        sec(36629)
            .format("%%"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOnlySpecifierOdd() {
    assertEquals("%",
        sec(36629)
            .format("%%%"));
  }

  // ADD MORE TESTS HERE.
  // THE ABOVE TEST NAMES ARE MERE PLACEHOLDERS. NAME YOUR TESTS MEANINGFULLY. 
  // IF YOU NAME YOUR TESTS SIMILAR TO ABOVE, YOU WILL LOSE POINTS!
  // Your tests must only use hms(...) and sec(...) to construct new Durations
  // and must *not* directly say "new CompactDuration(...)" or
  // "new HmsDuration(...)"




  

  /*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within 
    your tests.
   */

  /**
   * Constructs an instance of the class under test representing the duration given in hours,
   * minutes, and seconds.
   *
   * @param hours   the hours in the duration
   * @param minutes the minutes in the duration
   * @param seconds the seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration hms(int hours, int minutes, int seconds);

  /**
   * Constructs an instance of the class under test representing the duration given in seconds.
   *
   * @param inSeconds the total seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration sec(long inSeconds);

  /**
   * Concrete class for testing HmsDuration implementation of Duration.
   */
  public static final class HmsDurationTest extends AbstractDurationFormatTest {

    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  /**
   * Concrete class for testing CompactDuration implementation of Duration.
   */
  public static final class CompactDurationTest extends AbstractDurationFormatTest {

    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new CompactDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new CompactDuration(inSeconds);
    }
  }
}
