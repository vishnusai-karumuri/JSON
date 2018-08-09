package com.kvsn.builds.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ListView lv;
    Button b;
    RequestQueue rq;
    ArrayAdapter<String> adapter;
    ArrayList<String> al;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= findViewById(R.id.lv);
        b = findViewById(R.id.b1);
        rq = Volley.newRequestQueue(this);

        al = new ArrayList<>();
    }
    public void button(View v)
    {
        String url = "https://api.myjson.com/bins/i5ic4";
        //String url=  "https://api.androidhive.info/contacts/";

        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONArray jarr = response.getJSONArray("KVSN");
                            for(int i=0; i<jarr.length();i++)
                            {
                                JSONObject stu = jarr.getJSONObject(i);
                                String name = stu.getString("Name");
                                long mob = stu.getLong("Age");
                                String s = name + " "+mob;

                                al.add(s);
                                adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,al);
                                lv.setAdapter(adapter);
                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        }
        );
        rq.add(jreq);
    }
}
