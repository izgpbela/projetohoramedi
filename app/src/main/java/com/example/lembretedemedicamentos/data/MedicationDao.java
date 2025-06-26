package com.example.lembretedemedicamentos.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// @Dao informa ao Room que esta é uma interface de acesso a dados (Data Access Object).
@Dao
public interface MedicationDao {

    // @Insert define um método para inserir um medicamento.
    @Insert
    void insert(Medication medication);

    // @Update define um método para atualizar um medicamento.
    @Update
    void update(Medication medication);

    // @Delete define um método para deletar um medicamento.
    @Delete
    void delete(Medication medication);

    // @Query permite que você escreva suas próprias consultas SQL.
    // Esta consulta busca todos os medicamentos, ordenados por hora e minuto.
    // Usar LiveData faz com que a UI seja notificada automaticamente quando os dados mudam.
    @Query("SELECT * FROM medications ORDER BY hour, minute")
    LiveData<List<Medication>> getAllMedications();
}