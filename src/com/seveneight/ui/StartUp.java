package com.seveneight.ui;

import com.seveneight.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartUp extends Activity implements OnClickListener{

	final Context context = this;
	private final static String LOG_TAG = "StartUp";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);

		Button newGame = (Button)findViewById(R.id.new_game);
		newGame.setOnClickListener(this);
		
		Log.d(LOG_TAG, "onCreate");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		int ID = v.getId();
		switch(ID)
		{
		case R.id.new_game:		
			Intent intentSevenEight = new Intent(context, SevenEight.class);
			startActivity(intentSevenEight);
			break;
		}			
	}
}
