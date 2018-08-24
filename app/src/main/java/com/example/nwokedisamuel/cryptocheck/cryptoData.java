package com.example.nwokedisamuel.cryptocheck;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;

import java.sql.Blob;

/**
 * Created by nwokedi samuel on 7/17/2018.
 */

@Entity(tableName = "cryptoCurrency")
public class cryptoData{
    @PrimaryKey(autoGenerate = true)
    int id;
    String cryptValue;
    String currencySymbol;
    int icon;



    public cryptoData(int id, String cryptValue,String currencySymbol,int icon) {
        this.id = id;
        this.cryptValue = cryptValue;
         this.currencySymbol=currencySymbol;
         this.icon=icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCurrencySymbol() {

        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCryptValue() {
        return cryptValue;
    }

    public void setCryptValue(String cryptValue) {
        this.cryptValue = cryptValue;
    }

}
