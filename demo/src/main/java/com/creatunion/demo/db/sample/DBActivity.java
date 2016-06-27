package com.creatunion.demo.db.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.creatunion.demo.R;
import com.creatunion.demo.db.NoteBean;

public class DBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);


        NoteDao dao = NoteSource.getInstance().getNoteDao();
        NoteBean note = new NoteBean();
        note.setContent("test content");
        note.setOwnerUid("test_user_id");
        note.setGrammarId(10);
        
        dao.addIfNotExist(this,note);
    }
}
