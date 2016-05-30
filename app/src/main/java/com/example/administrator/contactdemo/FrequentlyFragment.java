package com.example.administrator.contactdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.contactdemo.Adapter.ContactsAdater;
import com.example.administrator.contactdemo.Adapter.FavoritesAdater;
import com.example.administrator.contactdemo.Utils.SystemDbUtils;
import com.example.administrator.contactdemo.bean.Contacts;
import com.example.administrator.contactdemo.bean.Favorites;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/5 0005.
 */
public class FrequentlyFragment extends Fragment {

    @Bind(R.id.recyclerView_contact)
    RecyclerView recyclerViewContact;


    private ArrayList<Contacts> userList;
    private SystemDbUtils Util2;
    private Context context;
    private FavoritesAdater favoritesAdater;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frequently, container, false);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());

        recyclerViewContact.setLayoutManager(manager);

        recyclerViewContact.setHasFixedSize(true);

        Util2 = new SystemDbUtils();

        userList = Util2.getContact(getActivity());

        favoritesAdater = new FavoritesAdater(userList, context);

        recyclerViewContact.setAdapter(favoritesAdater);

        recyclerViewContact.setItemAnimator(new DefaultItemAnimator());


        favoritesAdater.setOnCLickListener(new FavoritesAdater.OnViewOnclick() {
            @Override
            public void onClickListener(int itemData) {
                Integer id = Integer.valueOf(userList.get(itemData).contact_id);
                String name = userList.get(itemData).contact_name;
                String phone = String.valueOf(userList.get(itemData).contact_phone);
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
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
        userList.addAll(Util2.getContact(getActivity()));
        favoritesAdater.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}