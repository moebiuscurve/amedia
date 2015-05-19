package com.example.naveenk.amedia;

/**
 * Created by naveenk on 11/5/15.
 */
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Menu extends ListActivity{
    String classes[] ={"MainActivity", "TextPlay", "Email", "Camera", "Data", "GFX", "GFXSurface","SoundStuff","Slider","Tabs","SimpleBrowser",
            "Flipper", "SharedPrefs", "InternalData"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*No title and full screen. (Commented for convenience)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        */

        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cheese = classes[position];

        try {
            Class ourClass = Class.forName("com.example.naveenk.amedia."+cheese);
            Intent ourIntent = new Intent(Menu.this,ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        //super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);

        return true;
    }

/*

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
	}
*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        boolean valTF=false;
        switch(item.getItemId()){
            case R.id.aboutUs:
                Intent i = new Intent("com.example.naveenk.amedia.ABOUT");
                startActivity(i);
                valTF=true;
                break;
            case R.id.preferences:
                Intent p = new Intent("com.example.naveenk.amedia.PREFS");
                startActivity(p);
                valTF=true;
                break;
            case R.id.exit:
                finish();
                valTF=true;
                break;
        }
        if(valTF)return true;
        return super.onOptionsItemSelected(item);
    }


}