package util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by mark-42 on 3/5/17.
 */

public class InstaImageView extends AppCompatImageView {
    /**
     * @param context context
     */
    public InstaImageView(final Context context) {
        super(context);
    }

    /**
     * @param context context
     * @param attrs   attars
     */
    public InstaImageView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defstyleattr
     */
    public InstaImageView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

}
