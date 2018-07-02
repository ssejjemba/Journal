package diary.dan.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import diary.dan.journalapp.data.DiaryDbHelper;

public class Welcome extends AppCompatActivity {

    ImageButton btn_edt, btn_delete;
    RecyclerView mRecyclerView;
    RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        populateViews();
        loadMemos();
        btn_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = adapter.position;
                editMemo(position);
            }
        });
        
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMemo();
            }
        });
    }

    private void deleteMemo() {
    }

    private void editMemo(int position) {

        Intent intent = new Intent(Welcome.this, Edit.class);
        intent.putExtra("ID", position);
        startActivity(intent);
    }

    private void populateViews() {
        btn_edt = (ImageButton) findViewById(R.id.ib_edt);
        btn_delete = (ImageButton) findViewById(R.id.ib_delete);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
    }

    private void loadMemos() {


        DiaryDbHelper dbHelper = new DiaryDbHelper(Welcome.this);
        adapter = new RecyclerAdapter(dbHelper.getData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(Welcome.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(adapter);
    }
}
