package com.example.proyectofinalmadrijeando.ui.Actos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActosViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ActosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Encuentra tus actos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
