package com.it.customlistviewfilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepakr on 3/29/2016.
 */
public class MyAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<Bean> beanList;
    private LayoutInflater inflater;
    List<Bean> mStringFilterList;
    ValueFilter valueFilter;


    public MyAdapter(Context context, List<Bean> beanList) {
        this.context = context;
        this.beanList = beanList;
        mStringFilterList = beanList;
    }


    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int i) {
        return beanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.single_row, null);
        }

        TextView txtName = (TextView) view.findViewById(R.id.name);
        TextView txtAge = (TextView) view.findViewById(R.id.age);

        Bean bean = beanList.get(i);
        String name = bean.getName();
        String age = bean.getAge();

        txtName.setText(name);
        txtAge.setText(age);
        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Bean> filterList = new ArrayList<Bean>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getName().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        Bean bean = new Bean(mStringFilterList.get(i)
                                .getName(), mStringFilterList.get(i)
                                .getAge());
                        filterList.add(bean);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            beanList = (ArrayList<Bean>) results.values;
            notifyDataSetChanged();
        }

    }
}