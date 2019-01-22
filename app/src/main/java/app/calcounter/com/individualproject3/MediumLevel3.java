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
import static app.calcounter.com.individualproject3.Constants.Constant.MEDSCORE3;

/** MediumLevel3 is the first stage on easy mode it has 4 drag and drop buttons
 *  if the player drags the correct buttons onto the blank button fields
 *  then a traversal starts which is stored as an animation set
 *  the animations are done as a percentage of the screen
 *  this should be measured in a professional class but was roughly done for
 *  class work the drag listeners take in the event info to check if the player
 *  dragged the correct button symbol over
 *
 *  if the correct selections are made this activity will pass the score
 *  to the next activity EasyLevel2
 *
 *
 */

public class MediumLevel3 extends AppCompatActivity {

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
    private boolean firstTime8 = true;
    private boolean firstTime9 = true;

    private int playerScore = 0;

    StrtDrgLsntr strtDrgLsntr;
    EndDrgLsntr endDrgLsntr;
    @BindView(R.id.stage9ewokID)ImageView ewok;
    @BindView(R.id.stage9buttonExit)Button exit;

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
    private TranslateAnimation move10;

    private Intent restartIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediumn_level3);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        restartIntent = getIntent();


        ButterKnife.bind(this);
        fullAnimation = new AnimationSet(true);

        mediaPlayer = new MediaPlayer();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // ***********************************************************************
        // hack solution to get window size does not measure stuff like action bar
        // break screen down into ratios
        // seems to scale reasonably to other devices
        // ***********************************************************************
        int moveSize1 = (int) (width / 5.6);
        int moveSize2 = (int) (height / 4.45);
        int moveSize3 = (int) (width / 1.6);
        int moveSize4 = -1*(int) (height / 1.6);
        int moveSize5 = -1*(int) (width / 2.3);
        int moveSize6 = (int) (height / 12);
        int moveSize7 = -1*(int)(width / 18);
        int moveSize8 = -1*(int)(height/7);
        int moveSize9 = (int)(width / 1.85);
        int moveSize10 = (int) (height / 1.35); // off screen



        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();

        //**********************************************
        // these are the click listeners for the buttons

        findViewById(R.id.stage9buttonRightID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage9buttonLeftID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage9buttonDownID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage9buttonUpID).setOnLongClickListener(strtDrgLsntr);

        //***********************************************
        // drag listeners waiting for the correct button type to be dragged over
        // will accept the wrong button type which is intended
        // uses clip data to pass the actual information

        findViewById(R.id.stage9button1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage9button2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage9button3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage9button4).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage9button5).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage9button6).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage9button7).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage9button8).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage9button9).setOnDragListener(endDrgLsntr);

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
        move5.setFillAfter(true);

        move6 = new TranslateAnimation(0, 0,0,moveSize6);
        move6.setDuration(5000);
        move6.setStartOffset(30000);
        fullAnimation.addAnimation(move6);
        move6.setFillAfter(true);

        move7 = new TranslateAnimation(0, moveSize7,0,0);
        move7.setDuration(2000);
        move7.setStartOffset(37000);
        fullAnimation.addAnimation(move7);
        move7.setFillAfter(true);

        move8 = new TranslateAnimation(0, 0,0,moveSize8);
        move8.setDuration(2000);
        move8.setStartOffset(39000);
        fullAnimation.addAnimation(move8);
        move8.setFillAfter(true);

        move9 = new TranslateAnimation(0,moveSize9,0,0);
        move9.setDuration(5000);
        move9.setStartOffset(45000);
        fullAnimation.addAnimation(move9);
        move9.setFillAfter(true);

        move10 = new TranslateAnimation(0,0,0,moveSize10);
        move10.setDuration(5000);
        move10.setStartOffset(51000);
        fullAnimation.addAnimation(move10);
        move10.setFillAfter(true);

    }

    //*******************************
    // exit button

    @OnClick(R.id.stage9buttonExit)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    //**************************************************
    // this button replays level without saving score

    @OnClick(R.id.stage9buttonReplay)
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

            if(v.getId() == R.id.stage9buttonDownID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderdown", "down");
                v.startDrag(data,withShadow,v,0);

            }

            if(v.getId() == R.id.stage9buttonUpID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderup", "up");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage9buttonRightID)
            {
                // this is the specific clip data
                ClipData data = ClipData.newPlainText("senderright","right");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage9buttonLeftID)
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
            if(event.getAction() == event.ACTION_DROP){
                v.setBackground(((Button)event.getLocalState()).getBackground());

                // this is storing the actual clip data
                if(v.getId() == R.id.stage9button1)
                {
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("down"))
                    {
                        if(firstTime1) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime1 = false;
                            playerScore+= 25;
                        }
                    }

                }


                if(v.getId() == R.id.stage9button2)
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
                            playerScore+= 25;
                        }
                    }
                }

                if(v.getId() == R.id.stage9button3)
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
                            playerScore+= 25;
                        }
                    }

                }

                if(v.getId() == R.id.stage9button4)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("left"))
                    {
                        if(firstTime4) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime4 = false;
                            playerScore+=25;
                        }
                    }
                }

                if(v.getId() == R.id.stage9button5)
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
                            playerScore+=25;
                        }
                    }
                }

                if(v.getId() == R.id.stage9button6)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("left"))
                    {
                        if(firstTime6) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime6 = false;
                            playerScore+=25;
                        }
                    }
                }

                if(v.getId() == R.id.stage9button7)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("up"))
                    {
                        if(firstTime7) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime7 = false;
                            playerScore+=25;
                        }
                    }
                }

                if(v.getId() == R.id.stage9button8)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("right"))
                    {
                        if(firstTime8) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime8 = false;
                            playerScore+=25;
                        }
                    }
                }

                if(v.getId() == R.id.stage9button9)
                {
                    // this is storing the actual clip data
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    // test if it is the correct button
                    if(s1.equals("down"))
                    {
                        if(firstTime9) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime9 = false;
                            playerScore+=25;
                        }
                    }
                }

            }

            // **************************************************************************
            // if the player picked all the correct values the next activity is started
            // and the score data is passed in

            if(startAnimationCounter == 9)
            {
                ewok.startAnimation(fullAnimation);
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //ScoreViewModel actViewModel = ViewModelProviders.of(MediumLevel3.this).get(ScoreViewModel.class);
                        //actViewModel.setPlayerTotalScore(playerScore);
                        //actViewModel.saveScore(MEDSCORE3);

                        Intent previous = getIntent();
                        Bundle userbundle = previous.getExtras();

                        myPrefs = getSharedPreferences(userbundle.getString(CURRENTPLAYER),0);
                        SharedPreferences.Editor editor = myPrefs.edit();

                        editor.putInt(MEDSCORE3, playerScore);
                        editor.apply();


                        Intent intent = new Intent(MediumLevel3.this,ChildScoreMedium.class);
                        intent.putExtras(userbundle);
                        startActivity(intent);
                        finish();
                    }
                },56000L);
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
