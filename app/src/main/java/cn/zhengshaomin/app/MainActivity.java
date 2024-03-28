package cn.zhengshaomin.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import cn.zhengshaomin.library.ZsmTime;

public class MainActivity extends AppCompatActivity {

    private TextView InitialTime,Result;
    private Button choose,time1,time2;
    private String time=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitialTime=findViewById(R.id.InitialTime);
        Result=findViewById(R.id.Result);
        choose=findViewById(R.id.choose);
        time1=findViewById(R.id.time1);
        time2=findViewById(R.id.time2);

        //tv.setText(ZsmTime.day("2024-03-19 23:06:13"));

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseDateTime();
            }
        });

        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result.setText(ZsmTime.day(time));
            }
        });

        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result.setText(ZsmTime.time(time));
            }
        });
    }
    private void ChooseDateTime(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                final int selectedYear = year;
                final int selectedMonth = monthOfYear;
                final int selectedDay = dayOfMonth;

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // 设置选择的日期和时间
                        time = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay + " " + hourOfDay + ":" + minute+":00";
                        InitialTime.setText(time);
                    }
                }, hourOfDay, minute, true);

                timePickerDialog.show();
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }
}