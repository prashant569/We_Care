package hw.dispensary;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Admin_all_patient_show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all_patient_show);
        Intent i=getIntent();
        String dname=i.getStringExtra("doctor");
        DatabaseHelper df=new DatabaseHelper(getApplicationContext());
        String all_pat=df.admin_doctor_all_apppoint_show(dname);
        ListView list= (ListView) findViewById(R.id.list_pat_admin);
        String aa[]=all_pat.split("-");
        ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,aa);
        list.setAdapter(ad);
    }
}
