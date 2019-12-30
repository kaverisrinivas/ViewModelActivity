package com.example.viewmodelactivity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.database.Observable;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<RetroPhoto>> users;

    public LiveData<List<RetroPhoto>> getUsers() {
        if ( users== null) {
            users = new MutableLiveData<>();
        }
        return users;
    }

    public void loadUsers() {
        GetDataService service = RetrofitClientInstance.getRetrofit().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        //  service.getSinglePhoto(1);
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                users.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
             //   progressDialog.dismiss();
//                Toast.makeText(S.this,"something went wrong...please try again",Toast.LENGTH_LONG).show();

            }
        });

    }
}
