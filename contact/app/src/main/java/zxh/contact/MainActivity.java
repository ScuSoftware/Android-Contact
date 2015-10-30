package zxh.contact;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity {
    private ListView contactListView;
    private ListView messageListView;
    private ListView recentListView;
    private ContactAdapter contactAdapter;
    private MessageAdapter smsAdapter;
    private RecentAdapter recentAdapter;

    private Button contact_button;
    private Button message_button;
    private Button recent_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactAdapter = new ContactAdapter(this);
        contactAdapter.queryContact();

        smsAdapter = new MessageAdapter(this);
        smsAdapter.querySMSMessage("");

        recentAdapter = new RecentAdapter(this);
        recentAdapter.queryRecent();

        contactListView = (ListView) findViewById(R.id.contact_list);
        contactListView.setAdapter(contactAdapter);

        messageListView = (ListView) findViewById(R.id.message_list);
        messageListView.setAdapter(smsAdapter);

        recentListView = (ListView) findViewById(R.id.recent_call_list);
        recentListView.setAdapter(recentAdapter);

        save();

        contact_button = (Button) findViewById(R.id.contact_button);
        contact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhone();
            }
        });
        message_button = (Button) findViewById(R.id.message_button);
        message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMessage();
            }
        });
        recent_button = (Button) findViewById(R.id.recent_button);
        recent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecentCall();
            }
        });

    }

    private void save() {
        saveMessage();
        savePhone();
        saveRecentCall();
    }

    private void saveMessage() {
        StringBuffer sms_sb = new StringBuffer();

        for (TextSMSModel mode : smsAdapter.getSmsData()) {
            sms_sb.append(mode.getBody() + "\n");
        }
        FileUtil.saveToSDCard("message.text", sms_sb.toString());

    }

    private void savePhone() {
        StringBuffer contact_sb = new StringBuffer();
        for (ContactModel model : contactAdapter.getContactData()) {
            contact_sb.append(model.getName() + ":" + model.getPhone() + "\n");
        }
        FileUtil.saveToSDCard("contact.txt", contact_sb.toString());
    }

    private void saveRecentCall() {
        StringBuffer recent_call_sb = new StringBuffer();
        for (RecentModel model : recentAdapter.getRecentData()) {
            recent_call_sb.append(model.getName() + ":" + model.getPhone() + "\n" + ":" + model.getTime() + "\n" + ":" + model.getType() + "\n");
        }
        FileUtil.saveToSDCard("recentCall.txt", recent_call_sb.toString());
    }
}
