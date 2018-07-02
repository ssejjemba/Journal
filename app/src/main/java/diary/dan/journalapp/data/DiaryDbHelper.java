package diary.dan.journalapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import diary.dan.journalapp.data.DiaryContract.DiaryEntry;

import diary.dan.journalapp.model.DataModel;

public class DiaryDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DiaryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "diary.db";

    private static final int DATABASE_VERSION = 1;

    public DiaryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_DIARY_TABLE =  "CREATE TABLE " + DiaryEntry.TABLE_NAME + " ("
                + DiaryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DiaryEntry.COLUMN_MEMO_TITLE + " TEXT, "
                + DiaryEntry.COLUMN_MEMO_CONTENT + " TEXT, "
                + DiaryEntry.COLUMN_MEMO_DATE + " INTEGER NOT NULL );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_DIARY_TABLE);
    }

    private void insertData(String title, String date, String location, String content, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();


        contentValues.put(DiaryEntry.COLUMN_MEMO_TITLE, title);
        contentValues.put(DiaryEntry.COLUMN_MEMO_DATE, date);
        contentValues.put(DiaryEntry.COLUMN_MEMO_CONTENT,content);

        db.insert(DiaryEntry.TABLE_NAME,null,contentValues);
    }

    public DataModel getSingleData(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {DiaryEntry.COLUMN_MEMO_TITLE, DiaryEntry.COLUMN_MEMO_CONTENT, DiaryEntry.COLUMN_MEMO_DATE};
        DataModel model = null;
        Cursor cursor = db.query(DiaryEntry.TABLE_NAME, columns, DiaryEntry._ID + " = '" + id + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(DiaryEntry._ID);
            int index2 = cursor.getColumnIndex(DiaryEntry.COLUMN_MEMO_TITLE);
            int index3 = cursor.getColumnIndex(DiaryEntry.COLUMN_MEMO_CONTENT);
            int index4 = cursor.getColumnIndex(DiaryEntry.COLUMN_MEMO_DATE);
            int id1 = cursor.getInt(index);
            String title = cursor.getString(index2);
            String content = cursor.getString(index3);
            int date = cursor.getInt(index4);
            model = new DataModel(id1, title, content, date);
        }
        return model;
    }

    public List<DataModel> getData(){


            List<DataModel> data=new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from "+DiaryEntry.TABLE_NAME+" ;",null);
            StringBuffer stringBuffer = new StringBuffer();
            DataModel dataModel = null;
            while (cursor.moveToNext()) {
                dataModel= new DataModel();
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DiaryEntry.COLUMN_MEMO_TITLE));
                int date = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DiaryEntry.COLUMN_MEMO_DATE)));
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DiaryEntry._ID)));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(DiaryEntry.COLUMN_MEMO_CONTENT));
                dataModel.setTitle(title);
                dataModel.setId(id);
                dataModel.setDate(date);
                dataModel.setContent(content);
                stringBuffer.append(dataModel);
                // stringBuffer.append(dataModel);
                data.add(dataModel);
            }

            for (DataModel mo:data ) {

                Log.i(LOG_TAG,""+mo.getTitle());
            }

            //
            cursor.close();
            db.close();

            return data;

    }

    public void deleteData(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DiaryEntry.TABLE_NAME, DiaryEntry.COLUMN_MEMO_TITLE +" == ?", new String[] {title});
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
