package com.example.contactlist;

import java.util.ArrayList;

import android.R.array;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactListLoader extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayList<String> arList = getContactList();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arList);
		setListAdapter(adapter);	
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		ArrayList<String> arList = getContactList();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arList);
		setListAdapter(adapter);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		Intent intent = new Intent(ContactListLoader.this,ContactService.class);
		startActivity(intent);
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(ContactListLoader.this, ContactDetail.class);
		intent.putExtra("_ID position", position);
		startActivity(intent);
		
	}
	
	public ArrayList<String> getContactList()
	{
		String name = null;
		ContentResolver crObject = getContentResolver();
		Cursor cursorMain = crObject.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		
		ArrayList<String> arList = new ArrayList<String>();
		if(cursorMain.getCount() > 0)
		{
			while(cursorMain.moveToNext())
			{
				name = cursorMain.getString(cursorMain.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				
				arList.add(name);
			}
		}
		
		return arList;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

}
