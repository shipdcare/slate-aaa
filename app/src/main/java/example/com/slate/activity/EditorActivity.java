package example.com.slate.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.slate.R;
import example.com.slate.adapter.PagerAdapter;
import example.com.slate.fragment.ColorPickerFragment;
import example.com.slate.fragment.TextFragment;
import util.CustomViewPager;

/**
 * Editor Screen
 */
public class EditorActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private List<Fragment> fragmentList;
    private CustomViewPager pager;
    private TextView tvText, tvElements, tvLayouts, tvPages;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        init();
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);
        pager.setCurrentItem(0);
    }

    /**
     * Initialize all the views
     */
    private void init() {
        fragmentList = new ArrayList<>();
        tvText = (TextView) findViewById(R.id.tvText);
        tvElements = (TextView) findViewById(R.id.tvElements);
        tvLayouts = (TextView) findViewById(R.id.tvLayouts);
        tvPages = (TextView) findViewById(R.id.tvPages);
        pager = (CustomViewPager) findViewById(R.id.vpEditor);
        tvText.setOnClickListener(this);
        tvElements.setOnClickListener(this);
        tvLayouts.setOnClickListener(this);
        tvPages.setOnClickListener(this);
        fragmentList.add(new ColorPickerFragment());
        fragmentList.add(new TextFragment());
    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tvText:
                changeColors(0);
                pager.setCurrentItem(1);
                break;
            case R.id.tvElements:
                changeColors(1);
                break;
            case R.id.tvLayouts:
                changeColors(2);
                break;
            case R.id.tvPages:
                changeColors(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {

    }

    @Override
    public void onPageScrollStateChanged(final int state) {

    }

    /**
     * Change the background of the social icons
     *
     * @param pos icon to be changed
     */
    private void changeColors(final int pos) {
        tvText.setSelected(false);
        tvElements.setSelected(false);
        tvLayouts.setSelected(false);
        tvPages.setSelected(false);
        switch (pos) {
            case 0:
                tvText.setSelected(true);
                break;
            case 1:
                tvElements.setSelected(true);
                break;
            case 2:
                tvLayouts.setSelected(true);
                break;
            case 3:
                tvPages.setSelected(true);
                break;
            default:
                break;
        }
    }
}
