package com.example.my_closet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Newcloset> Newclosets;
    private User user;

    public ViewPagerAdapter(Context context, ArrayList<Newcloset> newclosets, User user)
    {
        this.mContext = context;
        this.Newclosets = newclosets;
        this.user = user;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.closetpage, null);

        TextView cls_name = view.findViewById(R.id.cls_name);
        ImageView imageView = view.findViewById(R.id.imageView);

        if(1==Newclosets.get(position).getStyle()){
            imageView.setImageResource(R.drawable.closet_illust1);
        }
        else if(2==Newclosets.get(position).getStyle()){
            imageView.setImageResource(R.drawable.closet_illust2);
        }
        else if(3==Newclosets.get(position).getStyle()){
            imageView.setImageResource(R.drawable.closet_illust3);
        }
        else if(4==Newclosets.get(position).getStyle()){
            imageView.setImageResource(R.drawable.closet_illust4);
        }
        else if(5==Newclosets.get(position).getStyle()){
            imageView.setImageResource(R.drawable.closet_illust5);
        }
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                AlertDialog.Builder builder=new AlertDialog.Builder(mContext).setTitle("옷장 삭제").setMessage("삭제하시겠습니까?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.e("123123", String.valueOf(Newclosets.get(position).getName()));
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Closets").child(user.getUserName()).child(Newclosets.get(position).getName());
                                reference.removeValue();
                                Toast.makeText(mContext,"옷장 삭제",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
        cls_name.setText(Newclosets.get(position).getName());
        container.addView(view);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Closet.class);
                intent.putExtra("cls_name", Newclosets.get(position).getName());
                intent.putExtra("cls_style", Newclosets.get(position).getStyle());
                intent.putExtra("userInfo", user);
                ((Activity)mContext).startActivity(intent);
            }
        });

        Log.e("123123123", Newclosets.get(position).getName());

        return view;
    }

    @Override
    public int getCount() {
        return Newclosets.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (View)o);
    }
}