/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amer.tm.ilp;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vrx02
 */
public class SummaryModel extends AbstractTableModel{
    
    private ArrayList<String> type;
    private ArrayList<Integer> count;
    
    public SummaryModel(){
        type = new ArrayList<>();
        count = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return type.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return type.get(rowIndex);
        } else {
            return count.get(rowIndex);
        }
    }
    
    public void setData(ArrayList<String> t, ArrayList<Integer> c){
        type = t;
        count = c;
        this.fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column){
        if(column == 0){
            return "Error Type";
        } else {
            return "Count";
        }
    }
    
    
}
