package util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by mark-42 on 29/4/17.
 */

public class CustomViewPager extends ViewPager {

    private boolean isPagingEnabled = true;

    /**
     * Instantiates a new Custom view pager.
     *
     * @param context the context
     */
    public CustomViewPager(final Context context) {
        super(context);
    }

    /**
     * Instantiates a new Custom view pager.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CustomViewPager(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Sets paging enabled.
     *
     * @param b the b
     */
    public void setPagingEnabled(final boolean b) {
        this.isPagingEnabled = b;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(final MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }
}
