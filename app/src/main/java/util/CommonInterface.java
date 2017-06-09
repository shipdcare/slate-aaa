package util;

import example.com.slate.model.CommonResponse;

/**
 * Created by mark-42 on 2/6/17.
 */

public interface CommonInterface {
    /**
     * @param colorId color id
     */
    void addColor(int colorId);

    /**
     * @param response common response object
     */
    void sendSvgString(CommonResponse response);
}
