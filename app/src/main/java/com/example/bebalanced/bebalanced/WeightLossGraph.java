package com.example.bebalanced.bebalanced;

import android.database.Cursor;
import android.graphics.Color;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class WeightLossGraph extends AppCompatActivity  {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_loss_graph);

        DatabaseHelper myDb= new DatabaseHelper(this);

        lineChart=findViewById(R.id.lineChart);

        List<Entry> valsComp= new ArrayList<Entry>();

        Cursor res= myDb.getGraphWeightData();

        Log.d("output", "Count:"+res.getCount());

int i=0;
       if(res.getCount()!=0){



           while (res.moveToNext()) {

               i=i+1;


                String yvalue= res.getString(3);
                Log.d("output", yvalue);

                String xvalue= res.getString(4);
                Log.d("output1", xvalue);

                Float yvalue1= Float.parseFloat(yvalue);

                //Float xvalue1= Float.parseFloat(i);

                valsComp.add(new Entry(i,yvalue1));

            }
        }


        ArrayList<ILineDataSet> LineDataSets =new ArrayList<>();

        LineDataSet lineDataSet = new LineDataSet(valsComp,"weight");
        lineDataSet.setDrawCircles(true);

        lineDataSet.setColor(Color.BLUE);


        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setFillAlpha(10);
        lineDataSet.setFillColor(Color.BLACK);
        lineDataSet.setDrawCircles(false);


       // lineDataSet.setFillAlpha(110);

        LineDataSets.add(lineDataSet);

        lineChart.setData(new LineData(LineDataSets));
        //lineChart.setVisibleXRangeMaximum(65f);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false);








    }


}
