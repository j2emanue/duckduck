
package jeffemanuel.org.POJOs;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;


public class DuckDataModel {

    @Expose
    private String DefinitionSource;
    @Expose
    private String Heading;
    @Expose
    private Integer ImageWidth;
    @Expose
    private List<RelatedTopic> RelatedTopics = new ArrayList<RelatedTopic>();
    @Expose
    private String Entity;
    @Expose
    private String Type;
    @Expose
    private String Redirect;
    @Expose
    private String DefinitionURL;
    @Expose
    private String AbstractURL;
    @Expose
    private String Definition;
    @Expose
    private String AbstractSource;
    @Expose
    private String Infobox;
    @Expose
    private String Image;
    @Expose
    private Integer ImageIsLogo;
    @Expose
    private String Abstract;
    @Expose
    private String AbstractText;
    @Expose
    private String AnswerType;
    @Expose
    private Integer ImageHeight;
    @Expose
    private List<Object> Results = new ArrayList<Object>();
    @Expose
    private String Answer;

    /**
     * 
     * @return
     *     The DefinitionSource
     */
    public String getDefinitionSource() {
        return DefinitionSource;
    }

    /**
     * 
     * @param DefinitionSource
     *     The DefinitionSource
     */
    public void setDefinitionSource(String DefinitionSource) {
        this.DefinitionSource = DefinitionSource;
    }

    /**
     * 
     * @return
     *     The Heading
     */
    public String getHeading() {
        return Heading;
    }

    /**
     * 
     * @param Heading
     *     The Heading
     */
    public void setHeading(String Heading) {
        this.Heading = Heading;
    }

    /**
     * 
     * @return
     *     The ImageWidth
     */
    public Integer getImageWidth() {
        return ImageWidth;
    }

    /**
     * 
     * @param ImageWidth
     *     The ImageWidth
     */
    public void setImageWidth(Integer ImageWidth) {
        this.ImageWidth = ImageWidth;
    }

    /**
     * 
     * @return
     *     The RelatedTopics
     */
    public List<RelatedTopic> getRelatedTopics() {
        return RelatedTopics;
    }

    /**
     * 
     * @param RelatedTopics
     *     The RelatedTopics
     */
    public void setRelatedTopics(List<RelatedTopic> RelatedTopics) {
        this.RelatedTopics = RelatedTopics;
    }

    /**
     * 
     * @return
     *     The Entity
     */
    public String getEntity() {
        return Entity;
    }

    /**
     * 
     * @param Entity
     *     The Entity
     */
    public void setEntity(String Entity) {
        this.Entity = Entity;
    }

    /**
     * 
     * @return
     *     The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * 
     * @param Type
     *     The Type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * 
     * @return
     *     The Redirect
     */
    public String getRedirect() {
        return Redirect;
    }

    /**
     * 
     * @param Redirect
     *     The Redirect
     */
    public void setRedirect(String Redirect) {
        this.Redirect = Redirect;
    }

    /**
     * 
     * @return
     *     The DefinitionURL
     */
    public String getDefinitionURL() {
        return DefinitionURL;
    }

    /**
     * 
     * @param DefinitionURL
     *     The DefinitionURL
     */
    public void setDefinitionURL(String DefinitionURL) {
        this.DefinitionURL = DefinitionURL;
    }

    /**
     * 
     * @return
     *     The AbstractURL
     */
    public String getAbstractURL() {
        return AbstractURL;
    }

    /**
     * 
     * @param AbstractURL
     *     The AbstractURL
     */
    public void setAbstractURL(String AbstractURL) {
        this.AbstractURL = AbstractURL;
    }

    /**
     * 
     * @return
     *     The Definition
     */
    public String getDefinition() {
        return Definition;
    }

    /**
     * 
     * @param Definition
     *     The Definition
     */
    public void setDefinition(String Definition) {
        this.Definition = Definition;
    }

    /**
     * 
     * @return
     *     The AbstractSource
     */
    public String getAbstractSource() {
        return AbstractSource;
    }

    /**
     * 
     * @param AbstractSource
     *     The AbstractSource
     */
    public void setAbstractSource(String AbstractSource) {
        this.AbstractSource = AbstractSource;
    }

    /**
     * 
     * @return
     *     The Infobox
     */
    public String getInfobox() {
        return Infobox;
    }

    /**
     * 
     * @param Infobox
     *     The Infobox
     */
    public void setInfobox(String Infobox) {
        this.Infobox = Infobox;
    }

    /**
     * 
     * @return
     *     The Image
     */
    public String getImage() {
        return Image;
    }

    /**
     * 
     * @param Image
     *     The Image
     */
    public void setImage(String Image) {
        this.Image = Image;
    }

    /**
     * 
     * @return
     *     The ImageIsLogo
     */
    public Integer getImageIsLogo() {
        return ImageIsLogo;
    }

    /**
     * 
     * @param ImageIsLogo
     *     The ImageIsLogo
     */
    public void setImageIsLogo(Integer ImageIsLogo) {
        this.ImageIsLogo = ImageIsLogo;
    }

    /**
     * 
     * @return
     *     The Abstract
     */
    public String getAbstract() {
        return Abstract;
    }

    /**
     * 
     * @param Abstract
     *     The Abstract
     */
    public void setAbstract(String Abstract) {
        this.Abstract = Abstract;
    }

    /**
     * 
     * @return
     *     The AbstractText
     */
    public String getAbstractText() {
        return AbstractText;
    }

    /**
     * 
     * @param AbstractText
     *     The AbstractText
     */
    public void setAbstractText(String AbstractText) {
        this.AbstractText = AbstractText;
    }

    /**
     * 
     * @return
     *     The AnswerType
     */
    public String getAnswerType() {
        return AnswerType;
    }

    /**
     * 
     * @param AnswerType
     *     The AnswerType
     */
    public void setAnswerType(String AnswerType) {
        this.AnswerType = AnswerType;
    }

    /**
     * 
     * @return
     *     The ImageHeight
     */
    public Integer getImageHeight() {
        return ImageHeight;
    }

    /**
     * 
     * @param ImageHeight
     *     The ImageHeight
     */
    public void setImageHeight(Integer ImageHeight) {
        this.ImageHeight = ImageHeight;
    }

    /**
     * 
     * @return
     *     The Results
     */
    public List<Object> getResults() {
        return Results;
    }

    /**
     * 
     * @param Results
     *     The Results
     */
    public void setResults(List<Object> Results) {
        this.Results = Results;
    }

    /**
     * 
     * @return
     *     The Answer
     */
    public String getAnswer() {
        return Answer;
    }

    /**
     * 
     * @param Answer
     *     The Answer
     */
    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

}
