package com.example.loginfacebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;


public class MainActivity extends AppCompatActivity {

    TextView txtuser;
    ProfilePictureView imagen;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtuser=findViewById( R.id.textView2);
        imagen=findViewById(R.id.profilePictureView);
        button=findViewById(R.id.button2);


        if(AccessToken.getCurrentAccessToken()==null){
            pantalla();
        }else{
            Profile profile = Profile.getCurrentProfile();
            txtuser.setText(profile.getName());
            imagen.setProfileId(profile.getId());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                pantalla();
            }
        });
    }

    private void pantalla() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        finish();
        startActivity(intent);
    }

    public void publicar(){


        ShareLinkContent.Builder content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#Publicando en facebook desde android studio....")
                        .build());
    }
}