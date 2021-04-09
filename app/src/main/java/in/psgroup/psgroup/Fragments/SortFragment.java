package in.psgroup.psgroup.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import in.psgroup.psgroup.R;

public class SortFragment extends Fragment {
    Button btn_apply,btn_close;
    View view;
   private RadioButton rbtn_high,rbtn_low;
   private RadioGroup radioPriceGroup;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sort_fragment,container,false);
        initView();
        addListenerOnButton();
        return view;
    }

    private void addListenerOnButton() {
        int selectedId =radioPriceGroup.getCheckedRadioButtonId();
        rbtn_high =(RadioButton) view.findViewById(selectedId);
        Toast.makeText(getContext(),
                "", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        btn_apply = view.findViewById(R.id.btn_apply);
        btn_close = view.findViewById(R.id.btn_close);
        radioPriceGroup = view.findViewById(R.id.radioPrice);

        rbtn_high = view.findViewById(R.id.rbtn_high);
        rbtn_low = view.findViewById(R.id.rbtn_low);
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

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
