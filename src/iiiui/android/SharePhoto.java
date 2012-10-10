package iiiui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;


public class SharePhoto extends Activity implements SurfaceHolder.Callback{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.registry);
		
		View bl = findViewById(R.id.registry_top_btn_left);
		bl.setClickable(true);
		bl.setOnClickListener(new OnClickListener(){
	        public void onClick(View v) {
//	        	Intent myIntent = new Intent(Registry.this,iiiui.class);
//	            startActivity(myIntent);
	        	SharePhoto.this.finish();
	        }
	    });
		
		View br = findViewById(R.id.registry_top_btn_right);
		br.setClickable(true);
		br.setOnClickListener(new OnClickListener(){
	        public void onClick(View v) {
	        	Intent myIntent = new Intent(SharePhoto.this,Index.class);
	            startActivity(myIntent);
	            SharePhoto.this.finish();
	        }
	    });
		
    }

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
