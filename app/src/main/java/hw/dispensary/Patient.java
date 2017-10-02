package hw.dispensary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Patient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        SharedPreferences sp=getSharedPreferences("pat", Context.MODE_PRIVATE);
        String str=sp.getString("patient_session_name",null);
        //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        Button  b_editD=(Button)findViewById(R.id.btn_ed);
        b_editD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.ll_pat,new Edit_data_patient(),"ED");
                ft.commit();
            }
        });

        Button  b_chkapp=(Button)findViewById(R.id.btn_ca);
        b_chkapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.ll_pat,new Check_Appointments_patient(),"CA");
                ft.commit();
            }
        });

        Button  b_bookapp=(Button)findViewById(R.id.btn_ba);
        b_bookapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.ll_pat,new Book_Appointments_patient(),"BA");
                ft.commit();
            }
        });

        Button  b_gentips=(Button)findViewById(R.id.btn_gt);
        b_gentips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.ll_pat,new GeneralTips_patient(),"GT");
                ft.commit();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences sp=getSharedPreferences("pat",MODE_PRIVATE);
            SharedPreferences.Editor ed=sp.edit();
            ed.clear();
            ed.commit();
            startActivity(new Intent(Patient.this,MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
