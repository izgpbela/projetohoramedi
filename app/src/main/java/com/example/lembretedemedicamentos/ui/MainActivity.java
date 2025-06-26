package com.example.lembretedemedicamentos.ui;

// Daniel Victor & Izabela Gomes

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lembretedemedicamentos.R;
import com.example.lembretedemedicamentos.data.MedicationDao;
import com.example.lembretedemedicamentos.data.MedicationDatabase;
import com.example.lembretedemedicamentos.ui.adapter.MedicationAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "medication_channel";
    private MedicationDao medicationDao;
    private MedicationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Criar canal de notificação
        createNotificationChannel();

        // 2. Obter instância do DAO
        medicationDao = MedicationDatabase.getInstance(this).medicationDao();

        // 3. Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // 4. Criar o adapter com context e DAO
        adapter = new MedicationAdapter(this, medicationDao);
        recyclerView.setAdapter(adapter);

        // 5. Observar o LiveData do banco e atualizar a lista automaticamente
        medicationDao.getAllMedications().observe(this, medications -> {
            adapter.setMedications(medications);
        });

        // 6. Botão para adicionar novo medicamento
        FloatingActionButton fab = findViewById(R.id.fab_add_medication);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddEditMedicationActivity.class);
            startActivity(intent);
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal de Lembretes de Medicamentos";
            String description = "Canal para notificações de horário de medicamentos";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
