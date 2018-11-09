package app.calcounter.com.individualproject3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChildScore extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @BindView(R.id.textViewChildScore1)TextView score1TV;
    @BindView(R.id.textViewChildScore2)TextView score2TV;
    @BindView(R.id.textViewChildScore3)TextView score3TV;
    @BindView(R.id.childscoreReplayID) Button replayBtn;
    @BindView(R.id.child_score_exitid) Button exit;

    private SharedPreferences myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_score);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();


        Intent previous = getIntent();
        Bundle userbundle = previous.getExtras();
        ButterKnife.bind(this);

        myPrefs = getSharedPreferences(userbundle.getString("curplayer"),0);
        //Log.e("currentplay value",userbundle.getString("curplayer"));
        SharedPreferences.Editor editor = myPrefs.edit();

        //Log.e("score value", Integer.toString(myPrefs.getInt("stage1score",0)));

        String tempscore1 = Integer.toString(myPrefs.getInt("waffle",0));
        String tempscore2 = Integer.toString(myPrefs.getInt("microbike",0));
        String tempscore3 = Integer.toString(myPrefs.getInt("stage3score",0));

        if(tempscore1 != null)
        {
            score1TV.setText("Stage 1 score: " + tempscore1);
        }
        else
        {
            score1TV.setText("zero");
        }

        if(tempscore2 != null)
        {
            score2TV.setText("Stage 2 score: " + tempscore2);
        }
        else
        {
            score1TV.setText("zero");
        }

        if(tempscore3 != null)
        {
            score3TV.setText("Stage 3 score: " + tempscore3);
        }
        else
        {
            score1TV.setText("zero");
        }



//                topScoreSoFar = myPrefs.getString("TopScore",null);
//        secondScore = myPrefs.getString("SecondScore", null);
//        thirdScore = myPrefs.getString("ThirdScore",null);
//
//        if(topScoreSoFar != null)
//        {
//            tVScore1.setText(topScoreSoFar);
//        }
//        else
//        {
//            tVScore1.setText("0");
//        }


//        try{
//            score1TV.setText("stage 1 score: " + Integer.toString(myPrefs.getInt("stage1score",0)));
//        } catch (Exception e)
//        {
//
//        }
//
//        try{
//            score2TV.setText("stage 2 score: " + Integer.toString(myPrefs.getInt("stage2score",0)));
//        } catch (Exception e)
//        {
//
//        }
//
//        try
//        {
//            score2TV.setText("stage 3 score: " + Integer.toString(myPrefs.getInt("stage3score",0)));
//        } catch(Exception e)
//        {
//
//        }
    }

    @OnClick(R.id.child_score_exitid)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    // this button replays level without saving score
    @OnClick(R.id.childscoreReplayID)
    public void restartLevel(View view)
    {
        Intent intent = new Intent(ChildScore.this, DifSelection.class);
        Intent previous = getIntent();
        Bundle userbundle = previous.getExtras();
        intent.putExtras(userbundle);
        finish();
        startActivity(intent);



    }


}