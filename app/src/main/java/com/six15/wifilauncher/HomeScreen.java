package com.six15.wifilauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

    private final List<LaunchItem> launchItems = Arrays.asList(
            new LaunchItem(R.drawable.browser_icon, "Web Browser",""),
            new LaunchItem(R.drawable.ar_icon, "Telestration",""),
            new LaunchItem(R.drawable.camera_icon, "Camera",""),
            new LaunchItem(R.drawable.app_drawer_icon, "Applications",""),
            new LaunchItem(R.drawable.setting_icon, "Settings","")
    );


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        int currentPosition = 0;
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(this,"Vol UP Pressed!", Toast.LENGTH_SHORT).show();
                /*
                currentPosition = fancyCoverFlow.getSelectedItemPosition();
                currentPosition++;
                if(currentPosition>= fancyCoverFlow.getCount())  currentPosition = fancyCoverFlow.getCount() -1;
                fancyCoverFlow.setSelection(currentPosition);
                */
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this,"Vol DN Pressed!", Toast.LENGTH_SHORT).show();
                /*
                currentPosition = fancyCoverFlow.getSelectedItemPosition();
                currentPosition--;
                if(currentPosition<0) currentPosition = 0;
                fancyCoverFlow.setSelection(currentPosition);
                */
                return true;
            case KeyEvent.KEYCODE_MENU:
                Toast.makeText(this,"Launch", Toast.LENGTH_SHORT).show();
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

        Button btnAppDrawer = (Button) findViewById(R.id.btnDrawer);
        btnAppDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppDrawer();
            }
        });


        CoverFlowContainer mContainer = (CoverFlowContainer) findViewById(R.id.pager_container);

        ViewPager pager = mContainer.getViewPager();

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

    public void showAppDrawer(){
        Intent intent1= new Intent(this, ApplistActivity.class);
        startActivity(intent1);
    }

}