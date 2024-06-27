package com.example.tiktokapp.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.R;
import com.example.tiktokapp.activity.HomeActivity;
import com.example.tiktokapp.activity.LoginActivity;
import com.example.tiktokapp.responseModel.APIRespone;
import com.example.tiktokapp.responseModel.APIResponeList;
import com.example.tiktokapp.responseModel.Post;
import com.example.tiktokapp.responseModel.SimpleAPIRespone;
import com.example.tiktokapp.responseModel.User;
import com.example.tiktokapp.services.ServiceGenerator;
import com.example.tiktokapp.utils.HttpUtil;
import com.example.tiktokapp.utils.IntentUtil;
import com.google.android.material.button.MaterialButton;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInfoFragment extends Fragment {
    private CircleImageView avatar;
    private TextView username;
    MaterialButton btnLogout;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);
        avatar = view.findViewById(R.id.avatar);
        username = view.findViewById(R.id.username);
        preferences = view.getContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = preferences.edit();
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            editor.clear();
            editor.commit();
            IntentUtil.changeActivity(view.getContext(), HomeActivity.class);
        });
        getMyInfo(view.getContext(),view);
        return view;
    }
    private void getMyInfo(Context context,View view) {
        ServiceGenerator.createUserService(context).me().enqueue(new Callback<APIRespone<User>>() {
            @Override
            public void onResponse(Call<APIRespone<User>> call, Response<APIRespone<User>> response) {
                if (response.isSuccessful()) {
                    APIRespone<User> apiResponse = response.body();
                    User user = apiResponse.getData();
                    Uri avatarUri = Uri.parse(user.getAvatarData().getUrl().toString());
                    Glide.with(view.getContext())
                            .load(avatarUri)
                            .into(avatar);
                    username.setText(user.getUserName());
                }else {
                    try {
                        SimpleAPIRespone errResponse = HttpUtil.parseError(response, SimpleAPIRespone.class,context);
                        Toast.makeText(context, "Error: " + errResponse.getMes(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<APIRespone<User>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}