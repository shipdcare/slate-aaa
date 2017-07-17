package example.com.slate.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import example.com.slate.R;
import example.com.slate.activity.EditorActivity;
import example.com.slate.model.CommonResponse;

/**
 * Created by mark-42 on 12/6/17.
 */

public class TextEditFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private static final String TAG = TextEditFragment.class.getName();
    private CommonResponse cr;
    private SeekBar sbFontSize;
    private TextView tvFontSize;
    private ImageView ivChangeColor;
    private CommonInterface commonInterface;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_edit_frag, container, false);
        this.commonInterface = (EditorActivity) getContext();
        sbFontSize = (SeekBar) view.findViewById(R.id.sbFontSize);
        tvFontSize = (TextView) view.findViewById(R.id.tvFontSize);
        ivChangeColor = (ImageView) view.findViewById(R.id.ivChangeColor);
        ivChangeColor.setOnClickListener(this);
        sbFontSize.setOnSeekBarChangeListener(this);
        return view;
    }

    public void getSvgObj(final CommonResponse response) {
        cr = response;
        tvFontSize.setText(String.valueOf(cr.getSvgTextProperties().getFontSize()));
        sbFontSize.setProgress(cr.getSvgTextProperties().getFontSize());
        Log.w(TAG, cr.toString());
    }

    @Override
    public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
        tvFontSize.setText(String.valueOf(progress));
        cr.getSvgTextProperties().setFontSize(progress);
        commonInterface.changedFontSize(cr);
    }

    @Override
    public void onStartTrackingTouch(final SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(final SeekBar seekBar) {

    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.ivChangeColor:
                ColorPickerDialogBuilder
                        .with(getContext())
                        .setTitle(getContext().getString(R.string.choose_color))
                        .initialColor(CURRENT_BACKGROUND_COLOR)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(DENSITY)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(final int selectedColor) {
                            }
                        })
                        .setPositiveButton(getContext().getString(R.string.ok), new ColorPickerClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog
                                    , final int selectedColor, final Integer[] allColors) {
                                ivChangeColor.setBackgroundColor(selectedColor);
                                commonInterface.changeFontColor(cr, selectedColor);
                            }
                        })
                        .setNegativeButton(getContext().getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialog, final int which) {
                                    }
                                })
                        .build()
                        .show();
                break;
            default:
                break;
        }
    }
}
