package hw.dispensary;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Calendar;

public class ViewAppointmentDoc extends Fragment {
    public ViewAppointmentDoc() {
        // Required empty public constructor
    }
    ListView list;
    SharedPreferences sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1=inflater.inflate(R.layout.fragment_view_appointment_doc,container,false);
         list= (ListView) v1.findViewById(R.id.view_appoint_doctor);
        sp=getActivity().getSharedPreferences("doc", Context.MODE_PRIVATE);
        return v1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHelper dh=new DatabaseHelper(getContext());
        String email1=sp.getString("doctor_session_name",null);
        String pp=dh.doct_appoint_show(email1);
        String []aa=pp.split("-");
        ArrayAdapter<String > ad=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,aa);
        list.setAdapter(ad);
    }
}
