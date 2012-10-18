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
	* 重写onCreateOptionsMenu 方法
	*/
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//获取MenuInflater对象
		MenuInflater inflater = getMenuInflater();
		// 使用自定义的XML菜单文件
		inflater.inflate(R.layout.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	/**
	* 判断所选择的菜单
	*/
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_index:
			Toast.makeText(this, "选择了主页", Toast.LENGTH_LONG).show();
			break;
			
			case R.id.menu_share:
			Toast.makeText(this, "选择了分享", Toast.LENGTH_LONG).show();
			break;
			
			default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
