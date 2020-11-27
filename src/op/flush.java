package op;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import Bean.DBBean;
import GUI.boolean_button;

public class flush {

    private DBBean db;
    private Vector<Object> name_sell_noStateChange;
    private Vector<Object> name_sell_haveStateChaneg;
    private Vector<Object> name_checkStock;

    public flush(DBBean dbBean) {
        this.db = dbBean;
        name_sell_noStateChange = returnVector.getHeadName(db, "ordermanager");
        name_sell_haveStateChaneg = returnVector.getHeadName(db, "ordermanager");
        name_sell_haveStateChaneg.add("stateChange");
        name_checkStock = new Vector<>(Arrays.asList("id", "name", "num", "inprice"));
    }

    public void flushAllTables(JScrollPane scrollPane_sell_unchecked, JScrollPane jScrollPane_sell_unpaid,
                               JScrollPane jScrollPane_sell_return, JScrollPane jScrollPane_sell_finish,
                               JScrollPane jScrollPane_sell_allOrder, JScrollPane jScrollPane_stock_checkStock,
                               JScrollPane jScrollPane_sell_returned) {
        //刷新待审核的表
        Vector<Vector<Object>> data_sell_unchecked = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "1", "State");
        DefaultTableModel tablemodel_sell_unchecked = new DefaultTableModel(data_sell_unchecked, name_sell_haveStateChaneg);
        JTable table_sell_unchecked = new JTable();
        table_sell_unchecked.setModel(tablemodel_sell_unchecked);
        TableColumn tcm_sell_unchecked = table_sell_unchecked.getColumnModel().getColumn(name_sell_haveStateChaneg.size() - 1);
        boolean_button.boolean_button(table_sell_unchecked, data_sell_unchecked, name_sell_haveStateChaneg.size() - 1, tablemodel_sell_unchecked, db, tcm_sell_unchecked, "2", "1");
        scrollPane_sell_unchecked.setViewportView(table_sell_unchecked);
        //刷新待收款的表
        Vector<Vector<Object>> data_sell_unpaid = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "2", "State");
        DefaultTableModel tablemodel_sell_unpaid = new DefaultTableModel(data_sell_unpaid, name_sell_haveStateChaneg);
        JTable table_sell_unpaid = new JTable();
        table_sell_unpaid.setModel(tablemodel_sell_unpaid);
        TableColumn tcm_sell_unpaid = table_sell_unpaid.getColumnModel().getColumn(name_sell_haveStateChaneg.size() - 1);
        boolean_button.boolean_button(table_sell_unpaid, data_sell_unpaid, name_sell_haveStateChaneg.size() - 1, tablemodel_sell_unpaid, db, tcm_sell_unpaid, "3", "2");
        jScrollPane_sell_unpaid.setViewportView(table_sell_unpaid);
        //刷新退货中状态的表
        Vector<Vector<Object>> data_sell_return = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "4", "State");
        DefaultTableModel tablemodel_sell_return = new DefaultTableModel(data_sell_return, name_sell_haveStateChaneg);
        JTable table_sell_return = new JTable();
        table_sell_return.setModel(tablemodel_sell_return);
        TableColumn tcm_sell_return = table_sell_return.getColumnModel().getColumn(name_sell_haveStateChaneg.size() - 1);
        boolean_button.boolean_button(table_sell_return, data_sell_return, name_sell_haveStateChaneg.size() - 1, tablemodel_sell_return, db, tcm_sell_return, "5", "4");
        jScrollPane_sell_return.setViewportView(table_sell_return);
        //刷新已退货退货状态的表
        Vector<Vector<Object>> data_sell_returned = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "5", "State");
        DefaultTableModel tablemodel_sell_returned = new DefaultTableModel(data_sell_returned, name_sell_noStateChange);
        JTable table_sell_returned = new JTable();
        table_sell_returned.setModel(tablemodel_sell_returned);
        jScrollPane_sell_returned.setViewportView(table_sell_returned);
        //刷新已完成状态的表
        Vector<Vector<Object>> data_sell_finish = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "3", "State");
        DefaultTableModel tablemodel_sell_finish = new DefaultTableModel(data_sell_finish, name_sell_haveStateChaneg);
        JTable table_sell_finish = new JTable();
        table_sell_finish.setModel(tablemodel_sell_finish);
        TableColumn tcm_sell_finish = table_sell_finish.getColumnModel().getColumn(name_sell_haveStateChaneg.size() - 1);
        boolean_button.boolean_button(table_sell_finish, data_sell_finish, name_sell_haveStateChaneg.size() - 1, tablemodel_sell_finish, db, tcm_sell_finish, "4", "3");
        jScrollPane_sell_finish.setViewportView(table_sell_finish);
        //刷新订单列表
        Vector<Vector<Object>> data_sell_allOrder = returnVector.FromDBReadAll(db, "ordermanager", name_sell_noStateChange);
        DefaultTableModel tablemodel_sell_allOrder = new DefaultTableModel(data_sell_allOrder, name_sell_noStateChange);
        JTable table_sell_allOrder = new JTable();
        table_sell_allOrder.setModel(tablemodel_sell_allOrder);
        jScrollPane_sell_allOrder.setViewportView(table_sell_allOrder);
        //刷新库存清点列表
        Vector<Vector<Object>> data_stock_checkStock = returnVector.FromDBReadAll(db, "itemmanager", name_checkStock);
        DefaultTableModel tablemodel_stock_checkStock = new DefaultTableModel(data_stock_checkStock, name_sell_noStateChange);
        JTable table_stock_checkStock = new JTable();
        table_stock_checkStock.setModel(tablemodel_stock_checkStock);
        jScrollPane_stock_checkStock.setViewportView(table_stock_checkStock);
    }
}
