package com.example.suxin.coolweather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.suxin.coolweather.db.CoolWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suxin on 2018/3/18.
 */

public class CoolWeatherDB {

    // 数据库名
    public static final String DB_NAME = "cool_weather";
    // 数据库版本
    public static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    // 构造方法私有化
    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    // 获取CoolWeatherDB实例
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    // Province实例存储到实例
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues cv = new ContentValues();
            cv.put("province_name", province.getProvinceName());
            cv.put("province_code", province.getProvinceCode());
            db.insert("Province", null, cv);
        }
    }

    // 从数据库读取所有的省份信息
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<>();
        Cursor cr = db.rawQuery("select * from Province", null);
        if (cr.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cr.getInt(cr.getColumnIndex("id")));
                province.setProvinceName(cr.getString(cr.getColumnIndex("province_name")));
                province.setProvinceCode(cr.getString(cr.getColumnIndex("province_code")));
                list.add(province);
            } while (cr.moveToNext());
        }
        if (cr != null) {
            cr.close();
        }
        return list;
    }

    // City实例存储到实例
    public void saveCity(City city) {
        if (city != null) {
            ContentValues cv = new ContentValues();
            cv.put("city_name", city.getCityName());
            cv.put("city_code", city.getCityCode());
            cv.put("province_id", city.getProvinceId());
            db.insert("City", null, cv);
        }
    }

    // 从数据库读取所有的省份信息
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<>();
        Cursor cr = db.rawQuery("select * from City where province_id = '"+ String.valueOf(provinceId) +"'", null);
        if (cr.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cr.getInt(cr.getColumnIndex("id")));
                city.setCityName(cr.getString(cr.getColumnIndex("city_name")));
                city.setCityCode(cr.getString(cr.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cr.moveToNext());
        }
        if (cr != null) {
            cr.close();
        }
        return list;
    }

    // County实例存储到实例
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues cv = new ContentValues();
            cv.put("county_name", county.getCountyName());
            cv.put("county_code", county.getCountyCode());
            cv.put("city_id", county.getCityId());
            db.insert("County", null, cv);
        }
    }

    // 从数据库读取所有的省份信息
    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<>();
        Cursor cr = db.rawQuery("select * from County where city_id = '"+ String.valueOf(cityId) +"'", null);
        if (cr.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cr.getInt(cr.getColumnIndex("id")));
                county.setCountyName(cr.getString(cr.getColumnIndex("county_name")));
                county.setCountyCode(cr.getString(cr.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            } while (cr.moveToNext());
        }
        if (cr != null) {
            cr.close();
        }
        return list;
    }


}
