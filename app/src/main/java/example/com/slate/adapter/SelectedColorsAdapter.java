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
import example.com.slate.model.EditorColors;

/**
 * Created by mark-42 on 2/6/17.
 */

public class SelectedColorsAdapter extends RecyclerView.Adapter<SelectedColorsAdapter.ViewHolder> implements AppConstant {
    private CommonInterface commonInterface;
    private Context mContext;
    private ArrayList<EditorColors> list;


    /**
     * @param mContext          mContext of the call fragment or activity
     * @param list              array list of the images to be inflated
     * @param commonInterface interface
     */
    public SelectedColorsAdapter(final Context mContext, final ArrayList<EditorColors> list,
                                 final CommonInterface commonInterface) {
        this.mContext = mContext;
        this.list = list;
        this.commonInterface = commonInterface;
    }

    @Override
    public SelectedColorsAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.color_view_rowlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SelectedColorsAdapter.ViewHolder holder, final int pos) {
        final int position = holder.getAdapterPosition();
        final int color = list.get(holder.getAdapterPosition()).getColorId();
        if (position == 0) {
            holder.ivSelectedColors.setBorderWidth(R.dimen.no_border_width);
            holder.ivSelectedColors.setBorderColor(ContextCompat.getColor(mContext, R.color.transparent));
            holder.ivSelectedColors.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bottomNavBg));
            holder.ivSelectedColors.setImageResource(R.drawable.ic_add_black_24dp);
        } else {
            holder.ivSelectedColors.setBackgroundColor(color);
            if (list.get(position).isSelected()) {
                holder.ivSelectedColors.setBorderWidth(R.dimen.border_width);
                holder.ivSelectedColors.setBorderColor(ContextCompat.getColor(mContext, R.color.colorWhite));
            } else {
                holder.ivSelectedColors.setBorderWidth(R.dimen.no_border_width);
                holder.ivSelectedColors.setBorderColor(ContextCompat.getColor(mContext, R.color.transparent));
            }
        }
        holder.ivSelectedColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch (v.getId()) {
                    case R.id.ivSelectedColors:
                        if (position == 0) {
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
                                            commonInterface.addColor(selectedColor);
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
                        } else {
                            for (int i = 0; i < list.size(); i++) {
                                if (position == i) {
                                    list.get(i).setSelected(true);
                                } else {
                                    list.get(i).setSelected(false);
                                }
                            }
                            notifyDataSetChanged();
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
