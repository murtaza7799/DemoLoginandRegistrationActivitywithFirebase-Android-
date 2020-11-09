package com.java.oop.lab.project.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.java.oop.lab.project.R;


public class CustomerProfile extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       final View v= inflater.inflate(R.layout.fragment_customer_profile, container, false);




       return  v;
    }


    }

