package com.example.androidchatapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    //firebase auth object
    FirebaseUser users;
    private FirebaseAuth firebaseAuth;
    TextView textViewnew;
    //view objects
    private TextView textViewUserEmail, textViewUserName, textViewUserPhone, textViewUserCity, textViewUserGender,textViewProfession;
    private Button buttonLogout,buttonMap;
    String name, phone,country;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        firebaseAuth = FirebaseAuth.getInstance();



        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, Login.class));
        }



        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserName=(TextView) findViewById(R.id.textViewUserName);
        textViewUserPhone=(TextView) findViewById(R.id.textViewUserPhone);
        textViewUserCity=(TextView)findViewById(R.id.textViewUserCity);
        textViewUserGender=(TextView) findViewById(R.id.textViewUserGender);
        textViewProfession=(TextView) findViewById(R.id.textViewUserProfession);





        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        //buttonMap= (Button) findViewById(R.id.buttonMap);

        users= firebaseAuth.getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference().child("users");


// Attach a listener to read the data at our posts reference
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                name= dataSnapshot.child(users.getDisplayName()).getValue(String.class);
//                phone= dataSnapshot.child(users.getUid()).child("phone").getValue(String.class);
//                country= dataSnapshot.child(users.getUid()).child("country").getValue(String.class);

                textViewUserName.setText("Name:"+name);
//                textViewUserPhone.setText("Phone:"+phone);
//                textViewUserCity.setText("Country:"+country);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
//        buttonMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
//                startActivity(intent);
//            }
//        });




        //adding listener to button
        buttonLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            startActivity(new Intent(this, Login.class));
        }}}
