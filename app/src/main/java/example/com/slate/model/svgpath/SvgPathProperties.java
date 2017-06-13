package example.com.slate.model.svgpath;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mark-42 on 12/6/17.
 */

public class SvgPathProperties {


    @SerializedName("objects")
    private List<Objects> objects;

    public List<Objects> getObjects() {
        return objects;
    }

    public void setObjects(List<Objects> objects) {
        this.objects = objects;
    }
}
