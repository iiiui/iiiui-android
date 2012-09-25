package iiiui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class iiiui extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        TextView label = new TextView(this);
//        label.setText(R.string.hello);
//        label.setTextSize(20);
//        label.setGravity(Gravity.CENTER_HORIZONTAL);
//        ImageView pic = new ImageView(this);
//        pic.setImageResource(R.drawable.icon);
//        pic.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//        pic.setAdjustViewBounds(true);
//        pic.setScaleType(ScaleType.FIT_XY);
//        pic.setMaxHeight(400);
//        pic.setMaxWidth(400);
//        
//        TableLayout tl = new TableLayout(this);
//        TableRow tr = new TableRow(this);
//        TextView t = new TextView(this);
//        t.setText("用户名");
//        TextView t2 = new TextView(this);
//        t2.setText("用户名");
//        tr.addView(t, 300, 50);
//        tr.addView(t2, 300, 50);
//        tl.addView(tr,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//        LinearLayout ll = new LinearLayout(this);
//        ll.setOrientation(LinearLayout.VERTICAL);
//        ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//        ll.setGravity(Gravity.TOP);
//        
//        ll.addView(pic);
//        ll.addView(label);
//        ll.addView(tl);
//        setContentView(ll);
    }
}