package example.com.slate.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mark-42 on 8/6/17.
 */

public class ViewBox {
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }
}
