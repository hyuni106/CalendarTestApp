package kr.co.tjeit.calendartestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.melnykov.fab.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private com.applandeo.materialcalendarview.CalendarView calendarView;
    private com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView1;
    private android.widget.ListView list;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.list = (ListView) findViewById(android.R.id.list);
        this.calendarView1 = (MaterialCalendarView) findViewById(R.id.calendarView1);
        this.calendarView = (CalendarView) findViewById(R.id.calendarView);

        final List<EventDay> events = new ArrayList<>();

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                events.add(new EventDay(clickedDayCalendar, R.drawable.heart));
                events.add(new EventDay(clickedDayCalendar, R.drawable.rating_star));
                calendarView.setEvents(events);
            }
        });

        fab.attachToListView(list);
    }
}
