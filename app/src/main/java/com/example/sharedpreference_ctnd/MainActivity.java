package com.example.sharedpreference_ctnd;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputA, inputB;
    private TextView resultValue;
    private Button btnSum, btnSub, btnClear;
    private ListView historyList;
    private ArrayAdapter<String> historyAdapter;
    private ArrayList<String> historyItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        resultValue = findViewById(R.id.resultValue);
        btnSum = findViewById(R.id.btnSum);
        btnSub = findViewById(R.id.btnSub);  // Nút trừ
        btnClear = findViewById(R.id.btnClear);
        historyList = findViewById(R.id.historyList);

        // Thiết lập danh sách lịch sử
        historyItems = new ArrayList<>();
        historyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyItems);
        historyList.setAdapter(historyAdapter);

        // Xử lý nút TỔNG
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strA = inputA.getText().toString();
                String strB = inputB.getText().toString();

                if (!strA.isEmpty() && !strB.isEmpty()) {
                    int a = Integer.parseInt(strA);
                    int b = Integer.parseInt(strB);
                    int sum = a + b;

                    resultValue.setText(String.valueOf(sum));

                    // Lưu lịch sử
                    String historyEntry = a + " + " + b + " = " + sum;
                    saveHistory(historyEntry);
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý nút TRỪ
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strA = inputA.getText().toString();
                String strB = inputB.getText().toString();

                if (!strA.isEmpty() && !strB.isEmpty()) {
                    int a = Integer.parseInt(strA);
                    int b = Integer.parseInt(strB);
                    int difference = a - b;

                    resultValue.setText(String.valueOf(difference));

                    // Lưu lịch sử
                    String historyEntry = a + " - " + b + " = " + difference;
                    saveHistory(historyEntry);
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý nút CLEAR
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputA.setText("");
                inputB.setText("");
                resultValue.setText("0");
                historyAdapter.notifyDataSetChanged();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void saveHistory(String entry) {
        historyItems.add(entry);
        historyAdapter.notifyDataSetChanged();
    }
}