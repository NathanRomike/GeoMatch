package comnathanromike.github.geomatch.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.SnapMapApplication;

public class LoginFragment extends DialogFragment implements View.OnClickListener {

    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.passwordLoginButton) Button mPasswordLoginButton;
    @Bind(R.id.registerButton) Button mRegisterButton;
    @Bind(R.id.errorTextView) TextView mErrorTextView;

    private Firebase mFirebaseRef;
    private View view;
    private Firebase.AuthResultHandler mAuthResultHandler;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        mFirebaseRef = SnapMapApplication.getAppInstance().getFirebaseRef();

        mPasswordLoginButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
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
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        mFirebaseRef.authWithPassword(email, password, mAuthResultHandler);
    }

    private void initializeAuthResultHandler() {
        mAuthResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                dismiss();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                mErrorTextView.setText(firebaseError.toString());
            }
        };
    }

    public void registerNewUser() {
        final String email = mEmailEditText.getText().toString();
        final String password = mPasswordEditText.getText().toString();

        mFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {

            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                mFirebaseRef.authWithPassword(email, password, mAuthResultHandler);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                mErrorTextView.setText(firebaseError.toString());
            }
        });
    }
}
