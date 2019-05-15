package com.example.md_blinkov_lab_3;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


public class RadioGroupFragment extends Fragment {

    private static String FONT = "sans-serif";

    private OnRadioGroupFragmentInteractionListener radioGroupListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radio_group, container, false);
        RadioGroup radGrp = view.findViewById(R.id.radios);
        radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch(id) {
                    case R.id.monospace:
                        FONT="monospace";
                        break;
                    case R.id.serif:
                        FONT="serif";
                        break;
                    case R.id.serif_monospace:
                        FONT="serif-monospace";
                        break;
                    case R.id.sans_serif:
                        FONT="sans-serif";
                        break;
                    case R.id.sans_serif_condensed:
                        FONT="sans-serif-condensed";
                        break;
                    case R.id.sans_serif_smallcaps:
                        FONT="sans-serif-smallcaps";
                        break;
                    case R.id.sans_serif_light:
                        FONT="sans-serif-light";
                        break;
                    case R.id.casual:
                        FONT="casual";
                        break;
                    case R.id.cursive:
                        FONT="cursive";
                        break;
                    default:
                        break;
                }
                updateFont();
            }});
        return view;
    }
    interface OnRadioGroupFragmentInteractionListener {

        void onRadioGroupFragmentInteraction(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            radioGroupListener = (OnRadioGroupFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnRadioGroupFragmentInteractionListener");
        }
    }

    public void updateFont() {
        // Посылаем данные Activity
        radioGroupListener.onRadioGroupFragmentInteraction(FONT);
    }
}