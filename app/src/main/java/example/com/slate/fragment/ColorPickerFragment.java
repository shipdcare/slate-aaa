package example.com.slate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.slate.R;
import example.com.slate.adapter.SelectedColorsAdapter;
import util.AddColorInterface;

/**
 * Created by mark-42 on 2/6/17.
 */

public class ColorPickerFragment extends BaseFragment implements AddColorInterface {
    private static final String TAG = ColorPickerFragment.class.getName();
    private RecyclerView rvSelectedColors;
    private ArrayList<Integer> colorIds;
    private SelectedColorsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        init(view);
        return view;
    }


    /**
     * Initialize all the views
     *
     * @param view object of the inflated view
     */
    private void init(final View view) {
        rvSelectedColors = (RecyclerView) view.findViewById(R.id.rvCommonFragment);
        rvSelectedColors.setLayoutManager(new GridLayoutManager(getContext(), 6));
        colorIds = new ArrayList<>();
        colorIds.add(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        colorIds.add(ContextCompat.getColor(getContext(), R.color.firstColor));
        colorIds.add(ContextCompat.getColor(getContext(), R.color.secondColor));
        colorIds.add(ContextCompat.getColor(getContext(), R.color.thirdColor));
        colorIds.add(ContextCompat.getColor(getContext(), R.color.fourthColor));
        colorIds.add(ContextCompat.getColor(getContext(), R.color.fifthColor));
        colorIds.add(ContextCompat.getColor(getContext(), R.color.sixthColor));

        adapter = new SelectedColorsAdapter(getContext(), colorIds, this);
        rvSelectedColors.setAdapter(adapter);
    }

    @Override
    public void addColor(final int colorId) {
        Log.i(TAG, String.valueOf(colorId));
        colorIds.add(colorId);
        adapter.notifyDataSetChanged();
    }
}
