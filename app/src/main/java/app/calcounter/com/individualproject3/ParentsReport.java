package app.calcounter.com.individualproject3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.ui.table.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static app.calcounter.com.individualproject3.Constants.Constant.EASYSCORE1;
import static app.calcounter.com.individualproject3.Constants.Constant.EASYSCORE2;
import static app.calcounter.com.individualproject3.Constants.Constant.EASYSCORE3;
import static app.calcounter.com.individualproject3.Constants.Constant.HARDSCORE1;
import static app.calcounter.com.individualproject3.Constants.Constant.HARDSCORE2;
import static app.calcounter.com.individualproject3.Constants.Constant.HARDSCORE3;
import static app.calcounter.com.individualproject3.Constants.Constant.INSANESCORE1;
import static app.calcounter.com.individualproject3.Constants.Constant.INSANESCORE2;
import static app.calcounter.com.individualproject3.Constants.Constant.INSANESCORE3;
import static app.calcounter.com.individualproject3.Constants.Constant.MEDSCORE1;
import static app.calcounter.com.individualproject3.Constants.Constant.MEDSCORE2;
import static app.calcounter.com.individualproject3.Constants.Constant.MEDSCORE3;
import static app.calcounter.com.individualproject3.Constants.Constant.THREAD_DELAY;

public class ParentsReport extends AppCompatActivity {

    /** ParentsReport activity displays a bar graph with each bar
     *  derived from a child score file
     *  this activity uses the anycharts API
     *  starts with an alert dialog telling user about waiting time
     *
     *
     */

    private SharedPreferences myPrefs;
    private String userKey;
    private MediaPlayer mediaPlayer;

    @BindView(R.id.childusernameEditText)EditText childUsernameInput;
    @BindView(R.id.submitchildusername)Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_report);
        ButterKnife.bind(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        // **********************************************************
        // alert dialog tells user graph might not appear right away

        AlertDialog.Builder alertDialogbuild = new AlertDialog.Builder(ParentsReport.this);

        alertDialogbuild.setTitle("Attention");
        alertDialogbuild.setMessage("Graph might take up to 30 seconds to build").setCancelable(true);

        AlertDialog alertDialog = alertDialogbuild.create();

        alertDialog.show();

        new Handler().postDelayed(() -> {

            alertDialog.dismiss();

        }, THREAD_DELAY);

        // put a notification that it takes a second to calculate

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userKey = childUsernameInput.getText().toString();
                myPrefs = getSharedPreferences(userKey,0);
                //Log.e("currentplay value",userbundle.getString("curplayer"));
                SharedPreferences.Editor editor = myPrefs.edit();

                childUsernameInput.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.INVISIBLE);

                //Log.e("value you want", Integer.toString(myPrefs.getInt("waffle",0)));

                //*****************************************************************
                // the next few lines of code retrieve the shared preferences data
                // into variables

                int tempEasyScore1 = (myPrefs.getInt(EASYSCORE1,0));
                int tempEasyScore2 = (myPrefs.getInt(EASYSCORE2,0));
                int tempEasyScore3 = (myPrefs.getInt(EASYSCORE3,0));

                int tempMedScore1 = (myPrefs.getInt(MEDSCORE1,0));
                int tempMedScore2 = (myPrefs.getInt(MEDSCORE2, 0));
                int tempMedScore3 = (myPrefs.getInt(MEDSCORE3,0));

                int tempHardScore1 = (myPrefs.getInt(HARDSCORE1,0));
                int tempHardScore2 = (myPrefs.getInt(HARDSCORE2, 0));
                int tempHardScore3 = (myPrefs.getInt(HARDSCORE3, 0));

                int tempInsaneScore1 = (myPrefs.getInt(INSANESCORE1, 0));
                int tempInsaneScore2 = (myPrefs.getInt(INSANESCORE2, 0));
                int tempInsaneScore3 = (myPrefs.getInt(INSANESCORE3, 0));


                // these are both in the xml used by anychart api
                AnyChartView anyChartView = findViewById(R.id.thatchartapi);
                anyChartView.setProgressBar(findViewById(R.id.progress_bar));

                Cartesian cartesian = AnyChart.column();

                List<DataEntry> data = new ArrayList<>();


                // *******************************
                //loads data values into anychart

                data.add(new ValueDataEntry("Easy1", tempEasyScore1));
                data.add(new ValueDataEntry("Easy2", tempEasyScore2));
                data.add(new ValueDataEntry("Easy3", tempEasyScore3));

                data.add(new ValueDataEntry("Medium1", tempMedScore1));
                data.add(new ValueDataEntry("Medium2", tempMedScore2));
                data.add(new ValueDataEntry("Medium3", tempMedScore3));

                data.add(new ValueDataEntry("Hard1", tempHardScore1));
                data.add(new ValueDataEntry("Hard2", tempHardScore2));
                data.add(new ValueDataEntry("Hard3", tempHardScore3));

                data.add(new ValueDataEntry("Insane1", tempInsaneScore1));
                data.add(new ValueDataEntry("Insane2", tempInsaneScore2));
                data.add(new ValueDataEntry("Insane3", tempInsaneScore3));

                com.anychart.core.cartesian.series.Column column = cartesian.column(data);

                column.tooltip()

                        .titleFormat("{%X}")

                        .position(Position.CENTER_BOTTOM)

                        .anchor(Anchor.CENTER_BOTTOM)

                        .offsetX(0d)

                        .offsetY(5d)

                        //.format("${%Value}{groupsSeparator: }");
                        .format("{%Value}{groupsSeparator: }");


                cartesian.animation(true);

                cartesian.title("Scores by level");

                cartesian.yScale().minimum(0d);

                //cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");
                cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");


                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

                cartesian.interactivity().hoverMode(HoverMode.BY_X);

                // sets x axis levels
                cartesian.xAxis(0).title("Levels");

                // sets y axis levels
                cartesian.yAxis(0).title("Scores");

                anyChartView.setChart(cartesian);
            }
        });
    }
}
