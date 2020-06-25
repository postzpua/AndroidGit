package space.lysenko.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Level1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

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
