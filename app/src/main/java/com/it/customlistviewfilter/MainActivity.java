package com.it.customlistviewfilter;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private ListView lv;
    private SearchView sv;
    private List<Bean> beanList;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sv = (SearchView) findViewById(R.id.search_view);
        lv = (ListView) findViewById(R.id.lv);

        String[] name = {"Deepak", "Ravi", "Raj", "Rakesh", "Mandeep", "Sishu"};
        String[] age = {"20", "30", "25", "45", "50", "35"};

        beanList = new ArrayList<Bean>();

        for (int i = 0; i < name.length; i++) {
            Bean bean = new Bean(name[i], age[i]);
            beanList.add(bean);
        }

        for (int i = 0; i < beanList.size(); i++) {
            Bean bean = beanList.get(i);
            String n = bean.getName();
            Log.e("Name", n);
        }


        adapter = new MyAdapter(getApplicationContext(), beanList);
        lv.setAdapter(adapter);
        sv.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        adapter.getFilter().filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}