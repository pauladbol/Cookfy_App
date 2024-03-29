package com.livrodereceitas.cookfy.Adapters;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livrodereceitas.cookfy.R;

import org.w3c.dom.Text;

public class ImagemPagerAdapter extends PagerAdapter {
    private Context ctx;
    private int[] imagens;

    public ImagemPagerAdapter(Context c, int[] imagens) {
        this.ctx = c;
        this.imagens = imagens;
    }

    @Override
    public int getCount() {
        return imagens.length;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public  Object instantiateItem(ViewGroup container, int position) {
//        View view = LayoutInflater.from(this.ctx).inflate(R.layout.adapter_imagem, container, false);
//        ImageView img = (ImageView) view.findViewById(R.id.img);
//        img.setImageResource(imagens[position]);
//        ((ViewGroup) container).addView(view);
//        return view;

        LinearLayout ll = new LinearLayout(ctx);
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);

        container.addView(ll);

//        ImageView logo = new ImageView(ctx);
//        logo.setImageResource(R.drawable.logo_mini);
//        ll.addView(logo);

        ImageView iv = new ImageView(ctx);
        iv.setImageResource(imagens[position]);
        iv.setVisibility(position);
        ll.addView(iv);

        String text = "";
        switch (position) {
            case 0:
                text = "Comida Mexicana";
                break;
            case 1:
                text = "Comida Italiana";
                break;
            case 2:
                text = "Comida Caseira";
                break;
            case 3:
                text = "Comida Tailandesa";
                break;
            case 4:
                text = "Café da Manhã";
                break;

        }

        TextView tv = new TextView(ctx);
        tv.setText(text);
        tv.setTextSize(20);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ll.addView(tv);



        return(ll);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}