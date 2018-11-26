package app.calcounter.com.individualproject3;

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

public class InsaneLevel1 extends AppCompatActivity {

    private SharedPreferences myPrefs;
    private MediaPlayer mediaPlayer;

    private int startAnimationCounter = 0;
    private boolean firstTime1 = true;
    private boolean firstTime2 = true;
    private boolean firstTime3 = true;
    private boolean firstTime4 = true;

    private int playerScore = 0;
    private Intent restartIntent;

    StrtDrgLsntr strtDrgLsntr;
    EndDrgLsntr endDrgLsntr;

    @BindView(R.id.stage10EwokID)ImageView ewok;
    @BindView(R.id.stage10buttonExit)Button exit;


    private AnimationSet fullAnimation;

    private TranslateAnimation move1;
    private TranslateAnimation move2;
    private TranslateAnimation move3;
    private TranslateAnimation move4;
    private TranslateAnimation move5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insane_level1);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        restartIntent = getIntent();

        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();

        ButterKnife.bind(this);
        fullAnimation = new AnimationSet(true);

        mediaPlayer = new MediaPlayer();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int moveSize1 = (int) (width / 3);
        int moveSize2 = (int) (height / 3.8);
        int moveSize3 = (int) (width / 2.9);
        int moveSize4 = -1*(int) (height / 6.3);
        int moveSize5 = (int) (height / 4.3);


        findViewById(R.id.stage10buttonDownID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage10buttonUpID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage10buttonLeftID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage10buttonRightID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage10buttonLoopID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage10buttonHaltID).setOnLongClickListener(strtDrgLsntr);

        findViewById(R.id.stage10button1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage10button2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage10button3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage10button4).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage10button5).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage10button6).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage10button7).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage10button8).setOnDragListener(endDrgLsntr);

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


        move5 = new TranslateAnimation(0, 0,0,moveSize5);
        move5.setDuration(5000);
        move5.setStartOffset(24000);
        move5.setFillAfter(true);
        fullAnimation.addAnimation(move5);


    }

    @OnClick(R.id.stage10buttonExit)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    // this button replays level without saving score
    @OnClick(R.id.stage10buttonReplay)
    public void restartLevel(View view)
    {
        finish();
        startActivity(restartIntent);
    }

    private class StrtDrgLsntr implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            WithShadow withShadow = new WithShadow(v);

            if(v.getId() == R.id.stage10buttonDownID)
            {
                ClipData data = ClipData.newPlainText("senderdown", "down");
                v.startDrag(data,withShadow,v,0);

            }

            if(v.getId() == R.id.stage10buttonUpID)
            {
                ClipData data = ClipData.newPlainText("senderup", "up");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage10buttonRightID)
            {
                ClipData data = ClipData.newPlainText("senderright","right");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage10buttonLeftID)
            {
                ClipData data = ClipData.newPlainText("senderleft","left");

                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage10buttonHaltID)
            {
                ClipData data = ClipData.newPlainText("senderhalt", "halt");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage10buttonLoopID)
            {
                ClipData data = ClipData.newPlainText("senderloop","loop");
                v.startDrag(data,withShadow,v,0);
            }

            return false;
        }
    }


    private class EndDrgLsntr implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event) {
            {
                if(event.getAction() == event.ACTION_DROP){
                    v.setBackground(((Button)event.getLocalState()).getBackground());

                    if(v.getId() == R.id.stage10button1)
                    {
                        ClipData s = event.getClipData();
                        String s1 = (String) s.getItemAt(0).getText();

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


                    if(v.getId() == R.id.stage10button2)
                    {
                        ClipData s = event.getClipData();
                        String s1 = (String) s.getItemAt(0).getText();

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

                    if(v.getId() == R.id.stage10button3)
                    {
                        ClipData s = event.getClipData();
                        String s1 = (String) s.getItemAt(0).getText();

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

                    if(v.getId() == R.id.stage10button4)
                    {
                        ClipData s = event.getClipData();
                        String s1 = (String) s.getItemAt(0).getText();

                        if(s1.equals("down"))
                        {
                            if(firstTime4) // prevents cheating
                            {
                                ++startAnimationCounter;
                                firstTime4 = false;
                                playerScore+=50;
                            }
                        }
                    }
                }

                //Todo fix this after debug
                if(startAnimationCounter == 4)
                {
                    ewok.startAnimation(fullAnimation);
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent previous = getIntent();
                            Bundle userbundle = previous.getExtras();

                            myPrefs = getSharedPreferences(userbundle.getString(CURRENTPLAYER),0);
                            SharedPreferences.Editor editor = myPrefs.edit();

                            editor.putInt(INSANESCORE1, playerScore);
                            editor.apply();

                            Intent intent = new Intent(InsaneLevel1.this,InsaneLevel2.class);
                            intent.putExtras(userbundle);
                            startActivity(intent);
                            finish();
                        }
                    },26000);
                }

                return true;
            }
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
