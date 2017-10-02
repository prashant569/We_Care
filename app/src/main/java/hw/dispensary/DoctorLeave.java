package hw.dispensary;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorLeave extends Fragment {


    public DoctorLeave() {
        // Required empty public constructor
    }

   Button leave,leave_cal;
    EditText leavedate;
    SharedPreferences sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doctor_leave, container, false);
        leave= (Button) view.findViewById(R.id.doc_leave_btn);
        leave_cal= (Button) view.findViewById(R.id.leave_cal);
        leavedate= (EditText) view.findViewById(R.id.get_leave);
        sp=getActivity().getSharedPreferences("doc", Context.MODE_PRIVATE);
        leave_cal.setOnClickListener(new View.OnClickListener() {
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
                        leavedate.setText(date1);
                    }
                }, yy, mm, dd);
                dp.show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date1=leavedate.getText().toString();
                String email1=sp.getString("doctor_session_name",null);
                DatabaseHelper dh=new DatabaseHelper(getContext());
                boolean chk=dh.doctor_leave(email1,date1);
                if(chk)
                {
                    Toast.makeText(getContext(), "leave done", Toast.LENGTH_SHORT).show();
                    //\startActivity(new Intent(getContext(),DoctorLeave.class));
                }
                else
                {
                    Toast.makeText(getContext(), "leave not done", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
