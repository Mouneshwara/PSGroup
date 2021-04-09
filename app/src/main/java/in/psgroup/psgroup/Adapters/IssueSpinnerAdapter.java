package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import in.psgroup.psgroup.Models.IssueBean;

/**
 * Created by codebele-07 on 22/2/18.
 */

public class IssueSpinnerAdapter extends ArrayAdapter<IssueBean> {
    private Context context;
    private ArrayList<IssueBean> issueList;

    public IssueSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<IssueBean> issueList) {
        super(context, resource);
        this.context = context;
        this.issueList = issueList;
    }

    public int getCount() {
        return issueList.size();
    }

    public IssueBean getItem(int position) {
        return issueList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(issueList.get(position).getIssue_type());
        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(issueList.get(position).getIssue_type());
        return label;
    }
}
