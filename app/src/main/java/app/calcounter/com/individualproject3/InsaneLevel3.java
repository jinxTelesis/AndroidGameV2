package app.calcounter.com.individualproject3;

//import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
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
import static app.calcounter.com.individualproject3.Constants.Constant.INSANESCORE1;
import static app.calcounter.com.individualproject3.Constants.Constant.INSANESCORE3;

/** InsaneLevel3 is the first stage on easy mode it has 7 drag and drop buttons
 *  if the player drags the correct buttons onto the blank button fields
 *  then a traversal starts which is stored as an animation set
 *  the animations are done as a percentage of the screen
 *  this should be measured in a professional class but was roughly done for
 *  class work the drag listeners take in the event info to check if the player
 *  dragged the correct button symbol over
 *
 *  if the correct selections are made this activity will pass the score
 *  to the next activity ChildScoreInsane
 *
 *
 */

public class InsaneLevel3 extends AppCompatActivity {

    private SharedPreferences myPrefs;
    private MediaPlayer mediaPlayer;

    private int startAnimationCounter = 0;
    private boolean firstTime1 = true;
    private boolean firstTime2 = true;
    private boolean firstTime3 = true;
    private boolean firstTime4 = true;
    private boolean firstTime5 = true;
    private boolean firstTime6 = true;
    private boolean firstTime7 = true;


    private int playerScore = 0;
    private Intent restartIntent;

    StrtDrgLsntr strtDrgLsntr;
    EndDrgLsntr endDrgLsntr;

    @BindView(R.id.stage12EwokID)ImageView ewok;
    @BindView(R.id.stage12buttonExit)Button exit;


    private AnimationSet fullAnimation;

    private TranslateAnimation move1;
    private TranslateAnimation move2;
    private TranslateAnimation move3;
    private TranslateAnimation move4;
    private TranslateAnimation move5;
    private TranslateAnimation move6;
    private TranslateAnimation move7;
    private TranslateAnimation move8;
    private TranslateAnimation move9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insane_level3);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        restartIntent = getIntent();

        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();


        ButterKnife.bind(this);
        fullAnimation = new AnimationSet(true);

        mediaPlayer = new MediaPlayer();

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
        int moveSize1 = (int) (width / 4.9);
        int moveSize2 = (int) (height / 4.65);
        int moveSize3 = (int) (width / 7.1);
        int moveSize4 = -1*(int) (height / 3.25);
        int moveSize5 = (int) (width / 6);
        int moveSize6 = (int) (height / 2.6);
        int moveSize7 = (int) (width/5.9); // this needs a long pause then go
        int moveSize8 = (int) (height/4);

        //**********************************************
        // these are the click listeners for the buttons

        findViewById(R.id.stage12buttonDownID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage12buttonUpID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage12buttonLeftID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage12buttonRightID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage12buttonLoopID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage12buttonHaltID).setOnLongClickListener(strtDrgLsntr);

        //***********************************************
        // drag listeners waiting for the correct button type to be dragged over
        // will accept the wrong button type which is intended
        // uses clip data to pass the actual information

        findViewById(R.id.stage12button1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage12button2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage12button3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage12button4).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage12button5).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage12button6).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage12button7).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage12button8).setOnDragListener(endDrgLsntr);

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
        move5.setFillAfter(true);
        fullAnimation.addAnimation(move5);


        move6 = new TranslateAnimation(0, 0,0,moveSize6);
        move6.setDuration(5000);
        move6.setStartOffset(29000);
        move6.setFillAfter(true);
        fullAnimation.addAnimation(move6);

        move7 = new TranslateAnimation(0,moveSize7,0,0);
        move7.setDuration(5000);
        move7.setStartOffset(35000);
        move7.setFillAfter(true);
        fullAnimation.addAnimation(move7);

        move8 = new TranslateAnimation(0, 0,0,moveSize8);
        move8.setDuration(5000);
        move8.setStartOffset(41000);
        move8.setFillAfter(true);
        fullAnimation.addAnimation(move8);

        //move9 = new TranslateAnimation(0,moveSize9,0,0);
        //move9.setDuration(5000);
        //move9.setStartOffset(47000);
        //move9.setFillAfter(true);
        //fullAnimation.addAnimation(move9);
    }

    //*******************************
    // exit button

    @OnClick(R.id.stage12buttonExit)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    //**************************************************
    // this button replays level without saving score


    @OnClick(R.id.stage12buttonReplay)
    public void restartLevel(View view)
    {
        finish();
        startActivity(restartIntent);
    }

    //*************************************************************
    // drag listeners with the clip data
    // info sent with the clip data and that also tests
    // if correct move was made

    private class StrtDrgLsntr implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            WithShadow withShadow = new WithShadow(v);

            if(v.getId() == R.id.stage12buttonDownID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderdown", "down");
                v.startDrag(data,withShadow,v,0);

            }

            if(v.getId() == R.id.stage12buttonUpID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderup", "up");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage12buttonRightID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderright","right");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage12buttonLeftID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderleft","left");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage12buttonHaltID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderhalt", "halt");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage12buttonLoopID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderloop","loop");
                v.startDrag(data,withShadow,v,0);
            }

            return false;
        }
    }

    //**********************************************************
    // end of drag listeners determines if the correct button
    // was dragged over

    private class EndDrgLsntr implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if(event.getAction() == event.ACTION_DROP){
                v.setBackground(((Button)event.getLocalState()).getBackground());


                if(v.getId() == R.id.stage12button1)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("down"))
                    {
                        if(firstTime1) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime1 = false;
                            playerScore+= 50;
                        }
                    }
                }


                if(v.getId() == R.id.stage12button2)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("right"))
                    {
                        if(firstTime2) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime2 = false;
                            playerScore+= 50;
                        }
                    }
                }


                if(v.getId() == R.id.stage12button3)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("up"))
                    {
                        if(firstTime3) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime3 = false;
                            playerScore+= 50;
                        }
                    }
                }


                if(v.getId() == R.id.stage12button4)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("right"))
                    {
                        if(firstTime4) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime4 = false;
                            playerScore+=50;
                        }
                    }
                }


                if(v.getId() == R.id.stage12button5)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("down"))
                    {
                        if(firstTime5) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime5 = false;
                            playerScore+=50;
                        }
                    }
                }


                if(v.getId() == R.id.stage12button6)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("right"))
                    {
                        if(firstTime6) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime6 = false;
                            playerScore+=50;
                        }
                    }
                }


                if(v.getId() == R.id.stage12button7)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("down"))
                    {
                        if(firstTime7) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime7 = false;
                            playerScore+=50;
                        }
                    }
                }
            }

            // **************************************************************************
            // if the player picked all the correct values the next activity is started
            // and the score data is passed in

            if(startAnimationCounter == 7)
            {
                ewok.startAnimation(fullAnimation);
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //ScoreViewModel actViewModel = ViewModelProviders.of(InsaneLevel3.this).get(ScoreViewModel.class);
                        //actViewModel.setPlayerTotalScore(playerScore);
                        //actViewModel.saveScore(INSANESCORE3);

                        Intent previous = getIntent();
                        Bundle userbundle = previous.getExtras();

                        myPrefs = getSharedPreferences(userbundle.getString(CURRENTPLAYER),0);
                        SharedPreferences.Editor editor = myPrefs.edit();

                        editor.putInt(INSANESCORE3, playerScore);
                        editor.apply();

                        Intent intent = new Intent(InsaneLevel3.this,ChildScoreInsane.class);
                        intent.putExtras(userbundle);
                        startActivity(intent);
                        finish();
                    }
                },44000);
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
