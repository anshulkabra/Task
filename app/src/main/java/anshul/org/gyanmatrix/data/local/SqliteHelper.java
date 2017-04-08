package anshul.org.gyanmatrix.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import anshul.org.gyanmatrix.injection.ApplicationContext;

@Singleton
public class SqliteHelper extends SQLiteOpenHelper {

    private static final String LOG = "SqliteHelper";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Stack";
    private static final String TABLE_FAVOURITE = "liked";

    private static final String KEY_ID = "id";
    private static final String KEY_PLAYER_NAME = "player_name";
    private static final String KEY_PLAYER_COUNTRY = "player_country";
    private static final String KEY_PLAYER_RUNS = "player_runs";
    private static final String KEY_PLAYER_MATCHES = "player_matches";
    private static final String KEY_PLAYER_IMAGE = "player_images";
    private static final String KEY_PLAYER_DESCRIPTION = "player_description";


    private static final String CREATE_TABLE_BOOKMARKS = "CREATE TABLE "
            + TABLE_FAVOURITE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_PLAYER_NAME + " TEXT," + KEY_PLAYER_COUNTRY + " TEXT,"
            + KEY_PLAYER_RUNS + " TEXT," + KEY_PLAYER_MATCHES + " TEXT,"
            + KEY_PLAYER_IMAGE + " TEXT," + KEY_PLAYER_DESCRIPTION + " TEXT" + ")";


    @Inject
    public SqliteHelper(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_BOOKMARKS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITE);
        onCreate(db);
    }

}

