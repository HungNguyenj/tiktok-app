package com.example.tiktokapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tiktokapp.R;
import com.example.tiktokapp.utils.IntentUtil;

public class EditInforActivity extends AppCompatActivity {

    private TextView cancel, save, txtTitle, subline;
    private EditText editText;
    private ImageButton deleteContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_infor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent i = getIntent();
        String titleName = i.getStringExtra("title");
        boolean method = i.getBooleanExtra("method", false);

        cancel = findViewById(R.id.cancelEdit);
        save = findViewById(R.id.saveEdit);
        editText = findViewById(R.id.editText);
        txtTitle = findViewById(R.id.contentEdit);
        deleteContent = findViewById(R.id.deleteContent);
        subline = findViewById(R.id.textView5);

        String edtValue = i.getStringExtra("edtValue");
        String sublines = i.getStringExtra("subline");

        subline.setText(sublines);
        txtTitle.setText(titleName);
        cancel.setOnClickListener(v -> {
            finish();
        });
        editText.setText(edtValue);

        deleteContent.setOnClickListener(v-> {
            editText.setText("");
        });

        save.setOnClickListener(v -> {
            // Save the EditText content
            String text = editText.getText().toString();
            if (!text.isEmpty()) {
                // Perform save operation (e.g., send to server or save locally)
                Toast.makeText(EditInforActivity.this, "Saved: " + text, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EditInforActivity.this, "Text is empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

}