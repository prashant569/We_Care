package hw.dispensary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ACER on 28-06-2017.
 */

public class Check_Appointments_patient extends Fragment {
    TextView chk;
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.check_appntmnts_patient,container,false);
        chk= (TextView) view.findViewById(R.id.appoint_show);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHelper dh=new DatabaseHelper(getContext());
        sp=getActivity().getSharedPreferences("pat", Context.MODE_PRIVATE);
       String email1= sp.getString("patient_session_name",null);
        String str=dh.appoint_show(email1);
        chk.setText(str);
    }
}
