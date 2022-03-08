/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amer.tm.ilp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vrx02
 */
public class ErrMapModel extends AbstractTableModel{
    
    class mapObj {
        public String type;
        public String keyword;
        public boolean tick;
        
    }
    
//    private ArrayList<String> type;
//    private ArrayList<String> keyword;
//    private ArrayList<Boolean> tick;
    private ArrayList<mapObj> objs;
    
    private String[] header = {"Error Type", "Keyword", "Select"};
    
    public ErrMapModel(){
//        type = new ArrayList<>();
//        keyword = new ArrayList<>();
//        tick = new ArrayList<>();
        objs = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return objs.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return objs.get(rowIndex).type;
        } else if(columnIndex == 1) {
            return objs.get(rowIndex).keyword;
        } else {
            return objs.get(rowIndex).tick;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 2;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 2){
            return Boolean.class;
        }
        
        return String.class;
    }
    
    
    public void add(String t, String k){
//        type.add(t);
//        keyword.add(k);
//        tick.add(false);

        mapObj a = new mapObj();
        a.type = t;
        a.keyword = k;
        a.tick = false;
        objs.add(a);
        
        saveToFile();
        this.fireTableDataChanged();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex == 2){
            objs.get(rowIndex).tick = (boolean) aValue;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return header[column];
    }
    
    public void deleteTicked(){
        Iterator<mapObj> i = objs.iterator();
        while (i.hasNext()) {
            mapObj s = i.next();
            if(s.tick){
                i.remove();
            }
            
        }
        
        saveToFile();
        
        this.fireTableDataChanged();
        
    }
    
    public void saveToFile(){
        File inf = new File("../err_map.txt");
        try {
            if(!inf.canWrite()){
                CommonHelper.PopMsg("No write access to mapping file", "Error?", JOptionPane.ERROR_MESSAGE);
                CommonHelper.log("No write access to mapping file", true);
                return;
            }
        } catch (Exception e) {
            CommonHelper.PopMsg("No write access to mapping file", "Error!", JOptionPane.ERROR_MESSAGE);
            CommonHelper.log("No write access to mapping file", true);
            CommonHelper.logStack(e);
            return;
        }
        
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(inf));
            for(mapObj a : objs){
                writer.write(a.type + "|" + a.keyword);
                writer.newLine();
            }
            
            writer.close();
        } catch (IOException e) {
            CommonHelper.PopMsg("Error saving mapping", "Error!", JOptionPane.ERROR_MESSAGE);
            CommonHelper.log("Error saving mapping", true);
            CommonHelper.logStack(e);
        }

    }
    
    public void loadFromFile(){
        // check for input file
        File inf = new File("../err_map.txt");
        if(!inf.isFile()){
            CommonHelper.PopMsg("Error mapping file does not exist", "Missing input", JOptionPane.ERROR_MESSAGE);
            try {
                inf.createNewFile();
                CommonHelper.log("blank err mapping file created", false);
            } catch (Exception e) {
                CommonHelper.logStack(e);
            }
            
            return;
        }
        
        // load the mapping from file
//        type = new ArrayList<>();
//        keyword = new ArrayList<>();
//        tick = new ArrayList<>();
        objs = new ArrayList<>();
        
        try {
            Scanner sc = new Scanner(inf);    
            CommonHelper.log("Loading from mapping file", false);
            while(sc.hasNextLine()){
                String text = sc.nextLine().trim().toLowerCase();
                int pos = text.indexOf("|");
                if(pos == -1){
                    CommonHelper.log("bad mapping line: " + text, true);
                    continue;
                }
                
                String etype = text.substring(0, pos).trim();
                String kwod  = text.substring(pos + 1).trim();
                
                if(etype.isEmpty() || kwod.isEmpty()){
                    CommonHelper.log("bad mapping line: " + text, true);
                    continue;
                }
                
//                type.add(etype);
//                keyword.add(kwod);
//                tick.add(false);
                mapObj a = new mapObj();
                a.type = etype;
                a.keyword = kwod;
                a.tick = false;
                objs.add(a);
            }
            
            sc.close();
            
            CommonHelper.log(objs.size() + " Mapping loaded", false);
            this.fireTableDataChanged();
        } catch (Exception e) {
            CommonHelper.log("Error loading mapping from file", true);
            CommonHelper.logStack(e);
        }
        
    }
    
    public String parseLogString(String text){
        for(mapObj o : objs){
            if(text.toLowerCase().contains(o.keyword)){
                return o.type;
            }
        }
        
        return "Others";
    }
    
    
}
