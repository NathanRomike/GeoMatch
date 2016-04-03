package comnathanromike.github.geomatch.Fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends DialogFragment implements View.OnClickListener {
    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.startButton) Button mStartButton;
    @Bind(R.id.goButton) Button mGoButton;
    @Bind(R.id.next_form) RelativeLayout mNextStepsLayout;
    @Bind(R.id.login_form) LinearLayout mLoginForm;


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
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mLoginButton) {
            mNextStepsLayout.setVisibility(View.GONE);
            mLoginForm.setVisibility(View.VISIBLE);
        } else if (view == mStartButton) {
            dismiss();
            FragmentManager fm = getChildFragmentManager();
            LoginFragment loginFragment = LoginFragment.newInstance();
            loginFragment.show(fm, "login_fragment");
        }

    }
}
