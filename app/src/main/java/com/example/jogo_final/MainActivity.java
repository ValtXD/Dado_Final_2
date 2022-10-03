package com.example.jogo_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv_dice_p1, iv_dice_p2, iv_lives_p1, iv_lives_p2;
    TextView tv_player1, tv_player2;

    Random r;

    int livesP1, livesP2;
    int rolledP1, rolledP2;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);

        iv_dice_p1 = findViewById(R.id.iv_dice_p1);
        iv_dice_p2 = findViewById(R.id.iv_dice_p2);

        iv_lives_p1 = findViewById(R.id.iv_lives_p1);
        iv_lives_p2 = findViewById(R.id.iv_lives_p2);

        tv_player1 = findViewById(R.id.tv_player1);
        tv_player2 = findViewById(R.id.tv_player2);

        tv_player1.setText("Jogador_1 Roll");
        tv_player2.setText("Jogador_2 Roll");

        //vidas esquerda
        livesP1 = 6;
        livesP2 = 6;

        setDiceImage(livesP1, iv_lives_p1);
        setDiceImage(livesP2, iv_lives_p2);

        iv_dice_p1.setOnClickListener(new View.OnClickListener() {
            //girar o dado
            @Override
            public void onClick(View view) {
                rolledP1 = r.nextInt(6) + 1;
                setDiceImage(rolledP1, iv_dice_p1);
                iv_dice_p1.startAnimation(animation);

                //ver se o outro jogador rolou seus dados
                if (rolledP2 != 0) {
                    tv_player1.setText("Jogador_1 Roll");
                    tv_player2.setText("Jogador_2 Roll");

                    //decidindo o vencedor(calculo)
                    if(rolledP1 > rolledP2){
                        livesP2--;
                        setDiceImage(livesP2, iv_dice_p2);

                        Toast.makeText(MainActivity.this,"Jogador_1 Venceu", Toast.LENGTH_SHORT).show();
                    }
                    if(rolledP2 > rolledP1){
                        livesP1--;
                        setDiceImage(livesP1, iv_dice_p1);

                        Toast.makeText(MainActivity.this,"Jogador_2 Venceu", Toast.LENGTH_SHORT).show();
                    }

                    if(rolledP1 == rolledP2){
                        Toast.makeText(MainActivity.this,"Jogue", Toast.LENGTH_SHORT).show();
                    }

                    //inicializando os valores
                    rolledP1 = 0;
                    rolledP2 = 0;

                    iv_dice_p1.setEnabled(true);
                    iv_dice_p2.setEnabled(true);

                    //verifique o jogador com 0 vidas restantes
                    checkEndGame();

                }else{
                    tv_player1.setText("Jogador_1 Rolled");
                    iv_dice_p1.setEnabled(false);
                }
            }
        });

        iv_dice_p2.setOnClickListener(new View.OnClickListener() {
            //girar o dado
            @Override
            public void onClick(View view) {

                rolledP2 = r.nextInt(6)+1;
                setDiceImage(rolledP2, iv_dice_p2);
                iv_dice_p2.startAnimation(animation);

                //ver se o outro jogador rolou seus dados
                if (rolledP1 != 0) {

                    tv_player1.setText("Jogador_1 Roll");
                    tv_player2.setText("Jogador_2 Roll");

                    //decidindo o vencedor(calculo)
                    if(rolledP1 > rolledP2){
                        livesP2--;
                        setDiceImage(livesP2, iv_dice_p2);

                        Toast.makeText(MainActivity.this,"Jogador_1 Venceu", Toast.LENGTH_SHORT).show();
                    }
                    if(rolledP2 > rolledP1){
                        livesP1--;
                        setDiceImage(livesP1, iv_dice_p1);

                        Toast.makeText(MainActivity.this,"Jogador_2 Venceu", Toast.LENGTH_SHORT).show();
                    }

                    if(rolledP1 == rolledP2){
                        Toast.makeText(MainActivity.this,"Jogue", Toast.LENGTH_SHORT).show();
                    }

                    //valor inicial
                    rolledP1 = 0;
                    rolledP2 = 0;

                    iv_dice_p1.setEnabled(true);
                    iv_dice_p2.setEnabled(true);

                    //verifique o jogador com 0 vidas restantes
                    checkEndGame();

                }else{
                    tv_player2.setText("Jogador_2 Rolled");

                    iv_dice_p2.setEnabled(false);
                }
            }
        });
    }
    //mostrar imagem de dados de acordo com o número
    private void setDiceImage(int dice, ImageView image){
        switch (dice){
            case 1:
                image.setImageResource(R.drawable.um);
                break;


            case 2:
                image.setImageResource(R.drawable.dois);
                break;


            case 3:
                image.setImageResource(R.drawable.tres);
                break;


            case 4:
                image.setImageResource(R.drawable.quatro);
                break;


            case 5:
                image.setImageResource(R.drawable.cinco);
                break;


            case 6:
                image.setImageResource(R.drawable.seis);
                break;


            default:
                image.setImageResource(R.drawable.zero);
        }
    }

    //mostrar fim do game_dialogo
    private void checkEndGame(){
        String text = "";
        if(livesP1 == 0 || livesP2 == 0){
            iv_dice_p1.setEnabled(false);
            iv_dice_p2.setEnabled(false);

            if(livesP1 != 0){
                text = "Fim de Jogo, Jogador_2 Venceu";
            }
            if(livesP2 != 0){
                text = "Fim de Jogo, Jogador_1 Venceu";
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage(text);
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

}