package GUI;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class DefaultTableModel_noEditable extends DefaultTableModel {
    private int columnNo;
    public DefaultTableModel_noEditable(Vector<Vector<Object>> data, Vector<Object> columnNames,int columnNo){
        super(data,columnNames);
        this.columnNo=columnNo;
    }

    @Override
    public boolean isCellEditable(int row,int column){
        if(column ==columnNo){
            return true;
        }else{
            return false;
        }
    }
}
