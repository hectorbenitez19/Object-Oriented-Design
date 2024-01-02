package cs3500.hw01.publication;

/**
 * Represents bibliographic information for webpages.
 */

public class Webpage implements Publication {

  private final String title;
  private final String url;
  private final String retrieved;

  /**
   * constructs a webpage using the paramaters provided.
   *
   * @param title     the title of the webpage
   * @param url       the link to the webpage
   * @param retrieved the date the webpage was retrieved
   */

  public Webpage(String title, String url, String retrieved) {
    if (title == null || url == null || retrieved == null) {
      throw new IllegalArgumentException("cannot have a null field");
    } else {
      this.title = title;
      this.url = url;
      this.retrieved = retrieved;
    }


  }

  @Override
  public String citeApa() {
    return this.title + ". " + "retrieved " + this.retrieved + ", " + "from " + this.url + ".";
  }

  @Override
  public String citeMla() {
    return "\"" + this.title + "." + "\"" + " Web. " + this.retrieved + " <" + this.url + ">.";
  }

}
