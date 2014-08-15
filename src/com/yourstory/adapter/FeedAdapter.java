package com.yourstory.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yourstory.R;
import com.yourstory.model.RowItem;


public class FeedAdapter extends ArrayAdapter<RowItem> {
	
	Context context;
	 
    public FeedAdapter(Context context, int resourceId, List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.main_list_view_row_content_view, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.titleTextView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.thumnailImageView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        
        
        holder.txtTitle.setText(rowItem.getTitle());
        Picasso.with(context).load(rowItem.getImageId()).resize(120, 60).centerCrop().into(holder.imageView);
         
        return convertView;
    }
	
}
