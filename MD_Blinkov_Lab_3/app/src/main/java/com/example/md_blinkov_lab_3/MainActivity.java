package com.example.md_blinkov_lab_3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements EraseButtonFragment.OnEraseButtonFragmentInteractionListener,
        RadioGroupFragment.OnRadioGroupFragmentInteractionListener,
        UpdateButtonFragment.OnUpdateButtonFragmentInteractionListener, OpenFileButtonFragment.OnOpenFileButtonInteractionListener{
    private String FONT="sans-serif";
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onEraseButtonFragmentInteraction() {
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
            fragment.sendMessage(FONT,this);
        }
    }

    @Override
    public void onOpenFileButtonInteraction() {
        Intent intent = new Intent(this, OpenedFileActivity.class);
        intent.putExtra(EXTRA_MESSAGE, EditTextFragment.getFILE());
        startActivity(intent);
    }
}