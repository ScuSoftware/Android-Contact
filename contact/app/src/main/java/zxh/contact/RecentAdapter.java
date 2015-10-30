package zxh.contact;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.provider.CallLog.Calls.*;

/**
 * Created by zyma on 10/30/15.
 */
public class RecentAdapter extends BaseAdapter {
    private Context context;

    public ArrayList<RecentModel> getRecentData() {
        return recentData;
    }

    public void setRecentData(ArrayList<RecentModel> recentData) {
        this.recentData = recentData;
    }

    private ArrayList<RecentModel> recentData = new ArrayList<>();

    public RecentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return recentData.size();
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
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.recent_cell, null);
        TextView name_text = (TextView) linearLayout.findViewById(R.id.name_text);
        TextView phone_text = (TextView) linearLayout.findViewById(R.id.phone_text);
        TextView type_text = (TextView) linearLayout.findViewById(R.id.type_text);
        TextView time_text = (TextView) linearLayout.findViewById(R.id.time_text);
        RecentModel mode = recentData.get(position);
        name_text.setText(mode.getName());
        phone_text.setText(mode.getPhone());
        type_text.setText(mode.getType());
        time_text.setText(mode.getTime());
        return linearLayout;
    }

    public void queryRecent() {
        String[] projection = new String[]{NUMBER, CACHED_NAME, TYPE, DATE, DURATION};

        Cursor callCursor = context.getContentResolver().query(
                CONTENT_URI,
                projection,
                null,
                null,
                DEFAULT_SORT_ORDER);

        if (callCursor != null) {
            while (callCursor.moveToNext()) {
                RecentModel mode = new RecentModel();
                String phone = callCursor.getString(callCursor.getColumnIndex(NUMBER));
                String type;
                switch (Integer.parseInt(callCursor.getString(callCursor.getColumnIndex(TYPE)))) {
                    case INCOMING_TYPE:
                        type = "呼入";
                        break;
                    case OUTGOING_TYPE:
                        type = "呼出";
                        break;
                    case MISSED_TYPE:
                        type = "未接";
                        break;
                    default:
                        type = "挂断";//应该是挂断.根据我手机类型判断出的
                        break;
                }
                SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(Long.parseLong(callCursor.getString(callCursor.getColumnIndexOrThrow(CallLog.Calls.DATE))));
                //呼叫时间
                String time = sfd.format(date);
                //联系人
                String name = callCursor.getString(callCursor.getColumnIndexOrThrow(CallLog.Calls.CACHED_NAME));
                mode.setPhone(phone);
                mode.setName(name);
                mode.setTime(time);
                mode.setType(type);

                recentData.add(mode);
            }
        }
    }
}
