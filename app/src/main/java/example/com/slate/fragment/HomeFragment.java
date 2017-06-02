package example.com.slate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.slate.R;
import example.com.slate.adapter.CommonRvAdapter;
import util.GridItemDecoration;

/**
 * Created by mark-42 on 31/5/17.
 */

public class HomeFragment extends BaseFragment {

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
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.fb_1);
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.fb_1);
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.fb_1);
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.fb_1);
        CommonRvAdapter adapter = new CommonRvAdapter(getContext(), list, HOME_FRAG);
        rvInstaFrag.setAdapter(adapter);
        rvInstaFrag.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvInstaFrag.addItemDecoration(new GridItemDecoration(2, 1, true, 0));
    }
}
