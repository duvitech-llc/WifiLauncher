package com.six15.wifilauncher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.six15.wifilauncher.lib.CoverFlowContainer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by George on 4/21/2016.
 */

public class HomeScreen extends FragmentActivity {

    public static String TAG = "HomeScreen";
    ViewPager pager;

    private final List<LaunchItem> launchItems = Arrays.asList(
            new LaunchItem(R.drawable.web_icon, "Web Browser", "web"),
            new LaunchItem(R.drawable.tele_icon, "Telestration", "ar"),
            new LaunchItem(R.drawable.camera_icon, "Camera", "camera"),
            new LaunchItem(R.drawable.all_icon, "Applications",  "apps"),
            new LaunchItem(R.drawable.settings_icon, "Settings", "config")
    );


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        int iCurrentItem = 0;
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                // Toast.makeText(this,"Vol UP Pressed!", Toast.LENGTH_SHORT).show();
                pager.arrowScroll(View.FOCUS_RIGHT);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                // Toast.makeText(this,"Vol DN Pressed!", Toast.LENGTH_SHORT).show();
                pager.arrowScroll(View.FOCUS_LEFT);
                return true;
            case KeyEvent.KEYCODE_MENU:
                // Toast.makeText(this,"Launch", Toast.LENGTH_SHORT).show();
                iCurrentItem = pager.getCurrentItem();
                Log.i(TAG,"Item Selected: "+ iCurrentItem);
                launchIntent(launchItems.get(iCurrentItem).intentName);
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                return true;
            case KeyEvent.KEYCODE_MENU:
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);


/*
        Button btnAppDrawer = (Button) findViewById(R.id.btnDrawer);
        btnAppDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppDrawer();
            }
        });
*/

        CoverFlowContainer mContainer = (CoverFlowContainer) findViewById(R.id.pager_container);

        pager = mContainer.getViewPager();

        PagerAdapter adapter = new LauncherCoverFlowAdapter(HomeScreen.this, launchItems);
        final TextView selectedTitle = (TextView) findViewById(R.id.selected_title);

        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount());
        pager.setClipChildren(false);
        selectedTitle.setText(launchItems.get(0).title);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectedTitle.setText(launchItems.get(position).title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    public void launchIntent(String intentName){
        Intent launchIntent = null;
        switch(intentName){
            case "web":
                launchIntent = new Intent(Intent.ACTION_VIEW);
                launchIntent.setData(Uri.parse("http://www.youtube.com"));
                break;
            case "config":
                launchIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                break;
            case "camera":
                launchIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                break;
            case "apps":
                launchIntent = new Intent(this, ApplistActivity.class);
            case "ar":
                launchIntent = new Intent(this, ApplistActivity.class);
            default:
                break;
        }

        if(launchIntent != null)
            startActivity(launchIntent);
    }

}