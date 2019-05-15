package com.example.md_blinkov_lab_3;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class EditTextFragment extends Fragment {

    private static final String FILE="textFile.txt";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_view, container, false);
        return  view;
    }
    // обновление текстового поля
    public void sendMessage(String FONT, Context context) {
        String message = getEditText().getText().toString();
        if(message.isEmpty()) return;
        else{
            getEditText().setText(message);
            getEditText().setTypeface(Typeface.create(FONT, Typeface.NORMAL));
            saveMessage(FONT,context);
        }
    }
    public void saveMessage(String Font,Context context){
        Toast.makeText(context, "Повідомлення збережено в файл - " + context.getFileStreamPath(FILE), Toast.LENGTH_SHORT).show();
        FileOutputStream fileOutputStream = null;
        try {
            String message = Font + "~" + getEditText().getText().toString()+"\n";
            fileOutputStream = context.openFileOutput(FILE,Context.MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
        }
        catch (IOException ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fileOutputStream!=null)
                    fileOutputStream.close();
            }
            catch(IOException ex){

                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eraseText(){
        getEditText().setText("");
        getEditText().setTypeface(null);
    }
    public EditText getEditText(){
        return (EditText) getView().findViewById(R.id.edit_message);
    }
    public static String getFILE() {
        return FILE;
    }
}