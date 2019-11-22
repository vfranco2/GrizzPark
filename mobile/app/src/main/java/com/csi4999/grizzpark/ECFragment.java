package com.csi4999.grizzpark;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ECFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ec, null);
    }
}
