package com.example.nwokedisamuel.cryptocheck;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by nwokedi samuel on 7/20/2018.
 */
@Database(entities = cryptoData.class,version =3,exportSchema = false)
public abstract class cryptoDatabase extends RoomDatabase {

    public abstract DAO getDao();


}


