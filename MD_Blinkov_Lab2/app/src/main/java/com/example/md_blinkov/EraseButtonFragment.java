package com.example.md_blinkov;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EraseButtonFragment extends Fragment {
    private OnEraseButtonFragmentInteractionListener eraseListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_erase_button, container, false);
        Button eraseButton = (Button) view.findViewById(R.id.erase_button);
        // задаем обработчик кнопки
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eraseText();
            }
        });
        return view;
    }

    public void eraseText(){

        eraseListener.onEraseButtonFragmentInteraction("");
    }

    interface OnEraseButtonFragmentInteractionListener {

        void onEraseButtonFragmentInteraction(String link);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            eraseListener = (OnEraseButtonFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnEraseButtonFragmentInteractionListener");
        }
    }
}
