package com.example.administrator.contactdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ModifyActivity extends AppCompatActivity {

    @Bind(R.id.modify_left_btn)
    ImageButton modifyLeftBtn;

    @Bind(R.id.modify_right_btn)
    ImageButton modifyRightBtn;


    @Bind(R.id.ed_modify_phone)
    EditText edModifyPhone;

    @Bind(R.id.ed_modify_email)
    EditText edModifyEmail;

    @Bind(R.id.ed_modify_name)
    EditText edModifyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        final Integer id = intent.getIntExtra("id", -1);
        final String name = intent.getStringExtra("name");
        final String phone = intent.getStringExtra("phone");

        edModifyEmail.setText(name);
        edModifyPhone.setText(phone);

        modifyLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyActivity.this.finish();
            }
        });

        modifyRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newname = String.valueOf(edModifyEmail.getText());
                String newphone = String.valueOf(edModifyPhone.getText());

                ContentValues values = new ContentValues();
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, newphone);
                values.put(ContactsContract.CommonDataKinds.Phone.MIMETYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);

                String where = ContactsContract.Data.RAW_CONTACT_ID + "=? AND " + ContactsContract.Data.MIMETYPE + "=?";
                String[] selectionArgs = new String[]{String.valueOf(id), ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE};

                getContentResolver().update(ContactsContract.Data.CONTENT_URI, values, where, selectionArgs);

                // 姓名编辑
                ContentValues values2 = new ContentValues();
                values2.put(ContactsContract.CommonDataKinds.Phone.DATA, newname);
                values2.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE); //内容类型
                String[] se = new String[]{String.valueOf(id), ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE};
                String wh = ContactsContract.Data.RAW_CONTACT_ID + "=? AND " + ContactsContract.Data.MIMETYPE + "=?";
                getContentResolver().update(ContactsContract.Data.CONTENT_URI, values2, wh, se);

                finish();
            }
        });
    }
}
