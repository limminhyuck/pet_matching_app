package com.example.pet_matching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.squareup.picasso.Picasso;


public class UpdateProfile extends AppCompatActivity {

    EditText etname, etage, etLocation, etspecies, etsex, etbreed;
    Button button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    DocumentReference documentReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();
        documentReference = db.collection("user").document(currentuid);

        etname = findViewById(R.id.et_name_ucp);
        etage = findViewById(R.id.et_age_ucp);
        etLocation = findViewById(R.id.et_location_ucp);
        etspecies = findViewById(R.id.et_species_ucp);
        etsex = findViewById(R.id.et_sex_ucp);
        etbreed = findViewById(R.id.et_breed_ucp);
        button = findViewById(R.id.btn_ucp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.getResult().exists()) {
                            String nameResult = task.getResult().getString("name");
                            String ageResult = task.getResult().getString("age");
                            String locationResult = task.getResult().getString("location");
                            String speciesResult = task.getResult().getString("species");
                            String sexResult = task.getResult().getString("sex");
                            String breedResult = task.getResult().getString("breed");
                            String url = task.getResult().getString("url");

                            etname.setText(nameResult);
                            etage.setText(ageResult);
                            etLocation.setText(locationResult);
                            etspecies.setText(speciesResult);
                            etsex.setText(sexResult);
                            etbreed.setText(breedResult);
                        }else {
                            Toast.makeText(UpdateProfile.this, "No profile", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateProfile() {
        String name = etname.getText().toString();
        String age = etage.getText().toString();
        String species = etspecies.getText().toString();
        String location = etLocation.getText().toString();
        String sex = etsex.getText().toString();
        String breed = etbreed.getText().toString();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();

        final DocumentReference sDoc = db.collection("user").document(currentuid);

        db.runTransaction(new Transaction.Function<Void>() {
                    @Override
                    public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                        //DocumentSnapshot snapshot = transaction.get(sfDocRef);

                        transaction.update(sDoc, "name", name);
                        transaction.update(sDoc, "age", age);
                        transaction.update(sDoc, "species" , species);
                        transaction.update(sDoc, "location", location);
                        transaction.update(sDoc, "sex", sex);
                        transaction.update(sDoc, "breed", breed);


                        // Success
                        return null;
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateProfile.this, "updated", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateProfile.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}