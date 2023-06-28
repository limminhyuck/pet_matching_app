package com.example.pet_matching;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ProfileViewholder extends RecyclerView.ViewHolder {

    TextView sendmessagebtn;
    ImageView imageView;
    CardView cardView;

    public ProfileViewholder(@NonNull View itemView) {
        super(itemView);
    }

    public void setProfileInchat(Application fragmentActivity, String name, String uid,
                                 String location, String url){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();

        ImageView imageView = itemView.findViewById(R.id.iv_ch_item);
        TextView nametv = itemView.findViewById(R.id.name_ch_item_tv);
        TextView loctv = itemView.findViewById(R.id.ch_locitem_tv);
        sendmessagebtn = itemView.findViewById(R.id.sent_messagech_item_btn);

        if (userid.equals(uid)){
            Picasso.get().load(url).into(imageView);
            nametv.setText(name);
            loctv.setText(location);
            sendmessagebtn.setVisibility(View.INVISIBLE);
        }else{
            Picasso.get().load(url).into(imageView);
            nametv.setText(name);
            loctv.setText(location);
        }

    }
}
