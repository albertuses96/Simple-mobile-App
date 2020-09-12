package com.example.simplemobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button reverseClick, undoClick;
    private TextInputLayout input;
    private TextView output;
    private String currentText, previousText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (TextInputLayout) findViewById(R.id.textInput);
        output = (TextView) findViewById(R.id.textView);

        reverseClick = (Button) findViewById(R.id.buttonReverse);
        undoClick = (Button) findViewById(R.id.buttonUndo);

        reverseClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String text = String.valueOf(input.getEditText().getText());
                if (previousText == null)
                {
                    previousText = text;
                }
                else {
                    if (currentText == null)
                    {
                        currentText = text;
                    }
                    else {
                        previousText = currentText;
                        currentText = text;
                    }

                }
                String printText = "";
                List<String> texts = Arrays.asList(text.split(""));
                Collections.reverse(texts);
                for(int i = 0; i<texts.size();i++)
                {
                    printText = printText+texts.get(i);
                }
                output.setText("Output: "+ printText);
            }
        });

        undoClick.setOnClickListener(new DoubleClickListener(300) {

            @Override
            public void onDoubleClick() {
                input.getEditText().setText(currentText);
            }

            @Override
            public void onSingleClick() {
                input.getEditText().setText(previousText);
            }
        });


    }
}