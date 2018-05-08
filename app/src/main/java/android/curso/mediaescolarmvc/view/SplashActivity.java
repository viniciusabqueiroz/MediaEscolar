package android.curso.mediaescolarmvc.view;

import android.content.Intent;
import android.curso.mediaescolarmvc.R;
import android.curso.mediaescolarmvc.controller.MediaEscolarController;
import android.curso.mediaescolarmvc.model.MediaEscolar;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity
    implements TextToSpeech.OnInitListener

{

    private static final int SPLASH_TIME_OUT = 5000;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MediaEscolarController mediaEscolarController =
                new MediaEscolarController(getBaseContext());

        textToSpeech = new TextToSpeech(this, this);

        apresentarTelaSplash();

    }

    private void apresentarTelaSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent telaPrincipal
                        = new Intent(SplashActivity.this,
                        MainActivity.class);

                startActivity(telaPrincipal);

                finish();

            }
        }, SPLASH_TIME_OUT);


    }

    @Override
    public void onInit(int status) {

        if(status == TextToSpeech.SUCCESS){

            int result = textToSpeech.setLanguage(Locale.ENGLISH);

            if(result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED){

                // Log
                Log.e("MVC", "Idioma não suportado");
            }else{

                boasVidas();
            }
        }else{

            Log.e("MVC", "Falha ao inicializar TTS");
        }

        
    }

    private void boasVidas() {

        String texto = "Welcome!";

        textToSpeech.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onDestroy(){

        if(textToSpeech != null){

            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();

    }
}
