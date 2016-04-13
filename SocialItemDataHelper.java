package com.example.paco.myapp1application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paco on 06/04/2016.
 */
public class SocialItemDataHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "database_tutorial";

    // Table name
    private static final String SOCIAL_ITEMS = "social_items";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_NUM_CLICKS = "clicks";

    public SocialItemDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SOCIAL_ITEMS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT UNIQUE NOT NULL,"
                + COLUMN_NUM_CLICKS + " LONG NOT NULL" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // simple database upgrade operation:
        // 1) drop the old table
        db.execSQL("DROP TABLE IF EXISTS " + SOCIAL_ITEMS);

        // 2) create a new database
        onCreate(db);
    }

    /**
     * retrieve all items from the database
     */
    public List<SocialItem> getAllItems() {
        // initialize the list
        List<SocialItem> items = new ArrayList<SocialItem>();

        // obtain a readable database
        SQLiteDatabase db = getReadableDatabase();

        // send query
        Cursor cursor = db.query(SOCIAL_ITEMS, new String[] {
                        COLUMN_TITLE,
                        COLUMN_NUM_CLICKS },
                null, null, null, null, null, null); // get all rows

        if (cursor != null) {
            // add items to the list
            for(cursor.moveToFirst(); cursor.isAfterLast() == false; cursor.moveToNext()) {
                items.add(new SocialItem(cursor.getString(0), Long.parseLong(cursor.getString(1))));
            }

            // close the cursor
            cursor.close();
        }

        // close the database connection
        db.close();

        // return the list
        return items;
    }

    /**
     * Add items to the list
     */
    public void addItems(List<SocialItem> items) {
        if(items != null && items.size() > 0) {
            // obtain a readable database
            SQLiteDatabase db = getWritableDatabase();

            for(SocialItem item : items) {
                addItem(db, item);
            }

            // close the database connection
            db.close();
        }
    }

    /**
     * update an existing item
     */
    public void updateItem(SocialItem item) {
        if(item != null) {
            // obtain a readable database
            SQLiteDatabase db = getWritableDatabase();

            // prepare values
            ContentValues values = new ContentValues();
            values.put(COLUMN_NUM_CLICKS, item.getNumClicks());

            // send query for the row id
            Cursor cursor = db.query(SOCIAL_ITEMS,
                    new String[] {COLUMN_ID},
                    COLUMN_TITLE + "=?",
                    new String[] {item.title},
                    null, null, null, null);

            if(cursor != null) {
                if(cursor.moveToFirst()) {
                    // update the row
                    db.update(SOCIAL_ITEMS, values,
                            COLUMN_ID + "=?",
                            new String[] {cursor.getString(0)});
                }

                cursor.close();
            }

            // close the database connection
            db.close();
        }
    }

    /**
     * Add a new item
     */
    private void addItem(SQLiteDatabase db, SocialItem item) {
        // prepare values
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, item.title);
        values.put(COLUMN_NUM_CLICKS, item.getNumClicks());

        // add the row
        db.insert(SOCIAL_ITEMS, null, values);
    }
}

