package com.example.suburban;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AddToCartDatabase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "AddToCartDatabase";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "AddToCart";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_BRAND = "brand";
    public static final String COL_OPRICE = "oprice";
    public static final String COL_DPRICE = "dprice";
    public static final String COL_IMG = "image";
    public static final String COL_SIZE = "size";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " TEXT ,"
                + COL_NAME + " TEXT,"
                + COL_OPRICE + " TEXT,"
                + COL_DPRICE + " TEXT,"
                + COL_SIZE + " TEXT,"
                + COL_BRAND + " TEXT,"
                + COL_IMG + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }




    public AddToCartDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

   public void insertData(addtoproductsITEM item){
        SQLiteDatabase db = this.getWritableDatabase();
       ContentValues cv = new ContentValues();
       cv.put(COL_ID , item.getId());
       cv.put(COL_NAME , item.getName());
       cv.put(COL_OPRICE , item.getOprice());
       cv.put(COL_DPRICE , item.getDprice());
       cv.put(COL_SIZE , item.getSize());
       cv.put(COL_BRAND , item.getBrand());
       cv.put(COL_IMG , item.getImage_uri());
       db.insert("AddToCart", null , cv);
       db.close();

   }

   public List<addtoproductsITEM> getallItems(){
        List<addtoproductsITEM> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String [] projection = {
                COL_ID,
                COL_BRAND,
                COL_SIZE,
                COL_DPRICE,
                COL_OPRICE,
                COL_NAME,
                COL_IMG
        };

       Cursor cursor = db.query(
               "AddToCart",
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
               String itemBrand = cursor.getString(cursor.getColumnIndexOrThrow(COL_BRAND));
               String itemSize = cursor.getString(cursor.getColumnIndexOrThrow(COL_SIZE));
               addtoproductsITEM addtoproductsITEM = new addtoproductsITEM(itemId , itemName , itemBrand , itemDprice , itemOprice , itemImg , itemSize);
               items.add(addtoproductsITEM);
           }while(cursor.moveToNext());
       }
       cursor.close();
       db.close();
       return items;
   }

    public  void deleteData (String id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = '" + id + "'";
        db.execSQL(sql);
    }



}

