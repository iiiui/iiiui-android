package iiiui.android;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Registry extends Activity {

	private static final int PHOTO_PICKED_WITH_DATA = 3021; 
	private static final int CAMERA_WITH_DATA = 3023;
	
	 /*���յ���Ƭ�洢λ��*/  
	private static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");  
	private File mCurrentPhotoFile;//��������յõ���ͼƬ
	private ImageView mEditor;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.registry);
		View bl = findViewById(R.id.registry_top_btn_left);
		bl.setClickable(true);
		bl.setOnClickListener(new OnClickListener(){
	        public void onClick(View v) {
//	        	Intent myIntent = new Intent(Registry.this,iiiui.class);
//	            startActivity(myIntent);
	            Registry.this.finish();
	        }
	    });
		View br = findViewById(R.id.registry_top_btn_right);
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
	        	MappingJacksonHttpMessageConverter hmc = new MappingJacksonHttpMessageConverter();
	        	restTemplate.getMessageConverters().add(hmc);
	    		
	        	String username = ((TextView)findViewById(R.id.reg_username_content)).getText().toString();
	    		String email = ((TextView)findViewById(R.id.reg_emailmobile_content)).getText().toString();
	    		String password = ((TextView)findViewById(R.id.reg_pwd_content)).getText().toString();
	    		
	    	    try{
	    	    	Map param = new HashMap();
	    	    	param.put("email", email);
	    	    	param.put("password", password);
	    	    	Map dataObj = new HashMap();
	    	    	dataObj.put("user", param);
	    	    	
	    	    	String url = "http://192.168.1.6:3000/api/users/sign_up";
	    	    	Map result = restTemplate.postForObject(url, dataObj, Map.class);
	    	    	
	    	    	if(result.containsKey("success")){
	    	    		String regStatus = result.get("success").toString();
		    	    	if("true".equals(regStatus)){
		    	    		Toast.makeText(Registry.this, "ע��ɹ���",Toast.LENGTH_LONG).show();
			    	    	Map user = (Map)result.get("user");
			    	    	if(null != user){
			    	    		Toast.makeText(Registry.this, "�û���Ϣ->"+user.get("id")+"-"+user.get("email")+"-"+user.get("created_at"),Toast.LENGTH_LONG).show();
			    	    	}
		    	    	}else{
		    	    		if(result.containsKey("errors")){
    	    					String errors = result.get("errors").toString();
    	    					Toast.makeText(Registry.this, "ע��ʧ�ܣ�"+errors,Toast.LENGTH_LONG).show();
    	    				}
		    	    		Toast.makeText(Registry.this, "ע��ʧ�ܣ�",Toast.LENGTH_LONG).show();
		    	    	}
	    	    	}else{
	    	    		Toast.makeText(Registry.this, "ע���������⣬���Ժ����ԣ�",Toast.LENGTH_LONG).show();
	    	    	}
	    	    }catch(RestClientException re){
	    	    	Toast.makeText(Registry.this, re.getMessage(),Toast.LENGTH_LONG).show();
	    	    }catch(Exception e){
	    	    	Toast.makeText(Registry.this, e.getMessage(),Toast.LENGTH_LONG).show();
	    	    }
	        	Intent myIntent = new Intent(Registry.this,Index.class);
	            startActivity(myIntent);
	            Registry.this.finish();
	        }
	    });
		
		View bb = findViewById(R.id.getuserphoto);
		bb.setOnClickListener(new OnClickListener(){
	        public void onClick(View v) {
//	        	Intent intent = new Intent();
//	    		intent.setAction(Intent.ACTION_GET_CONTENT);
//	    		intent.setType("image/*");
//	    		startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
	        	doPickPhotoFromGallery();
	        }
	    });
	}
	
	public static String mapToJson(Map<String,String> map){
	    if(map==null||map.size()<1){
	        return null;
	    }
	    StringBuffer buffer=new StringBuffer();
	    buffer.append("{");
	    for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
	        String key = (String) iterator.next();
	        buffer.append("'"+key+"'");
	        buffer.append(":");
	        buffer.append("'"+map.get(key)+"'");
	        if(iterator.hasNext()){
	            buffer.append(",");
	        }
	    }
	    buffer.append("}");
	    return buffer.toString();
	}

	
	/**
	 *Post����
	 */
	public void doPost(String url , List nameValuePairs){
		//�½�HttpClient����  
		HttpClient httpclient = new DefaultHttpClient();
		//����POST����
		HttpPost httppost = new HttpPost(url);
		try {
//			//ʹ��PSOT��ʽ��������NameValuePair���鴫�ݲ���
//			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//			nameValuePairs.add(new BasicNameValuePair("id", "12345"));
//			nameValuePairs.add(new BasicNameValuePair("stringdata","hps is Cool!"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��gallery���棬ѡ��ͼƬ
	protected void doPickPhotoFromGallery() { 
	try {               
			// Launch picker to choose photo for selected contact   
			final Intent intent = getPhotoPickIntent();        
			startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);     
		}catch(ActivityNotFoundException e){      
			Toast.makeText(this, "û�ҵ�ͼƬactivity",      
			Toast.LENGTH_LONG).show();    
		}     
	} 
	
	//��������Gallery��intent  
	public static Intent getPhotoPickIntent() {    
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);  
		intent.setType("image/*");     
		intent.putExtra("crop", "true");   
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);     
		intent.putExtra("outputX", 100);     
		intent.putExtra("outputY", 100);  
		intent.putExtra("return-data", true);    
		return intent;    
	}
	
	//��Ϊ������Camera��Gally����Ҫ�ж����Ǹ��Եķ������,��������ʱ��������startActivityForResult  
	protected void onActivityResult(int requestCode, int resultCode, Intent data){ 
		if (resultCode != RESULT_OK) 
			return;           
		switch (requestCode) {    
			case PHOTO_PICKED_WITH_DATA: {
				// ����Gallery���ص�                 
				final Bitmap photo = data.getParcelableExtra("data");    
				// ���������ʾ��Ƭ��                 
				System.out.println(photo); 
				//�����û�ѡ���ͼƬ               
				byte[] img = getBitmapByte(photo);  
				mEditor = (ImageView) findViewById(R.id.userphoto); 
				mEditor.setImageBitmap(getRoundedCornerBitmap(photo));       
				System.out.println("set new photo");   
				break;            
			}           
			case CAMERA_WITH_DATA: {
				// ��������򷵻ص�,�ٴε���ͼƬ��������ȥ�޼�ͼƬ       
				doCropPhoto(mCurrentPhotoFile); 
				break;            
			}
		}
	}
	
	public byte[] getBitmapByte(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
	}
	
	/** ���ջ�ȡͼƬ*/       
	protected void doTakePhoto() {  
		try {              
				 // Launch camera to take photo for selected contact  
				 PHOTO_DIR.mkdirs();// ������Ƭ�Ĵ洢Ŀ¼         
				 mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());
				 // �����յ���Ƭ�ļ�����   
				 final Intent intent = getTakePickIntent(mCurrentPhotoFile);  
				 startActivityForResult(intent, CAMERA_WITH_DATA);         
			 } catch (ActivityNotFoundException e) {    
				 Toast.makeText(this, "û�ҵ�ͼƬactivity2",Toast.LENGTH_LONG).show();      
			 }   
	 }
	
	public static Intent getTakePickIntent(File f) { 
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);     
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));       
		return intent;  
	}
	
	/**�õ�ǰʱ���ȡ�õ�ͼƬ����*/    
	private String getPhotoFileName() {        
		Date date = new Date(System.currentTimeMillis());     
		SimpleDateFormat dateFormat = new SimpleDateFormat( "'IMG'_yyyy-MM-dd HH:mm:ss");        
		return dateFormat.format(date) + ".jpg";   
	} 
	
	protected void doCropPhoto(File f) {     
		try {            
				// ����galleryȥ���������Ƭ     
				final Intent intent = getCropImageIntent(Uri.fromFile(f));   
				startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);    
			} catch (Exception e) {      
				Toast.makeText(this, "����ͼƬ�쳣", Toast.LENGTH_LONG).show();    
			}     
	} 
	
	/**Constructs an intent for image cropping. ����ͼƬ��������*/   
	public static Intent getCropImageIntent(Uri photoUri) {      
		Intent intent = new Intent("com.android.camera.action.CROP");      
		intent.setDataAndType(photoUri, "image/*");     
		intent.putExtra("crop", "true");        
		intent.putExtra("aspectX", 1);       
		intent.putExtra("aspectY", 1);       
		intent.putExtra("outputX", 80);     
		intent.putExtra("outputY", 80);       
		intent.putExtra("return-data", true); 
		return intent;    
	} 
	
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {  
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),  
	        bitmap.getHeight(), Config.ARGB_8888);  
	    Canvas canvas = new Canvas(output);  
	  
	    final int color = 0xff424242;  
	    final Paint paint = new Paint();  
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
	    final RectF rectF = new RectF(rect);  
	    final float roundPx = 12;  
	  
	    paint.setAntiAlias(true);  
	    canvas.drawARGB(0, 0, 0, 0);  
	    paint.setColor(color);  
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);  
	  
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
	    canvas.drawBitmap(bitmap, rect, rect, paint);  
	  
	    return output;  
	}
	
}
