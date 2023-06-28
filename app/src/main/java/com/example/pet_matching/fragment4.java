package com.example.pet_matching;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment4 extends Fragment implements View.OnClickListener{


    Button button;
    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference, likeref;
    Boolean likechecker = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4,container,false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = getActivity().findViewById(R.id.createpost_f4);
        reference = database.getReference("All posts");
        likeref = database.getReference("post likes");
        recyclerView = getActivity().findViewById(R.id.rv_posts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createpost_f4:
                Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivity(intent);
                break;

    }
}

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Postmember> options =
                new FirebaseRecyclerOptions.Builder<Postmember>()
                        .setQuery(reference, Postmember.class)
                        .build();

        FirebaseRecyclerAdapter<Postmember, PostViewholder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Postmember, PostViewholder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull PostViewholder holder, int position, @NonNull Postmember model) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String currentid = user.getUid();

                        final String postKey = getRef(position).getKey();
                        holder.SetPost(getActivity(), model.getName(), model.getUrl(), model.getPostUri(),
                                model.getTime(), model.getUid(), model.getType(), model.getDesc());



//                        String que = getItem(position).getQuestion();
//                        String name = getItem(position).getName();
//                        String url = getItem(position).getUrl();
//                        String time = getItem(position).getTime();
//                        String userid = getItem(position).getUserid();



                        holder.likeschecker(postKey);
                        holder.likebtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                likechecker = true;

                                likeref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (likechecker.equals(true)){
                                            if (snapshot.child(postKey).hasChild(currentid)){
                                                likeref.child(postKey).child(currentid).removeValue();
                                                // delete(time);
                                                likechecker = false;
                                            }else {
                                                likeref.child(postKey).child(currentid).setValue(true);

                                                likechecker = false;

                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public PostViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.post_layout, parent, false);

                        return  new PostViewholder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();

        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }
}
