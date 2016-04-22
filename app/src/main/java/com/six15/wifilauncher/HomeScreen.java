package com.six15.wifilauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Created by George on 4/21/2016.
 */

public class HomeScreen extends FragmentActivity {

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
    }

    public void showAppDrawer(){
        Intent intent1= new Intent(this, ApplistActivity.class);
        startActivity(intent1);
    }

}