package com.example.mobile_test_activity;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.TextView;


import com.example.mobile_test_activity.Adapter.UserAdapter;
import com.example.mobile_test_activity.Model.UserModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SelectListener{

    ApiCalls apicalls;



    RecyclerView recyclerView;

    List<UserModel>userModelList;


    TextView showtext123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        apicalls = RetrofitInstance.getRetrofit().create(ApiCalls.class);
        recyclerView=findViewById(R.id.recycleview);
        userModelList= new ArrayList<>();
        showtext123=findViewById(R.id.tvshow12);


        Call<JsonResponse> call = apicalls.getusermodel();

       call.enqueue(new Callback<JsonResponse>() {
           @Override
           public void onResponse(@NonNull Call<JsonResponse> call, @NonNull Response<JsonResponse> response) {

                       JsonResponse jsonResponse = response.body();

                       assert jsonResponse != null;
                       userModelList= new ArrayList<>(Arrays.asList(jsonResponse.getData()));

                       PutDataIntoRecyclerView(userModelList);

           }

           @Override
           public void onFailure(@NonNull Call<JsonResponse> call, @NonNull Throwable t) {

           }
       });

    }

    private void PutDataIntoRecyclerView(List<UserModel> userModelList) {

         UserAdapter adapter= new UserAdapter(userModelList,this,this);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(UserModel userModel) {

//        final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
//        loadingDialog.startLoadingDialog();
//
//        Handler handler = new Handler();
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                handler.postDelayed(this, 1000);
//
//
//
//            }
//        });
//        loadingDialog.dismissDialog();
    }
}