package sg.edu.rp.c346.id20000892.p13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity2 extends AppCompatActivity {
    Button btnAdd,btnCancel;
    DatePicker datePicker;
    android.widget.TimePicker timePicker;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        name = findViewById(R.id.name);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = name.getText().toString();
                String date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();
                String time = String.format("%02d:%02d", timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                DBHelper dbh = new DBHelper(MainActivity2.this);
                dbh.insertSch(title, time, date);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}