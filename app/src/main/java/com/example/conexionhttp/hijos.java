package com.example.conexionhttp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class hijos extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton opcionSeleccionada;

    String cedula;

    String seleccion;

    EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hijos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        cedula= intent.getStringExtra("cedula");

        nombre=(EditText) findViewById(R.id.etHijo);

        radioGroup= findViewById(R.id.radioGroupOpciones);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                opcionSeleccionada=findViewById(i);
                seleccion=opcionSeleccionada.getText().toString();
            }
        });


        //****************************************************
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        LinearLayout contenedor = findViewById(R.id.contenedorHijos);

        db.collection("users")
                .document(cedula)
                .collection("hijos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String idHijo = document.getId();
                            String nombreHijo = document.getString("nombre");
                            String seccion = document.getString("secccion");

                            // Crear un layout horizontal para cada hijo
                            LinearLayout layoutHijo = new LinearLayout(this);
                            layoutHijo.setOrientation(LinearLayout.HORIZONTAL);
                            layoutHijo.setPadding(0, 10, 0, 10);

                            // Crear TextView con info del hijo
                            TextView textView = new TextView(this);
                            textView.setText("Nombre: " + nombreHijo + ", Sección: " + seccion);
                            textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2));

                            // Botón eliminar
                            Button eliminarBtn = new Button(this);
                            eliminarBtn.setText("Eliminar");
                            eliminarBtn.setLayoutParams(new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                            ));

                            eliminarBtn.setOnClickListener(v -> {
                                db.collection("users")
                                        .document(cedula)
                                        .collection("hijos")
                                        .document(idHijo)
                                        .delete()
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(this, "Hijo eliminado", Toast.LENGTH_SHORT).show();
                                            contenedor.removeView(layoutHijo);
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                                        });
                            });

                            // Agregar elementos al layout del hijo
                            layoutHijo.addView(textView);
                            layoutHijo.addView(eliminarBtn);

                            // Agregar el layout del hijo al contenedor principal
                            contenedor.addView(layoutHijo);
                        }
                    } else {
                        Toast.makeText(this, "Error obteniendo los hijos", Toast.LENGTH_SHORT).show();
                    }
                });

        //****************************************************
    }

    public void crear(View view) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user with a first and last name
        Map<String, Object> hijo = new HashMap<>();
        hijo.put("nombre", nombre.getText().toString());
        hijo.put("secccion",seleccion);

        db.collection("users")
                .document(cedula)
                .collection("hijos")
                .add(hijo)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(hijos.this, "Hijo agregado correctamente",Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(hijos.this, "Hijo NO se agrego",Toast.LENGTH_LONG).show();
                });
    }

    public void atras(View view) {
        Intent intent= new Intent(hijos.this, menu.class);
        startActivity(intent);
    }
}