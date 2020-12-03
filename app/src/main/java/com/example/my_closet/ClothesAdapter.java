package com.example.my_closet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Clothes> Clothess;
    private User user;
    private String cls_name;


    public ClothesAdapter(Context context, ArrayList<Clothes> Clothes, User user, String cls_name){
        this.mContext=context;
        this.Clothess=Clothes;
        this.user=user;
        this.cls_name=cls_name;
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
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final int pos = getAdapterPosition() ;

                    AlertDialog.Builder builder=new AlertDialog.Builder(mContext).setTitle("옷 삭제").setMessage("삭제하시겠습니까?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Log.e("123123", String.valueOf(Clothess.get(pos).getKey()));
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Closets").child(user.getUserName()).child(cls_name).child("Clothes").child(Clothess.get(pos).getKey());
                                    reference.removeValue();
                                    Toast.makeText(mContext,"옷 삭제",Toast.LENGTH_LONG).show();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return true;
                }
            });

        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext).load(Clothess.get(position).getUrl()).into(((ViewHolder)holder).imageView);


    }

    @Override
    public int getItemCount() {
        return Clothess.size();
    }


}
