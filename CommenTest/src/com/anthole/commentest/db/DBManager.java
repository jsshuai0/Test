package com.anthole.commentest.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Camera.Area;
import android.os.Environment;
import android.util.Log;

import com.anthole.commentest.R;

public class DBManager {
    
   private final int BUFFER_SIZE = 400000;
   public static final String DB_NAME = "city.db"; //保存的数据库文件名
   public static String PACKAGE_NAME = "";
   public static String DB_PATH = "/data"
           + Environment.getDataDirectory().getAbsolutePath() + "/"
           + PACKAGE_NAME;  //在手机里存放数据库的位置(/data/data/com.cssystem.activity/cssystem.db)
    
    
   private SQLiteDatabase database;
   private Context context;
   
   public DBManager(Context context) {
       this.context = context;
       PACKAGE_NAME = context.getPackageName();
       DB_PATH = "/data"
               + Environment.getDataDirectory().getAbsolutePath() + "/"
               + PACKAGE_NAME+"/databases";
   }

   public SQLiteDatabase getDatabase() {
       return database;
   }

   public void setDatabase(SQLiteDatabase database) {
       this.database = database;
   }

   public void openDatabase() {
       System.out.println(DB_PATH + "/" + DB_NAME);
       File file = new File(DB_PATH);
       if(!file.exists()){
    	   file.mkdir();
       }
       this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
   }
   
   private SQLiteDatabase openDatabase(String dbfile) {

       try {
           if (!(new File(dbfile).exists())) {
               //判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
               InputStream is = this.context.getResources().openRawResource(
                       R.raw.messagedatabase); //欲导入的数据库
               FileOutputStream fos = new FileOutputStream(dbfile);
               byte[] buffer = new byte[BUFFER_SIZE];
               int count = 0;
               while ((count = is.read(buffer)) > 0) {
                   fos.write(buffer, 0, count);
               }
               fos.close();
               is.close();
           }

           SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
           return db;

       } catch (FileNotFoundException e) {
           Log.e("Database", "File not found");
           e.printStackTrace();
       } catch (IOException e) {
           Log.e("Database", "IO exception");
           e.printStackTrace();
       }
       return null;
   }
    
   public void closeDatabase() {
       this.database.close();

   }
   
   public List<HAT_PROVINCE> quaryProvince(){
	   List<HAT_PROVINCE> list = new ArrayList<HAT_PROVINCE>();
	   Cursor cursor = this.database.query("HAT_PROVINCE", new String[]{"_id","provinceid","province"}, null, null, null, null, null);
	   while (cursor.moveToNext()) {  
		   HAT_PROVINCE province = new HAT_PROVINCE();
		   int id = cursor.getInt(cursor.getColumnIndex("_id")); 
		   int provinceid = cursor.getInt(cursor.getColumnIndex("provinceid")); 
		   String provincename = cursor.getString(cursor.getColumnIndex("province")); 
		   province.set_id(id);
		   province.setProvinceid(provinceid);
		   province.setProvince(provincename);
		   province.setCitys(quaryCity(provinceid));
		   list.add(province);
       } 
	   cursor.close();
	   return list;
   }
   
   public List<HAT_CITY> quaryCity(int province_id){
	   List<HAT_CITY> list = new ArrayList<HAT_CITY>();
	   Cursor cursor = this.database.query("HAT_CITY", new String[]{"_id","cityid","city","father"}, "father = "+province_id, null, null, null, null);
	   while (cursor.moveToNext()) {  
		   HAT_CITY city = new HAT_CITY();
		   int id = cursor.getInt(cursor.getColumnIndex("_id")); 
		   int cityid = cursor.getInt(cursor.getColumnIndex("cityid")); 
		   String cityname = cursor.getString(cursor.getColumnIndex("city")); 
		   int father = cursor.getInt(cursor.getColumnIndex("father")); 
		   city.set_id(id);
		   city.setCityid(cityid);
		   city.setCity(cityname);
		   city.setFather(father);
		   city.setAreas(quaryArea(cityid));
		   list.add(city);
       }
	   cursor.close();
	   return list;
   }
   
   public List<HAT_AREA> quaryArea(int city_id){
	   List<HAT_AREA> list = new ArrayList<HAT_AREA>();
	   Cursor cursor = this.database.query("HAT_AREA", new String[]{"_id","areaid","area","father"}, "father = "+city_id, null, null, null, null);
	   while (cursor.moveToNext()) {  
		   HAT_AREA area = new HAT_AREA();
		   int id = cursor.getInt(cursor.getColumnIndex("_id")); 
		   int areaid = cursor.getInt(cursor.getColumnIndex("areaid")); 
		   String areaname = cursor.getString(cursor.getColumnIndex("area")); 
		   int father = cursor.getInt(cursor.getColumnIndex("father")); 
		   area.set_id(id);
		   area.setAreaid(areaid);
		   area.setArea(areaname);
		   area.setFather(father);
		   list.add(area);
       }
	   cursor.close();
	   return list;
   }
}
