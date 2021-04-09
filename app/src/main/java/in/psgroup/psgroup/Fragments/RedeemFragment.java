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

public class RedeemFragment extends Fragment {

    View view;
    Button proceed;
    EditText terms;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Returning the layout file after inflating
        //Change R.layout.redeem in you classes
        view = inflater.inflate(R.layout.fragment_redeem,container,false);
        initView();
        return view;
    }

    private void initView() {
        proceed = view.findViewById(R.id.next);
        terms = view.findViewById(R.id.termsConditions);

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
