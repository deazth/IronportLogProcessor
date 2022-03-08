/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amer.tm.ilp;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.netbeans.api.io.IOProvider;
import org.netbeans.api.io.InputOutput;

/**
 *
 * @author vrx02
 */
public class CommonHelper {

    public static void logStack(Exception e) {
        String ts = tsToDateNow("yyyy/MM/dd HH:mm:ss") + " > err stack";
        InputOutput io = IOProvider.getDefault().getIO("log", false);
        io.getErr().println(ts);
        e.printStackTrace(io.getErr());
        io.getErr().close();
        io.show();
    }

    public static void log(String text, boolean iserror) {
        String ts = tsToDateNow("yyyy/MM/dd HH:mm:ss") + " > ";
        InputOutput io = IOProvider.getDefault().getIO("log", false);
        if (iserror) {
            io.getErr().println(ts + text);
            io.getErr().close();
            io.show();
        } else {
            io.getOut().println(ts + text);
            io.getOut().close();
        }

        io.show();
    }

    public static String tsToDateNow(String date_format) {
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(date_format);

        return sdf.format(d);
    }
    
    public static void PopMsg(String errmsg, String title, int msgtype){
        log(errmsg, msgtype == JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null, errmsg, title, msgtype);
    }
}
