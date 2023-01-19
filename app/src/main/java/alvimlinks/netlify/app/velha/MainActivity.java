package alvimlinks.netlify.app.velha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private static final int nove = 9;
    public static final String player1 = "✕";
    public static final String player2= "◯";
    public static String ESC = "\u001B";
    public static String GS = "\u001D";
    protected String BoldOn = ESC + "E" + "\u0001";
    protected String BoldOff = ESC + "E" + "\0";

    private Button[] arrayBtn = new Button[nove];
    private String turn = player1;
    private int playTimes = 0;
    private String[] table = new String[nove];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initButtons();
        onClickButton();
    }

    private void initButtons(){
        arrayBtn[0] = (Button) findViewById(R.id.bt_00);
        arrayBtn[1] = (Button) findViewById(R.id.bt_01);
        arrayBtn[2] = (Button) findViewById(R.id.bt_02);
        arrayBtn[3] = (Button) findViewById(R.id.bt_03);
        arrayBtn[4] = (Button) findViewById(R.id.bt_04);
        arrayBtn[5] = (Button) findViewById(R.id.bt_05);
        arrayBtn[6] = (Button) findViewById(R.id.bt_06);
        arrayBtn[7] = (Button) findViewById(R.id.bt_07);
        arrayBtn[8] = (Button) findViewById(R.id.bt_08);
    }

    private void onClickButton(){
        for (int j = 0; j < nove; j++) {
            int finalJ = j;
            arrayBtn[finalJ].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    move(finalJ);
                }
            });
            table[finalJ] = "";
        }
    }

    public void showButtons(){
        for (int j = 0; j < nove; j++)
            arrayBtn[j].setText(table[j]);
    }

    private void move(int j){
        if (table[j].equals("")) {
            table[j] = turn;
            playTimes++;

            if (turn.equals(player1))
                turn = player2;
            else
                turn = player1;
        }
        showButtons();
        verifyTable();
    }

    private void verifyTable(){
        if(table[0].equals(table[1]) && table[0].equals(table[2]) && !table[0].equals("")){
            Winner(table[0]);
            return;
        }

        if(table[3].equals(table[4]) && table[3].equals(table[5]) && !table[3].equals("")){
            Winner(table[3]);
            return;
        }

        if(table[6].equals(table[7]) && table[6].equals(table[8]) && !table[6].equals("")){
            Winner(table[6]);
            return;
        }

        if(table[0].equals(table[3]) && table[0].equals(table[6]) && !table[0].equals("")){
            Winner(table[0]);
            return;
        }

        if(table[1].equals(table[4]) && table[1].equals(table[7]) && !table[1].equals("")){
            Winner(table[1]);
            return;
        }

        if(table[2].equals(table[5]) && table[2].equals(table[8]) && !table[2].equals("")){
            Winner(table[2]);
            return;
        }

        if(table[0].equals(table[4]) && table[0].equals(table[8]) && !table[0].equals("")){
            Winner(table[0]);
            return;
        }

        if(table[2].equals(table[5]) && table[2].equals(table[6]) && !table[2].equals("")){
            Winner(table[2]);
            return;
        }

        if (playTimes == nove) {
            Winner("");
            return;
        }
    }

    private void Winner(String w){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Match result");
        if (w.equals(""))
            builder.setMessage("Velha!");
        else
            builder.setMessage((w.equals(player1)) ? "Jogador X VENCEU" : "Jogador O Venceu");

        builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playTimes = 0;
                for (int j = 0; j < nove; j++){
                    table[j] = "";
                }
                turn = player1;
                showButtons();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}