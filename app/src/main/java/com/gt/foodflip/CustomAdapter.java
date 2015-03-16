package com.gt.foodflip;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    ListModel tempValues=null;
    int i = 0;

    /*************  CustomAdapter Constructor *****************/
    public CustomAdapter(Activity a, ArrayList d,Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {
        if(data.size()<=0) return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder {
        public TextView building;
        public TextView location;
        public TextView foodCategory;
        public TextView foodType;
        public TextView foodDescription;
    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.tabitem, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.building = (TextView) vi.findViewById(R.id.building);
            holder.location = (TextView) vi.findViewById(R.id.location);
            holder.foodCategory = (TextView) vi.findViewById(R.id.food_category);
            holder.foodType = (TextView) vi.findViewById(R.id.food_type);
            //holder.foodDescription = (TextView) vi.findViewById(R.id.food_description);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        }
        else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0)
            holder.building.setText("No Data");
        else {
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (ListModel) data.get(position);

            /************  Set Model values in Holder elements ***********/

            holder.building.setText(tempValues.getBuilding());
            holder.location.setText(tempValues.getLocation());
            holder.foodCategory.setText(tempValues.getCategory());
            holder.foodType.setText(tempValues.getType());
            //holder.foodDescription.setText(tempValues.getDescription());

            /******** Set Item Click Listner for LayoutInflater for each row *******/

            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            SearchScreenActivity sct = (SearchScreenActivity) activity;

            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            sct.onItemClick(mPosition);
        }
    }
}