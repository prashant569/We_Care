package hw.dispensary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ACER on 27-06-2017.
 */

public class Appointment_Admin extends Fragment {
    ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.appointments_administrator, container, false);
        list = (ListView) v.findViewById(R.id.admin_list);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHelper ff = new DatabaseHelper(getContext());
        String str = ff.admin_all_apppoint_show();
        final String a[] = str.split("-");

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, a);
        list.setAdapter(ad);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
                String aaa=a[position];
                String b[]=aaa.split(",");
                DatabaseHelper gf=new DatabaseHelper(getContext());
                String all_pat=gf.admin_doctor_all_apppoint_show(b[0]);
                AlertDialog.Builder bd=new AlertDialog.Builder(getContext());
                bd.setMessage(all_pat);
                bd.show();

            }
        });






    }
}
