package jbs2011.mazegame;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;


public class MazeGameStart extends Activity 
	implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.main);
        View v = (View) findViewById(R.id.button1);
        v.setOnClickListener(this);
	}
	  
	public void onClick(android.view.View v){
		Intent i = new Intent(this,MazeGameActivity.class);
		this.startActivity(i);
	}
	

}
