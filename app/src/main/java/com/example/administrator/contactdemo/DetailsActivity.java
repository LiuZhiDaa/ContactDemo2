package com.example.administrator.contactdemo;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @Bind(R.id.details_image)
    ImageButton detailsImage;

    @Bind(R.id.details_image2)
    ImageButton detailsImage2;

    @Bind(R.id.details_image3)
    ImageButton detailsImage3;

    @Bind(R.id.detail_imgbtn)
    ImageButton detailImgbtn;

    @Bind(R.id.textView)
    TextView textView;

    @Bind(R.id.textView2)
    TextView textView2;

    @Bind(R.id.imageView2)
    ImageButton imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final Integer id = intent.getIntExtra("id",-1);
        final String name = intent.getStringExtra("name");
        final String phone = intent.getStringExtra("phone");

        textView.setText(name);
        textView2.setText(phone);

        //返回
        detailsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.this.finish();
            }
        });

        //编辑
        detailsImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, ModifyActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });

        //删除
        detailsImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentResolver().delete(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, id), null, null);
                finish();
            }
        });

        //拨号
        detailImgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //短信
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("smsto:" + phone));
                startActivity(intent);
            }
        });
    }
}
