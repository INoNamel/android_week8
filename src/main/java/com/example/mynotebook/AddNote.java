package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotebook.adapter.NotesAdapter;
import com.example.mynotebook.model.Note;

import java.util.ArrayList;

public class AddNote extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.mynotebook.EXTRA_ID";
    public static final String EXTRA_HEADLINE = "com.example.mynotebook.EXTRA_HEADLINE";
    public static final String EXTRA_BODY = "com.example.mynotebook.EXTRA_BODY";

    EditText edit_headline, edit_body;
    Button save;
    /*private ArrayList<Note> notes;
    private NotesAdapter adapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edit_headline = findViewById(R.id.new_headline);
        edit_body = findViewById(R.id.new_body);

        save = findViewById(R.id.save);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)) {
            edit_headline.setText(intent.getStringExtra(EXTRA_HEADLINE));
            edit_body.setText(intent.getStringExtra(EXTRA_BODY));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }
    public void saveNote() {
        String headline = edit_headline.getText().toString();
        String body = edit_body.getText().toString();


        Intent data = new Intent();
        data.putExtra(EXTRA_HEADLINE, headline);
        data.putExtra(EXTRA_BODY, body);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        if(!headline.isEmpty() && !body.isEmpty()) {
            setResult(RESULT_OK, data);
            finish();
        } else {
            Toast.makeText(this, "empty fields", Toast.LENGTH_SHORT).show();
        }
    }
}
