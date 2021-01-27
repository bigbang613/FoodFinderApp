package com.vishwanath.rbcproject.foodfinderapp.view.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vishwanath.rbcproject.foodfinderapp.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment implements BaseView {
    
    private BaseActivity mBaseActivity;
    private Presenter mPresenter;
    
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        
        if (context instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) context;
        } else {
            throw new RuntimeException(String.format("A BaseFragment must attach to %s", BaseActivity.class.getName()));
        }
    }
    
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        mBaseActivity = null;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
    
    protected void setPresenter(Presenter presenter) {
        this.mPresenter = presenter;
    }
    
    @Override
    public void setTitle(String title) {
        mBaseActivity.setTitle(title);
    }

    public void showDialog(String title, String message) {
        new AlertDialog.Builder(mBaseActivity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.label_ok, (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mBaseActivity, message, Toast.LENGTH_LONG).show();
    }

}
