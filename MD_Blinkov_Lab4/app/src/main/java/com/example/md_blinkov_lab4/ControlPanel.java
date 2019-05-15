package com.example.md_blinkov_lab4;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ControlPanel extends Fragment {
    private int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 1;

    final String PATH_TO_DIRECTORY= Environment.getExternalStorageDirectory().getPath()+"/Music/Моя музика/Завантажене";
    List<String> mp3Files;
    private  final File startDirectory = new File(PATH_TO_DIRECTORY);
    MediaPlayer mPlayer = new MediaPlayer();
    Button previousButton;
    Button startButton;
    Button pauseButton;
    Button stopButton;
    Button nextButton;
    boolean isClickedPlayButton=false;
    SeekBar volumeControl;
    SeekBar positionControl;
    AudioManager audioManager;
    int curPosition = mPlayer.getCurrentPosition();
    int currentSong = 0;
    TextView currentSongTextView;
    Context context;
    View view;



    private Handler mSeekbarUpdateHandler = new Handler();
    private Runnable mUpdateSeekbar = new Runnable() {
        @Override
        public void run() {
            positionControl.setProgress(mPlayer.getCurrentPosition());
            mSeekbarUpdateHandler.postDelayed(this, 0);

        }
    };
    private OnFragmentInteractionListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_panel, container, false);
        this.view = view;
        setElements();
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous();
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
                isClickedPlayButton=true;
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
        return view;
    }

    interface OnFragmentInteractionListener {

        void onFragmentInteraction(List<String> mp3Files);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
    private void sendMP3Files(){
        mListener.onFragmentInteraction(mp3Files);
    }
    @TargetApi(Build.VERSION_CODES.M)
    public void loadMediaPlayer(Context context) {
        this.context = context;
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        }
        getListOfFiles();
        sendMP3Files();

        if(mp3Files==null){
            Toast.makeText(context, "Список аудіофайлів порожній", Toast.LENGTH_SHORT).show();
        }
        else{
            setCurrentSong();
        }

    }
    private void setCurrentSong() {
        try {

            mPlayer.setDataSource(PATH_TO_DIRECTORY + "/"+mp3Files.get(currentSong));
            mPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                    stopPlay();
                    if(currentSong+1 !=mp3Files.size()&isClickedPlayButton) next();
            }
        });

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int lengthValue = mPlayer.getDuration();


        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curValue);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        positionControl.setMax(lengthValue);
        positionControl.setProgress(curPosition);
        positionControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(fromUser)
                    mPlayer.seekTo(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        if(currentSong==0) previousButton.setEnabled(false);
        else previousButton.setEnabled(true);
        if(currentSong+1 == mp3Files.size()) nextButton.setEnabled(false);
        else nextButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        currentSongTextView.setText(mp3Files.get(currentSong));
    }



    private void setElements() {
        previousButton = view.findViewById(R.id.previous);
        startButton = view.findViewById(R.id.start);
        pauseButton = view.findViewById(R.id.pause);
        stopButton = view.findViewById(R.id.stop);
        nextButton = view.findViewById(R.id.next);
        volumeControl = view.findViewById(R.id.volumeControl);
        positionControl = view.findViewById(R.id.positionControl);
        currentSongTextView = view.findViewById(R.id.currentSong);

    }


    private void stopPlay(){
        mPlayer.stop();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            startButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void previous(){
        stopPlay();
        mPlayer = new MediaPlayer();
        currentSong--;
        setCurrentSong();
        play();


    }
    public void play(){
        mPlayer.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
        mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);


    }
    public void pause(){

        mPlayer.pause();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
        mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
    }

    public void stop(){
        stopPlay();

    }
    public void next(){
        stopPlay();
        mPlayer = new MediaPlayer();
        currentSong++;
        setCurrentSong();
        play();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
        }
    }
    public List<String> getListOfFiles(){
        mp3Files = new ArrayList<>();
        if(!startDirectory.exists()) return  null;
        try{

            File[] files = startDirectory.listFiles();
            for(File file:files){
                if(!file.isDirectory() & isMusicFile(file.getAbsolutePath())){
                    mp3Files.add(file.getName());
                }
            }
        }
        catch (NullPointerException ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
        return mp3Files;
    }

    private boolean isMusicFile(String absolutePath) {
        switch (getFileExtension(absolutePath)){
            case "mp3":{
                return true;
            }
            case "flac":{
                return true;
            }
            default:return false;
        }
    }
    private  String getFileExtension(String mystr) {
        int index = mystr.lastIndexOf('.');
        String extension = index == -1 ? null : mystr.substring(index + 1);
        return extension;
    }
    public void chooseSong(int selectedSong){
        stopPlay();
        mPlayer = new MediaPlayer();
        currentSong=selectedSong;
        setCurrentSong();
        play();
    }
}
