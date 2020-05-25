package com.example.blocdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    float textSize;
    boolean boldStyle;
    boolean italicStyle;
    String textColor;
    String backgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);

        String text = "Di`choso el árbol, que es apenas sensitivo,\n"
                        + "y más la piedra dura porque esa ya no siente,\n"
                        + "pues no hay dolor más grande que el dolor de ser vivo,\n"
                        + "ni mayor pesadumbre que la vida consciente.\n\n"

                        + "Ser y no saber nada, y ser sin rumbo cierto,\n"
                        + "y el temor de haber sido y un futuro terror...\n"
                        + "Y el espanto seguro de estar mañana muerto,\n"
                        + "y sufrir por la vida y por la sombra y por\n\n"

                        + "lo que no conocemos y apenas sospechamos,\n"
                        + "y la carne que tienta con sus frescos racimos,\n"
                        + "y la tumba que aguarda con sus fúnebres ramos,\n\n"

                        + "¡y no saber adónde vamos,\n"
                        + "ni de dónde venimos!...";
        editText.setText(text);


        PreferenceManager.setDefaultValues(this, R.xml.main_preferences, false);
        loadPreferences();
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        loadPreferences();
    }

    public void loadPreferences()
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        textSize = Float.parseFloat(pref.getString("textSize", "14"));
        boldStyle = pref.getBoolean("boldStyle", false);
        italicStyle = pref.getBoolean("italicStyle", false);
        textColor = pref.getString("textColor", "black");
        backgroundColor = pref.getString("backgroundColor", "white");

        //cambiar el tamño del texto
        editText.setTextSize(textSize);

        //negrita y cursiva
        int style = 0;
        if(boldStyle)
            style = style | Typeface.BOLD;
        if(italicStyle)
            style = style | Typeface.ITALIC;

        editText.setTypeface(null, style);

        //cambiar color de la letra
        switch (textColor)
        {
            case "black":
                editText.setTextColor(Color.BLACK);
                break;
            case "white":
                editText.setTextColor(Color.WHITE);
                break;
            case "blue":
                editText.setTextColor(Color.BLUE);
                break;
            case "red":
                editText.setTextColor(Color.RED);
                break;
        }

        switch (backgroundColor)
        {
            case "black":
                editText.setBackgroundColor(Color.BLACK);
                break;
            case "white":
                editText.setBackgroundColor(Color.WHITE);
                break;
            case "blue":
                editText.setBackgroundColor(Color.BLUE);
                break;
            case "red":
                editText.setBackgroundColor(Color.RED);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.item_menu_settings:
                //lanzar actividad de preferencias
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
