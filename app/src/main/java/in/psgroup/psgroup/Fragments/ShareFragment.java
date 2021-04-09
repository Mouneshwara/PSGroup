package in.psgroup.psgroup.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import in.psgroup.psgroup.R;

public class ShareFragment extends BottomSheetDialogFragment{

    View view;

    ImageView ic_whatsApp,ic_gmail,ic_chat,ic_facebook;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.share_fragment,container,false);

        initView();
        return view;
    }

    private void initView() {

        ic_whatsApp = view.findViewById(R.id.whatsApp);
        ic_gmail = view.findViewById(R.id.gmail);
        ic_chat = view.findViewById(R.id.chat);
        ic_facebook = view.findViewById(R.id.facebook);

        ic_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });

        ic_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
