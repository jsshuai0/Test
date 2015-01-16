package com.anthole.commentest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*******************
 * 
 *  
 *  
 * 对Sqlite数据库进行操作的类
 *  
 *  
 *  
 * ****************/

public class SQLOperateImpl {

	
	public class PVLogHelper extends SQLiteOpenHelper{

		public PVLogHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
	 
}


