package zxh.contact;

import java.lang.String; /**
 * Created by zyma on 10/28/15.
 */
public class SMS {
    public static final String ALL_CONTENT_URI = "content://sms/";

    public static final String INBOX_CONTENT_URI = "content://sms/inbox";

    public static final String SENT_CONTENT_URI = "content://sms/sent";

    public static final String DRAFT_CONTENT_URI = "content://sms/draft";

    public static final String ID = "_id";

    public static final String THREAD_ID = "thread_id";

    public static final String ADDRESS = "address";

    public static final String PERSON = "person";

    public static final String DATE = "date";

    public static final String PROTOCOL = "protocol";

    public static final String READ = "read";

    public static final String STATUS = "status";

    public static final String TYPE = "type";

    public static final String BODY = "body";

    public static final int TYPE_RECEIVED = 1;

    public static final int TYPE_SENT = 2;
}
