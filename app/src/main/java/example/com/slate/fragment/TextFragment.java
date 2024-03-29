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
import example.com.slate.model.CommonResponse;
import example.com.slate.model.svgtext.SvgTextProperties;
import example.com.slate.model.ViewBox;

/**
 * Created by mark-42 on 1/6/17.
 */

public class TextFragment extends BaseFragment {
    private RecyclerView rvInstaFrag;
    private ArrayList<CommonResponse> list;
    private CommonResponse response;
    private SvgTextProperties data;
    private ViewBox viewBox;

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
        addData("HelloWorld!");
        addData("Yes");
        addData("Happy Birthday");
        addData("Sup!");
        EditorCommonAdapter adapter = new EditorCommonAdapter(getContext(), list, ELEMENTS_FRAG);
        rvInstaFrag.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvInstaFrag.setAdapter(adapter);
    }

    private void addData(final String text) {
        response = new CommonResponse();
        data = new SvgTextProperties();
        viewBox = new ViewBox();
        viewBox.setHeight(150);
        viewBox.setWidth(150);
        data.setX(0);
        data.setY(50);
        data.setFill("#ffffff");
        data.setFontSize(25);
        data.setText(text);
        response.setViewBox(viewBox);
        response.setSvgTextProperties(data);
        list.add(response);
    }
}
