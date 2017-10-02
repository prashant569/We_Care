package hw.dispensary;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ACER on 28-06-2017.
 */

public class Edit_data_patient extends Fragment {
    EditText et1,et2,et4,et5,et6;
    TextView et3;
    Button update;

   SharedPreferences  sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v1=inflater.inflate(R.layout.edit_data_patient,container,false);
        et1=(EditText)v1.findViewById(R.id.pedname);
        et2=(EditText)v1.findViewById(R.id.pedgender);
        et3=(TextView)v1.findViewById(R.id.pedemail);
        et4=(EditText)v1.findViewById(R.id.pedstate);
        et5=(EditText)v1.findViewById(R.id.pedpincode);
        et6=(EditText)v1.findViewById(R.id.pedage);
        update=(Button)v1.findViewById(R.id.pedupdate);
        sp=getActivity().getSharedPreferences("pat", Context.MODE_PRIVATE);
        return v1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final String eeeemail=sp.getString("patient_session_name",null);
        DatabaseHelper db=new DatabaseHelper(getContext());
        String alldata=db.patient_Edit_data_get(eeeemail);
        String aa[]=alldata.split("-");
        et1.setText(aa[0]);
        et2.setText(aa[1]);
        et3.setText(aa[2]);
        et4.setText(aa[3]);
        et5.setText(aa[4]);
        et6.setText(aa[5]);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=et1.getText().toString();
                String gender=et2.getText().toString();
                String state=et4.getText().toString();
                String pincode=et5.getText().toString();
                String age=et6.getText().toString();

                DatabaseHelper dh=new DatabaseHelper(getContext());
                boolean check=dh.patient_edit_data(name,gender,eeeemail,state,pincode,age);
                if (check)
                {
                    startActivity(new Intent(getContext(),Patient.class));
                }
                else
                {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
