package com.example.healthcaresystemproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewProductActivity extends AppCompatActivity {

    private String categoryName, Description, Price, pName, saveCurrentDate, saveCurrentTime;
    private Button AddNewProductButton;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
    private String productRandomKey;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);
        categoryName = getIntent().getExtras().get("Category").toString();
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("products");

        AddNewProductButton = (Button) findViewById(R.id.add_new_product);
        InputProductName = (EditText) findViewById(R.id.product_name);
        InputProductDescription = (EditText) findViewById(R.id.product_description);
        InputProductPrice = (EditText) findViewById(R.id.product_price);
        loadingBar = new ProgressDialog(this);

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });
    }

    private void ValidateProductData(){
        Description = InputProductDescription.getText().toString();
        Price = InputProductPrice.getText().toString();
        pName = InputProductName.getText().toString();

        if(TextUtils.isEmpty(Description)){
            Toast.makeText(this, "Please insert the product's description!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pName)){
            Toast.makeText(this, "Please insert the product's name!", Toast.LENGTH_SHORT).show();
        }
        else{
            StoreProductInformation();
        }

    }

    private void StoreProductInformation() {
        loadingBar.setTitle("Adding New Product");
        loadingBar.setMessage("Please wait, while we are adding the new product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("mm/dd/yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        SaveProductInfoToDatabase();

    }

    private void SaveProductInfoToDatabase(){
        HashMap<String, Object> productMap = new HashMap<>();

        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("Description", Description);
        productMap.put("Category", categoryName);
        productMap.put("price", Price);
        productMap.put("pName", pName);

        ProductsRef.child(productRandomKey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(AdminAddNewProductActivity.this, AdminCategoryActivity.class);
                    loadingBar.dismiss();
                    Toast.makeText(AdminAddNewProductActivity.this, "Product Is Added Successfully!", Toast.LENGTH_SHORT).show();
                }
                else{
                    loadingBar.dismiss();
                    String message = task.getException().toString();
                    Toast.makeText(AdminAddNewProductActivity.this, "Error "+ message, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
