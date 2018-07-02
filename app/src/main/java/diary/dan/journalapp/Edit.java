package diary.dan.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import diary.dan.journalapp.data.DiaryDbHelper;
import diary.dan.journalapp.model.DataModel;

public class Edit extends AppCompatActivity {

    TextView edt_content, edt_title;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edt_content = (TextView) findViewById(R.id.edt_content);
        edt_title = (TextView) findViewById(R.id.edt_title);

        Intent intent = getIntent();
        position = intent.getIntExtra("ID", 0);

        loadViews(position);

    }

    private void loadViews(int position) {
        DiaryDbHelper diaryDbHelper = new DiaryDbHelper(Edit.this);
        DataModel dataModel = diaryDbHelper.getSingleData(position);
        setTextViews(dataModel.title, dataModel.content);
    }

    public void setTextViews(String title, String content){
        edt_title.setText(title);
        edt_content.setText(content);
    }
}
