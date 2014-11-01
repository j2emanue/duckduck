package jeffemanuel.org.duckduck;


/**
 * a POJO class to store json response data
 */
public class SearchItem {

    private String headline="";

    private String mDefinition="";
    private String nivURL="";

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public  String getImageURL() {
        return nivURL;
    }

    public void setImageURL(String URL) {
        this.nivURL = URL;
    }

    public String getDefinition() {
        return mDefinition;
    }

    public void setDefinition(String definition) {
        this.mDefinition = definition;
    }

    @Override
    public String toString() {
        return "[ headline=" + headline +
                " , imageView URL=" + nivURL + "]";
    }
}