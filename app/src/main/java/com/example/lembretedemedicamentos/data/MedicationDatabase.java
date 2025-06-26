package com.example.lembretedemedicamentos.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// @Database declara o banco de dados, listando suas entidades e a versão.
@Database(entities = {Medication.class}, version = 1, exportSchema = false)
public abstract class MedicationDatabase extends RoomDatabase {

    public abstract MedicationDao medicationDao();

    // A instância do banco de dados deve ser Singleton (apenas uma instância para todo o app).
    private static volatile MedicationDatabase INSTANCE;

    public static MedicationDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MedicationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MedicationDatabase.class,
                            "medication_database" // Nome do arquivo do banco de dados
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}