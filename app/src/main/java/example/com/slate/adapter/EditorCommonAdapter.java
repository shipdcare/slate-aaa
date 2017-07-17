package example.com.slate.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.util.ArrayList;

import example.com.slate.R;
import example.com.slate.activity.EditorActivity;
import example.com.slate.constant.AppConstant;
import example.com.slate.model.CommonResponse;
import util.CommonUtils;

/**
 * Created by mark-42 on 1/6/17.
 */

public class EditorCommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AppConstant {

    private Context mContext;
    private ArrayList<CommonResponse> svgImages;
    private int mEditorFragNum;
    private CommonInterface sendResponseObj;

    /**
     * @param mContext  mContext of the call fragment or activity
     * @param svgImages array list of the images to be inflated
     * @param mFragNum  number of the fragment calling Adapter
     */
    public EditorCommonAdapter(final Context mContext, final ArrayList<CommonResponse> svgImages,
                               final int mFragNum) {
        this.mContext = mContext;
        this.svgImages = svgImages;
        this.mEditorFragNum = mFragNum;
        this.sendResponseObj = (EditorActivity) mContext;
    }

    /**
     * @param mContext mContext of the call fragment or activity
     */
    public EditorCommonAdapter(final Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view;
        switch (viewType) {
            case ELEMENTS_FRAG:
                view = LayoutInflater.from(mContext).inflate(R.layout.text_frag_rowlayout, parent, false);
                return new Elements(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final CommonResponse response = svgImages.get(holder.getAdapterPosition());
        SVG text;
        switch (holder.getItemViewType()) {
            case ELEMENTS_FRAG:
                Elements vhElements = (Elements) holder;
                vhElements.ivText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        if (sendResponseObj != null) {
                            sendResponseObj.sendSvgString(response);
                        }
                    }
                });
                try {
                    text = SVG.getFromString(CommonUtils.renderText(response));
                    Drawable drawable = new PictureDrawable(text.renderToPicture());
                    vhElements.ivText.setImageDrawable(drawable);
                } catch (SVGParseException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return svgImages.size();
    }

    @Override
    public int getItemViewType(final int position) {
        switch (mEditorFragNum) {
            case ELEMENTS_FRAG:
                return ELEMENTS_FRAG;
            default:
                return 0;
        }
    }

    /**
     * View holder of Elements fragment
     */
    private final class Elements extends RecyclerView.ViewHolder {
        private ImageView ivText;

        /**
         * @param itemView object holding view to be inflated
         */
        private Elements(final View itemView) {
            super(itemView);
            ivText = (ImageView) itemView.findViewById(R.id.ivText);
        }
    }
}
