package com.example.md_blinkov_lab4;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ControlPanel.OnFragmentInteractionListener,ListOfSongs.OnFragmentListOfSongsInteractionListener{
    ControlPanel controlPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMediaPlayer();
    }
    @Override
    public void onFragmentInteraction(List<String> mp3Files) {
        ListOfSongs listOfSongs = (ListOfSongs)getFragmentManager().findFragmentById(R.id.songListFragment);
        if (listOfSongs != null && listOfSongs.isInLayout()) {
            listOfSongs.setContext(this);
            listOfSongs.setMp3Files(mp3Files);
            listOfSongs.createListOfSongs();
        }

    }

    public void createMediaPlayer(){

        controlPanel = (ControlPanel) getFragmentManager().findFragmentById(R.id.controlPanelFragment);
        controlPanel.loadMediaPlayer(this);
    }

    @Override
    public void onSongsFragmentInteraction(int selectedSong) {
        if (controlPanel != null && controlPanel.isInLayout())
        controlPanel.chooseSong(selectedSong);
    }
}