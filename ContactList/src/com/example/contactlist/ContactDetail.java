package com.example.contactlist;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.widget.TextView;

public class ContactDetail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_detail);
		
		int position = getIntent().getIntExtra("_ID position", 0);
		
		String name = null;
		String mobn = null;
		String email = null;
		
		ContentResolver crObject = getContentResolver();
		Cursor cursorMain = crObject.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		cursorMain.moveToPosition(position);
		
		name = cursorMain.getString(cursorMain.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		mobn = cursorMain.getString(cursorMain.getColumnIndex(ContactsContract.Contacts.));
		email = cursorMain.getString(cursorMain.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
		
		TextView cNameTxtView = (TextView) findViewById(R.id.textView1);
		//cNameTxtView.setText(strContactName);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_detail, menu);
		return true;
	}

}
