package hw.dispensary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.lang.UCharacter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.UncheckedIOException;

import hw.dispensary.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String arr1[]={"---select---","Admin","Doctor","Patient"};
        setContentView(R.layout.activity_main);
        final EditText et1=(EditText)findViewById(R.id.et_u);
        final EditText et2=(EditText)findViewById(R.id.et_p);
//        et2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if(count<8)
//                    et2.setError("min 8 characters");
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
        //final EditText et2=(EditText)findViewById(R.id.et_p);
        CheckBox c=(CheckBox)findViewById(R.id.cb);
        try{
            c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(!(isChecked))
                        et2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    if(isChecked)
                        et2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            });
        }
        catch(UncheckedIOException e){}

        //spinner
        ArrayAdapter<String> ad1=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,arr1);
        final Spinner sp=(Spinner)findViewById(R.id.sp);
        sp.setAdapter(ad1);
        final Button b1=(Button)findViewById(R.id.b1);
        final Button b2=(Button)findViewById(R.id.b2);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,final int position, long id) {
                if(position==1 || position==2)
                    b2.setVisibility(view.INVISIBLE);
                else if(position==0 || position==3)
                    b2.setVisibility(view.VISIBLE);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            String nname,ppass;
                          nname=et1.getText().toString();
                          ppass=et2.getText().toString();
                            if (position == 1) {
                                Intent I1 = new Intent(MainActivity.this, Administrator.class);
                                startActivity(I1);
                            }
                            if (position == 2) {
                                Intent I2 = new Intent(MainActivity.this, Doctor.class);
                                DatabaseHelper dbb=new DatabaseHelper(getApplicationContext());
                                boolean check=dbb.doctor_login_check(nname,ppass);
                                if (check) {
                                    SharedPreferences sp=getSharedPreferences("doc",MODE_PRIVATE);
                                    SharedPreferences.Editor ed=sp.edit();
                                    ed.putString("doctor_session_name",nname);
                                    ed.commit();
                                    startActivity(I2);
                                }
                                else
                                    Toast.makeText(MainActivity.this, "username and password invalid", Toast.LENGTH_SHORT).show();
                            }
                            if (position == 3) {
                                Intent I3 = new Intent(MainActivity.this, Patient.class);
                                DatabaseHelper dbb=new DatabaseHelper(getApplicationContext());
                                boolean check=dbb.patient_login_check(nname,ppass);
                                if (check) {
                                    SharedPreferences sp=getSharedPreferences("pat",MODE_PRIVATE);
                                    SharedPreferences.Editor ed=sp.edit();
                                    ed.putString("patient_session_name",nname);
                                    ed.commit();
                                    startActivity(I3);
                                }
                                else
                                    Toast.makeText(MainActivity.this, "username and password invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                                 @Override
//                                                 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                                     if(position==1)
//                                                     {
//                                                         Intent I1=new Intent(MainActivity.this,Administrator.class);
//                                                         startActivity(I1);
//                                                     }
//                                                     if(position==2)
//                                                     {
//                                                         Intent I2=new Intent(MainActivity.this,Doctor.class);
//                                                         startActivity(I2);
//                                                     }
//                                                     if(position==3)
//                                                     {
//                                                         Intent I3=new Intent(MainActivity.this,Patient.class);
//                                                         startActivity(I3);
//                                                     }
//                                                 }
//
//                                                 @Override
//                                                 public void onNothingSelected(AdapterView<?> parent) {
//
//                                                 }
//                                             }
//                );
//            }
//        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,PatientRegistration.class);
                startActivity(i);
            }
        });
    }

        //option menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.a1) {
            Toast.makeText(MainActivity.this, "About us is clicked", Toast.LENGTH_SHORT).show();


        }
        if (item.getItemId() == R.id.a2) {
            Toast.makeText(MainActivity.this, "Rate is clicked", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder ab=new AlertDialog.Builder(this);
            ab.setTitle("Rate the app");
            ab.setView(R.layout.rating);
            ab.setPositiveButton("SUBMIT",null);
            ab.show();
        }
        if (item.getItemId() == R.id.a3) {
            Toast.makeText(MainActivity.this, "Contact us is clicked", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.a4) {
            Toast.makeText(MainActivity.this, "Query is clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}



