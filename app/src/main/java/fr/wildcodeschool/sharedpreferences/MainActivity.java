package fr.wildcodeschool.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editRemember = findViewById(R.id.editRemember);
                String rememberMe = editRemember.getText().toString();

                saveInPref(rememberMe);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        displayRemembered();
    }

    private void displayRemembered() {
        String rememberMe = readFromPref();

        TextView backup = findViewById(R.id.textBackup);
        backup.setText(rememberMe);
    }

    private void saveInPref(String value) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.backup_text), value);
        editor.commit();

        displayRemembered();
    }

    private String readFromPref() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(getString(R.string.backup_text), getString(R.string.empty));
    }
}
