
package jeffemanuel.org.POJOs;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class RelatedTopic {

    @Expose
    private String Result;
    @Expose
    private jeffemanuel.org.POJOs.Icon Icon;
    @Expose
    private String FirstURL;
    @Expose
    private String Text;
    @Expose
    private List<Topic> Topics = new ArrayList<Topic>();
    @Expose
    private String Name;

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
    public jeffemanuel.org.POJOs.Icon getIcon() {
        return Icon;
    }

    /**
     * 
     * @param Icon
     *     The Icon
     */
    public void setIcon(jeffemanuel.org.POJOs.Icon Icon) {
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

    /**
     * 
     * @return
     *     The Topics
     */
    public List<Topic> getTopics() {
        return Topics;
    }

    /**
     * 
     * @param Topics
     *     The Topics
     */
    public void setTopics(List<Topic> Topics) {
        this.Topics = Topics;
    }

    /**
     * 
     * @return
     *     The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * 
     * @param Name
     *     The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

}
