package com.onurcelikeng.deubusstops.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.onurcelikeng.deubusstops.Models.BusStopModel;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DEUBusStopDB";
    private static final String TABLE_STOPS = "Stops";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_TOTALPOINT = "totalpoint";
    private static final String KEY_VOTECOUNT = "voteCount";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_STOPS =
                "CREATE TABLE " + TABLE_STOPS
                        + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_NAME + " TEXT,"
                        + KEY_LATITUDE + " TEXT,"
                        + KEY_LONGITUDE + " TEXT,"
                        + KEY_TOTALPOINT + " TEXT,"
                        + KEY_VOTECOUNT + " INTEGER"
                        + ")";
        db.execSQL(CREATE_TABLE_STOPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOPS);
        onCreate(db);
    }

    public void LoadBusStops() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues stop1 = new ContentValues();
        stop1.put(KEY_NAME, "Tınaztepe");
        stop1.put(KEY_LATITUDE, "38.368584");
        stop1.put(KEY_LONGITUDE, "27.210985");
        stop1.put(KEY_TOTALPOINT, "4.3");
        stop1.put(KEY_VOTECOUNT, 5);
        db.insert(TABLE_STOPS, null, stop1);

        ContentValues stop2 = new ContentValues();
        stop2.put(KEY_NAME, "Mühendislik Fakultesi");
        stop2.put(KEY_LATITUDE, "38.369537");
        stop2.put(KEY_LONGITUDE, "27.207884");
        stop2.put(KEY_TOTALPOINT, "3.8");
        stop2.put(KEY_VOTECOUNT, 3);
        db.insert(TABLE_STOPS, null, stop2);

        ContentValues stop3 = new ContentValues();
        stop3.put(KEY_NAME, "Mimarlık Fakultesi");
        stop3.put(KEY_LATITUDE, "38.369138");
        stop3.put(KEY_LONGITUDE, "27.207103");
        stop3.put(KEY_TOTALPOINT, "4.1");
        stop3.put(KEY_VOTECOUNT, 15);
        db.insert(TABLE_STOPS, null, stop3);

        ContentValues stop4 = new ContentValues();
        stop4.put(KEY_NAME, "Teknopark");
        stop4.put(KEY_LATITUDE, "38.367224");
        stop4.put(KEY_LONGITUDE, "27.205502");
        stop4.put(KEY_TOTALPOINT, "4.9");
        stop4.put(KEY_VOTECOUNT, 45);
        db.insert(TABLE_STOPS, null, stop4);

        ContentValues stop5 = new ContentValues();
        stop5.put(KEY_NAME, "İşletme Fakultesi");
        stop5.put(KEY_LATITUDE, "38.368160");
        stop5.put(KEY_LONGITUDE, "27.202866");
        stop5.put(KEY_TOTALPOINT, "2.7");
        stop5.put(KEY_VOTECOUNT, 33);
        db.insert(TABLE_STOPS, null, stop5);

        db.close();
    }

    public List<BusStopModel> getAllBusStops() {
        List<BusStopModel> busStops = new ArrayList<BusStopModel>();
        String query = "SELECT * FROM " + TABLE_STOPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                BusStopModel model = new BusStopModel();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setName(cursor.getString(1));
                model.setLatitude(cursor.getString(2));
                model.setLongitude(cursor.getString(3));
                model.setTotalPoint(cursor.getString(4));
                model.setVoteCount(Integer.parseInt(cursor.getString(5)));

                busStops.add(model);
            } while (cursor.moveToNext());
        }

        return busStops;
    }

    public BusStopModel getBusStop(int id) {
        BusStopModel busStop = new BusStopModel();
        String query = "SELECT * FROM " + TABLE_STOPS + " WHERE id =" + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            busStop.setId(Integer.parseInt(cursor.getString(0)));
            busStop.setName(cursor.getString(1));
            busStop.setLatitude(cursor.getString(2));
            busStop.setLongitude(cursor.getString(3));
            busStop.setTotalPoint(cursor.getString(4));
            busStop.setVoteCount(Integer.parseInt(cursor.getString(5)));
        }

        return busStop;
    }

    public int updateBusStop(BusStopModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, model.getName());
        values.put(KEY_LATITUDE, model.getLatitude());
        values.put(KEY_LONGITUDE, model.getLongitude());
        values.put(KEY_TOTALPOINT, model.getTotalPoint());
        values.put(KEY_VOTECOUNT, model.getVoteCount());

        return db.update(TABLE_STOPS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(model.getId()) });
    }
}
