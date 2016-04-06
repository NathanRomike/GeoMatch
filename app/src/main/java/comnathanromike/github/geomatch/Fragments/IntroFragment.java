package comnathanromike.github.geomatch.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.ui.AboutActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends DialogFragment implements View.OnClickListener {
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.startButton) Button mStartButton;
    @Bind(R.id.goButton) Button mGoButton;
    @Bind(R.id.next_form) RelativeLayout mNextStepsLayout;
    @Bind(R.id.login_form) LinearLayout mLoginForm;
    @Bind(R.id.errorTextView) TextView mErrorTextView;

    private Firebase mFirebaseRef;
    private Firebase.AuthResultHandler mAuthResultHandler;


    public IntroFragment() {
        // Required empty public constructor
    }

    public static IntroFragment newInstance() {
        return new IntroFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_into, container, false);
        ButterKnife.bind(this, view);

        mLoginButton.setOnClickListener(this);
        mStartButton.setOnClickListener(this);
        mGoButton.setOnClickListener(this);

        initializeAuthResultHandler();

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mLoginButton) {
            mNextStepsLayout.setVisibility(View.GONE);
            mLoginForm.setVisibility(View.VISIBLE);
        } else if (view == mStartButton) {
            dismiss();
            Intent intent = new Intent(getActivity(), AboutActivity.class);
            startActivity(intent);
        } else if (view == mLoginButton) {
            loginWithPassword();
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
                mErrorTextView.setVisibility(View.VISIBLE);
            }
        };
    }
}
