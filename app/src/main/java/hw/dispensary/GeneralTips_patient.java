package hw.dispensary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ACER on 28-06-2017.
 */

public class GeneralTips_patient extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v1=inflater.inflate(R.layout.generaltips_patient,container,false);
        return v1;
    }
}
