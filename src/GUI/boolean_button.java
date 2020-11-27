package GUI;

import Bean.DBBean;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    if((Boolean) data.get(e.getFirstRow()).get(size)) {
                        db.executeUpdate((String) (data.get(e.getFirstRow()).get(0))
                                , "ordermanager", "ID", nextstate, "State");
                        update_itemnum(data.get(e.getFirstRow()), db);
//                        db.executeUpdate((String) (data.get(e.getFirstRow()).get(0))
//                                , "ordermanager", "ID", nextstate, "");
                    }
                    else
                        db.executeUpdate((String)(data.get(e.getFirstRow()).get(0))
                                ,"ordermanager","ID",nowstate,"State");
                }
            }
        });
    }

    public static void update_itemnum(Vector<Object> x,DBBean db){
        System.out.println("kkkkkkkk"+"仓库数量已更新");
        String items=(String) x.get(1);
        String tmp1[]=items.split("<|>");
        for(String x1:tmp1) {
            if(!x1.equals("")) {
                String tmp2[]=x1.split(",");
                try {
                    int outnum= Integer.valueOf(tmp2[1]);
                    System.out.println(tmp2[0]+"仓库数量已更新");
                    ResultSet tmp=db.executeFind(tmp2[0],"itemmanager","name");
                    tmp.next();
                    String s=String.valueOf(tmp.getObject("num"));
                    String newnum=String.valueOf(Integer.valueOf(s)-outnum);
                    tmp2[0]="'"+tmp2[0]+"'";
                    db.executeUpdate(tmp2[0],"itemmanager","name",newnum,"num");

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}


