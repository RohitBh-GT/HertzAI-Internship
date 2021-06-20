package com.android.hertzai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView emailText;
    private Button logOut;
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = findViewById(R.id.UserEmail);
        logOut = findViewById(R.id.log_out);
        photo = findViewById(R.id.photo_url);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String email = (String) bundle.get("userID");
        Toast.makeText(getApplicationContext(),email+" registered",Toast.LENGTH_SHORT).show();
        Uri imageUrl = (Uri)bundle.get("photo");

        Glide.with(this).load(imageUrl.toString()).into(photo);

        emailText.setText(email);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent signInActivity = new Intent(MainActivity.this,SignIn.class);
                startActivity(signInActivity);
            }
        });

    }
}