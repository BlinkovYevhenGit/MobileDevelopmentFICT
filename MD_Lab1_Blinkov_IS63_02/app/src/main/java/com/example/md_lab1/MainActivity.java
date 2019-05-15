package com.example.md_lab1;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    public  static  String FONT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup radGrp = findViewById(R.id.radios);
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
        }});
    }
    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        if(message.isEmpty()) return;
        else{
            editText.setText(message);
            editText.setTypeface(Typeface.create(FONT, Typeface.NORMAL));
        }
    }
    public void eraseText(View view){
        EditText editText = findViewById(R.id.edit_message);
        editText.setText("");
    }
}
