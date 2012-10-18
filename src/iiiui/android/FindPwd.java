package iiiui.android;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class FindPwd extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.findpwd);
		View bl = findViewById(R.id.findpwd_top_btn_left);
		bl.setClickable(true);
		bl.setOnClickListener(new OnClickListener(){
	        public void onClick(View v) {
//	        	Intent myIntent = new Intent(Registry.this,iiiui.class);
//	            startActivity(myIntent);
	        	FindPwd.this.finish();
	        }
	    });
		View br = findViewById(R.id.findpwd_top_btn_right);
		br.setClickable(true);
		br.setOnClickListener(new OnClickListener(){
	        public void onClick(View v) {
	        	RestTemplate restTemplate = new RestTemplate();
	        	MappingJacksonHttpMessageConverter hmc = new MappingJacksonHttpMessageConverter();
	        	restTemplate.getMessageConverters().add(hmc);
	        	
	        	String username = ((TextView)findViewById(R.id.findpwd_username_content)).getText().toString();
	    		String email = ((TextView)findViewById(R.id.findpwd_emailmobile_content)).getText().toString();
	    		
	    		Map param = new HashMap();
	    		param.put("username", username);
	    		param.put("email", email);
	    		Map dataObj = new HashMap();
	    		dataObj.put("user", param);
	    	    
	    		try{
	    	    	String url = "http://192.168.1.6:3000/api/users/find_password";
	    	    	Map result = restTemplate.postForObject(url, dataObj, Map.class);
	    	    	if(result.containsKey("success")){
	    	    		String submitStatus = result.get("success").toString();
	    	    		if("true".equals(submitStatus)){
	    	    			Toast.makeText(FindPwd.this, "ÉêÇëÌá½»³É¹¦£¡",Toast.LENGTH_LONG).show();
	    	    		}else{
	    	    			Toast.makeText(FindPwd.this, "ÉêÇëÌá½»Ê§°Ü£¡",Toast.LENGTH_LONG).show();
	    	    		}
	    	    	}else{
	    	    		Toast.makeText(FindPwd.this, "ÉêÇëÌá½»Ê§°Ü£¡",Toast.LENGTH_LONG).show();
	    	    	}
	    	    }catch(RestClientException re){
	    	    	Toast.makeText(FindPwd.this, re.getMessage(),Toast.LENGTH_LONG).show();
	    	    }catch(Exception e){
	    	    	Toast.makeText(FindPwd.this, e.getMessage(),Toast.LENGTH_LONG).show();
	    	    }
	        	Intent myIntent = new Intent(FindPwd.this,iiiui.class);
	            startActivity(myIntent);
	            FindPwd.this.finish();
	        }
	    });
	}
}
