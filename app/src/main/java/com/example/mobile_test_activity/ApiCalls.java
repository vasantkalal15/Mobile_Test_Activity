package com.example.mobile_test_activity;




import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCalls {

    @GET("api/users/")
    Call<JsonResponse> getusermodel();


}
