package com.naorandd.tileslipgame;

import android.app.Application;
import android.widget.Button;

public class CommonDefine extends Application {

    Boolean[] tileFlg;
    Boolean[] ansTileFlg;

    /// Logic of Flip Tile
    int flipLogicArr[][] = {
            {1, 3, -1, -1},
            {0, 2, 4, -1},
            {1, 5, -1, -1},
            {0, 4, 6, -1},
            {1, 3, 5, 7},
            {2, 4, 8, -1},
            {3, 7, -1, -1},
            {4, 6, 8, -1},
            {5, 7, -1, -1}};
    
    int score;

    // Animation Setting
    static final int BACKGROUND_ENTER_ANIMATION_DURATION = 2000;
    static final int BACKGROUND_EXIT_ANIMATION_DURATION = 4000;
    static final int BUTTON_ANIMATION_DURATION = 3000;

    // Initialize Validation
    public void init(){
        tileFlg = new Boolean[9];
        tileFlg[0] = true;
        tileFlg[1] = true;
        tileFlg[2] = true;
        tileFlg[3] = true;
        tileFlg[4] = true;
        tileFlg[5] = true;
        tileFlg[6] = true;
        tileFlg[7] = true;
        tileFlg[8] = true;

        ansTileFlg = new Boolean[9];
        ansTileFlg[0] = true;
        ansTileFlg[1] = true;
        ansTileFlg[2] = true;
        ansTileFlg[3] = true;
        ansTileFlg[4] = true;
        ansTileFlg[5] = true;
        ansTileFlg[6] = true;
        ansTileFlg[7] = true;
        ansTileFlg[8] = true;

        score = 0;
    }
}
