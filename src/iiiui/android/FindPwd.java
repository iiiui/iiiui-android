package iiiui.android;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
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
	        	/**
	        	 * example for restful webservice
	        	 * */
//	        	RestTemplate restTemplate = new RestTemplate();
//	        	restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//	        	String url = "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q={query}";
//	        	String result = restTemplate.getForObject(url, String.class, "SpringSource");
	        	
	        	RestTemplate restTemplate = new RestTemplate();
	        	
//	        	List list = new ArrayList();
//	        	MediaType mt = new MediaType("text/html;charset=UTF-8");
//	        	list.add(mt);
//	        	MappingJacksonHttpMessageConverter hmc = new MappingJacksonHttpMessageConverter();
//	        	hmc.setSupportedMediaTypes(list);
	        	StringHttpMessageConverter hmc = new StringHttpMessageConverter();
	        	restTemplate.getMessageConverters().add(hmc);
	    		
	        	String username = ((TextView)findViewById(R.id.findpwd_username_content)).getText().toString();
	    		String email = ((TextView)findViewById(R.id.findpwd_emailmobile_content)).getText().toString();
	    		
	    		Map user = new HashMap();
	    		user.put("username", username);
	    		user.put("email", email);
	    		JSONObject result;
	    	    try{
	    	    	String url = "http://192.168.1.6:3000/api/users/find_password";
	    	    	String rs = restTemplate.postForObject(url, "", String.class);
	    	    	
	    	    	Toast.makeText(FindPwd.this, "提交success->"+rs,Toast.LENGTH_LONG).show();
	    	    }catch(RestClientException re){
	    	    	Toast.makeText(FindPwd.this, re.getMessage(),Toast.LENGTH_LONG).show();
	    	    }catch(Exception e){
	    	    	Toast.makeText(FindPwd.this, "原因:"+e.getMessage(),Toast.LENGTH_LONG).show();
	    	    }
	    	    
	        	Intent myIntent = new Intent(FindPwd.this,iiiui.class);
	            startActivity(myIntent);
	            FindPwd.this.finish();
	        }
	    });

	}
	
}
