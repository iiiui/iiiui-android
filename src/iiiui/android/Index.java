package iiiui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class Index extends Activity {

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.index);
	}
	
	/**
	* ��дonCreateOptionsMenu ����
	*/
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//��ȡMenuInflater����
		MenuInflater inflater = getMenuInflater();
		// ʹ���Զ����XML�˵��ļ�
		inflater.inflate(R.layout.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	/**
	* �ж���ѡ��Ĳ˵�
	*/
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_index:
			Toast.makeText(this, "ѡ������ҳ", Toast.LENGTH_LONG).show();
			break;
			
			case R.id.menu_share:
			Toast.makeText(this, "ѡ���˷���", Toast.LENGTH_LONG).show();
			break;
			
			default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
