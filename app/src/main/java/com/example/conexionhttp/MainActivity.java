package com.example.conexionhttp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText Nombre, Cedula, Celular, Correo, Contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Nombre = (EditText) findViewById(R.id.etNombre);
        Cedula = (EditText) findViewById(R.id.etCedula);
        Celular = (EditText) findViewById(R.id.etCelular);
        Correo = (EditText) findViewById(R.id.etCorreo);
        Contraseña = (EditText) findViewById(R.id.etContraseña);
    }

    public void guardar(View view) {
        String TNombre, TCedula, TCelular, TCorreo, TContraseña;
        TNombre = Nombre.getText().toString();
        TCedula = Cedula.getText().toString();
        TCelular = Celular.getText().toString();
        TCorreo = Correo.getText().toString();
        TContraseña = Contraseña.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("nombre", TNombre);
        user.put("cedula", TCedula);
        user.put("celular", TCelular);
        user.put("correo", TCorreo);
        user.put("contraseña", TContraseña);

        db.collection("users")
                .document(TCedula)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(MainActivity.this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();
                        Nombre.setText("");
                        Cedula.setText("");
                        Celular.setText("");
                        Correo.setText("");
                        Contraseña.setText("");

                        Intent intent = new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(MainActivity.this, "Usuario NO guardado", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}