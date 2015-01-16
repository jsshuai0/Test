package com.anthole.commentest;



import java.util.ArrayList;
import java.util.List;

import com.anthole.commentest.addPic.GalleryOrCameraCrop;

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
	
	List<String> listGroup;
	
	List<List<String>> listChild;
	
	ExpandableListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        listview = (ExpandableListView) findViewById(R.id.ex_listview);
        listview.setAdapter(new MyExpandableAdapter());
    }
    
    private void initData(){
    	listGroup = new ArrayList<String>();
    	listChild = new ArrayList<List<String>>();
    	for(int i = 0; i<10; i++){
    		listGroup.add("Group " +i);
    		List<String> groupChild = new ArrayList<String>();
    		for(int j = 0; j<10;j++){
    			groupChild.add("Child "+j);
    		}
    		listChild.add(groupChild);
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressLint("NewApi") @Override
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
    
    
    public class MyExpandableAdapter extends BaseExpandableListAdapter{

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return listGroup.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return listChild.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView == null){
				convertView = getLayoutInflater().inflate(R.layout.group, null);
			}
			TextView tvGroup = (TextView) convertView.findViewById(R.id.tv_group);
			tvGroup.setText(listGroup.get(groupPosition));
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView == null){
				convertView = getLayoutInflater().inflate(R.layout.child, null);
			}
			TextView tvGroup = (TextView) convertView.findViewById(R.id.tv_child);
			tvGroup.setText(listChild.get(groupPosition).get(childPosition));
			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return false;
		}
    	
    }

    
    
}
