package sg.edu.rp.c346.id20000892.p13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add, remove;
    TextView tv;
    ListView lv;
    ArrayList<sch> al;
    ArrayAdapter<sch> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        tv = findViewById(R.id.tv);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        al.clear();
        DBHelper dbh = new DBHelper(MainActivity.this);
        al.addAll(dbh.getSch());
        aa.notifyDataSetChanged();

        if (al.isEmpty()){
            tv.setText("List is empty");
        }
        else{
            tv.setText("");
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        MainActivity2.class);
                startActivity(i);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                sch target = al.get(position);
                Intent i = new Intent(MainActivity.this,
                        EditActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.delall();
                al.clear();
                al.addAll(dbh.getSch());
                aa.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        al.clear();
        DBHelper dbh = new DBHelper(MainActivity.this);
        al.addAll(dbh.getSch());
        aa.notifyDataSetChanged();

        if (al.isEmpty()){
            tv.setText("List is empty");
        }
        else{
            tv.setText("");
        }
    }
}
