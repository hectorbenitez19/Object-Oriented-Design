package cs3500.hw01.duration;

/**
 * Abstract base class for implementations of {@link Duration}.
 */
abstract class AbstractDuration implements Duration {

  /**
   * Constructs a {@link Duration} in a manner selected by concrete subclasses of this class.
   *
   * @param inSeconds the length in seconds
   * @return the new {@code Duration}
   */
  protected abstract Duration fromSeconds(long inSeconds);

  @Override
  public String toString() {
    return asHms();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Duration)) {
      return false;
    }

    return ((Duration) that).inSeconds() == this.inSeconds();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(inSeconds());
  }

  @Override
  public int compareTo(Duration that) {
    return Long.compare(this.inSeconds(), that.inSeconds());
  }

  @Override
  public Duration plus(Duration that) {
    return fromSeconds(this.inSeconds() + that.inSeconds());
  }

  /**
   * Converts an unpacked hours-minutes-seconds duration to its length in seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @return the duration in seconds
   */
  protected static long inSeconds(int hours, int minutes, int seconds) {
    return 3600 * hours + 60 * minutes + seconds;
  }

  /**
   * Formats an unpacked hours-minutes-seconds duration in the same {@code H:MM:SS} format that
   * {@link Duration#asHms()} returns. Assumes that
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @return formatted duration
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static String asHms(int hours, int minutes, int seconds) {
    return String.format("%d:%02d:%02d", hours, minutes, seconds);
  }

  /**
   * Ensures that the hours, minutes, and seconds are all non-negative. Is factoring this out
   * overkill? Or should we also factor out the {@code inSeconds < 0} check in the two unary
   * constructors? Discuss.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static void ensureHms(int hours, int minutes, int seconds) {
    if (hours < 0 || minutes < 0 || seconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }
  }

  /**
   * Returns the number of whole hours in the given number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of hours
   * @throws ArithmeticException if the correct result cannot fit in an {@code int}.
   */
  protected static int hoursOf(long inSeconds) {
    if (inSeconds / 3600 > Integer.MAX_VALUE) {
      throw new ArithmeticException("result cannot fit in type");
    }

    return (int) (inSeconds / 3600);
  }

  /**
   * Returns the number of whole minutes in the given number of seconds, less the number of whole
   * hours.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining minutes
   */
  protected static int minutesOf(long inSeconds) {
    return (int) (inSeconds / 60 % 60);
  }

  /**
   * Returns the number of seconds remaining after all full minutes are removed from the given
   * number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining seconds
   */
  protected static int secondsOf(long inSeconds) {
    return (int) (inSeconds % 60);
  }


  /**
   * The format method abstracted to work with classes that extend this one a for loop is made to
   * check each individual character to look for instances of format specifiers as soon as the loop
   * finds one it checks the character after it to see if it is a valid or invalid specifier and
   * handles each case accordingly if the first character is not a specifier then it is
   * automatically added to the final output string.
   *
   * @param template the template is a string that may or may not have format specifiers and other
   *                 strings must follow the rules of the method stated in the duration interface.
   * @return returns a string formatted correctly with fields from a duration subclass.
   */
  public String format(String template) throws IllegalArgumentException {
    if (template == null) {
      throw new IllegalArgumentException("template cannot be null");
    }
    String output = "";

    //loops through each single string to look for specifiers
    for (int i = 0; i < template.length(); i++) {

      //if it does start with a % go through each case to see what type of specifier it is
      if (template.charAt(i) == ('%')) {

        if (i == template.length() - 1) {
          throw new IllegalArgumentException("invalid specifier");
        }

        if (template.charAt(i + 1) == ('t')) {
          output += this.inSeconds();
          if (i == template.length() - 1) {
            break;
          } else {
            i += 1;
          }
        } else if (template.charAt(i + 1) == ('h')) {

          output += hoursOf(inSeconds());
          if (i == template.length() - 1) {
            break;
          } else {
            i += 1;
          }
        } else if (template.charAt(i + 1) == ('H')) {
          if (hoursOf(inSeconds()) < 10) {
            output += 0;
          }
          output += hoursOf(inSeconds());
          if (i == template.length() - 1) {
            break;
          } else {
            i += 1;
          }
        } else if (template.charAt(i + 1) == ('m')) {

          output += minutesOf(inSeconds());
          if (i == template.length() - 1) {
            break;
          } else {
            i += 1;
          }
        } else if (template.charAt(i + 1) == ('M')) {
          if (minutesOf(inSeconds()) < 10) {
            output += 0;
          }
          output += minutesOf(inSeconds());
          if (i == template.length() - 1) {
            break;
          } else {
            i += 1;
          }
        } else if (template.charAt(i + 1) == ('s')) {

          output += secondsOf(inSeconds());
          if (i == template.length() - 1) {
            break;
          } else {
            i += 1;
          }
        } else if (template.charAt(i + 1) == ('S')) {
          if (secondsOf(inSeconds()) < 10) {
            output += 0;
          }
          output += secondsOf(inSeconds());
          if (i == template.length() - 1) {
            break;
          } else {
            i += 1;
          }
        } else if (template.charAt(i + 1) == ('%')) {
          if (i == template.length() - 1) {
            break;
          }
          output += "%";
          i += 1;
        }

        //if it is an invalid specifier throw an error
        else {
          throw new IllegalArgumentException("invalid specifier");
        }

      }

      //if there isnt a specifier copy what is there onto the output string
      else {
        output += template.substring(i, i + 1);
      }

    }
    return output;
  }


}

//old format method using substring instead of charAt

/*
  public String format(String template) {
    if (template == null) {
      throw new IllegalArgumentException("template cannot be null");
    }
    String output = "";

    //loops through each single string to look for specifiers
    for (int i = 0; i < template.length(); i++) {

      //if it does start with a % go through checks to see what type of specifier it is
      if (template.substring(i, i + 1).equals("%")) {

        if (template.substring(i + 1, i + 2).equals("t")) {
          output += this.inSeconds();
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("h")) {
          if (hoursOf(inSeconds()) > 9) {
            throw new IllegalArgumentException("cannot pad hours");
          }
          output += hoursOf(inSeconds());
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("H")) {
          if (hoursOf(inSeconds()) < 10) {
            output += 0;
          }
          output += hoursOf(inSeconds());
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("m")) {
          if (minutesOf(inSeconds()) > 9) {
            throw new IllegalArgumentException("cannot pad minutes");
          }
          output += minutesOf(inSeconds());
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("M")) {
          if (minutesOf(inSeconds()) < 10) {
            output += 0;
          }
          output += minutesOf(inSeconds());
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("s")) {
          if (secondsOf(inSeconds()) > 9) {
            throw new IllegalArgumentException("cannot pad seconds");
          }
          output += secondsOf(inSeconds());
          if (i == template.length() - 2) {
            break;
          } else {
            i += 1;
          }
        }

        if (template.substring(i + 1, i + 2).equals("S")) {
          if (secondsOf(inSeconds()) < 10) {
            output += 0;
          }
          output += secondsOf(inSeconds());
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
        else {
          throw new IllegalArgumentException("invalid specifier");
        }

      }
      //if there isnt a specifier copy what is there onto output
      else {
        output += template.substring(i, i + 1);
      }

    }
    return output;
  }

*/
