package zxh.contact;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zyma on 10/28/15.
 */
public class MessageAdapter extends BaseAdapter {
    private Context context;

    public ArrayList<TextSMSModel> getSmsData() {
        return smsData;
    }

    public void setSmsData(ArrayList<TextSMSModel> smsData) {
        this.smsData = smsData;
    }

    private ArrayList<TextSMSModel> smsData = new ArrayList<>();

    public MessageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return smsData.size();
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
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.message_cell, null);
        TextView smsText = (TextView) linearLayout.findViewById(R.id.sms_text);
        TextSMSModel model = smsData.get(position);
        smsText.setText(model.getBody());
        TextView smsRemarkText = (TextView) linearLayout.findViewById(R.id.sms_remark);
        smsRemarkText.setText(position + ":" + "短信");
        return linearLayout;
    }

    public void querySMSMessage(String keyword) {
        String[] projection = new String[]{SMS.BODY, SMS.TYPE, SMS.ADDRESS};

        Cursor smsCursor = context.getContentResolver().query(
                Uri.parse(SMS.ALL_CONTENT_URI),
                projection,
                null,//SMS.BODY + " like '%" + keyword + "%'",
                null,
                null//SMS.TYPE + "," + SMS.DATE + " DESC"
        );

        if (smsCursor != null) {
            while (smsCursor.moveToNext()) {
                TextSMSModel mode = new TextSMSModel();
                String smsBody = smsCursor.getString(smsCursor.getColumnIndex(SMS.BODY));
                mode.setBody(smsBody);
                smsData.add(mode);
            }
        }
    }
}
