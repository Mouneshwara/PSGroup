package in.psgroup.psgroup.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import in.psgroup.psgroup.R;

public class ReferAndEarnFragment extends Fragment {

    View view;

    Button share;
    EditText terms;

    ShareFragment shareFragmentFragment;
    
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Returning the layout file after inflating
        //Change R.layout.refer_fragment in you classes
        view =inflater.inflate(R.layout.refer_fragment,container,false);
        
        initView();
        return view;
    }

    private void initView() {

        share = view.findViewById(R.id.share);
        terms = view.findViewById(R.id.referTermsConditions);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFragmentFragment = new ShareFragment();
                shareFragmentFragment.show(getFragmentManager(),getTag());
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
