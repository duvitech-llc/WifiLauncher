package com.six15.wifilauncher;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by George on 4/22/2016.
 */
public class LauncherCoverFlowAdapter extends com.six15.wifilauncher.lib.AbstractCoverFlowAdapter<LaunchItem> {
    public LauncherCoverFlowAdapter(Context context, List<LaunchItem> launchItems) {
        super(context, launchItems);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LaunchItem item = items.get(position);

        View v = LayoutInflater.from(context).inflate(R.layout.cover_item, null);
        ImageView coverImage = (ImageView) v.findViewById(R.id.cover_image);
        coverImage.setImageResource(item.imageResourceId);

        TextView title = (TextView) v.findViewById(R.id.title);
        title.setText(item.title);

        ImageView reflectionImage = (ImageView) v.findViewById(R.id.reflection_image);
        reflectionImage.setImageBitmap(getReflectionBitmap(((BitmapDrawable) coverImage.getDrawable()).getBitmap()));

        container.addView(v);

        return v;
    }
}
