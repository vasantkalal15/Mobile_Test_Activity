package com.example.mobile_test_activity.Adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mobile_test_activity.Model.UserModel;
import com.example.mobile_test_activity.R;
import com.example.mobile_test_activity.SelectListener;
import com.example.mobile_test_activity.ShowActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewholder> {

    private final List<UserModel> userModelList;
    private final Context context;

    private final SelectListener listener;

    SharedPreferences sharedPreferences;

    float counter = 0;




    public UserAdapter(List<UserModel> userModelList, Context context, SelectListener listener) {
        this.userModelList = userModelList;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public UserAdapter.CustomViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.design,parent,false);
        return new CustomViewholder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.CustomViewholder holder,int position) {

//        Picasso.get().load(datalist.get(position).getAvatar()).into(holder.IvImage);
        holder.tvemail.setText(userModelList.get(position).getEmail());
        holder.tvfirst_name.setText(userModelList.get(position).getFirst_name());
        holder.tvlast_name.setText(userModelList.get(position).getLast_name());

        Glide.with(context)
                .load(userModelList.get(position).getAvatar())
                .into(holder.IvImage);


        holder.relativeLayout.setOnClickListener(v -> {
            listener.onItemClicked(userModelList.get(holder.getAdapterPosition()));

           sharedPreferences = context.getSharedPreferences("myPREFERENCE", Context.MODE_PRIVATE);
           SharedPreferences.Editor editor = sharedPreferences.edit();

           counter = sharedPreferences.getFloat("key",0);
            counter++;

            String strCounter = Float.toString(counter);




            Toast.makeText(context, "You click : "+ strCounter + "Times" , Toast.LENGTH_SHORT).show();

            editor.putFloat("key" ,counter);

            editor.apply();


            Intent intent = new Intent(context, ShowActivity.class);
            Bundle bundle = new Bundle();

            bundle.putString("Avatar",userModelList.get(holder.getAdapterPosition()).avatar);
            bundle.putString("Email",userModelList.get(holder.getAdapterPosition()).email);
            bundle.putString("First_name",userModelList.get(holder.getAdapterPosition()).first_name);
            bundle.putString("Last_name",userModelList.get(holder.getAdapterPosition()).last_name);

            intent.putExtras(bundle);

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }


    public static class CustomViewholder extends RecyclerView.ViewHolder {

        ImageView IvImage;
        TextView tvemail, tvfirst_name, tvlast_name;

        TextView show123;
        public RelativeLayout relativeLayout;
        public CustomViewholder(@NonNull View itemView) {
            super(itemView);

            IvImage=itemView.findViewById(R.id.ivImage);
            tvemail=itemView.findViewById(R.id.tvEmail);
            tvfirst_name=itemView.findViewById(R.id.tvFirstName);
            tvlast_name=itemView.findViewById(R.id.tvLastName);

            relativeLayout=itemView.findViewById(R.id.CvDesign);
            show123=itemView.findViewById(R.id.tvshow12);

        }
    }
}

