package in.psgroup.psgroup.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import in.psgroup.psgroup.R;

public class FilterFragment extends Fragment{

    Button btn_apply,btn_close;
    View view;
    Button btn_kolkata,btn_all,btn_residence,btn_commercial,btn_underfl,btn_newLaunch,btn_chennai,btn_unknow,btn_unknows;
    ArrayList<String> location = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<String> price = new ArrayList<String>();
    ArrayList<String> status = new ArrayList<String>();
    ArrayList<String> bhk = new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filter,container,false);
        initView();
        return view;
    }

    private void initView() {
        btn_apply = view.findViewById(R.id.btn_apply);
        btn_close = view.findViewById(R.id.btn_close);
        btn_kolkata = (Button) view.findViewById(R.id.kolkata);
        btn_all = (Button) view.findViewById(R.id.all);
        btn_underfl = (Button) view.findViewById(R.id.underFL);
        btn_newLaunch = (Button) view.findViewById(R.id.launch);
        btn_chennai = (Button) view.findViewById(R.id.chennai);
        btn_unknow = (Button) view.findViewById(R.id.placeOne);
        btn_unknows = (Button) view.findViewById(R.id.placeTwo);


        btn_residence = (Button) view.findViewById(R.id.residence);
        btn_commercial = (Button) view.findViewById(R.id.commercial);
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        btn_kolkata.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if(v.isSelected()) {
                    btn_kolkata.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_kolkata.setTextColor(getResources().getColor(R.color.white));
                    location.add(btn_kolkata.getText().toString());

                }
                else{
                    btn_kolkata.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_kolkata.setTextColor(getResources().getColor(R.color.sbiDesc));
                }
            }
        });

        btn_chennai.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if(v.isSelected()) {
                    btn_chennai.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_chennai.setTextColor(getResources().getColor(R.color.white));
                    location.add(btn_kolkata.getText().toString());

                }
                else{
                    btn_chennai.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_chennai.setTextColor(getResources().getColor(R.color.sbiDesc));
                }
            }
        });

        btn_unknow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if(v.isSelected()) {
                    btn_unknow.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_unknow.setTextColor(getResources().getColor(R.color.white));
                    location.add(btn_kolkata.getText().toString());

                }
                else{
                    btn_unknow.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_unknow.setTextColor(getResources().getColor(R.color.sbiDesc));
                }
            }
        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if(v.isSelected()) {
                    btn_all.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_all.setTextColor(getResources().getColor(R.color.white));
                    btn_residence.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_commercial.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_residence.setTextColor(getResources().getColor(R.color.white));
                    btn_commercial.setTextColor(getResources().getColor(R.color.white));
                    type.add(btn_all.getText().toString());

                }
                else{
                    btn_commercial.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_residence.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_all.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_all.setTextColor(getResources().getColor(R.color.sbiDesc));
                    btn_commercial.setTextColor(getResources().getColor(R.color.sbiDesc));
                    btn_residence.setTextColor(getResources().getColor(R.color.sbiDesc));
                }
            }
        });

        btn_commercial.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if(v.isSelected()) {
                    btn_commercial.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_commercial.setTextColor(getResources().getColor(R.color.white));
                    type.add(btn_commercial.getText().toString());

                }
                else{
                    btn_commercial.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_commercial.setTextColor(getResources().getColor(R.color.sbiDesc));
                }
            }
        });

        btn_residence.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if(v.isSelected()) {
                    btn_residence.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_residence.setTextColor(getResources().getColor(R.color.white));
                    type.add(btn_residence.getText().toString());

                }
                else{
                    btn_residence.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_residence.setTextColor(getResources().getColor(R.color.sbiDesc));
                }
            }
        });

        btn_underfl.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if(v.isSelected()) {
                    btn_underfl.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_underfl.setTextColor(getResources().getColor(R.color.white));
                    price.add(btn_residence.getText().toString());

                }
                else{
                    btn_underfl.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_underfl.setTextColor(getResources().getColor(R.color.sbiDesc));
                }

            }
        });

        btn_newLaunch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if(v.isSelected()) {
                    btn_newLaunch.setBackground(getResources().getDrawable(R.drawable.button_orange));
                    btn_newLaunch.setTextColor(getResources().getColor(R.color.white));
                    status.add(btn_residence.getText().toString());

                }
                else{
                    btn_newLaunch.setBackground(getResources().getDrawable(R.drawable.filter_background));
                    btn_newLaunch.setTextColor(getResources().getColor(R.color.sbiDesc));
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
