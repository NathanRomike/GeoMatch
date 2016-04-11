package comnathanromike.github.geomatch.fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.SnapMapApplication;
import comnathanromike.github.geomatch.ui.PuzzleListActivity;

public class LoginFragment extends DialogFragment implements View.OnClickListener {

    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.passwordLoginButton) Button mPasswordLoginButton;
    @Bind(R.id.registerButton) Button mRegisterButton;
    @Bind(R.id.errorTextView) TextView mErrorTextView;

    private Firebase mFirebaseRef;
    private View view;
    private Firebase.AuthResultHandler mAuthResultHandler;
    private ProgressDialog mAuthProgressDialog;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        mFirebaseRef = SnapMapApplication.getAppInstance().getFirebaseRef();

        mPasswordLoginButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);

        initializeProgressDialog();
        initializeAuthResultHandler();

        return view;
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onClick(View view) {
        if (view == mPasswordLoginButton) {
            loginWithPassword();
        }
        if (view == mRegisterButton) {
            registerNewUser();
        }
    }

    public void loginWithPassword() {
        mAuthProgressDialog.show();
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        mFirebaseRef.authWithPassword(email, password, mAuthResultHandler);
    }

    private void initializeProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this.getContext());
        mAuthProgressDialog.setTitle("Loading");
        mAuthProgressDialog.setMessage("Confirming authentication...");
        mAuthProgressDialog.setCancelable(true);
    }

    private void initializeAuthResultHandler() {
        mAuthResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userName", mNameEditText.getText().toString());
                if(authData.getProviderData().containsKey("email")) {
                    map.put("email", authData.getProviderData().get("email").toString());
                }
                mFirebaseRef.child("users").child(authData.getUid()).setValue(map);

                dismiss();
                Intent intent = new Intent(getActivity(), PuzzleListActivity.class);
                startActivity(intent);
                mAuthProgressDialog.hide();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                mAuthProgressDialog.hide();
                showErrorDialog(firebaseError.toString());
//                mErrorTextView.setText(firebaseError.toString());
            }
        };
    }

    public void registerNewUser() {
        mAuthProgressDialog.show();
        final String email = mEmailEditText.getText().toString();
        final String password = mPasswordEditText.getText().toString();

        mFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {

            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                mFirebaseRef.authWithPassword(email, password, mAuthResultHandler);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                mAuthProgressDialog.hide();
                showErrorDialog(firebaseError.toString());
//                mErrorTextView.setText(firebaseError.toString());
            }
        });
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this.getContext())
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
