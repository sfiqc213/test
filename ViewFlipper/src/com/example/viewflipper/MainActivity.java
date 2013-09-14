package com.example.viewflipper;

import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.GestureDetector.OnGestureListener;

public class MainActivity extends Activity {
   private ViewFlipper vf;
   private LinearLayout linear;
   private Animation amLeftIn,amLeftOut,amrightIn,amrightOut;
   private ImageView img;
   private TextView tv;
   private int[] resId={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
   private GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAnimation();
        setView();
        gd = new GestureDetector(this, new OnGestureListener() {

			@Override
			public boolean onDown(MotionEvent e) {
				return false;
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				float x1=e1.getX(), x2=e2.getX();
				if(x1-x2>100){
					vf.setInAnimation(amrightIn);
					vf.setOutAnimation(amLeftOut);
					vf.showNext();
				}else if(x1-x2<-100){
					vf.setInAnimation(amLeftIn);
					vf.setOutAnimation(amrightOut);
					vf.showPrevious();
					
				}
					
				
				return false;
			}

			@Override
			public void onLongPress(MotionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onShowPress(MotionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				// TODO Auto-generated method stub
				return false;
			}
		});
    }
    private void setView(){
    	vf=(ViewFlipper)this.findViewById(R.id.viewFlipper1);
    	for(int i=0;i<resId.length;i++){
    		linear=new LinearLayout(this);
    		linear.setOrientation(LinearLayout.VERTICAL);
    		linear.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    	
    		tv=new TextView(this);
    		tv.setText("TextView"+(i+1));
    		tv.setTextSize(25);
    		tv.setGravity(Gravity.CENTER_HORIZONTAL);
    		
    	    img=new ImageView(this);
    	    img.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    	    img.setImageResource(resId[i]);
    	    
    	    linear.addView(tv);
    	    linear.addView(img);
    	    vf.addView(linear);
    		
    	}
    	
    }
    private void setAnimation(){
    	amLeftIn=AnimationUtils.loadAnimation(this, R.anim.left_in);
    	amLeftOut=AnimationUtils.loadAnimation(this, R.anim.left_out);
    	amrightIn=AnimationUtils.loadAnimation(this, R.anim.right_in);
    	amrightOut=AnimationUtils.loadAnimation(this, R.anim.right_out);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gd.onTouchEvent(event);
	}

    
}
