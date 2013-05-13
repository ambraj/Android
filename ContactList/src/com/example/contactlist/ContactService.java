package com.example.contactlist;

import java.util.ArrayList;

import android.R.string;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactService extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_service);
		
		
		
	}
	
	public boolean createContact(View view) {
		
		EditText fNameEditText = (EditText)findViewById(R.id.editTextfName);
		EditText lNameEditText = (EditText)findViewById(R.id.editTextlName);
		EditText mobileNoEditText = (EditText)findViewById(R.id.editTextMobileNo);
		EditText emailIdEditText = (EditText)findViewById(R.id.editTextEmailId);
		
		String strFname = fNameEditText.getText().toString();
		String strLname = lNameEditText.getText().toString();
		String strMobileNo = mobileNoEditText.getText().toString();
		String strEmailId = emailIdEditText.getText().toString();
		
		System.out.println(strFname);
		
		ArrayList<ContentProviderOperation> contact = new ArrayList<ContentProviderOperation>();
		
		try {
			contact.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
					.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
					.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
					.build()
					);
			
			contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, strFname)
					.build()
					);
			
			contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, strLname)
					.build()
					);
			
			contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, strMobileNo)
					.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
					.build()
					);
			
			contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Email.DATA, strEmailId)
					.withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME)
					.build()
					);
	
			getContentResolver().applyBatch(ContactsContract.AUTHORITY, contact);
			
			AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
			alertBox.setTitle("Success");
			alertBox.setMessage("Contact is saved !");
			alertBox.show();
			
			Intent intent = new Intent(ContactService.this, ContactListLoader.class);
			startActivity(intent);
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
			AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
			alertBox.setTitle("Failed");
			alertBox.setMessage("Error Occured !");
			alertBox.show();
			
		} catch (OperationApplicationException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
			AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
			alertBox.setTitle("Failed");
			alertBox.setMessage("Error Occured !");
			alertBox.show();
		}
		
		return true;		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

}
