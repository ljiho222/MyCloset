package com.example.my_closet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Clothes> Clothess;

    public ClothesAdapter(Context context, ArrayList<Clothes> Clothes){
        this.mContext=context;
        this.Clothess=Clothes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.adapterlist, parent, false);
        ClothesAdapter.ViewHolder vh = new ClothesAdapter.ViewHolder(view);

        return vh;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgview);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(Clothess.get(position).getUrl()).into(((ViewHolder)holder).imageView);
    }

    @Override
    public int getItemCount() {
        return Clothess.size();
    }


}
