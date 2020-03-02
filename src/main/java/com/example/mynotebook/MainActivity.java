package com.example.mynotebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mynotebook.adapter.NotesAdapter;
import com.example.mynotebook.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private NotesAdapter adapter;

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewNote();
            }
        });
        loadNotes();

        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                intent.putExtra(AddNote.EXTRA_ID, note.getId());
                intent.putExtra(AddNote.EXTRA_HEADLINE, note.getHeadline());
                intent.putExtra(AddNote.EXTRA_BODY, note.getBody());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }

    private void loadNotes() {
        this.notes = new ArrayList<>();

        for (int w = 1; w < 5; w++) {
            notes.add(new Note(w,"note "+w, "desc "+w+" "+(w*w+2)));
        }
        adapter = new NotesAdapter(notes);
        recyclerView.setAdapter(adapter);
    }

    private void addNewNote() {
        Intent addNote = new Intent(MainActivity.this, AddNote.class);
        startActivityForResult(addNote, ADD_NOTE_REQUEST);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null && requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String headline = data.getStringExtra(AddNote.EXTRA_HEADLINE);
            String body = data.getStringExtra(AddNote.EXTRA_BODY);
            notes.add(new Note(notes.size()+1, headline, body));
            adapter.notifyDataSetChanged();
        } else if(data != null && requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddNote.EXTRA_ID, -1);

            String headline = data.getStringExtra(AddNote.EXTRA_HEADLINE);
            String body = data.getStringExtra(AddNote.EXTRA_BODY);

            for(Note note: notes) {
                if(note.getId() == id) {
                    note.setHeadline(headline);
                    note.setBody(body);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
