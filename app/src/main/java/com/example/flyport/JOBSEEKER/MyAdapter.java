package com.example.flyport.JOBSEEKER;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.flyport.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> myDataName;
    ArrayList<Integer>myDataImg;
    int selectedPosition = -1;

    public MyAdapter(Context context, ArrayList<String> myDataName, ArrayList<Integer> myDataImg) {
        this.context = context;
        this.myDataName = myDataName;
        this.myDataImg = myDataImg;
    }

    @Override
    public int getCount() {
        return myDataName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.mylayout,parent, false);
        ImageView myImg=view.findViewById(R.id.myImg);
        TextView myName=view.findViewById(R.id.myName);

        // Ensure that the ArrayLists have enough elements
        if (position < myDataImg.size() && position < myDataName.size()) {
            myImg.setImageResource(myDataImg.get(position));
            myName.setText(myDataName.get(position));
            view.setSelected(position == selectedPosition);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                }
            });
            if (view.isSelected()) {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_color));
            } else {
                view.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            }
        } else {
            // Handle the case where there aren't enough elements in the ArrayLists
            Log.e("MyAdapter", "getView: not enough elements in ArrayLists");
        }

        return view;
    }
}
