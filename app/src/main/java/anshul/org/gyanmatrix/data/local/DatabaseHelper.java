package anshul.org.gyanmatrix.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;


import anshul.org.gyanmatrix.data.model.RecordsEntity;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

@Singleton
public class DatabaseHelper {

    private SqliteHelper sqliteHelper;

    private static final String TABLE_FAVOURITE = "liked";
    private static final String KEY_ID = "id";
    private static final String KEY_PLAYER_NAME = "player_name";
    private static final String KEY_PLAYER_COUNTRY = "player_country";
    private static final String KEY_PLAYER_RUNS = "player_runs";
    private static final String KEY_PLAYER_MATCHES = "player_matches";
    private static final String KEY_PLAYER_IMAGE = "player_images";
    private static final String KEY_PLAYER_DESCRIPTION = "player_description";

    @Inject
    public DatabaseHelper(SqliteHelper dbOpenHelper) {

        sqliteHelper = dbOpenHelper;

    }

    public boolean insertIntoFavourite(RecordsEntity model) {
        ContentValues values;
        values = new ContentValues();
        SQLiteDatabase db = sqliteHelper.getWritableDatabase();
        values.put(KEY_ID, model.getId());
        values.put(KEY_PLAYER_NAME, model.getName());
        values.put(KEY_PLAYER_COUNTRY, model.getCountry());

        values.put(KEY_PLAYER_RUNS, model.getTotal_score());
        values.put(KEY_PLAYER_MATCHES, model.getMatches_played());
        values.put(KEY_PLAYER_IMAGE, model.getImage());
        values.put(KEY_PLAYER_DESCRIPTION, model.getDescription());
        try {
            db.insert(TABLE_FAVOURITE, null, values);
            db.close();

            return true;
        } catch (SQLException e) {
            db.close();
            return false;
        }
    }

    public Observable<ArrayList<RecordsEntity>> getUsers() {
        return Observable.fromCallable(new Callable<ArrayList<RecordsEntity>>() {

            @Override
            public ArrayList<RecordsEntity> call() {
                ArrayList<RecordsEntity> playerList = new ArrayList<>();
                SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                String query = "select *  from " + TABLE_FAVOURITE;
                Cursor cursor = db.rawQuery(query, null);

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        RecordsEntity listData = new RecordsEntity();
                        listData.setName(cursor.getString(cursor.getColumnIndex(KEY_PLAYER_NAME)));
                        listData.setCountry(cursor.getString(cursor.getColumnIndex(KEY_PLAYER_COUNTRY)));
                        listData.setDescription(cursor.getString(cursor.getColumnIndex(KEY_PLAYER_DESCRIPTION)));
                        listData.setImage(cursor.getString(cursor.getColumnIndex(KEY_PLAYER_IMAGE)));
                        listData.setMatches_played(cursor.getString(cursor.getColumnIndex(KEY_PLAYER_MATCHES)));
                        listData.setTotal_score(cursor.getString(cursor.getColumnIndex(KEY_PLAYER_RUNS)));

                        playerList.add(listData);


                    } while (cursor.moveToNext());
                }
                cursor.close();
                db.close();

                return playerList;

            }
        });
    }
}

