package ru.synergy.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/{

    EditText et;
    Button btnSave, btnLoad;
    SharedPreferences sharedPreferences;
    final String SAVED_TEXT = "Saved text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.edText);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad = (Button) findViewById(R.id.btnLoad);

        //сохранение и загрузка текста по нажатию на кнопки
/*        btnSave.setOnClickListener(this);
        btnLoad.setOnClickListener(this);*/

        //сохранение и загрузка текста по загрузке приложения
        loadText();
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionID, KeyEvent event) {
                saveText();
                Log.d(SAVED_TEXT, et.getText().toString());
                return true;
            }
        });
    }

    //сохранение и загрузка текста по нажатию на кнопки
/*    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                saveText();
                break;
            case R.id.btnLoad:
                loadText();
                break;
            default: break;
        }
    }*/

    private void loadText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedText = sharedPreferences.getString(SAVED_TEXT, "Ничего не сохранено в Shared Preferences");
        et.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_LONG).show();
    }

    private void saveText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString(SAVED_TEXT, et.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_LONG).show();
    }

    //сохранение текста при выходе из приложения
    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}