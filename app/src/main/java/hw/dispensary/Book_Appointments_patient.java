package hw.dispensary;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by ACER on 28-06-2017.
 */

public class Book_Appointments_patient extends Fragment {
    EditText name,date;
    Spinner doctorwala,departwala;
    Button book;
    SharedPreferences sp;
    String []bb;
    String alldoc;

    int pos1,pos2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View v1=inflater.inflate(R.layout.book_apointments_patient,container,false);
        date=(EditText)v1.findViewById(R.id.bookdate);
        name=(EditText)v1.findViewById(R.id.bookpatname);
        doctorwala=(Spinner)v1.findViewById(R.id.bookspdoc);
        departwala=(Spinner)v1.findViewById(R.id.book_depart);
        book=(Button)v1.findViewById(R.id.bookbutton) ;
        Button bap_cal=(Button)v1.findViewById(R.id.bap_cal);
         sp=getActivity().getSharedPreferences("pat", Context.MODE_PRIVATE);

        bap_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int dd=c.get(Calendar.DAY_OF_MONTH);
                int mm=c.get(Calendar.MONTH);
                int yy=c.get(Calendar.YEAR);
                DatePickerDialog dp=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date1=dayOfMonth+"/"+month+"/"+year;
                        date.setText(date1);
                    }
                }, yy, mm, dd);
                dp.show();
            }
        });
        return v1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final DatabaseHelper dh=new DatabaseHelper(getContext());
        String alldepart=dh.department_in_spinner();
        final String []aa=alldepart.split("-");

        //Toast.makeText(getContext(), ""+pos2, Toast.LENGTH_SHORT).show();




        String str=sp.getString("patient_session_name",null);
        ArrayAdapter<String> ad1=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,aa);
        departwala.setAdapter(ad1);


        departwala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                alldoc=dh.doctor_in_spinner(aa[position]);
                bb=alldoc.split("-");
                ArrayAdapter<String> ad2=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,bb);
                doctorwala.setAdapter(ad2);

                doctorwala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        pos2=position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1=name.getText().toString();
                String date1=date.getText().toString();
                String depar=aa[pos1];
                String doct=bb[pos2];
                String email11=sp.getString("patient_session_name",null);
                DatabaseHelper dh=new DatabaseHelper(getContext());
                boolean check=dh.check_appointment_if_exist(name1,depar,doct,date1,email11);
                if(check)
                {
                    Toast.makeText(getContext(), "your appointment done", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(),Patient.class));
                }
                else
                {
                    Toast.makeText(getContext(), "Doctor not available for this date", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
