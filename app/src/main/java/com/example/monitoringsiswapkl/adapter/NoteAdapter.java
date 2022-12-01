package com.example.monitoringsiswapkl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoringsiswapkl.R;
import com.example.monitoringsiswapkl.model.DataNote;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.HolderData> {

    private Context context;
    private List<DataNote> dataNotes;

    public NoteAdapter(Context context, List<DataNote> dataNotes) {
        this.context = context;
        this.dataNotes = dataNotes;
    }

    @NonNull
    @Override
    public NoteAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.HolderData holder, int position) {
        DataNote dataNote = dataNotes.get(position);
        holder.note.setText(dataNote.getNote());

    }

    @Override
    public int getItemCount() {
        return dataNotes.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView note;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.note);
        }
    }
}
