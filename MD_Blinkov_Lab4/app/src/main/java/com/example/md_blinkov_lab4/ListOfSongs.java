package com.example.md_blinkov_lab4;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class ListOfSongs extends Fragment {
    private String[] mp3Files;
    private Context context;
    public int selectedSong;
    private OnFragmentListOfSongsInteractionListener mSongsListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_of_songs, container, false);
        sendSelectedSong();
        return view;
    }

    public void setMp3Files(List<String> mp3Files) {

        this.mp3Files = mp3Files.toArray(new  String[0]);
    }
    interface OnFragmentListOfSongsInteractionListener {

        void onSongsFragmentInteraction(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mSongsListener = (OnFragmentListOfSongsInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public void createListOfSongs(){
        ListView songList = getActivity().findViewById(R.id.songList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, mp3Files);
        songList.setAdapter(arrayAdapter);
        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSong=position;
                sendSelectedSong();
            }
        });
    }

    public void sendSelectedSong(){
        mSongsListener.onSongsFragmentInteraction(selectedSong);
    }
}
