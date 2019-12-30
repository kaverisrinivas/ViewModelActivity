package com.example.viewmodelactivity;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ScrollingActivity extends AppCompatActivity implements OnNoteClickListener {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);
        progressDialog = new ProgressDialog(ScrollingActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        recyclerView = findViewById(R.id.customRecycler);
        adapter = new CustomAdapter(ScrollingActivity.this, Picasso.with(getBaseContext()), ScrollingActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);
        model.loadUsers();
        model.getUsers().observe(this, new Observer<List<RetroPhoto>>() {
            @Override
            public void onChanged(@Nullable List<RetroPhoto> retroPhotos) {
                progressDialog.dismiss();
                adapter.setList(retroPhotos);
            }
        });


//    private void generatePhotoList(List<RetroPhoto> photoList) {

        //      recyclerView=findViewById(R.id.customRecycler);
        //    adapter=new CustomAdapter(this,photoList, Picasso.with(getBaseContext()),this);
        //   recyclerView.setHasFixedSize(true);
        //  RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ScrollingActivity.this);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position) {
        Toast.makeText(this,"hello busy people",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,ChildActivity.class);
        //intent.putExtra("all",1);
        startActivity(intent);

    }
}/*

    @GET("/refresh_token")
    fun createToken(): Call<LoginData>
@GET("GET /episodes/{id}")
    fun episodeList(@Path ("id") pathId: Int): Call<EpisodeData>*/