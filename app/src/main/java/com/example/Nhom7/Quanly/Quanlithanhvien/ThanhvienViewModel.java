package com.example.Nhom7.Quanly.Quanlithanhvien;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.Nhom7.LOPPRODUCT.ThanhVien;
import com.example.Nhom7.LOPDAO.ThanhVienDao;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ThanhvienViewModel extends AndroidViewModel {
    ThanhVienDao vienDao;
    MutableLiveData<List<ThanhVien>> liveData;

    public ThanhvienViewModel(@NonNull @NotNull Application application) {
        super(application);
        liveData = new MutableLiveData<>();
        vienDao = new ThanhVienDao(application);
    }

    public MutableLiveData<List<ThanhVien>> getLiveData() {
        loaddl();
        return liveData;
    }

    public void loaddl() {
        List<ThanhVien> list = new ArrayList<>();
        list = vienDao.GETTV();
        liveData.setValue(list);
    }
}