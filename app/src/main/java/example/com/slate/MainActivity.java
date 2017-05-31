package example.com.slate;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Landing screen of the app
 */
public class MainActivity extends BaseActivity {
    private TextView tvAppName;
    private ImageView ivHome, ivTwitter, ivInsta, ivFb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ivHome:
                changeColors(0);
                break;
            case R.id.ivInsta:
                changeColors(1);
                break;
            case R.id.ivFb:
                changeColors(2);
                break;
            case R.id.ivTwitter:
                changeColors(3);
                break;
            default:
                break;
        }
    }

    /**
     * Initialize all the views
     */
    private void init() {
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/DancingScript-Bold.ttf");
        tvAppName = (TextView) findViewById(R.id.tvAppName);
        tvAppName.setTypeface(customFont);
        ivHome = (ImageView) findViewById(R.id.ivHome);
        ivTwitter = (ImageView) findViewById(R.id.ivTwitter);
        ivInsta = (ImageView) findViewById(R.id.ivInsta);
        ivFb = (ImageView) findViewById(R.id.ivFb);
        ivHome.setOnClickListener(this);
        ivInsta.setOnClickListener(this);
        ivFb.setOnClickListener(this);
        ivTwitter.setOnClickListener(this);
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
}
