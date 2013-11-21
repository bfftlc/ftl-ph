package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
         
        JSONObject json =	getJSONfromURL();

        try{
		      	//Get the element that holds the earthquakes ( JSONArray )
		      	JSONArray  messages = json.getJSONArray("messages");
		
		      	/*Note from Katie: For this part you have to make a text view on the activity_main.xml
		      	(either the graphical layout or xml is fine) and then make an intance of a text view object
		      	here and point it to the id of the one from the xml. This way we can now do anything we want
		      	to it (change the text, color, styling, etc.). In this case I put on the graphical layout a
		      	linear layout with columns and then made one text view in the left column for the "froms" 
		      	and one text view in the right column for the "messages". Any questions let me know! */      	
		      	
		      	TextView tvFrom = (TextView) findViewById(R.id.textView1);
		      	tvFrom.setText("");
		      	TextView tvMessage = (TextView) findViewById(R.id.textView3);
		      	tvMessage.setText("");
            	
		      	//Loop the Array
		      	for(int i=0;i < messages.length();i++){						

	              	HashMap<String, String> map = new HashMap<String, String>();
	              	JSONObject e = messages.getJSONObject(i);
	
	              	map.put("id",  String.valueOf(i));
	              	map.put("from", e.getString("from"));
	              	map.put("time", e.getString("time"));
	              	map.put("message", e.getString("message"));
	              	
	               
	              	tvFrom.setTextColor(Color.RED);
	              	tvFrom.setTypeface(null, Typeface.BOLD);
	              	tvFrom.append(map.get("from") + ":" + "\n");
	              	tvMessage.setTextColor(Color.BLUE);
	              	tvMessage.setTypeface(null, Typeface.NORMAL);
	              	tvMessage.append(map.get("message") + "\n");
	              	              	
	              	mylist.add(map);
              	
		      	}
         }
         catch(JSONException e) {
         	 Log.e("log_tag", "Error parsing data "+e.toString());
         }        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    


	public static JSONObject getJSONfromURL(){
	
		String jsonyo = "{\"messages\": [{\"from\": \"kfspurple\",\"time\": \"12:15\",\"message\": \"type mismatches are my favorite error and cyan is my favorite  color.\"      },{        \"from\": \"Mari$$a\",        \"time\": \"12:16\",        \"message\": \"Isn’t Cyan Cat a thing?\"},{        \"from\": \"MrsJSON\",        \"time\": \"12:17\",        \"message\": \"Like...what the lump?\"     }]}";
				
		//initialize
		//InputStream is = null;
		String result = jsonyo;
		JSONObject jArray = null;
	
		//try parse the string to a JSON object
		try{
	        	jArray = new JSONObject(result);
		}catch(JSONException e){
			Log.e("log_tag", "Error parsing data "+e.toString());
		}
	
		return jArray;
	} 

}