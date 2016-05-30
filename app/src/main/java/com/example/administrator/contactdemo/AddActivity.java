package com.example.administrator.contactdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {

    @Bind(R.id.add_left_btn)
    ImageButton addLeftBtn;

    @Bind(R.id.add_right_btn)
    ImageButton addRightBtn;

    @Bind(R.id.ed_add_name)
    EditText edAddName;

    @Bind(R.id.ed_add_phone)
    EditText edAddPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        addLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AddActivity.this, ContactFragment.class);
                finish();
            }
        });
        addRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nname = String.valueOf(edAddName.getText());
                String nphone = String.valueOf(edAddPhone.getText());


                ContentResolver resolver = getApplicationContext().getContentResolver();
                ContentValues values = new ContentValues();

                // 获取id
                Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
                long rawContactId = ContentUris.parseId(rawContactUri);

                if (nname != "") {
                    values.clear();
                    //Id 必带
                    values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                    //MIMETYPE 必带
                    values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                    values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, nname);
                    String a = String.valueOf(resolver.insert(ContactsContract.Data.CONTENT_URI, values));

                }else{
                    Toast.makeText(getApplicationContext(), "姓名不能为空", Toast.LENGTH_SHORT).show();
                }
                if (nphone!= ""){

                    values.clear();
                    values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                    values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                    values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, nphone);
                    values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                    getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                            values);
                }else{
                    Toast.makeText(getApplicationContext(),"号码不能为空",Toast.LENGTH_SHORT).show();
                }

                finish();

            }
        });
    }
}
