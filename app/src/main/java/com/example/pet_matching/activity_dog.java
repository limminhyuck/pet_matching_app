package com.example.pet_matching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.firebase.animal;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_dog extends AppCompatActivity {

    ArrayAdapter<CharSequence> adapter;
    Uri uri;
    ImageView imageView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    private FirebaseUser currentUser;

    private String uid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);


        Button complete = (Button) findViewById(R.id.button6);


        EditText name = findViewById(R.id.editTextTextPersonName6);
        EditText age = findViewById(R.id.editTextTextPersonName7);
        EditText species = findViewById(R.id.species);
        EditText location = findViewById(R.id.location);
        EditText sex = findViewById(R.id.sex);
        EditText breed = findViewById(R.id.breed);

        Spinner spinner = findViewById(R.id.spinner); //종
        Spinner spinner1 = findViewById(R.id.spinner2); //지역
        Spinner spinner2 = findViewById(R.id.spinner3); //성별
        Spinner spinner3 = findViewById(R.id.spinner4); //교배가능여부


        adapter = ArrayAdapter.createFromResource(this, R.array.species, R.layout.spinner);
        spinner.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.location, R.layout.spinner);
        spinner1.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.sex, R.layout.spinner);
        spinner2.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.breed, R.layout.spinner);
        spinner3.setAdapter(adapter);






        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addanimal(name.getText().toString(), age.getText().toString(), species.getText().toString(), location.getText().toString(), sex.getText().toString(), breed.getText().toString());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                species.setText(adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                location.setText(adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sex.setText(adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                breed.setText(adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void addanimal(String name, String age, String species, String location, String sex, String breed){
        animal animal = new animal(name, age, species, location, sex, breed);

        databaseReference.child("dog").push().setValue(animal);
    }



}