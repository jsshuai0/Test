package com.anthole.commentest;



import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalDb;

import com.anthole.commentest.addPic.GalleryOrCameraCrop;
import com.anthole.commentest.db.DBManager;
import com.anthole.commentest.db.HAT_PROVINCE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;


public class MainActivity extends Activity{
	
	FinalDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }
    
    private void initData(){
    	copyDB();
    }
    
    private void copyDB(){
    	DBManager dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        dbHelper.quaryProvince();
        dbHelper.closeDatabase();
//        db = FinalDb.create(this, DBManager.DB_NAME);
//        List<HAT_PROVINCE> list1 = db.findAll(HAT_PROVINCE.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.take_pic) {
        	
            return true;
        }else if(id == R.id.open_gallery){
        	GalleryOrCameraCrop.chooseImage(this);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    

    
    
}
