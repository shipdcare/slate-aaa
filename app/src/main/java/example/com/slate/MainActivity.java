package example.com.slate;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.slate.adapter.PagerAdapter;
import example.com.slate.fragment.FbFragment;
import example.com.slate.fragment.HomeFragment;
import example.com.slate.fragment.InstaFragment;
import example.com.slate.fragment.TwitterFragment;

/**
 * Landing screen of the app
 */
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private TextView tvAppName;
    private ImageView ivHome, ivTwitter, ivInsta, ivFb;
    private List<Fragment> fragmentList;
    private ViewPager pager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ivHome:
                changeColors(0);
                pager.setCurrentItem(0);
                break;
            case R.id.ivInsta:
                changeColors(1);
                pager.setCurrentItem(1);
                break;
            case R.id.ivFb:
                changeColors(2);
                pager.setCurrentItem(2);
                break;
            case R.id.ivTwitter:
                changeColors(3);
                pager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    /**
     * Initialize all the views
     */
    private void init() {
        fragmentList = new ArrayList<>();
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/DancingScript-Bold.ttf");
        tvAppName = (TextView) findViewById(R.id.tvAppName);
        tvAppName.setTypeface(customFont);
        ivHome = (ImageView) findViewById(R.id.ivHome);
        ivTwitter = (ImageView) findViewById(R.id.ivTwitter);
        ivInsta = (ImageView) findViewById(R.id.ivInsta);
        ivFb = (ImageView) findViewById(R.id.ivFb);
        pager = (ViewPager) findViewById(R.id.vpCategories);
        ivHome.setOnClickListener(this);
        ivInsta.setOnClickListener(this);
        ivFb.setOnClickListener(this);
        ivTwitter.setOnClickListener(this);
        changeColors(0);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InstaFragment());
        fragmentList.add(new FbFragment());
        fragmentList.add(new TwitterFragment());
    }

    /**
     * Change the background of the social icons
     *
     * @param pos icon to be changed
     */
    private void changeColors(final int pos) {
        ivHome.setSelected(false);
        ivInsta.setSelected(false);
        ivFb.setSelected(false);
        ivTwitter.setSelected(false);
        switch (pos) {
            case 0:
                ivHome.setSelected(true);
                break;
            case 1:
                ivInsta.setSelected(true);
                break;
            case 2:
                ivFb.setSelected(true);
                break;
            case 3:
                ivTwitter.setSelected(true);
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
        changeColors(position);
    }

    @Override
    public void onPageScrollStateChanged(final int state) {

    }
}
