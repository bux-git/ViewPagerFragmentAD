package com.dqr.www.viewpagerfragmentad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-05-10 10:37
 */

public class TestFragment extends Fragment {

    private static final String TAG = "TestFragment";
    private TextView mTextView;
    private String text;

    public static TestFragment newInstance(String text){
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
         text=getArguments().getString("text");
        Log.d(TAG+text, "setUserVisibleHint: "+isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG+toString(), "onCreate: "+text);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment_layout, container, false);
        mTextView = (TextView) view.findViewById(R.id.tv_test);

        mTextView.setText(text);
        Log.d(TAG+toString(), "onCreateView: ");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG+toString(), "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG+toString(), "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG+toString(), "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG+toString(), "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG+toString(), "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG+toString(), "onDestroy: ");
    }

    @Override
    public String toString() {
        return text;
    }
}
