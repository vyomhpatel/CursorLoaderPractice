package b12app.vyom.com.cursorloaderpractice;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        contactList= new ArrayList<>();

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);

        } else{
            getSupportLoaderManager().initLoader(1,null,this);
        }

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Uri CONTACT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        CursorLoader cursorLoader = new CursorLoader(this,CONTACT_URI,null,null,null,ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {


        for(data.moveToFirst();!data.isAfterLast();data.moveToNext()) {

            Contact contact = new Contact(data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            contactList.add(contact);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(this,contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
