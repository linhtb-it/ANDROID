package com.example.watchnow_project.Control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.watchnow_project.Model.entity.Category;
import com.example.watchnow_project.Model.entity.Video;
import com.example.watchnow_project.R;

import java.util.ArrayList;

public class Category_Items_Adapter extends RecyclerView.Adapter<Category_Items_Adapter.ViewHolder> {
    Category category;
    Context context;
    ArrayList<Video> videoList;

    public Category_Items_Adapter(Category category, Context context,ArrayList<Video> videoList) {
        this.category = category;
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public Category_Items_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_items_list, parent, false);
        Category_Items_Adapter.ViewHolder viewHolder = new Category_Items_Adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Items_Adapter.ViewHolder holder, int position) {
        if(position == 0){
            Glide.with(context).load(category.getThumb()).into(holder.img_Avatar_Category_Video);
            holder.tv_Title_Category_Video.setText(category.getTitle());
        }
        else {
            Glide.with(context).load(videoList.get(position-1)).into(holder.img_Avatar_Category_Video);
            holder.tv_Title_Category_Video.setText(videoList.get(position-1).getTitle());
        }

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_Avatar_Category_Video;
        TextView tv_Title_Category_Video;
        public ViewHolder(@NonNull View view) {
            super(view);

            img_Avatar_Category_Video = view.findViewById(R.id.img_Avatar_Category_Video);
            tv_Title_Category_Video = view.findViewById(R.id.tv_Title_Category_Video);
        }
    }
}
