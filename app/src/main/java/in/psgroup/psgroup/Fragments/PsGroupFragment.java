package in.psgroup.psgroup.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import in.psgroup.psgroup.LoyaltyActivity;
import in.psgroup.psgroup.OffersActivity;
import in.psgroup.psgroup.ProjectsActivity;
import in.psgroup.psgroup.R;

public class PsGroupFragment extends Fragment {

    private View view;
    private RelativeLayout rr_programme,rr_offers,rr_projects;

    public static PsGroupFragment newInstance() {

        Bundle args = new Bundle();

        PsGroupFragment fragment = new PsGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_psgroup,container,false);
        initView();
        return view;
    }

    private void initView() {

        rr_programme = view.findViewById(R.id.program_card);
        rr_offers = view.findViewById(R.id.offers_card);
        rr_projects = view.findViewById(R.id.projects_card);

        rr_programme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), LoyaltyActivity.class));
            }
        });

        rr_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), OffersActivity.class));
            }
        });

        rr_projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), ProjectsActivity.class));
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public PsGroupFragment(){

    }
}
