package kr.co.tjeit.calendartestapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.melnykov.fab.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private com.applandeo.materialcalendarview.CalendarView calendarView;
    private com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView1;
    private android.widget.ListView list;
    private FloatingActionButton fab;
    ArrayAdapter mAdapter;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void addDecorator() {
        CalendarDay date = CalendarDay.today();
        ArrayList<CalendarDay> dates = new ArrayList<>();
        dates.add(date);
        calendarView1.addDecorator(new EventDecorator(Color.RED, dates));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Toast.makeText(MainActivity.this, "결과가 성공이 아님.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == 1) {
            addDecorator();
        } else {
            Toast.makeText(MainActivity.this, "REQUEST_ACT가 아님", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, GlobalData.test);
        list.setAdapter(mAdapter);
    }

    public class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(5, color));
        }
    }
}
