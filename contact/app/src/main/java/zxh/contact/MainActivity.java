package zxh.contact;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends Activity {
    private ListView contactListView;
    private ListView messageListView;
    private ContactAdapter contactAdapter;
    private MessageAdapter smsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactAdapter = new ContactAdapter(this);
        contactAdapter.queryContact();

        smsAdapter = new MessageAdapter(this);
        smsAdapter.querySMSMessage("");

        contactListView = (ListView) findViewById(R.id.contact_list);
        contactListView.setAdapter(contactAdapter);

        messageListView = (ListView) findViewById(R.id.message_list);
        messageListView.setAdapter(smsAdapter);

        save();
    }

    private void save(){
        StringBuffer sms_sb = new StringBuffer();

        for (TextSMSModel mode : smsAdapter.getSmsData()){
            sms_sb.append(mode.getBody()+"\n");
        }
        FileUtil.saveToSDCard("message.text", sms_sb.toString());

        StringBuffer contact_sb = new StringBuffer();
        for (ContactModel model : contactAdapter.getContactData()){
            contact_sb.append(model.getName()+":"+model.getPhone()+"\n");
        }
        FileUtil.saveToSDCard("contact.txt", contact_sb.toString());
    }
}
