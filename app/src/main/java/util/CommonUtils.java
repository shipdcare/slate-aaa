package util;

import android.util.Log;

import example.com.slate.model.CommonResponse;
import example.com.slate.model.Objects;
import example.com.slate.model.ViewBox;

/**
 * Created by mark-42 on 8/6/17.
 */

public class CommonUtils {

    /**
     * Convert response obj into svg string
     *
     * @param response response obj
     * @return svg string
     */
    public static String renderText(final CommonResponse response) {
        ViewBox vb = response.getViewBox();
        Objects data = response.getObjects();
        Log.w("common utils", "<svg width=\"" + vb.getWidth() + "\" height=\"" + vb.getHeight() + "\"><text x=\"" + data.getX()
                + "\" y=\"" + data.getY() + "\" font-size=\"" + data.getFontSize() + "\" fill=\"" + data.getFill()
                + "\" font-family=\"" + data.getFontFamily() + "\">" + "#HelloWorld</text></svg> ");
        return "<svg width=\"" + vb.getWidth() + "\" height=\"" + vb.getHeight() + "\"><text x=\"" + data.getX()
                + "\" y=\"" + data.getY() + "\" font-size=\"" + data.getFontSize() + "\" fill=\"" + data.getFill()
                + "\" font-family=\"" + data.getFontFamily() + "\">" + "#HelloWorld</text></svg> ";
    }
}
