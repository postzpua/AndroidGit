package space.lysenko.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Level1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        final ImageView img_left = findViewById(R.id.img_left);
        final ImageView img_right = findViewById(R.id.img_right);

        // код который скругляет углы
        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);

        //Развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Развернуть игру на весь экран - конец

    }



    //Системная кнопка "Назад" - начало
    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        //начало конструкции
        try{

            Intent intent = new Intent (Level1.this, GameLevels.class);
            startActivity(intent);
            finish();

        }catch (Exception e){

        }
        //конец конструкции
    }
    //Системная кнопка "Назад" - конец
}
