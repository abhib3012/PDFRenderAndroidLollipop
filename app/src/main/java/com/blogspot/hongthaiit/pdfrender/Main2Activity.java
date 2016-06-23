package com.blogspot.hongthaiit.pdfrender;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends Activity {

    JSONObject obj = null;
    List<HashMap<String, String>> formList;
    String formula_value, url_value, key, value;
    ListView myListView;
    HashMap<String, String> m_li;

   // Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        button = (Button) findViewById(R.id.button);

        myListView = (ListView) findViewById(R.id.myListView);

        loadJSON();

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("sample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.i("Done", "Done");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.i("This Not", "Done");
        return json;

    }

    public void loadJSON() {

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("formules");
            formList = new ArrayList<HashMap<String, String>>();


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("formule"));
                formula_value = jo_inside.getString("formule");
                url_value = jo_inside.getString("url");

                Log.i(formula_value, url_value);
                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<>();
                m_li.put("formule", formula_value);
                m_li.put("url", url_value);

                formList.add(m_li);
            }
            Log.i("Done", "Done");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("This also Not", "Done");
        }

        ListAdapter adapter = new SimpleAdapter(getApplicationContext(), formList, android.R.layout.simple_list_item_1, new String[]{"formule"}, new int[]{android.R.id.text1});
        myListView.setAdapter(adapter);
        Log.i("Keyset is", m_li.keySet().toString());

        for (HashMap<String, String> map : formList) {
            for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                key = mapEntry.getKey();
                value = mapEntry.getValue();
            }
        }

        Log.i("Key is", key);
        Log.i("Value is", value);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //   String[] itemString = (String[]) adapterView.getItemAtPosition(i);
                String itemString = formList.get(i).toString();

                Log.i("Tapped on: ", formList.get(i).get("formule"));
                Log.i("Tapped on: ", formList.get(i).get("url"));

                goIntent();
                //File pdffile = new File("file:///android_assets/sample.pdf");
//                File pdffile = new File("res/assets/sample.pdf");
//                File ff = new File(getAssets().open("sample.pdf"));
//                Uri path = Uri.fromFile(pdffile);
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                try {
//                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.setDataAndType(path, "application/pdf");
//                    startActivity(intent);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


//                myintent = new Intent(MainActivity.this, lectureActivity.class)
//                .putExtra("PDFUrl", "hello");
//                startActivity(myintent);


                //Log.i("Tapped on: ", m_li.get(itemString[i]));
//            }
//        });


            }
        });
    }

    public void goIntent() {

        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);

    }
}
