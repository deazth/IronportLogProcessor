/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amer.tm.ilp;

/**
 *
 * @author vrx02
 */
public class LogLineProcessor {
    
    private String data;
    public String time;
    public String errtype;
    public String bano;
    public String email;
    public String mappederr;
    public String fullerr;
    private String expectedSender;
    
    public LogLineProcessor(String line, String sender){
        this.data = line;
        this.expectedSender = sender;
    }
    
    public boolean process(){
        // first get the time
        int pos = data.indexOf(" Info: ");
        if(pos == -1){
            return false;
        }
        
        time = data.substring(0, pos);
        data = data.substring(pos + 7);
        
        // get the line type
        pos = data.indexOf(":");
        if(pos == -1){
            return false;
        }
        String btype = data.substring(0, pos);
        
        if(!btype.equals("Bounced")){
            return false;
        }
        
        // check the sender
        pos = data.indexOf("From:<");
        data = data.substring(pos + 6);
        pos = data.indexOf(">");
        String sender = data.substring(0, pos);
        if(!sender.toLowerCase().equals(expectedSender)){
            return false;
        }
        
        // get the recipient
        pos = data.indexOf("To:<");
        data = data.substring(pos + 4);
        pos = data.indexOf(">");
        email = data.substring(0, pos);
        
        // get the assigned error msg
        data = data.substring(pos + 10);
        pos = data.indexOf("(");
        errtype = data.substring(0, pos).trim();
        
        // get the full error msg
        data = data.substring(pos);
        pos = data.indexOf("Message:");
        fullerr = data.substring(0, pos).replaceAll(";", "");
        map();
        
        // get the bano
        bano = "";
        data = data.substring(pos);
        pos = data.indexOf("(No Akaun:");
        if(pos == -1){
            pos = data.indexOf("(Account No:");
            if(pos == -1){
                bano = "not found";
            } else {
                data = data.substring(pos + 12);
            }
        } else {
            data = data.substring(pos + 10);
        }
        
        if(bano.equals("")){
            pos = data.indexOf(")");
            bano = data.substring(0, pos).trim();
            pos = bano.indexOf(" ");
            // remove \r\n
            if(pos != -1){
                bano = bano.substring(pos).trim();
            }
        }
        
        
        
        return true;
    }
    
    private void map(){
        
        if(errtype.equals("5.3.0 - Other mail system problem")){
            mappederr = "Email doesnt exist";
        } else if(errtype.equals("5.1.0 - Unknown address error")) {
            mappederr = "Email doesnt exist";
        } else if(errtype.equals("5.1.2 - Bad destination host")) {
            mappederr = "Invalid domain";
        } else if(errtype.equals("5.4.7 - Delivery expired")) {
            mappederr = "Mailbox full or timeout";
        } else {
            mappederr = "Others";
        }
        
        
        
        
    }
}
