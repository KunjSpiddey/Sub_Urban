package com.example.suburban;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class WislhListDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WishlistDatabase";
    private static final int DATABASE_VERSION = 1;

    // Define table name and columns
    public static final String TABLE_NAME = "Wishlist";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_OPRICE = "oprice";
    public static final String COL_DPRICE = "dprice";
    public static final String COL_IMG = "image";
    public static final String COL_CHECK = "checked";


    public WislhListDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " TEXT ,"
                + COL_NAME + " TEXT,"
                + COL_OPRICE + " TEXT,"
                + COL_DPRICE + " TEXT,"
                + COL_CHECK + " INTEGER DEFAULT 0,"
                + COL_IMG + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    public void insertData (WishListItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID,item.getId());
        contentValues.put(COL_NAME,item.getName());
        contentValues.put(COL_OPRICE,item.getOprice());
        contentValues.put(COL_DPRICE,item.getDprice());
        contentValues.put(COL_IMG,item.getImg());
        db.insert("Wishlist" , null , contentValues);
        db.close();
    }



    public List<WishListItem> getAllItems(){
        List<WishListItem> fav_items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String [] projection = {
               COL_ID,
               COL_NAME,
               COL_OPRICE,
               COL_DPRICE,
               COL_IMG,
                COL_CHECK

        };

        Cursor cursor = db.query(
                "Wishlist",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()){
            do {
                String itemId = cursor.getString(cursor.getColumnIndexOrThrow(COL_ID));
                String itemName = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                String itemImg = cursor.getString(cursor.getColumnIndexOrThrow(COL_IMG));
                String itemOprice= cursor.getString(cursor.getColumnIndexOrThrow(COL_OPRICE));
                String itemDprice = cursor.getString(cursor.getColumnIndexOrThrow(COL_DPRICE));
                String itemChecked = cursor.getString(cursor.getColumnIndexOrThrow(COL_CHECK));
                WishListItem wishListItem = new WishListItem(itemId , itemName , itemImg , itemOprice , itemDprice);
                fav_items.add(wishListItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return fav_items;
    }

    public  void deleteData (String id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = '" + id + "'";
        db.execSQL(sql);
    }


    public void changeStatus1(String id){
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_NAME + " SET " + COL_CHECK + " = '1' WHERE " + COL_ID + " = '" + id + "'";
        db.execSQL(updateQuery);
    }

    public void changeStatus0(String id){
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_NAME + " SET " + COL_CHECK + " = '0' WHERE " + COL_ID + " = '" + id + "'";
        db.execSQL(updateQuery);
    }

    public void status(String id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_CHECK + " = '1' ";
        db.execSQL(sql);
    }

    public String getCheckStatus(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_CHECK + " FROM " + TABLE_NAME + " WHERE " + COL_ID + " = '" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        String checkStatus = "0";
        if (cursor.moveToFirst()) {
            checkStatus = cursor.getString(cursor.getColumnIndexOrThrow(COL_CHECK));
        }
        cursor.close();
        return checkStatus;
    }





}
