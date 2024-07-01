package com.example.tiktokapp.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.R;
import com.example.tiktokapp.utils.IntentUtil;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView avatar, btnBack;
    private TextView txtUsername, tiktokID, copyID;

    private String username, fullname, avt;
    private int userID;
    private android.widget.Toast Toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        avatar = findViewById(R.id.userAvatar);
        txtUsername = findViewById(R.id.nameOfUser);
        tiktokID = findViewById(R.id.tiktokID);
        btnBack = findViewById(R.id.btnBack);
        copyID = findViewById(R.id.copyID);

        txtUsername.setText(fullname);
        tiktokID.setText(username);

        loadPreferences();

        txtUsername.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditInforActivity.class);
            intent.putExtra("title", "Tên");
            intent.putExtra("method", true);
            intent.putExtra("id", userID);
            intent.putExtra("fullname", fullname);
            intent.putExtra("username", username);
            intent.putExtra("subline", "Tên");
            startActivity(intent);
        });

        tiktokID.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditInforActivity.class);
            intent.putExtra("title", "Tiktok ID");
            intent.putExtra("method", false);
            intent.putExtra("id", userID);
            intent.putExtra("fullname", fullname);
            intent.putExtra("username", username);
            intent.putExtra("subline", "www.tiktok.com/@" + username);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> {
            finish();
        });

        copyID.setOnClickListener(v -> {
            copyID(username);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload preferences in case they have been updated
        loadPreferences();

        txtUsername.setText(fullname);
        copyID.setText(username);
        tiktokID.setText(username);

        Uri avatarUri = Uri.parse(avt);
        Glide.with(this)
                .load(avatarUri)
                .into(avatar);
    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        username = preferences.getString("username", "");
        fullname = preferences.getString("fullName", "");
        avt = preferences.getString("avatar", "");
        userID = preferences.getInt("userID", -1);

        Log.d("checkin", "username: " + username);
        Log.d("checkin", "fullname: " + fullname);
    }

    public void copyID(String tiktokID) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("TikTok ID", tiktokID);
        clipboard.setPrimaryClip(clip);

        // Show a Toast message to inform the user that the ID has been copied
        Toast.makeText(this, "TikTok ID copied to clipboard", Toast.LENGTH_SHORT).show();
    }
}
