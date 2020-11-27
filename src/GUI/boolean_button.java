package GUI;

import Bean.DBBean;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.Vector;

public class boolean_button {
    /**
     * 用来加入复选框
     *
     * @param table 当前Jtable
     * @param data  获取的数据
     * @param size=order_name2.size()-1 或者 order_name.size()
     * @param tablemodel
     * @param db 数据库
     *
     * @see javax.swing.JComponent#contains
     * @see java.awt.Component#contains
     */
    public static void boolean_button(JTable table, Vector<Vector<Object>> data, int size, DefaultTableModel tablemodel
            , DBBean db,TableColumn tcm,String nextstate,String nowstate){
//        TableColumn tcm4 = table.getColumnModel().getColumn(size-1);
        tcm.setCellEditor(table.getDefaultEditor(Boolean.class));
        tcm.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        tablemodel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getColumn()==size){
                    if((Boolean) data.get(e.getFirstRow()).get(size))
                        db.executeUpdate((String)(data.get(e.getFirstRow()).get(0))
                                ,"ordermanager","ID",nextstate,"State");
                    else
                        db.executeUpdate((String)(data.get(e.getFirstRow()).get(0))
                                ,"ordermanager","ID",nowstate,"State");
                }
            }
        });
    }
}
