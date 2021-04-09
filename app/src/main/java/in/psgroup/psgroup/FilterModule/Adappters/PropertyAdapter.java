package in.psgroup.psgroup.FilterModule.Adappters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.psgroup.psgroup.R;

/**
 * Created by Codebele on 4/15/2019.
 */
public class PropertyAdapter extends BaseAdapter {
    private Context context;
    private List<String> arrayList;
    private LayoutInflater inflater;

    public PropertyAdapter(Context context, List<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public String getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_filter_propertytype, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String currentItem = getItem(i);
        viewHolder.button.setText(currentItem);

        return view;
    }

    private class ViewHolder {
        Button button;
        TextView itemDescription;

        public ViewHolder(View view) {
            button = (Button) view.findViewById(R.id.propertyType);
        }
    }
}
