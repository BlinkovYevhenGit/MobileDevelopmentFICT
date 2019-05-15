package com.example.md_blinkov;


import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditTextFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_view, container, false);
        return  view;
    }
    // обновление текстового поля
    public void sendMessage(String FONT) {
        String message = getEditText().getText().toString();
        if(message.isEmpty()) return;        else{
            getEditText().setText(message);
            getEditText().setTypeface(Typeface.create(FONT, Typeface.NORMAL));
        }
    }
    public void eraseText(){
        getEditText().setText("");
        getEditText().setTypeface(null);
    }
    public EditText getEditText(){
        return (EditText) getView().findViewById(R.id.edit_message);
    }
}
