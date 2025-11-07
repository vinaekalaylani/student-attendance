package com.vinaekal.absensimahasiswa;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    TextView textGreeting, textDate, textClockInTime, textClockOutTime;
    Button buttonClockIn, buttonClockOut;

    private String clockInTime = "-", clockOutTime = "-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Log lifecycle
        Log.d("Lifecycle", "onCreate: DashboardActivity");

        textGreeting = findViewById(R.id.textGreeting);
        textDate = findViewById(R.id.textDate);
        textClockInTime = findViewById(R.id.textClockInTime);
        textClockOutTime = findViewById(R.id.textClockOutTime);
        buttonClockIn = findViewById(R.id.buttonClockIn);
        buttonClockOut = findViewById(R.id.buttonClockOut);

        updateGreeting();
        updateDateTime();

        buttonClockIn.setOnClickListener(v -> {
            clockInTime = getCurrentTime();
            textClockInTime.setText("Clock In: " + clockInTime);
            Toast.makeText(this, "Clocked In at " + clockInTime, Toast.LENGTH_SHORT).show();
        });

        buttonClockOut.setOnClickListener(v -> {
            clockOutTime = getCurrentTime();
            textClockOutTime.setText("Clock Out: " + clockOutTime);
            Toast.makeText(this, "Clocked Out at " + clockOutTime, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "onStart: DashboardActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume: DashboardActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause: DashboardActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "onStop: DashboardActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy: DashboardActivity");
    }

    private void updateGreeting() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String greeting;
        if (hour < 12) {
            greeting = "Good Morning,";
        } else if (hour < 18) {
            greeting = "Good Afternoon,";
        } else {
            greeting = "Good Evening,";
        }
        textGreeting.setText(greeting);
    }

    private void updateDateTime() {
        String date = new SimpleDateFormat("dd MMMM yyyy | hh:mm a", Locale.getDefault()).format(Calendar.getInstance().getTime());
        textDate.setText(date);
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
    }
}