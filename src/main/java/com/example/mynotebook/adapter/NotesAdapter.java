package com.example.mynotebook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynotebook.R;

import com.example.mynotebook.model.Note;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {

    private ArrayList<Note> notes;
    private OnItemClickListener listener;

    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ll = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        return new NoteHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note = getNote(position);
        if(note != null) {
            holder.headline.setText(note.getHeadline());
            holder.body.setText(note.getBody());
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private Note getNote(int position) {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        TextView headline, body;
        public NoteHolder(@NonNull View view) {
            super(view);
            headline = view.findViewById(R.id.new_headline);
            body = view.findViewById(R.id.note_body);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(notes.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
