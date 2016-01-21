package com.ronstruempf.ronstruempfcontacts;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static String APP_TAG = "RonStruempfContacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if Landscape orientation, change config for landscape
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(APP_TAG, "Screen orientation is Landscape");
            LinearLayout main = (LinearLayout)findViewById(R.id.content_main);
            main.setOrientation(LinearLayout.HORIZONTAL);
            RelativeLayout editor = (RelativeLayout)findViewById(R.id.content_editor);
            editor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
            RelativeLayout view = (RelativeLayout)findViewById(R.id.content_view);
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
        }

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyEditorToTextView(R.id.nameEdit, R.id.nameView);
                copyEditorToTextView(R.id.emailEdit, R.id.emailView);
                copyEditorToTextView(R.id.phoneEdit, R.id.phoneView);
                RadioButton rb = (RadioButton)findViewById(R.id.phoneIsCell);
                String text = "";
                if (rb.isChecked()) {
                    text = "(CELL)";
                } else {
                    rb = (RadioButton)findViewById(R.id.phoneIsLandLine);
                    if (rb.isChecked()) {
                        text = "Land Line";
                    }
                }
                TextView textView = (TextView)findViewById(R.id.phoneTypeView);
                textView.setText(text);
            }
        });

    }

    /**
     * Copy an editor value to a text view for that item
     *
     * @param editorId View id for EditText to copy from
     * @param textViewId View id of TextView to copy to
     */
    private void copyEditorToTextView(int editorId, int textViewId) {
        EditText editText = (EditText)findViewById(editorId);
        TextView textView = (TextView)findViewById(textViewId);
        textView.setText(editText.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
