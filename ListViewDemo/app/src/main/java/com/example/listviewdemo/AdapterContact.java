package com.example.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterContact extends BaseAdapter {

    List<Contact> lstContact;

    public AdapterContact(List<Contact> lstContact) {
        this.lstContact = lstContact;
    }

    @Override
    public int getCount() {
        return lstContact.size();
    }

    @Override
    public Object getItem(int i) {
        return lstContact.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.items,viewGroup,false);
        TextView tvName;
        TextView tvPhone;
        ImageView imgIcon;

        tvName =  v.findViewById(R.id.tvName);
        tvPhone = v.findViewById(R.id.tvPhone);
        imgIcon = v.findViewById(R.id.imgIcon);
        tvName.setText(lstContact.get(i).getName());
        tvPhone.setText(lstContact.get(i).getPhone());
        if(lstContact.get(i).isIcon())
            imgIcon.setImageResource(R.drawable.user);
        return v;
    }
}
