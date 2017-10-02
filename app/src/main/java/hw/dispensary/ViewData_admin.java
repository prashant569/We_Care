package hw.dispensary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ACER on 27-06-2017.
 */

public class ViewData_admin extends Fragment {
    Button b1,b2;
    ListView ll;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.view_data_administrator,container,false);
        b1=(Button)v.findViewById(R.id.doc);
        b2=(Button)v.findViewById(R.id.pat);
        ll=(ListView)v.findViewById(R.id.list);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getContext());
                String allDoctor = db.showDoctor();

                //Toast.makeText(getContext(),allDoctor, Toast.LENGTH_SHORT).show();
                String []aa=allDoctor.split("/");
               // String []bb;
                ArrayAdapter <String> ad=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,aa);
                ll.setAdapter(ad);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getContext());
                String allPat = db.showPat();

                //Toast.makeText(getContext(),allDoctor, Toast.LENGTH_SHORT).show();
                String []aa=allPat.split("/");
                // String []bb;
                ArrayAdapter <String> ad=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,aa);
                ll.setAdapter(ad);

            }
        });

    }
}
