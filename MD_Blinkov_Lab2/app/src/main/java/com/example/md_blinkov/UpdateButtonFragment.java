package com.example.md_blinkov;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class UpdateButtonFragment extends Fragment {
    private OnUpdateButtonFragmentInteractionListener updateButtonListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_button, container, false);
        Button updateButton = (Button) view.findViewById(R.id.update_button);
        // задаем обработчик кнопки
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEditView();
            }
        });
        return view;
    }

    interface OnUpdateButtonFragmentInteractionListener {

        void onUpdateButtonFragmentInteraction();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            updateButtonListener = (OnUpdateButtonFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnUpdateButtonFragmentInteractionListener");
        }
    }
    public void updateEditView() {
        updateButtonListener.onUpdateButtonFragmentInteraction();
    }

}
