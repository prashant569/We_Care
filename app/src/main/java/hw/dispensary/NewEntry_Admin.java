package hw.dispensary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by ACER on 27-06-2017.
 */

public class NewEntry_Admin extends Fragment{
EditText et1 ,et2,et3,et4,et5,et6;
    Button submit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v1=inflater.inflate(R.layout.new_entry_administrator,container,false);
       et1=(EditText)v1.findViewById(R.id.d_name);
        et2=(EditText)v1.findViewById(R.id.d_spec);
        et3=(EditText)v1.findViewById(R.id.d_mail);
        et4=(EditText)v1.findViewById(R.id.d_exp);
        et5=(EditText)v1.findViewById(R.id.d_pin);
        et6=(EditText)v1.findViewById(R.id.d_state);
        submit=(Button)v1.findViewById(R.id.d_submit);
        return v1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=et1.getText().toString();
                final String spec=et2.getText().toString();
                final String email=et3.getText().toString();
                final String exp=et4.getText().toString();
                final String pin=et5.getText().toString();
                final String state=et6.getText().toString();
                DatabaseHelper db=new DatabaseHelper(getContext());
                boolean check=  db.addDoctor(name,spec,email,exp,state,pin);

                if(check) {
                    Toast.makeText(getContext(),"You Are Sccessfully Registered", Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(getContext(),NewEntry_Admin.class));
                }
                else
                    Toast.makeText(getContext(), "data insertion failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
