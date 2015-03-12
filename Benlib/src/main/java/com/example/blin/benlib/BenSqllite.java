package com.example.blin.benlib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by blin on 2015/3/12.
 */
public class BenSqllite {
    public static class DBManager {

        private DatabaseHelper dbHelper;

        private Context context;

        private SQLiteDatabase database;

        public DBManager(Context c) {
            context = c;
        }

        public DBManager open() throws SQLException {
            try {
                dbHelper = new DatabaseHelper(context, Environment.getExternalStorageDirectory()+File.separator+
                "JAVATECHIG_TODOS.DB", "TODOS");
                database = dbHelper.getWritableDatabase();
            }
            catch (Exception e)
            {
               Log.i("BenSqllit",e.toString());
                return null;
            }
            return this;
        }

        public void close() {
            dbHelper.close();
        }

        public void insert(String name, String desc) {
            ContentValues contentValue = new ContentValues();
            contentValue.put(DatabaseHelper.TODO_SUBJECT, name);
            contentValue.put(DatabaseHelper.TODO_DESC, desc);
            database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        }

        public Cursor fetch() {
            String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TODO_SUBJECT, DatabaseHelper.TODO_DESC };
            Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;
        }

        public int update(long _id, String name, String desc) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.TODO_SUBJECT, name);
            contentValues.put(DatabaseHelper.TODO_DESC, desc);
            int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
            return i;
        }

        public void delete(long _id) {
            database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
        }

    }


    public static class DatabaseHelper extends SQLiteOpenHelper {

        // Table Name
        public  static String TABLE_NAME;
        public  static String DB_NAME;

        // Table columns
        public static final String _ID = "_id";
        public static final String TODO_SUBJECT = "subject";
        public static final String TODO_DESC = "description";
        // Database Information
//        static final String DB_NAME = "JAVATECHIG_TODOS.DB";

        // database version
        static final int DB_VERSION = 1;

        // Creating table query


        public DatabaseHelper(Context context,String DB_NAME1,String TABLE_NAME1) {
            super(context, DB_NAME1, null, DB_VERSION);
            this.TABLE_NAME= TABLE_NAME1;
            this.DB_NAME=DB_NAME1;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("SQL","Table name is "+TABLE_NAME);
            if(TABLE_NAME!=null) {
                String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO_SUBJECT + " TEXT NOT NULL, " + TODO_DESC + " TEXT);";
                Log.i("SQLHELP", CREATE_TABLE);

                db.execSQL(CREATE_TABLE);
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
    /**
     * Exports the cursor value to an excel sheet.
     * Recommended to call this method in a separate thread,
     * especially if you have more number of threads.
     *
     * @param cursor
     */
    private void exportToExcel(Cursor cursor) {

        final String fileName = "TodoList.xls";

        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();

        File directory = new File(sdCard.getAbsolutePath() + "/javatechig.todo");

        //create directory if not exist
        if(!directory.isDirectory()){
            directory.mkdirs();
        }

        //file path
        File file = new File(directory, fileName);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook;

        try {
            workbook = Workbook.createWorkbook(file, wbSettings);

            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("MyShoppingList", 0);

            try {
                sheet.addCell(new Label(0, 0, "Subject")); // column and row
                sheet.addCell(new Label(1, 0, "Description"));

                if (cursor.moveToFirst()) {
                    do {
                        String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_SUBJECT));
                        String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DESC));

                        int i = cursor.getPosition() + 1;

                        sheet.addCell(new Label(0, i, title));
                        sheet.addCell(new Label(1, i, desc));

                    } while (cursor.moveToNext());
                }

                //closing cursor
                cursor.close();

            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

            workbook.write();

            try {
                workbook.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
