package com.example.selectmultipleimages;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    Context context;
    List<Uri> listImages;
    LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, List<Uri> listImages){
        this.context = context;
        this.listImages = listImages;
    }

    @Override
    public int getCount() {
        return listImages.size();
    }

    @Override
    public Object getItem(int position) {
        return listImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_gv_images, null);
        }

        ImageView ivImage = convertView.findViewById(R.id.ivimage);
        ImageButton ibtnEliminar = convertView.findViewById(R.id.ibtnEliminar);

        ivImage.setImageURI(listImages.get(position));

        ibtnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listImages.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
