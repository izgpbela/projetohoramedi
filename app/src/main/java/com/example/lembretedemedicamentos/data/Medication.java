package com.example.lembretedemedicamentos.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// @Entity define que esta classe será uma tabela no banco de dados.
@Entity(tableName = "medications")
public class Medication {

    // @PrimaryKey define a chave primária da tabela. autoGenerate = true faz com que o ID seja gerado automaticamente.
    @PrimaryKey(autoGenerate = true)
    public int id;

    // @NonNull garante que o nome nunca será nulo no banco de dados.
    @NonNull
    public String name;

    public String dosage;
    public int hour;
    public int minute;

    // Construtor vazio é necessário para o Room
    public Medication() {}

    // Construtor para facilitar a criação de objetos
    public Medication(@NonNull String name, String dosage, int hour, int minute) {
        this.name = name;
        this.dosage = dosage;
        this.hour = hour;
        this.minute = minute;
    }
}