package util;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.util.HashSet;

import example.com.slate.activity.EditorActivity;
import example.com.slate.constant.AppConstant;
import example.com.slate.model.CommonResponse;
import example.com.slate.model.svgtext.SvgTextProperties;

/**
 * Created by mark-42 on 8/6/17.
 */

public class CustomCanvasView extends View implements AppConstant {

    private static final String TAG = CustomCanvasView.class.getName();
    private ScaleGestureDetector mScaleDetector;
    private SvgTextProperties data;
    private float mScaleFactor = SCALE_FACTOR;
    private int mMode;
    private HashSet<CommonResponse> mObj = new HashSet<>();
    private SparseArray<CommonResponse> mObjPointer = new SparseArray<>();
    private Context mContext;
    private String strSvg;
    private SVG svg;

    /**
     * @param context context of the class
     */
    public CustomCanvasView(final Context context) {
        super(context);
        init(context);
    }

    /**
     * @param context context of the class
     * @param attrs   attributes
     */
    public CustomCanvasView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context      context of the class
     * @param attrs        attributes
     * @param defStyleAttr defStyleAttr
     */
    public CustomCanvasView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * Initialize the canvas
     *
     * @param context context of the attached view
     */
    private void init(final Context context) {
        mContext = context;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

    }


    @Override
    protected void onDraw(final Canvas canvas) {
        Log.w(TAG, "onDraw");
        for (CommonResponse data : mObj) {
            strSvg = CommonUtils.renderText(data);
            Log.i("svg", strSvg);
            try {
                svg = SVG.getFromString(strSvg);
            } catch (SVGParseException e) {
                e.printStackTrace();
            }
            svg.renderToCanvas(canvas);

        }
    }


    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        Log.w(TAG, "onTouch");
        boolean handled = false;
        mScaleDetector.onTouchEvent(event);
        CommonResponse touchedObj;
        int xTouch;
        int yTouch;
        int pointerId;
        int actionIndex = event.getActionIndex();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.w(TAG, "Down");
                xTouch = (int) event.getX(0);
                yTouch = (int) event.getY(0);
                touchedObj = getTouchedObj(xTouch, yTouch);
                if (touchedObj != null) {
                    Log.w(TAG, "found");
                    mObjPointer.put(event.getPointerId(0), touchedObj);
                    ((EditorActivity) mContext).getSvgTextObj(touchedObj);
                }
                invalidate();
                handled = true;
                break;
            case MotionEvent.ACTION_MOVE:
                final int pointerCount = event.getPointerCount();

                Log.w(TAG, "Move");

                for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                    // Some pointer has moved, search it by pointer id
                    pointerId = event.getPointerId(actionIndex);

                    xTouch = (int) event.getX(actionIndex);
                    yTouch = (int) event.getY(actionIndex);

                    touchedObj = mObjPointer.get(pointerId);

                    if (null != touchedObj) {
                        touchedObj.getSvgTextProperties().setX(xTouch);
                        touchedObj.getSvgTextProperties().setY(yTouch);
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mMode = NONE;
                pointerId = event.getPointerId(actionIndex);
                mObjPointer.remove(pointerId);
                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                mMode = NONE;
                // not general pointer was up
                pointerId = event.getPointerId(actionIndex);

                mObjPointer.remove(pointerId);
                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_CANCEL:
                handled = true;
                break;
            default:
                break;
        }


        return super.onTouchEvent(event) || handled;
    }

    /**
     * Determines touched circle
     *
     * @param xTouch int x touch coordinate
     * @param yTouch int y touch coordinate
     * @return {@link SvgTextProperties} touched circle or null if no circle has been touched
     */
    private CommonResponse getTouchedObj(final int xTouch, final int yTouch) {
        CommonResponse touched = null;

        for (CommonResponse obj : mObj) {
            /*Log.w("getx", String.valueOf(obj.getSvgTextProperties().getX()));
            Log.w("gety", String.valueOf(obj.getSvgTextProperties().getY()));
            Log.w("xtouch", String.valueOf(xTouch));
            Log.w("ytouch", String.valueOf(yTouch));*/

            int objStartX = obj.getSvgTextProperties().getX();
            int objStartY = obj.getSvgTextProperties().getY() - 50;

            int objectEndX = objStartX + obj.getViewBox().getWidth() + 50;
            int objectEndY = objStartY + obj.getViewBox().getHeight();

            if (xTouch >= objStartX && xTouch <= objectEndX) {
                if (yTouch >= objStartY && yTouch <= objectEndY) {
                    Log.w(TAG, "entered");
                    touched = obj;
                    break;
                }
            }
        }

        return touched;
    }

    public void receiveObj(final CommonResponse response, final int mMode) {
        switch (mMode) {
            case MODE_ADAPTER_CLICK:
                mObj.add(response);
                invalidate();
                break;
            case MODE_TEXT_FRAG:
                if (mObj.contains(response)) {
                    for (CommonResponse obj : mObj) {
                        if (obj.equals(response)) {
                            obj.getSvgTextProperties().setFontSize(response.getSvgTextProperties().getFontSize());
                            invalidate();
                        }
                    }
                }
            default:
                break;
        }
    }

    public void changeFontColor(final CommonResponse response, final int colorId) {
        if (mObj.contains(response)) {
            for (CommonResponse obj : mObj) {
                if (obj.equals(response)) {
                    obj.getSvgTextProperties().setFill(String.format("#%06X", (0xFFFFFF & colorId)));
                    invalidate();
                }
            }
        }
    }


    /**
     * Scale listener class
     */
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScaleBegin(final ScaleGestureDetector detector) {
            mMode = ZOOM;
            return true;
        }


        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            Log.w(TAG, String.valueOf(mScaleFactor));

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(MIN_SCALE_VAL, Math.min(mScaleFactor, MAX_SCALE_VAL));

            Log.d(TAG + "ZOOM", String.valueOf(mScaleFactor));

            data.setFontSize((int) mScaleFactor);

            Log.d(TAG + "font-size", String.valueOf(mScaleFactor));

            invalidate();
            return true;
        }
    }

}