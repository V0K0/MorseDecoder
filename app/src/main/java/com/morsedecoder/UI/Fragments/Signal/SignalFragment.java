package com.morsedecoder.UI.Fragments.Signal;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.morsedecoder.Domain.Flashlight;
import com.morsedecoder.HelpClasses.UserDialogs;
import com.morsedecoder.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignalFragment extends Fragment {

    @BindView(R.id.imageViewSos) ImageView imageViewSOS;
    @BindView(R.id.imageViewPower) ImageView imageViewPower;
    @BindView(R.id.imageViewMessage) ImageView imageViewMessage;

    private BottomNavigationView bottomMenu;
    private OnSendRequest onSendRequest;

    private boolean isActive = false;
    private int powerDefaultIconRes;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_signal, container, false);
        unbinder = ButterKnife.bind(this, view);
        bottomMenu = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_navigation);
        powerDefaultIconRes = getResources().getIdentifier(imageViewPower.toString(), "drawable", getActivity().getPackageName());
        return view;
    }

    public void setOnSendRequest(OnSendRequest onSendRequest) {
        this.onSendRequest = onSendRequest;
    }

    public void sendSignalWithMessage(String msg) {
        if (!isActive) {
            isActive = true;
            imageViewPower.setImageResource(R.drawable.ic_power_off_144dp);
            boolean hasFlash = getActivity().getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
            if (!hasFlash) {
                UserDialogs.ShowAlertDialog(getContext(), getString(R.string.ERROR_FLASH));
            } else {
                Flashlight.getInstance().sendSignal(msg);
            }
        }
    }

    public interface OnSendRequest {
        String requestMessage();
    }

    @OnClick(R.id.imageViewPower)
    void onPowerClick() {
        if (isActive) {
            stopWork();
        }
    }

   @OnClick(R.id.imageViewMessage)
   void onRequestMessageClick  ()  {
        if (!isActive && onSendRequest != null) {
            String message = onSendRequest.requestMessage();
            if (message != null && !message.isEmpty()) {
                sendSignalWithMessage(message);
            } else {
                Toast.makeText(getContext(), getString(R.string.message_request_failed), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.imageViewSos)
    void onSOSClick() {
        if (!isActive) {
            isActive = true;
            imageViewPower.setImageResource(R.drawable.ic_power_off_144dp);
            boolean hasFlash = getActivity().getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
            if (!hasFlash) {
                UserDialogs.ShowAlertDialog(getContext(), getString(R.string.ERROR_FLASH));
            } else {
                Flashlight.getInstance().sendSignal("... --- ...");
            }
        }
    }

    private void stopWork() {
        isActive = false;
        imageViewPower.setImageResource(powerDefaultIconRes);
        Flashlight.getInstance().interruptFlashThread();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isActive) {
            stopWork();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
