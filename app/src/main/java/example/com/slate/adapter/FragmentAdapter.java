package example.com.slate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.slate.R;
import util.SquareImageView;

/**
 * Created by mark-42 on 31/5/17.
 */

public class FragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Integer> imageIds;
    private int mFragNum;

    /**
     * @param mContext mContext of the call fragment or activity
     * @param imageIds array list of the images to be inflated
     * @param mFragNum number of the fragment calling Adapter
     */
    public FragmentAdapter(final Context mContext, final ArrayList<Integer> imageIds, final int mFragNum) {
        this.mContext = mContext;
        this.imageIds = imageIds;
        this.mFragNum = mFragNum;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(mContext).inflate(R.layout.grid_rowlayout, parent, false);
                return new InstaViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int id = imageIds.get(position);
        InstaViewHolder viewHolder = (InstaViewHolder) holder;
        viewHolder.ivTemplates.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return imageIds.size();
    }

    @Override
    public int getItemViewType(final int position) {
        if (mFragNum == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * View holder of insta fragment
     */
    private final class InstaViewHolder extends RecyclerView.ViewHolder {
        private SquareImageView ivTemplates;

        /**
         * @param itemView object holding view to be inflated
         */
        private InstaViewHolder(final View itemView) {
            super(itemView);
            ivTemplates = (SquareImageView) itemView.findViewById(R.id.ivGridView);
        }
    }


}
