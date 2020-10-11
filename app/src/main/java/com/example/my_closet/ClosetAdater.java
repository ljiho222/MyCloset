package com.example.my_closet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//파이어베이스에서 데이터 가져와서 뿌려주는 클래스(어댑터)
public class ClosetAdater extends RecyclerView.Adapter<ClosetAdater.CustomViewHolder> {

    private ArrayList<Newcloset> arrayList;
    private Context context;

    public ClosetAdater(ArrayList<Newcloset> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_closet,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.closet_profile);
        holder.closet_name.setText(arrayList.get(position).getName());
        holder.closet_style.setText(arrayList.get(position).getStyle());
        holder.closet_location.setText(arrayList.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView closet_profile;
        TextView closet_name;
        TextView closet_style;
        TextView closet_location;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.closet_profile = itemView.findViewById(R.id.closet_profile);
            this.closet_name = itemView.findViewById(R.id.closet_name);
            this.closet_style = itemView.findViewById(R.id.closet_style);
            this.closet_location = itemView.findViewById(R.id.closet_location);
        }
    }
}
