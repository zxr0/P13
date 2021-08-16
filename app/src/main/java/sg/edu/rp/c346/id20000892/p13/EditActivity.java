package sg.edu.rp.c346.id20000892.p13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


public class EditActivity extends AppCompatActivity {
    sch data;
    EditText name2;
    DatePicker datePicker2;
    TimePicker timePicker2;
    Button btnUp, btnCancel2, btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name2 = findViewById(R.id.name2);
        datePicker2 = findViewById(R.id.datePicker2);
        timePicker2 = findViewById(R.id.timePicker2);
        btnUp = findViewById(R.id.btnUpdate);
        btnCancel2 = findViewById(R.id.btnCancel2);
        btnDel = findViewById(R.id.btnDel);
        datePicker2.setMinDate(System.currentTimeMillis() - 1000);

        Intent i = getIntent();
        data = (sch) i.getSerializableExtra("data");

        name2.setText(data.getName());


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setName(name2.getText().toString());
                data.setDate(datePicker2.getDayOfMonth() + "/" + datePicker2.getMonth() + "/" + datePicker2.getYear());
                String timeformat = String.format("%02d:%02d", timePicker2.getCurrentHour(), timePicker2.getCurrentMinute());
                data.setTime(timeformat);
                dbh.updateSch(data);
                dbh.close();
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setMessage("Are you sure you want to delete: " + data.getName() + "?");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditActivity.this);
                        dbh.del(data.getId());
                        finish();
                    }
                });
                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        finish();
                    }
                });
    }

}