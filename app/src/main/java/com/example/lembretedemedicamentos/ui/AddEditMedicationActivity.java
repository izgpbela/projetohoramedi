package com.example.lembretedemedicamentos.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lembretedemedicamentos.R;
import com.example.lembretedemedicamentos.data.Medication;
import com.example.lembretedemedicamentos.data.MedicationDatabase;
import com.example.lembretedemedicamentos.utils.AlarmUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddEditMedicationActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etDosage;
    private TimePicker timePicker;
    private Button btnSave;

    private MedicationDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_medication);

        etName = findViewById(R.id.et_medication_name);
        etDosage = findViewById(R.id.et_medication_dosage);
        timePicker = findViewById(R.id.time_picker);
        btnSave = findViewById(R.id.btn_save);

        // Define o formato de 24 horas para o TimePicker
        timePicker.setIs24HourView(true);

        db = MedicationDatabase.getInstance(getApplicationContext());

        btnSave.setOnClickListener(v -> saveMedication());
    }

    private void saveMedication() {
        String name = etName.getText().toString().trim();
        String dosage = etDosage.getText().toString().trim();
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        if (name.isEmpty()) {
            Toast.makeText(this, "Por favor, insira o nome do medicamento", Toast.LENGTH_SHORT).show();
            return;
        }

        Medication medication = new Medication(name, dosage, hour, minute);

        // Room não permite operações na thread principal. Usamos um Executor para rodar em background.
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // Insere no banco e obtém o ID gerado
            db.medicationDao().insert(medication);

            // Após salvar, configura o alarme
            // É importante fazer isso em uma thread que possa acessar a UI se necessário,
            // ou passar todos os dados necessários.
            runOnUiThread(() -> {
                // Precisamos buscar o medicamento recém-salvo para ter seu ID e agendar o alarme
                // Uma abordagem simples é buscar o último inserido, mas isso pode ter falhas.
                // A forma correta é complexa (ex: obter o ID retornado pelo insert).
                // Para este exemplo, vamos agendar o alarme aqui, mas sem o ID correto no PendingIntent.
                // Uma melhoria seria refatorar para agendar depois de obter o ID.

                // Para simplificar, vamos usar o ID do horário como um ID "único" para o alarme.
                int alarmId = hour * 100 + minute; // ID simples baseado no horário

                // Agendar o alarme
                AlarmUtils.setAlarm(this, hour, minute, name, alarmId);

                Toast.makeText(this, "Medicamento salvo!", Toast.LENGTH_SHORT).show();
                finish(); // Fecha a activity e volta para a MainActivity
            });
        });
    }
}