package iiiui.android;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class iiiui extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
//            		letCamera();
            	RestTemplate restTemplate = new RestTemplate();
	        	MappingJacksonHttpMessageConverter hmc = new MappingJacksonHttpMessageConverter();
	        	restTemplate.getMessageConverters().add(hmc);
            	
	        	String account = ((TextView)findViewById(R.id.account_content)).getText().toString();
	    		String password = ((TextView)findViewById(R.id.user_pwd_content)).getText().toString();
	    		
	    		Map param = new HashMap();
	    		param.put("email", account);
	    		param.put("password", password);
				Map dataObj = new HashMap();
				dataObj.put("user", param);
            	
    	    	try{
    	    		String url = "http://192.168.1.6:3000/api/users/sign_in";
    	    		Map result = restTemplate.postForObject(url, dataObj, Map.class);
    	    		if(result.containsKey("success")){
    	    			String loginStatus = result.get("success").toString();
    	    			if("true".equals(loginStatus)){
    	    				Toast.makeText(iiiui.this, "µÇÂ¼³É¹¦£¡",Toast.LENGTH_LONG).show();
			    	    	Map user = (Map)result.get("user");
			    	    	if(null != user){
			    	    		Toast.makeText(iiiui.this, "ÓÃ»§ÐÅÏ¢->"+user.get("id")+"-"+user.get("email")+"-"+user.get("created_at"),Toast.LENGTH_LONG).show();
			    	    	}
    	    			}else{
    	    				if(result.containsKey("errors")){
    	    					String errors = result.get("errors").toString();
    	    					Toast.makeText(iiiui.this, "µÇÂ¼Ê§°Ü£¡"+errors,Toast.LENGTH_LONG).show();
    	    				}
    	    				Toast.makeText(iiiui.this, "µÇÂ¼Ê§°Ü£¡",Toast.LENGTH_LONG).show();
    	    			}
    	    		}else{
    	    			Toast.makeText(iiiui.this, "µÇÂ¼Ê§°Ü£¡",Toast.LENGTH_LONG).show();
    	    		}
    	    	}catch(Exception e){
    	    		Toast.makeText(iiiui.this, e.getMessage(),Toast.LENGTH_LONG).show();
    	    	}
    	    	Intent myIntent = new Intent(iiiui.this,Index.class);
	            startActivity(myIntent);
	            iiiui.this.finish();
            	}
            }
        );
        
        Button buttonleft = (Button)findViewById(R.id.buttonleft);
        buttonleft.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                Intent regist = new Intent();
                regist.setComponent(new ComponentName(iiiui.this,Registry.class));
                startActivityForResult(regist, 1);
            }
        });
        
        Button buttonright = (Button)findViewById(R.id.buttonright);
        buttonright.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                Intent findpwd = new Intent();
                findpwd.setComponent(new ComponentName(iiiui.this,FindPwd.class));
                startActivityForResult(findpwd, 1);
            }
        });
        
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        case 1:
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "photo finished!", Toast.LENGTH_SHORT).show();
            }
            break;
        default:
            break;
        }
    }
    
    protected void letCamera() {
        Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String strImgPath = Environment.getExternalStorageDirectory()
                .toString() + "/iiiui/";
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()) + ".jpg";
        File out = new File(strImgPath);
        if (!out.exists()) {
            out.mkdirs();
        }
        out = new File(strImgPath, fileName);
        strImgPath = strImgPath + fileName;
        Uri uri = Uri.fromFile(out);
        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        imageCaptureIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(imageCaptureIntent, 1);
    }
}