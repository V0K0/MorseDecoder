package com.morsedecoder.UI.Fragments.Signal;

import android.content.pm.PackageManager;
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

public class SignalFragment extends Fragment {

    private ImageView imageViewSOS;
    private ImageView imageViewPower;
    private ImageView imageViewMessage;
    private BottomNavigationView bottomMenu;
    private OnSendRequest onSendRequest;

    private boolean isActive = false;
    private int powerDefaultIconRes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_signal, container, false);
        imageViewSOS = view.findViewById(R.id.imageViewSos);
        imageViewPower = view.findViewById(R.id.imageViewPower);
        imageViewMessage = view.findViewById(R.id.imageViewMessage);
        bottomMenu = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_navigation);
        imageViewPower.setOnClickListener(onPowerClick);
        imageViewSOS.setOnClickListener(onSOSClick);
        imageViewMessage.setOnClickListener(onRequestMessageClick);
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

    private ImageView.OnClickListener onPowerClick = (View view) -> {
        if (isActive) {
            stopWork();
        }
    };

    private ImageView.OnClickListener onRequestMessageClick = (View view) -> {
        if (!isActive && onSendRequest != null) {
            String message = onSendRequest.requestMessage();
            if (message != null && !message.isEmpty()) {
                sendSignalWithMessage(message);
            } else {
                Toast.makeText(getContext(), getString(R.string.message_request_failed), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private ImageView.OnClickListener onSOSClick = (View view) -> {
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
    };

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
}
