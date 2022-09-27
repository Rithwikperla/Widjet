package com.radefffactory.mywidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyWidget extends AppWidgetProvider {

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        List<List<String>> listOfLists = new ArrayList<>();
        List<String> i1 = new ArrayList<>();

        // Adding elements to innerList
        i1.add("Data Structures");
        i1.add("9:45 - 10:45");
        i1.add("Sudrsan P");

        // Adding innerList to listOfLists
        listOfLists.add(i1);

        List<String> i2 = new ArrayList<>();

        // Adding elements to innerList
        i2.add("Data Warehouse");
        i2.add("10:45 - 11:45");
        i2.add("Sudarsanam P");

        // Adding innerList to listOfLists
        listOfLists.add(i2);


        List<String> i3 = new ArrayList<>();

        // Adding elements to innerList
        i3.add("Data Mining");
        i3.add("12:45 - 1:45");
        i3.add("Audarsan P");

        // Adding innerList to listOfLists
        listOfLists.add(i3);

        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        //get the widget value
        SharedPreferences preferences = context.getSharedPreferences("PREFS", 0);
        int value = preferences.getInt("value", 0);

        Log.e("Value: ",value+"");

        //set the value in the textview
        if(value < 3){
            views.setTextViewText(R.id.subject, "" + listOfLists.get(value).get(0));
            views.setTextViewText(R.id.time, "" + currentTime);
            views.setTextViewText(R.id.lecturer, "" + listOfLists.get(value).get(2));
        }else{
            value=0;
        }


        //update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        //reschedule the widget refresh
        AlarmHandler alarmHandler = new AlarmHandler(context);
        alarmHandler.cancelAlarmManager();
        alarmHandler.setAlarmManager();

        Log.d("WIDGET", "Widget updated!");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId: appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDisabled(Context context) {
        //stop updating the widget
        AlarmHandler alarmHandler = new AlarmHandler(context);
        alarmHandler.cancelAlarmManager();

        Log.d("WIDGET", "Widget removed!");
    }
}