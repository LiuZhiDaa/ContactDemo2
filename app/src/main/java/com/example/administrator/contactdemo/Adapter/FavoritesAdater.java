package com.example.administrator.contactdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.contactdemo.R;
import com.example.administrator.contactdemo.bean.Contacts;
import com.example.administrator.contactdemo.bean.Favorites;

import java.util.ArrayList;

import butterknife.Bind;


public class FavoritesAdater extends RecyclerView.Adapter<FavoritesAdater.ViewHolder> {

    private ArrayList<Favorites> userList;
    private Context context;
    private OnViewOnclick onClick=null;


    public FavoritesAdater(ArrayList<Contacts> userList, Context context) {
        super();
        this.userList = userList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textName.setText(userList.get(position).getName());

        holder.itemView.setTag(position);

        if (!holder.itemView.hasOnClickListeners()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClick != null) {
                        onClick.onClickListener((int) holder.itemView.getTag());
                    }
                }

            });

        }
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        @Bind(R.id.imageView)
        ImageView imageView;
        @Bind(R.id.text_name)
        TextView textName;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textName = (TextView) itemView.findViewById(R.id.text_name);


        }

    }

    public void setOnCLickListener(OnViewOnclick listener){
        this.onClick=listener;
    }
    public interface OnViewOnclick{
        void onClickListener(int itemData);
    }
}
