package com.example.animatedfab;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.floating_button);
        bottomAppBar = findViewById(R.id.bottom_bar);
        floatingActionButton.setOnClickListener(new HandleClick());
    }

    private class HandleClick implements View.OnClickListener {
        private Handler handler;
        public HandleClick() {
            handler = new Handler();
        }
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                handler.postDelayed(replaceMainMenu(bottomAppBar), 325);
                bottomAppBar.getMenu().clear();
                floatingActionButton.setRotation(180);
            } else if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_END) {
                bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                bottomAppBar.replaceMenu(R.menu.bottom_bar);
                handler.postDelayed(replaceRightMenu(bottomAppBar), 335);
                bottomAppBar.getMenu().clear();
                floatingActionButton.setRotation(0);
            }
        }

        private Runnable replaceMainMenu(final BottomAppBar currentMenu){
            return new Runnable() {
                @Override
                public void run() {
                    currentMenu.replaceMenu(R.menu.bottom_bar_right);
                }
            };
        }

        private Runnable replaceRightMenu(final BottomAppBar currentMenu){
            return new Runnable() {
                @Override
                public void run() {
                    currentMenu.replaceMenu(R.menu.bottom_bar);
                }
            };
        }
    }
}
