package com.chan.csbueger.ui.main;

import android.widget.Toast;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.chan.csbueger.MainActivity;
import com.chan.csbueger.R;
import com.google.android.material.snackbar.Snackbar;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        //@Override
        String TabIndex;

        public String apply(Integer input) {
            //Toast.makeText(PageViewModel.this,"Hello Javatpoint", Toast.LENGTH_SHORT).show();
            //return null;//
            /*if (input.equals(1))
            {
               TabIndex ="Sets";
            }else if (input.equals(2))
            {
                TabIndex ="Burger";
            }else if (input.equals(3))
            {
                TabIndex ="Drinks";
            }*/
            return "Return Value : " + input;
       }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}