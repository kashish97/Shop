package in.codecorp.ssgcp.shop;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.codecorp.ssgcp.shop.Utils.utils;

public class MyContentProvider extends ContentProvider {
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        String tabName = uri.getLastPathSegment();
        int i = sqLiteDatabase.delete(tabName,selection,null);

        return i;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        String tabname=uri.getLastPathSegment();
        long id=sqLiteDatabase.insert(tabname,null,values);
        Uri tempUri=Uri.parse("dummyuri/"+id);
        return  tempUri;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper=new DBHelper(getContext(),utils.DB_NAME,null,utils.DB_VERSION);
        sqLiteDatabase=dbHelper.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        String tabname=uri.getLastPathSegment();
        Cursor cursor=sqLiteDatabase.query(tabname,projection,null,null,null,null,null
        );

        return  cursor;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        String tabName = uri.getLastPathSegment();
        int i = sqLiteDatabase.update(tabName,values,selection,null);
        return i;
    }
    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version); // Create the DB
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(utils.CREATE_TAB_QUERY); // Create the Table
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}

