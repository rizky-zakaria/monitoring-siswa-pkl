package com.example.monitoringsiswapkl.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.monitoringsiswapkl.R;
import com.example.monitoringsiswapkl.adapter.NoteAdapter;
import com.example.monitoringsiswapkl.api.ApiInterface;
import com.example.monitoringsiswapkl.api.ApiServer;
import com.example.monitoringsiswapkl.model.DataNote;
import com.example.monitoringsiswapkl.model.ResponseNote;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataNote> dataNotes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = v.findViewById(R.id.list_item);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        loadNote();


        return v;
    }

    private void loadNote() {
        ApiInterface apiInterface = ApiServer.getClient().create(ApiInterface.class);
        Call<ResponseNote> call = apiInterface.getNote();
        call.enqueue(new Callback<ResponseNote>() {
            @Override
            public void onResponse(Call<ResponseNote> call, Response<ResponseNote> response) {
                if (response.isSuccessful()){
                    dataNotes = response.body().getData();
                    adapter = new NoteAdapter(getContext(), dataNotes);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseNote> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}