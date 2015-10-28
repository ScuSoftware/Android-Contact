package zxh.contact;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zyma on 10/28/15.
 */
public class FileUtil {
    public static void save(String fileName , String content, Context context) {

        try {
            /* 根据用户提供的文件名，以及文件的应用模式，打开一个输出流.文件不存系统会为你创建一个的，
             * 至于为什么这个地方还有FileNotFoundException抛出，我也比较纳闷。在Context中是这样定义的
             *   public abstract FileOutputStream openFileOutput(String name, int mode)
             *   throws FileNotFoundException;
             * openFileOutput(String name, int mode);
             * 第一个参数，代表文件名称，注意这里的文件名称不能包括任何的/或者/这种分隔符，只能是文件名
             *          该文件会被保存在/data/data/应用名称/files/chenzheng_java.txt
             * 第二个参数，代表文件的操作模式
             *          MODE_PRIVATE 私有（只能创建它的应用访问） 重复写入时会文件覆盖
             *          MODE_APPEND  私有   重复写入时会在文件的末尾进行追加，而不是覆盖掉原来的文件
             *          MODE_WORLD_READABLE 公用  可读
             *          MODE_WORLD_WRITEABLE 公用 可读写
             *  */
            FileOutputStream outputStream = context.openFileOutput("context.txt", context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
