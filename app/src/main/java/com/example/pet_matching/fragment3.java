package com.example.pet_matching;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fragment3 extends Fragment {

    FirebaseDatabase database;
    DatabaseReference databaseReference,databaseReference1,profileRef;
    RecyclerView recyclerView, recyclerView_profile;
    TextView requesttv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3,container,false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Requests").child(currentUserId);
        profileRef = database.getReference("All Users");


        recyclerView_profile = getActivity().findViewById(R.id.recylerview_profile);

        recyclerView_profile.setHasFixedSize(true);

        recyclerView_profile.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView = getActivity().findViewById(R.id.recylerview_requestf3);
        requesttv = getActivity().findViewById(R.id.requeststv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
    }
}
