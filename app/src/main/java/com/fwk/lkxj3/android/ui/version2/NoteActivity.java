package com.fwk.lkxj3.android.ui.version2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.common.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NoteActivity extends Activity {
    @InjectView(R.id.note_contents)
    EditText note_contents;
    @InjectView(R.id.note_wc)
    TextView note_wc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.inject(this);


        if (!TimuListActivity.note.equals("")) {
            note_contents.setText(TimuListActivity.note);
        }

//        完成按钮
        note_wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (note_contents.getText().toString().equals("")) {
                    ToastUtil.show("请您先填写备注");
                } else {
                    TimuListActivity.note = note_contents.getText().toString();
                    finish();
                }
            }
        });

    }

    /**
     * 完成按钮
     *
     * @param view
     */
    public void ok(View view) {


    }

    /**
     * 返回按钮
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }
}
