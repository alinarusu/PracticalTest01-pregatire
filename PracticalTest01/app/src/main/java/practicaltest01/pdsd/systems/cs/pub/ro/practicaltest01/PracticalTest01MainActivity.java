package practicaltest01.pdsd.systems.cs.pub.ro.practicaltest01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PracticalTest01MainActivity extends ActionBarActivity {

    protected EditText left, right;
    protected Button leftButton, rightButton, nav;
    private final static int SECONDARY_REQUEST_CODE = 2000;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){
            case SECONDARY_REQUEST_CODE:

        }

        Toast.makeText(this, "result = " + resultCode, Toast.LENGTH_LONG).show();
    }// apelata cand activitatea secundara apeleeaza setResult si finish una dupa alta



    // 2. instantiere clasa ascultator:
    protected ButtonClickListener but = new ButtonClickListener();


    // 1. Implementare clasa ascultator
    protected class ButtonClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button1:
                    left.setText(String.valueOf(Integer.parseInt(left.getText().toString())+1));
                    break;
                case R.id.button2:
                    right.setText(String.valueOf(Integer.parseInt(right.getText().toString())+1));
                    break;
                case R.id.button:
                    Intent intent = new Intent(PracticalTest01MainActivity.this, PracticalTest01SecondaryActivity.class);
                    intent.putExtra("noOfClicks",
                            String.valueOf(
                                            Integer.parseInt(left.getText().toString()) +
                                            Integer.parseInt(right.getText().toString())
                                           ));
                    startActivityForResult(intent, SECONDARY_REQUEST_CODE);
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("leftClicks", Integer.parseInt(left.getText().toString()));
        outState.putInt("rightClicks", Integer.parseInt(right.getText().toString()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);


        left = (EditText)findViewById(R.id.editText);
        right = (EditText)findViewById(R.id.editText2);



        // 3. Inregistrare clasa ascultator
        leftButton  = (Button)findViewById(R.id.button1);
        rightButton  = (Button)findViewById(R.id.button2);
        nav = (Button)findViewById(R.id.button);


        leftButton.setOnClickListener(but);
        rightButton.setOnClickListener(but);
        nav.setOnClickListener(but);



        // salvare stare
        if(savedInstanceState!=null){
            int leftClicks = savedInstanceState.getInt("leftClicks",01);
            left.setText(String.valueOf(leftClicks));

            int rightClicks = savedInstanceState.getInt("rightClicks", -1);
            right.setText(String.valueOf(rightClicks));
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
