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


    public WislhListDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " TEXT ,"
                + COL_NAME + " TEXT,"
                + COL_OPRICE + " TEXT,"
                + COL_DPRICE + " TEXT,"
                + COL_IMG + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    public void insertData (String id , String name , String oprice , String dprice , String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID,id);
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_OPRICE,oprice);
        contentValues.put(COL_DPRICE,dprice);
        contentValues.put(COL_IMG,image);
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


}
