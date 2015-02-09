
package jeffemanuel.org.POJOs;

import com.google.gson.annotations.Expose;

public class Icon {

    @Expose
    private String URL;
    @Expose
    private String Height;
    @Expose
    private String Width;

    /**
     * 
     * @return
     *     The URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * 
     * @param URL
     *     The URL
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * 
     * @return
     *     The Height
     */
    public String getHeight() {
        return Height;
    }

    /**
     * 
     * @param Height
     *     The Height
     */
    public void setHeight(String Height) {
        this.Height = Height;
    }

    /**
     * 
     * @return
     *     The Width
     */
    public String getWidth() {
        return Width;
    }

    /**
     * 
     * @param Width
     *     The Width
     */
    public void setWidth(String Width) {
        this.Width = Width;
    }

}
