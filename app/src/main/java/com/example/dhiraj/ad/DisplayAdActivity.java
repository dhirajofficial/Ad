package com.example.dhiraj.ad;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayAdActivity extends ArrayAdapter<String> {

    private String[] adName;
    private String[] description;
    private String[] contact;
    private Integer[] imgid;
    private Activity context;

    public DisplayAdActivity(Activity context,String[] adName,String[] description,String[] contact,Integer[] imgid) {
        super(context, R.layout.activity_display_ad,adName);

        this.context=context;
        this.adName=adName;
        this.description=description;
        this.contact=contact;
        this.imgid=imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;

        if(r==null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_display_ad,null,true);

            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) r.getTag();

        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(adName[position]);
        viewHolder.tvw2.setText(description[position]);
        viewHolder.tvw3.setText(contact[position]);

//        return super.getView(position, convertView, parent);
        return r;
    }

    class ViewHolder
    {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        ImageView ivw;

        ViewHolder(View v)
        {
            tvw1 = v.findViewById(R.id.adTitle);
            tvw2 = v.findViewById(R.id.adDescription);
            tvw3 = v.findViewById(R.id.adContact);
            ivw = v.findViewById(R.id.adImage);
        }
    }
}
