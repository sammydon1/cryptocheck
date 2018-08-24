package com.example.nwokedisamuel.cryptocheck;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by nwokedi samuel on 7/20/2018.
 */


@Dao
public interface DAO {

    @Query("SELECT * FROM cryptoCurrency")
    List<cryptoData> getCryptoData();

    @Query("DELETE FROM cryptoCurrency WHERE  id=:id")
    void delete(int id);


    @Insert
    void insert(cryptoData...cryptoData);





}


