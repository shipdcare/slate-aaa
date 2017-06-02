package example.com.slate.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import example.com.slate.R;
import example.com.slate.constant.AppConstant;
import util.AddColorInterface;

/**
 * Created by mark-42 on 2/6/17.
 */

public class SelectedColorsAdapter extends RecyclerView.Adapter<SelectedColorsAdapter.ViewHolder> implements AppConstant {
    private AddColorInterface addColorInterface;
    private Context mContext;
    private ArrayList<Integer> list;


    /**
     * @param mContext          mContext of the call fragment or activity
     * @param list              array list of the images to be inflated
     * @param addColorInterface interface
     */
    public SelectedColorsAdapter(final Context mContext, final ArrayList<Integer> list,
                                 final AddColorInterface addColorInterface) {
        this.mContext = mContext;
        this.list = list;
        this.addColorInterface = addColorInterface;
    }

    @Override
    public SelectedColorsAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.color_view_rowlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SelectedColorsAdapter.ViewHolder holder, final int position) {
        int color = list.get(holder.getAdapterPosition());
        if (holder.getAdapterPosition() == 0) {
            holder.ivSelectedColors.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bottomNavBg));
            holder.ivSelectedColors.setImageResource(R.drawable.ic_add_black_24dp);
        } else {
            holder.ivSelectedColors.setBackgroundColor(color);
        }
        holder.ivSelectedColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch (v.getId()) {
                    case R.id.ivSelectedColors:
                        if (holder.getAdapterPosition() == 0) {
                            ColorPickerDialogBuilder
                                    .with(mContext)
                                    .setTitle(mContext.getString(R.string.choose_color))
                                    .initialColor(CURRENT_BACKGROUND_COLOR)
                                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                                    .density(DENSITY)
                                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                                        @Override
                                        public void onColorSelected(final int selectedColor) {
                                        }
                                    })
                                    .setPositiveButton(mContext.getString(R.string.ok), new ColorPickerClickListener() {
                                        @Override
                                        public void onClick(final DialogInterface dialog
                                                , final int selectedColor, final Integer[] allColors) {
                                            addColorInterface.addColor(selectedColor);
                                        }
                                    })
                                    .setNegativeButton(mContext.getString(R.string.cancel),
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(final DialogInterface dialog, final int which) {
                                                }
                                            })
                                    .build()
                                    .show();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * View holder of selected color fragment
     */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView ivSelectedColors;

        /**
         * @param itemView object holding view to be inflated
         */
        public ViewHolder(final View itemView) {
            super(itemView);
            ivSelectedColors = (RoundedImageView) itemView.findViewById(R.id.ivSelectedColors);
        }
    }
}
