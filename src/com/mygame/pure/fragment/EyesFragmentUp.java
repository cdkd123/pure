package com.mygame.pure.fragment;

import com.mygame.pure.R;
import com.mygame.pure.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EyesFragmentUp extends BaseFragment{
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.tab_fragment_eyes, container, false);
		return rootView;
	}

	public static EyesFragmentUp newInstance(int type) {
		return null;
	}
}
