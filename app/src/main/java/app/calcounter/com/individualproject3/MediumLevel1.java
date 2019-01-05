package app.calcounter.com.individualproject3;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static app.calcounter.com.individualproject3.Constants.Constant.CURRENTPLAYER;
import static app.calcounter.com.individualproject3.Constants.Constant.EASYSCORE2;
import static app.calcounter.com.individualproject3.Constants.Constant.MEDSCORE1;
import static app.calcounter.com.individualproject3.Constants.Constant.THREAD_DELAY;

public class MediumLevel1 extends AppCompatActivity {

    /** this is a level that uses drag n drop like the others
     *  if the player chooses the correct systems that match the course
     *  then AnimationSet is started and the score is updated and sent to the next
     *  activity MediumLevel2
     *
     *  movement of the translation is roughly calculated by screen size
     *  hack solution because this was a homework with limited time to solve
     *
     *  event drag listeners wait for the correct button to be dragon onto them
     *
     */

    private SharedPreferences myPrefs; // for the players score
    private MediaPlayer mediaPlayer; // music

    private int startAnimationCounter = 0;
    private boolean firstTime1 = true; // prevent score incrementation with repeated
    private boolean firstTime2 = true;
    private boolean firstTime3 = true;
    private boolean firstTime4 = true;
    private boolean firstTime5 = true;
    private int playerScore = 0;



    StrtDrgLsntr strtDrgLsntr;
    EndDrgLsntr endDrgLsntr;
    @BindView(R.id.stage7ewokID)ImageView ewok;
    @BindView(R.id.stage7buttonExit)Button exit;

    private AnimationSet fullAnimation;

    private TranslateAnimation move1; // each of the translated animations in the set
    private TranslateAnimation move2;
    private TranslateAnimation move3;
    private TranslateAnimation move4;
    private TranslateAnimation move5;
    private TranslateAnimation move6;
    private TranslateAnimation move7;
    private TranslateAnimation move8;
    private TranslateAnimation move9;
    private TranslateAnimation move10;
    private TranslateAnimation move11;
    private TranslateAnimation move12;
    private TranslateAnimation move13;

    private Intent restartIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_level1);

        AlertDialog.Builder alertDialogbuild = new AlertDialog.Builder(MediumLevel1.this);

        alertDialogbuild.setTitle("Directions");
        alertDialogbuild.setMessage("Enter the first five moves and let the current take our adventurer!").setCancelable(true);

        AlertDialog alertDialog = alertDialogbuild.create();

        alertDialog.show();

        new Handler().postDelayed(() -> {

            alertDialog.dismiss();

        }, THREAD_DELAY);



        // ***********************************************************************
        // hack solution to get window size does not measure stuff like action bar
        // break screen down into ratios
        // seems to scale reasonably to other devices
        // ***********************************************************************

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int moveSize1 = (int) (width / 6); // cor
        int moveSize2 = (int) (height / 5.2); // cor
        int moveSize3 = (int) (width / 1.55); // cor
        int moveSize4 = -1*(int) (height / 1.7); // cor
        int moveSize5 = -1*(int) (width / 2.1); // cor
        int moveSize6 = (int) (height / 2.1); // dead on
        int moveSize7 = (int) (width / 2.55); // good
        int moveSize8 = -1*(int) (height/ 2.75); // good
        int moveSize9 = -1*(int) (width/ 3.5); // wrong
        int moveSize10 = (int) (height/4.2); // wrong
        int moveSize11 = (int) (width / 5.1); //wrong
        int moveSize12 = -1*(int)(width/18); // wrong
        int moveSize13 = -1*(int)(width/14); // wrong

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        restartIntent = getIntent();


        ButterKnife.bind(this);
        fullAnimation = new AnimationSet(true);

        mediaPlayer = new MediaPlayer();

        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();

        //**********************************************
        // these are the click listeners for the buttons

        findViewById(R.id.stage7buttonRightID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage7buttonLeftID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage7buttonDownID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage7buttonUpID).setOnLongClickListener(strtDrgLsntr);

        //***********************************************
        // drag listeners waiting for the correct button type to be dragged over
        // will accept the wrong button type which is intended
        // uses clip data to pass the actual information

        findViewById(R.id.stage7button1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage7button2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage7button3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage7button4).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage7button5).setOnDragListener(endDrgLsntr);


        //***************************************************************
        // the screen is grid like so one transaltion is done at a time
        // for the most part
        move1 = new TranslateAnimation(0, moveSize1, 0,0);
        move1.setDuration(5000);
        move1.setFillAfter(true);
        fullAnimation.addAnimation(move1);

        // move two
        move2 = new TranslateAnimation(0,0,0,moveSize2);
        move2.setDuration(5000);
        move2.setFillAfter(true);
        move2.setStartOffset(6000);
        fullAnimation.addAnimation(move2);

        move3 = new TranslateAnimation(0, moveSize3,0,0);
        move3.setDuration(5000);
        move3.setFillAfter(true);
        move3.setStartOffset(12000);
        fullAnimation.addAnimation(move3);


        move4 = new TranslateAnimation(0, 0,0,moveSize4);
        move4.setDuration(5000);
        move4.setFillAfter(true);
        move4.setStartOffset(18000);
        fullAnimation.addAnimation(move4);


        move5 = new TranslateAnimation(0, moveSize5,0,0);
        move5.setDuration(5000);
        move5.setStartOffset(24000);
        fullAnimation.addAnimation(move5);

        move6 = new TranslateAnimation(0, 0,0,moveSize6);
        move6.setDuration(5000);
        move6.setFillAfter(true);
        move6.setStartOffset(30000);
        fullAnimation.addAnimation(move6);

        move7 = new TranslateAnimation(0,moveSize7,0,0);
        move7.setDuration(5000);
        move7.setFillAfter(true);
        move7.setStartOffset(36000);
        fullAnimation.addAnimation(move7);

        move8 = new TranslateAnimation(0,0,0,moveSize8);
        move8.setDuration(5000);
        move8.setFillAfter(true);
        move8.setStartOffset(42000);
        fullAnimation.addAnimation(move8);

        move9 = new TranslateAnimation(0,moveSize9,0,0);
        move9.setDuration(5000);
        move9.setFillAfter(true);
        move9.setStartOffset(48000);
        fullAnimation.addAnimation(move9);

        move10 = new TranslateAnimation(0,0,0,moveSize10);
        move10.setDuration(5000);
        move10.setFillAfter(true);
        move10.setStartOffset(54000);
        fullAnimation.addAnimation(move10);

        move11 = new TranslateAnimation(0, moveSize11, 0,0);
        move11.setDuration(5000);
        move11.setFillAfter(true);
        move11.setStartOffset(60000);
        fullAnimation.addAnimation(move11);


        move12 = new TranslateAnimation(0, 0, 0,moveSize12);
        move12.setDuration(3000);
        move12.setFillAfter(true);
        move12.setStartOffset(64000);
        fullAnimation.addAnimation(move12);

        move13 = new TranslateAnimation(0, moveSize13, 0,0);
        move13.setDuration(2000);
        move13.setFillAfter(true);
        move13.setStartOffset(66000);
        fullAnimation.addAnimation(move13);

    }


    //*******************************
    // exit button


    @OnClick(R.id.stage7buttonExit)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    //**************************************************
    // this button replays level without saving score

    @OnClick(R.id.stage7buttonReplay)
    public void restartLevel(View view)
    {
        finish();
        startActivity(restartIntent);
    }

    //*************************************************************
    // drag listeners with the clip data

    private class StrtDrgLsntr implements View.OnLongClickListener{


        @Override
        public boolean onLongClick(View v) {
            WithShadow withShadow = new WithShadow(v);

            if(v.getId() == R.id.stage7buttonDownID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderdown", "down");
                v.startDrag(data,withShadow,v,0);

            }

            if(v.getId() == R.id.stage7buttonUpID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderup", "up");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage7buttonRightID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderright","right");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage7buttonLeftID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderleft","left");

                v.startDrag(data,withShadow,v,0);
            }



            //Log.e("output",data.toString());
            return false;
        }
    }

    //**********************************************************
    // end of drag listeners determines if the correct button
    // was dragged over

    private class EndDrgLsntr implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event) {

            if (event.getAction() == event.ACTION_DROP) {
                v.setBackground(((Button) event.getLocalState()).getBackground());

                if (v.getId() == R.id.stage7button1) {
                    ClipData s = event.getClipData();
                    // this is storing the actual clip data
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if (s1.equals("down")) {
                        if (firstTime1) // prevents cheating
                        {
                            // score values
                            ++startAnimationCounter;
                            firstTime1 = false;
                            playerScore += 25;
                        }

                    }
                }


                if (v.getId() == R.id.stage7button2) {
                    ClipData s = event.getClipData();
                    // this is storing the actual clip data
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if (s1.equals("right")) {
                        if (firstTime2) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime2 = false;
                            playerScore += 25;
                        }
                    }
                }

                if (v.getId() == R.id.stage7button3) {
                    ClipData s = event.getClipData();
                    // this is storing the actual clip data
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if (s1.equals("up")) {
                        if (firstTime3) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime3 = false;
                            playerScore += 25;
                        }
                    }
                }

                if (v.getId() == R.id.stage7button4) {
                    ClipData s = event.getClipData();
                    // test if it is the correct button
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if (s1.equals("left")) {
                        if (firstTime4) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime4 = false;
                            playerScore += 25;
                        }
                    }
                }

                if (v.getId() == R.id.stage7button5) {
                    ClipData s = event.getClipData();

                    // test if it is the correct button
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if (s1.equals("down")) {
                        if (firstTime5) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime5 = false;
                            playerScore += 25;
                        }
                    }
                }

            }

            // **************************************************************************
            // if the player picked all the correct values the next activity is started
            // and the score data is passed in

            if(startAnimationCounter == 5)
            {
                ewok.startAnimation(fullAnimation);
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ScoreViewModel actViewModel = ViewModelProviders.of(MediumLevel1.this).get(ScoreViewModel.class);
                        actViewModel.setPlayerTotalScore(playerScore);
                        actViewModel.saveScore(MEDSCORE1);

                        Intent previous = getIntent();
                        Bundle userbundle = previous.getExtras();

                        myPrefs = getSharedPreferences(userbundle.getString(CURRENTPLAYER),0);
                        SharedPreferences.Editor editor = myPrefs.edit();


                        editor.putInt(MEDSCORE1, playerScore);
                        editor.apply();



                        Intent intent = new Intent(MediumLevel1.this,MediumLevel2.class);
                        intent.putExtras(userbundle);
                        startActivity(intent);
                        finish();
                    }

                },66000L);

            }

            return true;
        }

    }

    private class WithShadow extends View.DragShadowBuilder{
        public WithShadow(View v){
            super(v);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
        }
    }

}
