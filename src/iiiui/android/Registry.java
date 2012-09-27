package iiiui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Registry extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.registry);
		
		View bl = findViewById(R.id.registry_top_btn_left);
		bl.setClickable(true);
		bl.setOnClickListener(new OnClickListener(){
	        public void onClick(View v) {
	        	Intent myIntent = new Intent(Registry.this,iiiui.class);
	            startActivity(myIntent);
	            Registry.this.finish();
	        }
	    });
		
		View br = findViewById(R.id.registry_top_btn_right);
		br.setClickable(true);
		br.setOnClickListener(new OnClickListener(){
	        public void onClick(View v) {
	        	Intent myIntent = new Intent(Registry.this,Index.class);
	            startActivity(myIntent);
	            Registry.this.finish();
	        }
	    });
		
		
    }
	
//	public void onClick(View v) {
//		if(R.id.buttonleft == v.getId())
//		{
//			Intent i = new Intent(this,Registry.class);
//			this.startActivity(i);
//		}
//	}
	
}
