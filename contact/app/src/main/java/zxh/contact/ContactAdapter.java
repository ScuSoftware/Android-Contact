package zxh.contact;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.Object;import java.lang.Override;import java.lang.String;import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Phone;


/**
 * Created by zyma on 10/27/15.
 */
public class ContactAdapter extends BaseAdapter {
    private Context context;

    public ArrayList<ContactModel> getContactData() {
        return contactData;
    }

    public void setContactData(ArrayList<ContactModel> contactData) {
        this.contactData = contactData;
    }

    private ArrayList<ContactModel> contactData = new ArrayList<>();


    public ContactAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout contactView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.contact_cell,null);
        TextView phoneText = (TextView) contactView.findViewById(R.id.phone_text);
        TextView nameText = (TextView) contactView.findViewById(R.id.name_text);
        ContactModel contactModel = contactData.get(position);
        phoneText.setText(contactModel.getPhone());
        nameText.setText(contactModel.getName());

        TextView smsRemarkText = (TextView) contactView.findViewById(R.id.name_remark);
        smsRemarkText.setText(position + ":" + "姓名");
        return contactView;
    }

    public void queryContact() {

        String[] projection = new String[]{Phone.DISPLAY_NAME, Phone.TYPE, Phone.NUMBER, Phone.LABEL};

        Cursor phoneCursor = context.getContentResolver().query(
                Phone.CONTENT_URI,
                projection,
                null,
                null,
                null);

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                ContactModel model = new ContactModel();
                String phone = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER));
                String name = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.DISPLAY_NAME));
                model.setPhone(phone);
                model.setName(name);
                contactData.add(model);
            }
        }
    }



}
