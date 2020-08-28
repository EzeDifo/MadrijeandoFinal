package com.example.proyectofinalmadrijeando.ui.Subir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SubirViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public SubirViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Encuentra tus materiales");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
