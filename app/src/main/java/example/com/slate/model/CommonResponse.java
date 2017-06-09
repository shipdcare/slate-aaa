package example.com.slate.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mark-42 on 1/6/17.
 */

public class CommonResponse {

    @SerializedName("viewbox")
    private ViewBox viewBox;

    @SerializedName("objects")
    private Objects objects;

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
     * @return object of the objects class
     */
    public Objects getObjects() {
        return objects;
    }

    /**
     * @param objects object of the objects class
     */
    public void setObjects(final Objects objects) {
        this.objects = objects;
    }
}
