package com.example.lembretedemedicamentos.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lembretedemedicamentos.R;
import com.example.lembretedemedicamentos.data.Medication;
import com.example.lembretedemedicamentos.data.MedicationDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder> {

    private List<Medication> medications = new ArrayList<>();
    private Context context;
    private MedicationDao medicationDao;

    // ✅ Novo construtor com Context e DAO
    public MedicationAdapter(Context context, MedicationDao medicationDao) {
        this.context = context;
        this.medicationDao = medicationDao;
    }

    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medication, parent, false);
        return new MedicationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
        Medication currentMedication = medications.get(position);
        holder.tvName.setText(currentMedication.name);
        holder.tvDosage.setText(currentMedication.dosage);
        String time = String.format(Locale.getDefault(), "%02d:%02d", currentMedication.hour, currentMedication.minute);
        holder.tvTime.setText(time);

        // ✅ Clique no botão de excluir
        holder.btnDelete.setOnClickListener(v -> {
            new Thread(() -> {
                medicationDao.delete(currentMedication); // Exclui do banco
                ((Activity) context).runOnUiThread(() -> {
                    int pos = holder.getAdapterPosition();
                    medications.remove(pos);              // Remove da lista
                    notifyItemRemoved(pos);               // Atualiza RecyclerView
                    Toast.makeText(context, "Medicamento excluído", Toast.LENGTH_SHORT).show();
                });
            }).start();
        });
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
        notifyDataSetChanged();
    }

    static class MedicationViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDosage, tvTime;
        ImageButton btnDelete;

        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_medication_name);
            tvDosage = itemView.findViewById(R.id.tv_medication_dosage);
            tvTime = itemView.findViewById(R.id.tv_medication_time);
            btnDelete = itemView.findViewById(R.id.button_delete); // ID do botão de excluir
        }
    }
}
