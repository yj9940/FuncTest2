package com.example.lenovo.functest2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


//便签记录
public class ContactMainTabFragment extends Fragment {
    private Button button;
    private ListView listView;
    private MyDataBase myDataBase;
    private LayoutInflater layoutInflater;
    private ArrayList<Note> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_main,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView) getActivity().findViewById(R.id.listView1);
        layoutInflater = getActivity().getLayoutInflater();
        myDataBase = new MyDataBase(getActivity());
        button = (Button) getActivity().findViewById(R.id.button1);
        arrayList = myDataBase.getArray();
        MyAdapter myAdapter = new MyAdapter(layoutInflater, arrayList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("id", arrayList.get(i).getId());
                startActivity(intent);
                getActivity().finish();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new AlertDialog.Builder(getActivity()).setTitle("删除").setMessage("数据无价，谨慎操作!")
                        .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        myDataBase.deleteNote(arrayList.get(position).getId());
                        arrayList = myDataBase.getArray();
                        MyAdapter myAdapter = new MyAdapter(layoutInflater, arrayList);
                        listView.setAdapter(myAdapter);

                    }
                }).create().show();
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


    }
}