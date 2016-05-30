package com.example.administrator.contactdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.contactdemo.Adapter.ContactsAdater;
import com.example.administrator.contactdemo.Utils.SystemDbUtils;
import com.example.administrator.contactdemo.bean.Contacts;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/5 0005.
 */
public class ContactFragment extends Fragment {

    @Bind(R.id.recyclerView_contact)
    RecyclerView recyclerViewContact;


    private ArrayList<Contacts> userList;
    private SystemDbUtils systemDbUtils;
    private Context context;
    private ContactsAdater contactsAdate;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerViewContact.setLayoutManager(manager);
        recyclerViewContact.setHasFixedSize(true);
        systemDbUtils = new SystemDbUtils();
        userList = systemDbUtils.getContact(getActivity());
        contactsAdate = new ContactsAdater(userList,context);
        recyclerViewContact.setAdapter(contactsAdate);
        recyclerViewContact.setItemAnimator(new DefaultItemAnimator());




        contactsAdate.setOnCLickListener(new ContactsAdater.OnViewOnclick() {
            @Override
            public void onClickListener(int itemData) {

                Integer id = Integer.valueOf(userList.get(itemData).contact_id);
                String name = userList.get(itemData).contact_name;
                String phone = String.valueOf(userList.get(itemData).contact_phone);
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        userList.clear();
        userList.addAll(systemDbUtils.getContact(getActivity()));
        contactsAdate.notifyDataSetChanged();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
