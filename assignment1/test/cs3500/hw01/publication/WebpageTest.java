package cs3500.hw01.publication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A test class for webpages makes sure that webpages are cited correctly and do not have any null
 * fields.
 */
public class WebpageTest {

  Publication cs3500_v2 = new Webpage("CS3500: Object-Oriented Design",
      "http://www.ccs.neu.edu/course/cs3500/",
      "August 11, 2014");


  @Test
  public void testCiteApa() {
    assertEquals("CS3500: Object-Oriented Design. " + "retrieved " +
            "August 11, 2014, " + "from " +
            "http://www.ccs.neu.edu/course/cs3500/.",
        cs3500_v2.citeApa());
  }

  @Test
  public void testCiteMla() {
    assertEquals("\"CS3500: Object-Oriented Design.\"" + " Web. " +
            "August 11, 2014 " +
            "<http://www.ccs.neu.edu/course/cs3500/>.",
        cs3500_v2.citeMla());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testCiteMlaNullField() {
    Publication cs3520_v3 = new Webpage("CS3520: Programming C++",
        "http://www.ccs.neu.edu/course/cs3520/",
        null);
  }


}
