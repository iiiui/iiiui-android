package iiiui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registry extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.registry);
    }
	
	public void onClick(View v) {
		if(R.id.buttonleft == v.getId())
		{
		Intent i = new Intent(this,Registry.class);
		this.startActivity(i);
		}
	}
	
}
