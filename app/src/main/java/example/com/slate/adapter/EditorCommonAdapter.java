package example.com.slate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.slate.R;
import example.com.slate.constant.AppConstant;
import util.InstaImageView;

/**
 * Created by mark-42 on 1/6/17.
 */

public class EditorCommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AppConstant {

    private Context mContext;
    private ArrayList<Integer> imageIds;
    private int mEditorFragNum;

    /**
     * @param mContext mContext of the call fragment or activity
     * @param imageIds array list of the images to be inflated
     * @param mFragNum number of the fragment calling Adapter
     */
    public EditorCommonAdapter(final Context mContext, final ArrayList<Integer> imageIds, final int mFragNum) {
        this.mContext = mContext;
        this.imageIds = imageIds;
        this.mEditorFragNum = mFragNum;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view;
        switch (viewType) {
            case ELEMENTS_FRAG:
                view = LayoutInflater.from(mContext).inflate(R.layout.grid_insta_rowlayout, parent, false);
                return new Elements(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int id = imageIds.get(holder.getAdapterPosition());
        switch (holder.getItemViewType()) {
            case ELEMENTS_FRAG:
                Elements vhElements = (Elements) holder;
                vhElements.ivHomeTemp.setImageResource(id);
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return imageIds.size();
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
        private InstaImageView ivHomeTemp;

        /**
         * @param itemView object holding view to be inflated
         */
        private Elements(final View itemView) {
            super(itemView);
            ivHomeTemp = (InstaImageView) itemView.findViewById(R.id.ivInstaGridView);
        }
    }
}
