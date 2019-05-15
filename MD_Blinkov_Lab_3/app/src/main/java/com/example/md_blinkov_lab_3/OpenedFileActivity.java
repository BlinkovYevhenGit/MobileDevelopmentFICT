package com.example.md_blinkov_lab_3;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class OpenedFileActivity extends AppCompatActivity {
private String FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opened_file);
        Intent intent = getIntent();
        FILE = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        outputMessageFromFile();
    }
    private void outputMessageFromFile(){
        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.outputtedText);
        try {
            fin = openFileInput(FILE);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            if(text.isEmpty()){
                Toast.makeText(this, "Дані в файлі відсутні", Toast.LENGTH_SHORT).show();
            }
            String message = text.substring(text.indexOf("~")+1);
            String font = text.replace("~" + message,"");
            textView.setText(message);
            textView.setTypeface(Typeface.create(font, Typeface.NORMAL));
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
