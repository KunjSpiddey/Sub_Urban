package com.example.suburban;

import static com.example.suburban.R.array.india_states;
import static com.example.suburban.R.array.default_City;

import android.os.Bundle;

import androidx.annotation.ContentView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.suburban.databinding.FragmentFSellerSellerInfoBinding;

public class FSeller_Seller_info extends Fragment {
    String Selected_state , Selected_city;
    ArrayAdapter<CharSequence> city_adapter;
    public FSeller_Seller_info() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_f_seller__seller_info, container, false);
       Button button = (Button)view.findViewById(R.id.seller_info_next);
       Spinner city = (Spinner)view.findViewById(R.id.city);
       Spinner state = (Spinner)view.findViewById(R.id.state);


String [] states_india = getResources().getStringArray(india_states);
String [] default_city = getResources().getStringArray(default_City);
String [] andrapradesh = getResources().getStringArray(R.array.andhra_pradesh_cities);
String [] arunachal = getResources().getStringArray(R.array.arunachal_pradesh_cities);
String [] assam = getResources().getStringArray(R.array.assam_cities);
String [] bihar = getResources().getStringArray(R.array.bihar_cities);
String [] chattishgarh = getResources().getStringArray(R.array.chhattisgarh_cities);
String [] goa = getResources().getStringArray(R.array.goa_cities);
String [] gujarat = getResources().getStringArray(R.array.gujarat_cities);
String [] haryana = getResources().getStringArray(R.array.haryana_cities);
String [] himachalpradesh = getResources().getStringArray(R.array.himachal_pradesh_cities);
String [] jharkhand = getResources().getStringArray(R.array.jharkhand_cities);
String [] karnataka = getResources().getStringArray(R.array.karnataka_cities);
String [] kerela = getResources().getStringArray(R.array.kerala_cities);
String [] madhyapradesh = getResources().getStringArray(R.array.madhya_pradesh_cities);
String [] maharastra = getResources().getStringArray(R.array.maharashtra_cities);
String [] manipur = getResources().getStringArray(R.array.manipur_cities);
String [] meghalaya = getResources().getStringArray(R.array.meghalaya_cities);
String [] mizoram = getResources().getStringArray(R.array.mizoram_cities);
String [] nagaland = getResources().getStringArray(R.array.nagaland_cities);
String [] odisha = getResources().getStringArray(R.array.odisha_cities);
String [] punjab = getResources().getStringArray(R.array.punjab_cities);
String [] rajashthan = getResources().getStringArray(R.array.rajasthan_cities);
String [] sikkim = getResources().getStringArray(R.array.sikkim_cities);
String [] taminadu = getResources().getStringArray(R.array.tamil_nadu_cities);
String [] tripura = getResources().getStringArray(R.array.tripura_cities);
String [] telanga = getResources().getStringArray(R.array.telangana_cities);
String [] uttarpradesh = getResources().getStringArray(R.array.uttar_pradesh_cities);
String [] uttrakhand = getResources().getStringArray(R.array.uttarakhand_cities);
String [] westbengal = getResources().getStringArray(R.array.west_bengal_cities);







        ArrayAdapter<CharSequence> state_adapter = new ArrayAdapter(getContext(), androidx.transition.R.layout.support_simple_spinner_dropdown_item,states_india);
//                , city_adapter;
//                state_adapter = ArrayAdapter.createFromResource(getContext(),R.array.india_states,R.layout.custom_spinner,R.array.india_states);
                state.setAdapter(state_adapter);

            state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Selected_state = state.getSelectedItem().toString();

                    int parentid = adapterView.getId();
                    if (parentid == R.id.state){

                        switch(Selected_state){
                            case "Select Your State":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,default_city);
                            break;
                            case "Andhra Pradesh":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,andrapradesh);
                                break;

                            case "Arunachal Pradesh":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,arunachal);
                                break;
                            case "Assam":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,assam);
                                break;
                            case "Bihar":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,bihar);
                                break;
                            case "Chhattisgarh":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,chattishgarh);
                                break;
                            case "Goa":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,goa);
                                break;
                            case "Gujarat":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,gujarat);
                                break;
                            case "Haryana":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,haryana);
                                break;
                            case "Himachal Pradesh":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,himachalpradesh);
                                break;
                            case "Jharkhand":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,jharkhand);
                                break;
                            case "Karnataka":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,karnataka);
                                break;
                            case "Kerala":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,kerela);
                                break;
                            case "Madhya Pradesh":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,madhyapradesh);
                                break;
                            case "Maharashtra":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,maharastra);
                                break;
                            case "Manipur":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,manipur);
                                break;
                            case "Meghalaya":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,meghalaya);
                                break;
                            case "Mizoram":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,mizoram);
                                break;
                            case "Nagaland":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,nagaland);
                                break;
                            case "Odisha":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,odisha);
                                break;
                            case "Punjab":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,punjab);
                                break;
                            case "Rajasthan":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,rajashthan);
                                break;
                            case "Sikkim":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,sikkim);
                                break;
                            case "Tamil Nadu":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,taminadu);
                                break;
                            case "Telangana":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,telanga);
                                break;
                            case "Tripura":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,tripura);
                                break;
                            case "Uttar Pradesh":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,uttarpradesh);
                                break;
                            case "Uttarakhand":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,uttrakhand);
                                break;
                            case "West Bengal":
                                city_adapter = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,westbengal);
                                break;

                            default:break;
                        }
                        city_adapter.setDropDownViewResource(com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item);
                            city.setAdapter(city_adapter);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           fl(new FSeller_ProductDetails(),1);
            }
        });



        return view;
    }

    private void fl(Fragment fragment, int flag) {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.seller_container, fragment);
        ft.replace(R.id.seller_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}