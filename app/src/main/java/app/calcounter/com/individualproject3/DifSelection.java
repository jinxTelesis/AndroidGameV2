package app.calcounter.com.individualproject3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DifSelection extends AppCompatActivity {

    /** DifSelection is just a menu activity it goes to
     *  Either EasyLevel1, MediumLevel1, HardLevel1 or InsaneLevel1
     *
     */

    AudioClass myAudio;
    private MediaPlayer mediaPlayer;

    @BindView(R.id.btnIDEasy) Button btnEasy;
    @BindView(R.id.btnIDHard) Button btnHard;
    @BindView(R.id.btnIDMedium) Button btnMedium;
    @BindView(R.id.btnIDInsane) Button btnInsane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dif_selection);
        ButterKnife.bind(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifSelection.this, EasyLevel1.class);

                ////////
                ////////
                Intent previous = getIntent();
                Bundle userbundle = previous.getExtras();
                intent.putExtras(userbundle);
                ////////
                ////////
                startActivity(intent);
                finish();
            }
        });

        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifSelection.this, MediumLevel1.class);

                ////////
                ////////
                Intent previous = getIntent();
                Bundle userbundle = previous.getExtras();
                intent.putExtras(userbundle);
                ////////
                ////////
                startActivity(intent);
                finish();
            }
        });




        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifSelection.this, HardLevel1.class);

                Intent previous = getIntent();
                Bundle userbundle = previous.getExtras();
                intent.putExtras(userbundle);

                startActivity(intent);
                finish();
            }
        });

        btnInsane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent previous = getIntent();
                Bundle userbundle = previous.getExtras();
                Intent intent = new Intent(DifSelection.this, InsaneLevel1.class);
                intent.putExtras(userbundle);

                startActivity(intent);
                finish();
            }
        });
    }
}
