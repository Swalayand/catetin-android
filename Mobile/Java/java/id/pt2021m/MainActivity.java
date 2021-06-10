package id.pt2021m;

import android.content.Context;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


    private List list;
    private ListView listView;
    private ListviewAdapter adpter;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        list = new ArrayList<Integer>();
        listView =(ListView)findViewById(R.id.listview);
        listView.setItemsCanFocus(true);
        for(int i=0;i<30;i++){
            list.add(i);
        }
        adpter=new ListviewAdapter(this,list);
        listView.setAdapter(adpter);
    }
}