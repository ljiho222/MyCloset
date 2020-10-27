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



public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SearchedData> arrayList;

    public SearchAdapter(Context context, ArrayList<SearchedData> arrayList){
        this.mContext=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_closet, parent, false);
        SearchAdapter.ViewHolder vh = new SearchAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((ViewHolder)holder).c_name.setText(arrayList.get(position).getCloset());
        ((ViewHolder)holder).c_style.setText(arrayList.get(position).getStyle());
        Glide.with(mContext).load(arrayList.get(position).getUrl()).into(((ViewHolder)holder).closet_profile);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView closet_profile;
        TextView c_name;
        TextView c_style;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            closet_profile=itemView.findViewById(R.id.closet_profile);
            c_name = itemView.findViewById(R.id.c_name);
            c_style = itemView.findViewById(R.id.c_style);

        }
    }
}
