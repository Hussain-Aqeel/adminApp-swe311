package com.example.swe311projecta.Core;

import com.example.swe311projecta.ViewModel.AdminStartUpViewModel;
import com.example.swe311projecta.ViewModel.AdminViewModel;



public class ViewModelFactory {
    private ModelFactory mf;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }
    
    public AdminViewModel getAdminViewModel() {
        return new  AdminViewModel(mf.getAdmin(), mf.getFileIO());
    }
    
    public AdminStartUpViewModel getStartUpViewModel() {
        return new AdminStartUpViewModel(this, mf.getFileIO(), mf.getAdmin());
    }
    

    
    
    public ModelFactory getModelFactory() {
        return mf;
    }
}
