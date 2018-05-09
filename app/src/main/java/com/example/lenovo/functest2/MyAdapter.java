package com.example.lenovo.functest2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<Note> arrayList;
    public MyAdapter(LayoutInflater layoutInflater,ArrayList<Note> arrayList){
        this.arrayList=arrayList;
        this.layoutInflater=layoutInflater;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=new ViewHolder();
        if(view==null){
            view=layoutInflater.inflate(R.layout.adapter_listview,null);
            viewHolder.textView1=(TextView)view.findViewById(R.id.textview1);
            viewHolder.textView2=(TextView)view.findViewById(R.id.textview2);
            view.setTag(viewHolder);
        }
        viewHolder=(ViewHolder)view.getTag();
        viewHolder.textView1.setText(arrayList.get(i).getTitle());
        viewHolder.textView2.setText(arrayList.get(i).getDate());
        return view;
    }
}