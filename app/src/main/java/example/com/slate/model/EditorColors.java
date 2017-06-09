package example.com.slate.model;

/**
 * Created by mark-42 on 2/6/17.
 */

public class EditorColors {

    private int colorId;
    private boolean isSelected = false;

    /**
     * @return id of the color
     */
    public int getColorId() {
        return colorId;
    }

    /**
     * @param colorId id of the color
     */
    public void setColorId(final int colorId) {
        this.colorId = colorId;
    }

    /**
     * @return true if the color is selected
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * @param selected set true if the color is selected
     */
    public void setSelected(final boolean selected) {
        isSelected = selected;
    }
}
