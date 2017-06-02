package example.com.slate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.slate.R;
import example.com.slate.adapter.EditorCommonAdapter;

/**
 * Created by mark-42 on 1/6/17.
 */

public class TextFragment extends BaseFragment {
    private RecyclerView rvInstaFrag;
    private ArrayList<Integer> list;

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
        rvInstaFrag = (RecyclerView) view.findViewById(R.id.rvCommonFragment);
        list = new ArrayList<>();
        list.add(R.drawable.text1);
        list.add(R.drawable.text2);
        list.add(R.drawable.text4);
        list.add(R.drawable.text1);
        list.add(R.drawable.text2);
        list.add(R.drawable.text4);
        list.add(R.drawable.ic_check_box_empty);
        list.add(R.drawable.ic_circle_shape_outline);
        list.add(R.drawable.ic_check_box_empty);
        EditorCommonAdapter adapter = new EditorCommonAdapter(getContext(), list, ELEMENTS_FRAG);
        rvInstaFrag.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvInstaFrag.setAdapter(adapter);
    }
}
