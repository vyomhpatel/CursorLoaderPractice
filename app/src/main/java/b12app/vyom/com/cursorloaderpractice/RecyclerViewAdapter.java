package b12app.vyom.com.cursorloaderpractice;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContactHolder> {

    private static final String TAG = "adapter" ;
    private Context context;
    private List<Contact> cursorList;

    public RecyclerViewAdapter(Context context, List<Contact> cursor) {
        this.context = context;
        this.cursorList = cursor;
//        Log.i(TAG, "RecyclerViewAdapter: "+cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ContactHolder contactHolder = null;


            View contactView = LayoutInflater.from(context).inflate(R.layout.item_format,parent,false);
            contactHolder = new ContactHolder(contactView);


        return contactHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {



                holder.tvName.setText(cursorList.get(position).getName());
                holder.tvPhone.setText(cursorList.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return cursorList.size();
    }


    class ContactHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvPhone;


        public ContactHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }
}
