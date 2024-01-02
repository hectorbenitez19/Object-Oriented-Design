package cs3500.hw01.duration;

/**
 * Durations represented compactly, with a range of 0 to 2<sup>63</sup>-1 seconds.
 */
public final class CompactDuration extends AbstractDuration {

  /**
   * Constructs a duration in terms of its length in hours, minutes, and seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of inSeconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public CompactDuration(int hours, int minutes, int seconds) {
    ensureHms(hours, minutes, seconds);
    this.inSeconds = inSeconds(hours, minutes, seconds);
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public CompactDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    this.inSeconds = inSeconds;
  }

  private final long inSeconds;

  @Override
  protected Duration fromSeconds(long seconds) {
    return new CompactDuration(seconds);
  }


  @Override
  public long inSeconds() {
    return inSeconds;
  }

  @Override
  public String asHms() {
    return String.format("%d:%02d:%02d",
        hoursOf(inSeconds),
        minutesOf(inSeconds),
        secondsOf(inSeconds));
  }
}

//format for the compactDuration class before it was abstracted

/*
  @Override
  public String format(String template) {
    if (template == null) {
      throw new IllegalArgumentException("template cannot be null");
    }
    String output = "";
    for (int i = 0; i < template.length(); i++) {
      if (template.substring(i, i + 1).equals("%")) {
        if (template.substring(i + 1, i + 2).equals("t")) {
          output += this.inSeconds;
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("h")) {
          if (hoursOf(inSeconds) > 9) {
            throw new IllegalArgumentException("cannot pad hours");
          }
          output += hoursOf(inSeconds);
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("H")) {
          if (hoursOf(inSeconds) < 10) {
            output += 0;
          }
          output += hoursOf(inSeconds);
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("m")) {
          if (minutesOf(inSeconds) > 9) {
            throw new IllegalArgumentException("cannot pad minutes");
          }
          output += minutesOf(inSeconds);
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("M")) {
          if (minutesOf(inSeconds) < 10) {
            output += 0;
          }
          output += minutesOf(inSeconds);
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("s")) {
          if (secondsOf(inSeconds) > 9) {
            throw new IllegalArgumentException("cannot pad seconds");
          }
          output += secondsOf(inSeconds);
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("S")) {
          if (secondsOf(inSeconds) < 10) {
            output += 0;
          }
          output += secondsOf(inSeconds);
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