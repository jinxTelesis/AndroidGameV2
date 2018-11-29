package app.calcounter.com.individualproject3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static app.calcounter.com.individualproject3.Constants.Constant.CURRENTPLAYER;
import static app.calcounter.com.individualproject3.Constants.Constant.INSANESCORE1;
import static app.calcounter.com.individualproject3.Constants.Constant.INSANESCORE2;
import static app.calcounter.com.individualproject3.Constants.Constant.INSANESCORE3;

public class ChildScoreInsane extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @BindView(R.id.stage10scoreTV)TextView score1TV;
    @BindView(R.id.stage11scoreTV)TextView score2TV;
    @BindView(R.id.stage12scoreTV)TextView score3TV;
    @BindView(R.id.childscoreInsaneReplayID) Button replayBtn;
    @BindView(R.id.child_scoreInsane_exitid) Button exit;
    private SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_score_insane);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();


        Intent previous = getIntent();
        Bundle userbundle = previous.getExtras();
        ButterKnife.bind(this);

        myPrefs = getSharedPreferences(userbundle.getString(CURRENTPLAYER),0);
//        //Log.e("currentplay value",userbundle.getString("curplayer"));
        SharedPreferences.Editor editor = myPrefs.edit();
//
//        //Log.e("score value", Integer.toString(myPrefs.getInt("stage1score",0)));
//
        String tempscore1 = Integer.toString(myPrefs.getInt(INSANESCORE1,0));
        String tempscore2 = Integer.toString(myPrefs.getInt(INSANESCORE2,0));
        String tempscore3 = Integer.toString(myPrefs.getInt(INSANESCORE3,0));

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



    }



    @OnClick(R.id.childscoreInsaneReplayID)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    // this button replays level without saving score
    @OnClick(R.id.child_scoreInsane_exitid)
    public void restartLevel(View view)
    {

        Intent previous = getIntent();
        Bundle userbundle = previous.getExtras();
        Intent intent = new Intent(ChildScoreInsane.this, DifSelection.class);
        intent.putExtras(userbundle);
        finish();
        startActivity(intent);
    }
}
