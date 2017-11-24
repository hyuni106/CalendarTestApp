package kr.co.tjeit.calendartestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private android.widget.EditText inputEdt;
    private android.widget.Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.inputEdt = (EditText) findViewById(R.id.inputEdt);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalData.test.add(inputEdt.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("result_msg", "결과가 넘어간다 얍!");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
