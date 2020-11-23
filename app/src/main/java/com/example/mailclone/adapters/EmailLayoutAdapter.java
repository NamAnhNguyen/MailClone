package com.example.mailclone.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mailclone.R;

import java.util.List;

public class EmailLayoutAdapter extends BaseAdapter {

    List<Email> emailList;
    Context ctx;
    int count = 0;

    public EmailLayoutAdapter(List<Email> emailList, Context context) {
        this.emailList = emailList;
        this.ctx = context;
        count = 0;
    }

    @Override
    public int getCount() {
        return emailList.size();
    }

    @Override
    public Object getItem(int i) {
        return emailList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.a_mail_layout, viewGroup, false);
            Log.v("TAG", "Count: " + (count++));

            viewHolder = new ViewHolder();
            viewHolder.avatar = view.findViewById(R.id.avatar);
            viewHolder.subject = view.findViewById(R.id.subject);
            viewHolder.time = view.findViewById(R.id.time);
            viewHolder.content = view.findViewById(R.id.content);
            viewHolder.email = view.findViewById(R.id.email);
            viewHolder.checkBox = view.findViewById(R.id.checkBox);
            view.setTag(viewHolder);
        } else viewHolder = (ViewHolder) view.getTag();
        Email item = emailList.get(i);
        viewHolder.email.setText(item.email);
        viewHolder.avatar.setText(item.avatar);
        viewHolder.subject.setText(item.subject);
        viewHolder.content.setText(item.content);
        viewHolder.time.setText(item.time);

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("click", "olalsdjansjdnaksndkan");
                boolean check = item.checked;
                item.checked = !check;
                if (check)
                    viewHolder.checkBox.setBackgroundResource(R.drawable.grey_star_foreground);
                else viewHolder.checkBox.setBackgroundResource(R.drawable.star_foreground);
            }
        });

        return view;
    }

    private class ViewHolder {
        TextView avatar;
        TextView subject;
        TextView content;
        TextView time;
        TextView email;
        ImageButton checkBox;
    }

    ;
};

