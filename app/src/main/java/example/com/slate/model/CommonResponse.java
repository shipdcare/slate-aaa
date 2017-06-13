package example.com.slate.model;

import com.google.gson.annotations.SerializedName;

import example.com.slate.model.svgtext.SvgTextProperties;

/**
 * Created by mark-42 on 1/6/17.
 */

public class CommonResponse {

    @SerializedName("viewbox")
    private ViewBox viewBox;

    @SerializedName("svgTextProperties")
    private SvgTextProperties svgTextProperties;

    /**
     * @return object of the view box
     */
    public ViewBox getViewBox() {
        return viewBox;
    }

    /**
     * @param viewBox object of the view box
     */
    public void setViewBox(final ViewBox viewBox) {
        this.viewBox = viewBox;
    }

    /**
     * @return object of the svgTextProperties class
     */
    public SvgTextProperties getSvgTextProperties() {
        return svgTextProperties;
    }

    /**
     * @param svgTextProperties object of the svgTextProperties class
     */
    public void setSvgTextProperties(final SvgTextProperties svgTextProperties) {
        this.svgTextProperties = svgTextProperties;
    }
}
