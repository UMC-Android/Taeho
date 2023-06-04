package com.example.chapter9_sociallogin;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.chapter9_sociallogin.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onResume() {
        super.onResume();
        if(GoogleSignIn.getLastSignedInAccount(this) != null) {
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Yet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setResultSignUp();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        binding.btnSignIn.setOnClickListener(v -> signIn());
        binding.btnSignOut.setOnClickListener(v -> signOut());
        binding.btnGetProfile.setOnClickListener(v -> getCurrentUserProfile());

        setContentView(binding.getRoot());
    }

    private void setResultSignUp() {
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                // Handle the sign-up result here
                Intent data = result.getData();
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
            }
        });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String email = account.getEmail();
            String familyName = account.getFamilyName();
            String givenName = account.getGivenName();
            String displayName = account.getDisplayName();
            String photoUrl = account.getPhotoUrl().toString();

            Log.d("로그인한 유저의 이메일", email);
            Log.d("로그인한 유저의 성", familyName);
            Log.d("로그인한 유저의 이름", givenName);
            Log.d("로그인한 유저의 전체이름", displayName);
            Log.d("로그인한 유저의 프로필 사진의 주소", photoUrl);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GooglesignlnStatusCodes class reference for more information.
            Log.w("failed", "signInResult failed code=" + e.getStatusCode());
        }
    }

    private void signIn() {
        if (mGoogleSignInClient != null && GoogleSignIn.getLastSignedInAccount(this) != null) {
            // 이미 로그인된 상태
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
        } else {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            resultLauncher.launch(signInIntent);
        }
    }

    private void signOut() {
        if (mGoogleSignInClient != null && GoogleSignIn.getLastSignedInAccount(this) != null) {
            mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // 로그아웃 성공
                    Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                } else {
                    // 로그아웃 실패
                    Toast.makeText(this, "Failed to Log Out", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // 이미 로그아웃된 상태
            Toast.makeText(this, "Already Logged Out", Toast.LENGTH_SHORT).show();
        }
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this, task -> {
            // ...
        });
    }

    private void getCurrentUserProfile() {
        GoogleSignInAccount curUser = GoogleSignIn.getLastSignedInAccount(this);
        if(curUser != null) {
            String email = curUser.getEmail();
            String familyName = curUser.getFamilyName();
            String givenName = curUser.getGivenName();
            String displayName = curUser.getDisplayName();
            String photoUrl = curUser.getPhotoUrl().toString();

            Log.d("현재 로그인 되어있는 유저의 이메일", email);
            Log.d("현재 로그인 되어있는 유저의 성", familyName);
            Log.d("현재 로그인 되어있는 유저의 이름", givenName);
            Log.d("현재 로그인 되어있는 유저의 전체이름", displayName);
            Log.d("현재 로그인 되어있는 유저의 프로필 사진의 주소", photoUrl);
        }
    }

}