package com.example.conexionhttp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class menu extends AppCompatActivity {

    String cedula;

    RadioGroup radioGroup;
    RadioButton opcionSeleccionada;
    String seleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        cedula= intent.getStringExtra("cedula");

        radioGroup= findViewById(R.id.radioGroupOpciones);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                opcionSeleccionada=findViewById(i);
                seleccion=opcionSeleccionada.getText().toString();
            }
        });
    }

    public void llegue(View view) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users")
                .document(cedula)
                .update("carril", seleccion)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(menu.this, "Carril actualizado", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(menu.this, "Error al actualizar carril", Toast.LENGTH_SHORT).show();
                });
    }

    public void listo(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users")
                .document(cedula)
                .update("carril", 0)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(menu.this, "Gracias!!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(menu.this, "Error al actualizar carril", Toast.LENGTH_SHORT).show();
                });
    }

    public void salir(View view) {
        Intent intent= new Intent(menu.this, login.class);

        startActivity(intent);

    }

    public void hijos(View view) {
        Intent intent= new Intent(menu.this, hijos.class);
        intent.putExtra("cedula",cedula);
        startActivity(intent);

    }
}