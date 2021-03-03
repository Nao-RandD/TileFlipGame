package com.naorandd.tileslipgame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    // Member validation
    private CommonDefine _commonDefine;

    private Button[] bt_arr = new Button[9];

    private Button ans_tile_1;
    private Button ans_tile_2;
    private Button ans_tile_3;
    private Button ans_tile_4;
    private Button ans_tile_5;
    private Button ans_tile_6;
    private Button ans_tile_7;
    private Button ans_tile_8;
    private Button ans_tile_9;

    private TextView clear_text;

    private Button set_bt;
    private Button top_bt;

    private SoundPool soundPool;
    private int tapSound;

    private MediaPlayer mediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "onCreate");

        setContentView(R.layout.activity_main);

        // Get CommonDefine Status
        _commonDefine = (CommonDefine) getApplication();
        _commonDefine.init();

        // Find Tile id
        bt_arr[0] = findViewById(R.id.tile_1);
        bt_arr[1] = findViewById(R.id.tile_2);
        bt_arr[2] = findViewById(R.id.tile_3);
        bt_arr[3] = findViewById(R.id.tile_4);
        bt_arr[4] = findViewById(R.id.tile_5);
        bt_arr[5] = findViewById(R.id.tile_6);
        bt_arr[6] = findViewById(R.id.tile_7);
        bt_arr[7] = findViewById(R.id.tile_8);
        bt_arr[8] = findViewById(R.id.tile_9);

        // Find Answer Tile id
        ans_tile_1 = findViewById(R.id.ans_tile_1);
        ans_tile_2 = findViewById(R.id.ans_tile_2);
        ans_tile_3 = findViewById(R.id.ans_tile_3);
        ans_tile_4 = findViewById(R.id.ans_tile_4);
        ans_tile_5 = findViewById(R.id.ans_tile_5);
        ans_tile_6 = findViewById(R.id.ans_tile_6);
        ans_tile_7 = findViewById(R.id.ans_tile_7);
        ans_tile_8 = findViewById(R.id.ans_tile_8);
        ans_tile_9 = findViewById(R.id.ans_tile_9);

        // Find Set Game Button id
        set_bt = findViewById(R.id.set_button);

        top_bt = findViewById(R.id.top_button);

        // FInd Clear Screen Text id
        clear_text = findViewById(R.id.clear_text);

        // Set Each Button Listener
        bt_arr[0].setOnClickListener(this);
        bt_arr[1].setOnClickListener(this);
        bt_arr[2].setOnClickListener(this);
        bt_arr[3].setOnClickListener(this);
        bt_arr[4].setOnClickListener(this);
        bt_arr[5].setOnClickListener(this);
        bt_arr[6].setOnClickListener(this);
        bt_arr[7].setOnClickListener(this);
        bt_arr[8].setOnClickListener(this);

        set_bt.setOnClickListener(this);

        top_bt.setOnClickListener(this);

        // Setting tap button sound
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        // Generate SoundPool instance for tap button sound
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();

        // Load soundPool from tap_sound.mp3
        tapSound = soundPool.load(this, R.raw.tap_sound, 1);

        // Check Load Status
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("debug", "sampleId=" + sampleId);
                Log.d("debug", "status=" + status);
            }
        });

        // Get SystemService Sound for background music
        AudioManager audio = (AudioManager)getSystemService(AUDIO_SERVICE);
        int ringVol = audio.getStreamVolume(AudioManager.STREAM_RING);

        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(ringVol,ringVol);
        mediaPlayer.start();

        // Set Answer tile
        generateRandomArray();
        set_bt.setText("Next Game");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "onStart");

        if(!(mediaPlayer.isPlaying())){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "onStop");

        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    /**
     * Button Action Function
     * @param v
     * @implNote Get button id for user click main board.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tile_1:
                if (_commonDefine.tileFlg[0]) {
                    bt_arr[0].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[0].setBackgroundColor(Color.rgb(200, 0, 0));
                    bt_arr[0].setBackgroundResource(R.drawable.button_background_tail);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(0);
                    _commonDefine.tileFlg[0] = false;
                } else {
                    bt_arr[0].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[0].setBackgroundColor(Color.rgb(0, 0, 255));
                    bt_arr[0].setBackgroundResource(R.drawable.button_background_head);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(0);
                    _commonDefine.tileFlg[0] = true;
                }
                break;
            case R.id.tile_2:
                if (_commonDefine.tileFlg[1]) {
                    bt_arr[1].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[1].setBackgroundColor(Color.rgb(200, 0, 0));
                    bt_arr[1].setBackgroundResource(R.drawable.button_background_tail);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(1);
                    _commonDefine.tileFlg[1] = false;
                } else {
                    bt_arr[1].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[1].setBackgroundColor(Color.rgb(0, 0, 255));
                    bt_arr[1].setBackgroundResource(R.drawable.button_background_head);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(1);
                    _commonDefine.tileFlg[1] = true;
                }
                break;
            case R.id.tile_3:
                if (_commonDefine.tileFlg[2]) {
                    bt_arr[2].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[2].setBackgroundColor(Color.rgb(200, 0, 0));
                    bt_arr[2].setBackgroundResource(R.drawable.button_background_tail);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(2);
                    _commonDefine.tileFlg[2] = false;
                } else {
                    bt_arr[2].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[2].setBackgroundColor(Color.rgb(0, 0, 255));
                    bt_arr[2].setBackgroundResource(R.drawable.button_background_head);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(2);
                    _commonDefine.tileFlg[2] = true;
                }
                break;
            case R.id.tile_4:
                if (_commonDefine.tileFlg[3]) {
                    bt_arr[3].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[3].setBackgroundColor(Color.rgb(200, 0, 0));
                    bt_arr[3].setBackgroundResource(R.drawable.button_background_tail);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(3);
                    _commonDefine.tileFlg[3] = false;
                } else {
                    bt_arr[3].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[3].setBackgroundColor(Color.rgb(0, 0, 255));
                    bt_arr[3].setBackgroundResource(R.drawable.button_background_head);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(3);
                    _commonDefine.tileFlg[3] = true;
                }
                break;
            case R.id.tile_5:
                if (_commonDefine.tileFlg[4]) {
                    bt_arr[4].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[4].setBackgroundColor(Color.rgb(200, 0, 0));
                    bt_arr[4].setBackgroundResource(R.drawable.button_background_tail);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(4);
                    _commonDefine.tileFlg[4] = false;
                } else {
                    bt_arr[4].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[4].setBackgroundColor(Color.rgb(0, 0, 255));
                    bt_arr[4].setBackgroundResource(R.drawable.button_background_head);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(4);
                    _commonDefine.tileFlg[4] = true;
                }
                break;
            case R.id.tile_6:
                if (_commonDefine.tileFlg[5]) {
                    bt_arr[5].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[5].setBackgroundColor(Color.rgb(200, 0, 0));
                    bt_arr[5].setBackgroundResource(R.drawable.button_background_tail);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(5);
                    _commonDefine.tileFlg[5] = false;
                } else {
                    bt_arr[5].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[5].setBackgroundColor(Color.rgb(0, 0, 255));
                    bt_arr[5].setBackgroundResource(R.drawable.button_background_head);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(5);
                    _commonDefine.tileFlg[5] = true;
                }
                break;
            case R.id.tile_7:
                if (_commonDefine.tileFlg[6]) {
                    bt_arr[6].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[6].setBackgroundColor(Color.rgb(200, 0, 0));
                    bt_arr[6].setBackgroundResource(R.drawable.button_background_tail);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(6);
                    _commonDefine.tileFlg[6] = false;
                } else {
                    bt_arr[6].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[6].setBackgroundColor(Color.rgb(0, 0, 255));
                    bt_arr[6].setBackgroundResource(R.drawable.button_background_head);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(6);
                    _commonDefine.tileFlg[6] = true;
                }
                break;
            case R.id.tile_8:
                if (_commonDefine.tileFlg[7]) {
                    bt_arr[7].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[7].setBackgroundColor(Color.rgb(200, 0, 0));
                    bt_arr[7].setBackgroundResource(R.drawable.button_background_tail);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(7);
                    _commonDefine.tileFlg[7] = false;
                } else {
                    bt_arr[7].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[7].setBackgroundColor(Color.rgb(0, 0, 255));
                    bt_arr[7].setBackgroundResource(R.drawable.button_background_head);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(7);
                    _commonDefine.tileFlg[7] = true;
                }
                break;
            case R.id.tile_9:
                if (_commonDefine.tileFlg[8]) {
                    bt_arr[8].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[8].setBackgroundColor(Color.rgb(200, 0, 0));
                    bt_arr[8].setBackgroundResource(R.drawable.button_background_tail);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(8);
                    _commonDefine.tileFlg[8] = false;
                } else {
                    bt_arr[8].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//                    bt_arr[8].setBackgroundColor(Color.rgb(0, 0, 255));
                    bt_arr[8].setBackgroundResource(R.drawable.button_background_head);
                    soundPool.play(tapSound, 1.0f, 1.0f, 0, 0, 1);
                    flipLogic(8);
                    _commonDefine.tileFlg[8] = true;
                }
                break;

            case R.id.set_button:
                generateRandomArray();
                set_bt.setText("Next Game");
                break;

            case R.id.top_button:
                Intent intent = new Intent(this, TopActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
        }

        // Set Clear Condition
        if(compareAnswerArr()){
            clear_text.setVisibility(View.VISIBLE);
            _commonDefine.score++;
            if(_commonDefine.score == 4){
                _commonDefine.score = 0;
                Intent intent = new Intent(this, ClearActivity.class);
                startActivity(intent);
            }
        }
        System.out.println(Arrays.toString(_commonDefine.tileFlg));
    }

    /**
     * Generate Binary Array Condition
     * @implNote Set answer array.
     */
    private void generateRandomArray(){
        Random r = new Random();

        for(int i = 0; i < 9; i++) {
                _commonDefine.ansTileFlg[i] = convertBool(r.nextInt(2));
        }
        clear_text.setVisibility(View.GONE);
        loadStatusArray();
    }

    /**
     * Load Status Answer Random Array to Answer Tile
     * @implNote Load status to answer tile from random array(generateRandomArray)
     */
    private void loadStatusArray(){
        for(int i = 0; i < 9; i++) {
            switch(i + 1) {
                case 1:
                    if (_commonDefine.ansTileFlg[0]) {
//                        ans_tile_1.setBackgroundColor(Color.rgb(200, 0, 0));
                        ans_tile_1.setBackgroundResource(R.drawable.button_background_tail);
                        _commonDefine.ansTileFlg[0] = false;
                    } else {
//                        ans_tile_1.setBackgroundColor(Color.rgb(0, 0, 200));
                        ans_tile_1.setBackgroundResource(R.drawable.button_background_head);
                        _commonDefine.ansTileFlg[0] = true;
                    }
                    break;
                case 2:
                    if (_commonDefine.ansTileFlg[1]) {
//                        ans_tile_2.setBackgroundColor(Color.rgb(200, 0, 0));
                        ans_tile_2.setBackgroundResource(R.drawable.button_background_tail);
                        _commonDefine.ansTileFlg[1] = false;
                    } else {
//                        ans_tile_2.setBackgroundColor(Color.rgb(0, 0, 200));
                        ans_tile_2.setBackgroundResource(R.drawable.button_background_head);
                        _commonDefine.ansTileFlg[1] = true;
                    }
                    break;
                case 3:
                    if (_commonDefine.ansTileFlg[2]) {
//                        ans_tile_3.setBackgroundColor(Color.rgb(200, 0, 0));
                        ans_tile_3.setBackgroundResource(R.drawable.button_background_tail);
                        _commonDefine.ansTileFlg[2] = false;
                    } else {
//                        ans_tile_3.setBackgroundColor(Color.rgb(0, 0, 200));
                        ans_tile_3.setBackgroundResource(R.drawable.button_background_head);
                        _commonDefine.ansTileFlg[2] = true;
                    }
                    break;
                case 4:
                    if (_commonDefine.ansTileFlg[3]) {
//                        ans_tile_4.setBackgroundColor(Color.rgb(200, 0, 0));
                        ans_tile_4.setBackgroundResource(R.drawable.button_background_tail);
                        _commonDefine.ansTileFlg[3] = false;
                    } else {
//                        ans_tile_4.setBackgroundColor(Color.rgb(0, 0, 200));
                        ans_tile_4.setBackgroundResource(R.drawable.button_background_head);
                        _commonDefine.ansTileFlg[3] = true;
                    }
                    break;
                case 5:
                    if (_commonDefine.ansTileFlg[4]) {
//                        ans_tile_5.setBackgroundColor(Color.rgb(200, 0, 0));
                        ans_tile_5.setBackgroundResource(R.drawable.button_background_tail);
                        _commonDefine.ansTileFlg[4] = false;
                    } else {
//                        ans_tile_5.setBackgroundColor(Color.rgb(0, 0, 200));
                        ans_tile_5.setBackgroundResource(R.drawable.button_background_head);
                        _commonDefine.ansTileFlg[4] = true;
                    }
                    break;
                case 6:
                    if (_commonDefine.ansTileFlg[5]) {
//                        ans_tile_6.setBackgroundColor(Color.rgb(200, 0, 0));
                        ans_tile_6.setBackgroundResource(R.drawable.button_background_tail);
                        _commonDefine.ansTileFlg[5] = false;
                    } else {
//                        ans_tile_6.setBackgroundColor(Color.rgb(0, 0, 200));
                        ans_tile_6.setBackgroundResource(R.drawable.button_background_head);
                        _commonDefine.ansTileFlg[5] = true;
                    }
                    break;
                case 7:
                    if (_commonDefine.ansTileFlg[6]) {
//                        ans_tile_7.setBackgroundColor(Color.rgb(200, 0, 0));
                        ans_tile_7.setBackgroundResource(R.drawable.button_background_tail);
                        _commonDefine.ansTileFlg[6] = false;
                    } else {
//                        ans_tile_7.setBackgroundColor(Color.rgb(0, 0, 200));
                        ans_tile_7.setBackgroundResource(R.drawable.button_background_head);
                        _commonDefine.ansTileFlg[6] = true;
                    }
                    break;
                case 8:
                    if (_commonDefine.ansTileFlg[7]) {
//                        ans_tile_8.setBackgroundColor(Color.rgb(200, 0, 0));
                        ans_tile_8.setBackgroundResource(R.drawable.button_background_tail);
                        _commonDefine.ansTileFlg[7] = false;
                    } else {
//                        ans_tile_8.setBackgroundColor(Color.rgb(0, 0, 200));
                        ans_tile_8.setBackgroundResource(R.drawable.button_background_head);
                        _commonDefine.ansTileFlg[7] = true;
                    }
                    break;
                case 9:
                    if (_commonDefine.ansTileFlg[8]) {
//                        ans_tile_9.setBackgroundColor(Color.rgb(200, 0, 0));
                        ans_tile_9.setBackgroundResource(R.drawable.button_background_tail);
                        _commonDefine.ansTileFlg[8] = false;
                    } else {
//                        ans_tile_9.setBackgroundColor(Color.rgb(0, 0, 200));
                        ans_tile_9.setBackgroundResource(R.drawable.button_background_head);
                        _commonDefine.ansTileFlg[8] = true;
                    }
                    break;
            }
        }
    }

    /**
     * Convert Array from Binary to Boolean
     * @param binary 0 or 1
     * @return true or false
     */
    private boolean convertBool(int binary){
        if(binary == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Compare User Tile to Answer Tile
     * @return true or false
     */
    private boolean compareAnswerArr(){
        for(int i = 0; i < 9; i++){
            if(_commonDefine.tileFlg[i] != _commonDefine.ansTileFlg[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * Set to Tile Flip Logic
     * @param num: user touch tile number
     * @implNote Flip tile vertical and horizontal.
     */
    private void flipLogic(int num){
        if(num == 4){
            for(int i = 0; i < 4; i++) {
                tileFlip(_commonDefine.flipLogicArr[num][i]);
            }
        } else if (num == 0 || num == 2 || num == 6 || num == 8) {
            for(int i = 0; i < 2; i++) {
                tileFlip(_commonDefine.flipLogicArr[num][i]);
            }
        }else{
            for(int i = 0; i < 3; i++) {
                tileFlip(_commonDefine.flipLogicArr[num][i]);
            }
        }
    }

    /**
     * Flip Tile Setting
     * @param num: user touch tile number
     * @implNote Tile animation and change flg.
     */
    private void tileFlip(int num){
        if (_commonDefine.tileFlg[num]) {
            bt_arr[num].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//            bt_arr[num].setBackgroundColor(Color.rgb(200, 0, 0));
            bt_arr[num].setBackgroundResource(R.drawable.button_background_tail);
            _commonDefine.tileFlg[num] = false;
        } else {
            bt_arr[num].startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_on_click));
//            bt_arr[num].setBackgroundColor(Color.rgb(0, 0, 255));
            bt_arr[num].setBackgroundResource(R.drawable.button_background_head);
            _commonDefine.tileFlg[num] = true;
        }
    }
}