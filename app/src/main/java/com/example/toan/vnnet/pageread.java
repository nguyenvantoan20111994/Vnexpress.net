package com.example.toan.vnnet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by toan on 4/19/2016.
 */
public class pageread extends AppCompatActivity {
    Toolbar toolbarBottom;
    ImageButton btncomment,btnshare,btnviet;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageread_layout);
        initToolbars();
        eventtouch();
    }

    private void initToolbars() {
        toolbarBottom = (Toolbar) findViewById(R.id.toolbar_bottom);
         btncomment = (ImageButton) toolbarBottom.findViewById(R.id.btncomment);
        btnshare = (ImageButton) toolbarBottom.findViewById(R.id.btnshare);
        btnviet = (ImageButton) toolbarBottom.findViewById(R.id.btnviet);
        btncomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "an", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void eventtouch() {
        view=new View(pageread.this);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    Toast.makeText(getApplicationContext(), "1233", Toast.LENGTH_LONG).show();
                }else if(event.getAction()==MotionEvent.ACTION_MOVE) {
                    Toast.makeText(getApplicationContext(), "1233", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }

}