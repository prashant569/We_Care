package hw.dispensary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientRegistration extends AppCompatActivity {
EditText et1,et2,et3,et4,et5,et6;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        et1=(EditText)findViewById(R.id.rpname);
        et2=(EditText)findViewById(R.id.rpgender);
        et3=(EditText)findViewById(R.id.rpemail);
        et4=(EditText)findViewById(R.id.rpstate);
        et5=(EditText)findViewById(R.id.rppincode);
        et6=(EditText)findViewById(R.id.rpage);
        submit=(Button)findViewById(R.id.rpsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et1.getText().toString();
                String gender=et2.getText().toString();
                String email=et3.getText().toString();
                String state=et4.getText().toString();
                String pincode=et5.getText().toString();
                String age=et6.getText().toString();
                DatabaseHelper db=new DatabaseHelper(getApplicationContext());
                boolean check=db.patient_registration(name,gender,email,state,pincode,age);
                if (check)
                    Toast.makeText(PatientRegistration.this, "patient successfully registered", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PatientRegistration.this, "patient not registered", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
