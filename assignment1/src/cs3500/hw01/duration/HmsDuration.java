package cs3500.hw01.duration;

/**
 * Durations represented as hours, minutes, and seconds.
 */
public final class HmsDuration extends AbstractDuration {

  /**
   * Constructs a duration in terms of its length in hours, minutes, and seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public HmsDuration(int hours, int minutes, int seconds) {
    this(inSeconds(hours, minutes, seconds));
    ensureHms(hours, minutes, seconds);
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public HmsDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    seconds = secondsOf(inSeconds);
    minutes = minutesOf(inSeconds);
    hours = hoursOf(inSeconds);
  }

  private final int hours;
  private final int minutes;
  private final int seconds;

  @Override
  protected AbstractDuration fromSeconds(long seconds) {
    return new HmsDuration(seconds);
  }

  @Override
  public long inSeconds() {
    return inSeconds(hours, minutes, seconds);
  }

  @Override
  public String asHms() {
    return asHms(hours, minutes, seconds);
  }
}

// format for the HmsDuration class before being abstracted

/*
  @Override
  public String format(String template) {
    String output = "";
    for (int i = 0; i < template.length(); i++) {

      if (template.substring(i, i + 1).equals("%")) {

        if (template.substring(i + 1, i + 2).equals("t")) {
          output += this.inSeconds();
        }

        if (template.substring(i + 1, i + 2).equals("h")) {
          if (this.hours > 9) {
            throw new IllegalArgumentException("cannot pad hours");
          }
          output += this.hours;
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("H")) {
          output += this.hours;
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("m")) {
          if (this.minutes > 9) {
            throw new IllegalArgumentException("cannot pad minutes");
          }
          output += this.minutes;
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("M")) {
          if (this.minutes < 10) {
            output += 0;
          }
          output += this.minutes;
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("s")) {
          if (this.seconds > 9) {
            throw new IllegalArgumentException("cannot pad seconds");
          }
          output += this.seconds;
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("S")) {
          output += this.seconds;
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("%")) {
          output += "%";
          i += 1;
        }

      } else {
        output += template.substring(i, i + 1);
      }

    }
    return output;
  }
*/
