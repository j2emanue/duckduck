
package jeffemanuel.org.POJOs;

import com.google.gson.annotations.Expose;

public class Topic {

    @Expose
    private String Result;
    @Expose
    private Icon_ Icon;
    @Expose
    private String FirstURL;
    @Expose
    private String Text;

    /**
     * 
     * @return
     *     The Result
     */
    public String getResult() {
        return Result;
    }

    /**
     * 
     * @param Result
     *     The Result
     */
    public void setResult(String Result) {
        this.Result = Result;
    }

    /**
     * 
     * @return
     *     The Icon
     */
    public Icon_ getIcon() {
        return Icon;
    }

    /**
     * 
     * @param Icon
     *     The Icon
     */
    public void setIcon(Icon_ Icon) {
        this.Icon = Icon;
    }

    /**
     * 
     * @return
     *     The FirstURL
     */
    public String getFirstURL() {
        return FirstURL;
    }

    /**
     * 
     * @param FirstURL
     *     The FirstURL
     */
    public void setFirstURL(String FirstURL) {
        this.FirstURL = FirstURL;
    }

    /**
     * 
     * @return
     *     The Text
     */
    public String getText() {
        return Text;
    }

    /**
     * 
     * @param Text
     *     The Text
     */
    public void setText(String Text) {
        this.Text = Text;
    }

}
