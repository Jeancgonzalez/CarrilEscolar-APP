package com.example.conexionhttp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {

    EditText usuario, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuario=(EditText) findViewById(R.id.etUsuario);
        contraseña=(EditText) findViewById(R.id.etContrasena);
    }

    public void loginn(View view) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(usuario.getText().toString())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String contrasena = documentSnapshot.getString("contraseña");
                            //Toast.makeText(login.this, "Contraseña"+contraseña,Toast.LENGTH_LONG).show();
                            if((contraseña.getText().toString()).equals(contrasena)){
                                Intent intent= new Intent(login.this, menu.class);
                                intent.putExtra("cedula",usuario.getText().toString());
                                startActivity(intent);
                            }else{
                                Toast.makeText(login.this, "Usuario o Contraseña incorrectos",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    public void registrar(View view) {
        Intent intent= new Intent(login.this, MainActivity.class);
        startActivity(intent);
    }
    }


