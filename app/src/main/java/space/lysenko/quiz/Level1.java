package space.lysenko.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStreamReader;
import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;

    public int numLeft; // Переменная для левой картинки + текст
    public int numRight; // Переменная для правой картинки + текст

    Array array = new Array();
    Random random = new Random();

    public int count = 0; //Счетчик правильных ответов



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //Создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1); // Установили текст



        final ImageView img_left = findViewById(R.id.img_left);
        final ImageView img_right = findViewById(R.id.img_right);

        // код который скругляет углы
        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);

        //Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);

        //Путь к правой TextView
        final TextView text_right = findViewById(R.id.text_left);

        //Развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Развернуть игру на весь экран - конец

        //Вызов диалогового окна в начале игры.
        dialog = new Dialog(this); // создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); // окно нельзя закрыть кнопкой назад

        //Кнопка которая закрывает диалоговое окно - начало
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Обрабатываем нажатие кнопки - начало
                try{
                    //Вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level1.this, GameLevels.class); // Создали намерение для перехода
                    startActivity(intent); // Старт намерения
                    finish(); //Закрыть этот класс


                    //Вернуться назад к выбору уровня - конец

                }catch (Exception e){
                    //Здесь кода не будет
                }

                dialog.dismiss(); //Закрываем диалоговое окно
                //Обрабатываем нажатие кнопки - конец

            }
        });

        //Кнопка которая закрывает диалоговое окно - конец

        //Кнопка "Продолжить" - начало
        Button btncontinue = dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); //Закрываем диалоговое окно
            }
        });

        //Кнопка "Продолжить" - конец


        dialog.show(); //показать диалоговое окно


        //Кнопка "Назад" - начало
        Button btn_back = findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Обрабатываем нажатие кнопки "Назад" - начало
                try {
                    //Вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level1.this, GameLevels.class); //Создали намерение для перехода
                    startActivity(intent); //Старт намерения
                    finish(); //Закрыть этот клас

                    //Вернуться назад к выбору уровня - конец

                }catch (Exception e){
                    //Здесь кода не будет

                }

                //Обрабатываем нажатие кнопки "Назад" - конец
            }
        });

        //Кнопка "Назад" - конец

        //Массив для прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };
        //Массив для прогресса игры - конец


        // Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

        // Подключаем анимацию - конец


        numLeft = random.nextInt(10); //Генерируем случайное число от 0 до 9
        img_left.setImageResource(array.images1[numLeft]); //Достаем из массива картинку

        text_left.setText(array.texts1[numLeft]); //Достаем из массива текст



        numRight = random.nextInt(10);


        //Цикл с предусловием, проверяющий равенство чисел - начало
        while (numLeft == numRight) {
            numRight = random.nextInt(10);
        }
        //Цикл с предусловием, проверяющий равенство чисел - конец

        img_right.setImageResource(array.images1[numRight]);
        text_right.setText(array.texts1[numRight]);

        // Обрабатываем нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                // Условие касания картинки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки - начало
                    img_right.setEnabled(false); //Блокируем правую картинку
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if(numLeft>numRight){
                        //Если левая картинка больше
                        if(count<20){
                            count++;
                        }

                        //Закрашиваем прогресс серым цветом - начало
                            for(int i=0; i<20; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                        //Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашиваем зеленым - начало
                            for(int i=0; i<count; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }

                        // Определяем правильные ответы и закрашиваем зеленым - конец

                    }else{
                        //Если левая картинка меньше
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }

                        //Закрашиваем прогресс серым цветом - начало
                        for(int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашиваем зеленым - начало
                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                        // Определяем правильные ответы и закрашиваем зеленым - конец

                    }
                    //Если отпустил палец - конец
                        if(count==20){
                            //ВЫХОД ИЗ УРОВНЯ
                        }else{
                            numLeft = random.nextInt(10); //Генерируем случайное число от 0 до 9
                            img_left.setImageResource(array.images1[numLeft]); //Достаем из массива картинку
                            img_left.startAnimation(a);

                            text_left.setText(array.texts1[numLeft]); //Достаем из массива текст



                            numRight = random.nextInt(10);


                            //Цикл с предусловием, проверяющий равенство чисел - начало
                            while (numLeft == numRight) {
                                numRight = random.nextInt(10);
                            }
                            //Цикл с предусловием, проверяющий равенство чисел - конец

                            img_right.setImageResource(array.images1[numRight]);
                            img_right.startAnimation(a);
                            text_right.setText(array.texts1[numRight]);

                            img_right.setEnabled(true); //Включаем обратно правую картинку


                        }

                }
                // Условие касания картинки - конец


                return true;
            }
        });

        // Обрабатываем нажатие на левую картинку - конец







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
