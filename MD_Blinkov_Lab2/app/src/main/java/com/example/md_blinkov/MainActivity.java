package com.example.md_blinkov;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements EraseButtonFragment.OnEraseButtonFragmentInteractionListener,
        RadioGroupFragment.OnRadioGroupFragmentInteractionListener,
        UpdateButtonFragment.OnUpdateButtonFragmentInteractionListener{
    private String FONT="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onEraseButtonFragmentInteraction(String link) {
        EditTextFragment fragment =(EditTextFragment) getFragmentManager().findFragmentById(R.id.editTextFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.eraseText();

        }
    }
    @Override
    public void onRadioGroupFragmentInteraction(String link){
        FONT = link;
    }
    @Override
    public void onUpdateButtonFragmentInteraction(){
        EditTextFragment fragment = (EditTextFragment) getFragmentManager().findFragmentById(R.id.editTextFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.sendMessage(FONT);
        }
    }

}
/**/


