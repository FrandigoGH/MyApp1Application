package com.example.paco.myapp1application;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Paco on 12/04/2016.
 */
public class OtherFragment extends Fragment {
    static String KEY_POSITION = "position";

    static OtherFragment newInstance(int position) {
        OtherFragment frag=new OtherFragment();
        Bundle args=new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);
        return(frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.othereditor, container, false);
        EditText editorX=(EditText)result.findViewById(R.id.editorX);
        int position=getArguments().getInt(KEY_POSITION, -1);
        editorX.setHint(String.format(getString(R.string.hint), position + 1));
        return(result);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.hint), position + 1));
    }
}
