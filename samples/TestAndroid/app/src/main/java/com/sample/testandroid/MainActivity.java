package com.sample.testandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.setProperty("java.io.tmpdir","/data/data/com.sample.sqlite/cache"); //Samsung
        TestAndroid test = new TestAndroid(this);
        try {
            //Toast.makeText(this,"create",Toast.LENGTH_LONG);
            long start = System.currentTimeMillis();
            test.create();
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            TextView text = findViewById(R.id.text_view);
            String hh = System.getProperty("java.io.tmpdir");
            //text.setText(hh);
            text.setText(String.valueOf(timeElapsed));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String DATABASE_NAME = "Cryptobuddy.db";
    private static final String DATABASE_TABLE = "favorites_list";
    private static final int DATABASE_VERSION = 1;
    private static final String FAVORITE_COINS_COL_0 = "ID";
    private static final String FAVORITE_COINS_COL_1 = "FAVORITES";
    private static final String DEFAULT_FAVORITE_COINS = "BTC,ETH,XRP,LTC,BCH";

    public void onCreate (SQLiteDatabase db) {
        // Drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        // Create the new table with 2 columns. One for ID and one for the coin list
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + FAVORITE_COINS_COL_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FAVORITE_COINS_COL_1 + " TEXT)");
        // Convert the defaultCoinsList string into a list of strings. Use comma as the delimeter
        List<String> defaultCoinsList = new ArrayList<>(Arrays.asList(DEFAULT_FAVORITE_COINS.split(",")));
        // Instantiate a serializer so we can easily load/store the list in the DB
        /*Gson gson = new Gson();
        // Serialize the list of strings into a JSON payload that we can store in the DB

        // Put the serialized paylout into a ContentValues object to prepare it for storage*/
        String favoritesListString = "";//gson.toJson(defaultCoinsList);
        ContentValues defaultFavoriteCoins = new ContentValues();
        defaultFavoriteCoins.put(FAVORITE_COINS_COL_1, favoritesListString);
        // Insert the list into the DB
        db.insert(DATABASE_TABLE, null, defaultFavoriteCoins);
    }

//    public void getFavorites() {
//        // Get a connection to the DB
//        Cursor cursor;
//        try (SQLiteDatabase db = SQLiteOpenHelper.getReadableDatabase()) {
//            // Pull the favorites out of the DB
//            cursor = db.rawQuery("select FAVORITES from " + DATABASE_TABLE, null);
//        }
//        // Move the cursor to the first element returned by the query
//        cursor.moveToPosition(0);
//        // Get the list represented as a string out of the cursor
//        String favoritesListString = cursor.getString(0);
//        cursor.close();
//        // Instantiate a new serializer so that we can convert the string from the DB into a real list object
//        Gson gson = new Gson();
//        // Tell Gson what type we want to convert the serialized string to
//        Type type = new TypeToken<ArrayList<String>>() {}.getType();
//        // Load the string of the user's favorite coins into a list of strings!
//        ArrayList<String> favoritesList = gson.fromJson(favoritesListString, type);
//        // Instantiate a new hashmap and put all of the items in the list above into the hashmap
//        HashMap<String, String> favoritesMap = new HashMap<>();
//        for (int i = 0; i < favoritesList.size(); i++) {
//            favoritesMap.put(favoritesList.get(i), favoritesList.get(i));
//        }
//        // Return a wrapper object that holds both the list and the hashmap
//        return new CoinFavoritesStructures(favoritesList, favoritesMap);
//    }
//
//    // This will allow us to save the user's coin favorites any time by passing in a wrapper object
//    // that contains the list of the user's favorite coins as well as the hashtable representation
//    public void saveCoinFavorites() {
//        SQLiteOpenHelper helper = new DBHelper(this);
//        SQLiteDatabase db = null;
//        Cursor cursor = null;
//        try {
//            db = helper.getWritableDatabase();
//            cursor = db.query(DBHelper."table1",
//                    1,
//                    null, null, null, null,
//                    DBHelper.2 + " DESC");
//            cursor.move(3 + 1);
//            db.delete(DBHelper."table1", DBHelper.2 + '=' + cursor.getString(0), null);
//        } finally {
//            close(cursor, db);
//        }
//        // Get a writeable connection to the db
//        try (SQLiteDatabase db = SQLiteOpenHelper.getWritableDatabase()) {
//            // Instantiate a new serializer so that we can convert our list of coins back into a string
//            // that is storable in the DB
//        Gson gson = new Gson();
//        // Convert the list of the user's favorite coins into a string*/
//            String favoritesListString = "";// = gson.toJson(favs.favoriteList);
//            // Put the serialized paylout into a ContentValues object to prepare it for storage*/
//            ContentValues newCoinFavorites = new ContentValues();
//            newCoinFavorites.put(FAVORITE_COINS_COL_1, favoritesListString);
//            // Update the row in the DB with the new list of favorites!
//            db.update(DATABASE_TABLE, newCoinFavorites, null, null);
//        }
//    }
//https://www.programcreek.com/java-api-examples/?class=android.database.sqlite.SQLiteOpenHelper&method=getWritableDatabase
/*
    public void deleteHistoryItem(int number) {
        try (SQLiteOpenHelper helper = new SQLiteOpenHelper(this) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        }) {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            try {
                db = helper.getWritableDatabase();
                cursor = db.query(DBHelper.TABLE_NAME,
                        ID_COL_PROJECTION,
                        null, null, null, null,
                        DBHelper.TIMESTAMP_COL + " DESC");
                cursor.move(number + 1);
                db.delete(DBHelper.TABLE_NAME, DBHelper.ID_COL + '=' + cursor.getString(0), null);
            } finally {
                close(cursor, db);
            }
        }
    }

    public static void createTable(SQLiteOpenHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL(new StringBuilder("CREATE TABLE '").append(TABLE_NAME)
                .append("' ('").append(BaseColumns._ID)
                .append("' INTEGER PRIMARY KEY AUTOINCREMENT, '")
                .append(CLIENT_ID).append("' INTEGER, '").append(SORTED_BY)
                .append("' REAL, '").append(CREATED_AT).append("' INTEGER, '")
                .append(CONTENT).append("' TEXT, '").append(SENDER_ID)
                .append("' INTEGER NOT NULL, '").append(CHANNEL_ID)
                .append("' INTEGER NOT NULL, '").append(COMMAND_ID)
                .append("' INTEGER);").toString());

        db.execSQL(new StringBuilder("CREATE INDEX IDX_MESSAGE_COMMAND_ID ON ")
                .append(TABLE_NAME).append(" (").append(COMMAND_ID)
                .append(");").toString());
    }


    private void deletePrevious(String text) {
        SQLiteOpenHelper helper = new DBHelper(activity);
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.delete(DBHelper.TABLE_NAME, DBHelper.TEXT_COL + "=?", new String[] { text });
        } finally {
            close(null, db);
        }
    }
    */
/**
 * Tests to ensure that inserts into your database results in automatically
 * incrementing row IDs.
 * @throws Exception in case the constructor hasn't been implemented yet
 *//*

    @Test
    public void autoincrement_test() throws Exception{

        */
    /* First, let's ensure we have some values in our table initially *//*

        insert_single_record_test();

        */
    /* Use reflection to try to run the correct constructor whenever implemented *//*

        SQLiteOpenHelper dbHelper =
                (SQLiteOpenHelper) mDbHelperClass.getConstructor(Context.class).newInstance(mContext);

        */
    /* Use WaitlistDbHelper to get access to a writable database *//*

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues testValues = new ContentValues();
        testValues.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "test name");
        testValues.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 99);

        */
    /* Insert ContentValues into database and get first row ID back *//*

        long firstRowId = database.insert(
                WaitlistContract.WaitlistEntry.TABLE_NAME,
                null,
                testValues);

        */
    /* Insert ContentValues into database and get another row ID back *//*

        long secondRowId = database.insert(
                WaitlistContract.WaitlistEntry.TABLE_NAME,
                null,
                testValues);

        assertEquals("ID Autoincrement test failed!",
                firstRowId + 1, secondRowId);


    }

    */
/**
 * <p>Builds a text representation of the scanning history. Each scan is encoded on one
 * line, terminated by a line break (\r\n). The values in each line are comma-separated,
 * and double-quoted. Double-quotes within values are escaped with a sequence of two
 * double-quotes. The fields output are:</p>
 *
 * <ol>
 *  <li>Raw text</li>
 *  <li>Display text</li>
 *  <li>Format (e.g. QR_CODE)</li>
 *  <li>Unix timestamp (milliseconds since the epoch)</li>
 *  <li>Formatted version of timestamp</li>
 *  <li>Supplemental info (e.g. price info for a product barcode)</li>
 * </ol>
 *//*

    CharSequence buildHistory() {
        SQLiteOpenHelper helper = new DBHelper(activity);
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = helper.getWritableDatabase();
            cursor = db.query(DBHelper.TABLE_NAME,
                    COLUMNS,
                    null, null, null, null,
                    DBHelper.TIMESTAMP_COL + " DESC");

            DateFormat format = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
            StringBuilder historyText = new StringBuilder(1000);
            while (cursor.moveToNext()) {

                historyText.append('"').append(massageHistoryField(cursor.getString(0))).append("\",");
                historyText.append('"').append(massageHistoryField(cursor.getString(1))).append("\",");
                historyText.append('"').append(massageHistoryField(cursor.getString(2))).append("\",");
                historyText.append('"').append(massageHistoryField(cursor.getString(3))).append("\",");

                // Add timestamp again, formatted
                long timestamp = cursor.getLong(3);
                historyText.append('"').append(massageHistoryField(
                        format.format(new Date(timestamp)))).append("\",");

                // Above we're preserving the old ordering of columns which had formatted data in position 5

                historyText.append('"').append(massageHistoryField(cursor.getString(4))).append("\"\r\n");
            }
            return historyText;
        } finally {
            close(cursor, db);
        }
    }
    void clearHistory() {
        SQLiteOpenHelper helper = new DBHelper(activity);
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.delete(DBHelper.TABLE_NAME, null, null);
        } finally {
            close(null, db);
        }
    }
    */
/**
 * <p>Builds a text representation of the scanning history. Each scan is encoded on one
 * line, terminated by a line break (\r\n). The values in each line are comma-separated,
 * and double-quoted. Double-quotes within values are escaped with a sequence of two
 * double-quotes. The fields output are:</p>
 *
 * <ol>
 *  <li>Raw text</li>
 *  <li>Display text</li>
 *  <li>Format (e.g. QR_CODE)</li>
 *  <li>Unix timestamp (milliseconds since the epoch)</li>
 *  <li>Formatted version of timestamp</li>
 *  <li>Supplemental info (e.g. price info for a product barcode)</li>
 * </ol>
 *//*

    CharSequence buildHistory() {
        SQLiteOpenHelper helper = new DBHelper(activity);
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = helper.getWritableDatabase();
            cursor = db.query(DBHelper.TABLE_NAME,
                    COLUMNS,
                    null, null, null, null,
                    DBHelper.TIMESTAMP_COL + " DESC");

            DateFormat format = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
            StringBuilder historyText = new StringBuilder(1000);
            while (cursor.moveToNext()) {

                historyText.append('"').append(massageHistoryField(cursor.getString(0))).append("\",");
                historyText.append('"').append(massageHistoryField(cursor.getString(1))).append("\",");
                historyText.append('"').append(massageHistoryField(cursor.getString(2))).append("\",");
                historyText.append('"').append(massageHistoryField(cursor.getString(3))).append("\",");

                // Add timestamp again, formatted
                long timestamp = cursor.getLong(3);
                historyText.append('"').append(massageHistoryField(
                        format.format(new Date(timestamp)))).append("\",");

                // Above we're preserving the old ordering of columns which had formatted data in position 5

                historyText.append('"').append(massageHistoryField(cursor.getString(4))).append("\"\r\n");
            }
            return historyText;
        } finally {
            close(cursor, db);
        }
    }
*/

}
