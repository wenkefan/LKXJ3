package com.fwk.lkxj3.MVP.view;

import android.widget.GridView;
import android.widget.ImageView;

import com.fwk.lkxj3.MVP.base.IMVP_view;

/**
 * Created by fanwenke on 16/11/10.
 */

public interface IMain_Activity_view extends IMVP_view {

    ImageView getImageView();
    GridView getGridView();

}
