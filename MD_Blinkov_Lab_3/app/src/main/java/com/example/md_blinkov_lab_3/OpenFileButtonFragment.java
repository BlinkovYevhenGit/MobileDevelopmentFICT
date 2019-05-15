package com.example.md_blinkov_lab_3;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class OpenFileButtonFragment extends Fragment {
    private OnOpenFileButtonInteractionListener openFileButtonInteractionListener;

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view = inflater.inflate(R.layout.fragment_open_file_button, container, false);
          Button openFile = (Button) view.findViewById(R.id.open_button);
          // задаем обработчик кнопки
          openFile.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  openFile();
              }
          });
          return view;
    }
    interface OnOpenFileButtonInteractionListener {

        void onOpenFileButtonInteraction();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            openFileButtonInteractionListener = (OnOpenFileButtonInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnUpdateButtonFragmentInteractionListener");
        }
    }
    public void openFile() {
        openFileButtonInteractionListener.onOpenFileButtonInteraction();
    }
}
