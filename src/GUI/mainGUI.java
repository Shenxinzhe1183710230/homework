package GUI;

import Bean.DBBean;
import op.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class mainGUI extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JTable table_1;
    private JTextField textField_2;
    private JTextField textField;
    private JTextField textField_1;
    private JTable table_2;
    private JTable table_3;
    private JTable table_4;
    private JTable table_5;
    private JTable table_6;
    private JTable table_7;
    private JTable table_9;
    private JTable table_8;
    private JTable table_10;
    private JTable table_IncomingGoodsList;
    private JTextField totalPriceDisplay;
    private JTextField textField_3;
    private JTextField textField_4;
    private JScrollPane scrollPane_8 = new JScrollPane();
    private JScrollPane scrollPane_3 = new JScrollPane();
    private DBBean db=new DBBean();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainGUI frame = new mainGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws SQLException
     */
    public mainGUI() throws SQLException {
        // 连接数据库
        //this.db = new DBBean();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 794, 510);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);

        // 商品
        JPanel panel = new JPanel();
        tabbedPane.addTab("\u8D27\u54C1", null, panel, null);
        panel.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);
        Vector<Object> name = new Vector<Object>();
        name.add("id");
        name.add("name");
        name.add("outprice");
        Vector<Vector<Object>> data = returnVector.FromDBReadAll(db,"itemmanager",name);
        DefaultTableModel tablemodel = new DefaultTableModel(data, name);
        table = new JTable();
        table.setModel(tablemodel);
        scrollPane.setViewportView(table);


        // 客户
        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("\u5BA2\u6237", null, panel_1, null);
        panel_1.setLayout(new BorderLayout(0, 0));

        // 新增用户按钮
        JButton btnNewButton_1 = new JButton("\u65B0\u589E\u5BA2\u6237");
        panel_1.add(btnNewButton_1, BorderLayout.NORTH);

        // 用户列表
        JScrollPane scrollPane_1 = new JScrollPane();
        panel_1.add(scrollPane_1, BorderLayout.CENTER);
        Vector<Object> name_1 = returnVector.getHeadName(db,"customermanager");
        Vector<Vector<Object>> data_1 = returnVector.FromDBReadAll(db,"customermanager",name_1);
        DefaultTableModel tablemodel_1 = new DefaultTableModel(data_1, name_1);
        table_1 = new JTable();
        table_1.setModel(tablemodel_1);
        scrollPane_1.setViewportView(table_1);

        // 新增用户按钮绑定
        btnNewButton_1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // 弹出对话框
                windowsToCreateCustomer window_2 = new windowsToCreateCustomer(db, table_1);
                window_2.setVisible(true);
                window_2.setSize(400, 400);

            }
        });

        Vector<Object> order_name = returnVector.getHeadName(db,"ordermanager");
        Vector<Object> order_name2 = returnVector.getHeadName(db,"ordermanager");
        order_name2.add("stateChange");
        //销售
        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("\u9500\u552E", null, panel_2, null);
        panel_2.setLayout(new BorderLayout(0, 0));

        //二级选项卡
        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        panel_2.add(tabbedPane_1, BorderLayout.CENTER);

        //开销售单
        JPanel panel_6 = new JPanel();
        tabbedPane_1.addTab("\u5F00\u9500\u552E\u5355", null, panel_6, null);
        panel_6.setLayout(new BorderLayout(0, 0));
        Box horizontalBox = Box.createHorizontalBox();
        panel_6.add(horizontalBox, BorderLayout.NORTH);

        // 客户确认按钮  显示的label
        JLabel lblNewLabel_2 = new JLabel("\u5BA2\u6237");
        horizontalBox.add(lblNewLabel_2);
        // 客户确认按钮  输入客户编号的文本框，通过姓名查
        textField_2 = new JTextField();
        textField_2.setText("input name");
        textField_2.setColumns(10);
        horizontalBox.add(textField_2);
        // 客户确认按钮  按钮大小等属性
        JButton btnNewButton_2_1 = new JButton("确认");
        horizontalBox.add(btnNewButton_2_1);

        Component horizontalStrut_1 = Box.createHorizontalStrut(450);
        horizontalBox.add(horizontalStrut_1);

        // 查找到的客户 label
        JLabel lblNewLabel_1_1 = new JLabel("查找到的客户");
        horizontalBox.add(lblNewLabel_1_1);
        // 查找到的客户 客户名的打印的textfield
        textField = new JTextField();
        textField.setEnabled(false);
        textField.setEditable(false);
        horizontalBox.add(textField);
        textField.setColumns(10);

        // 客户确认按钮  按钮绑定鉴定器
        btnNewButton_2_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet res = db.executeFind(textField_2.getText(), "customermanager", "name");
                try {
                    if (res.next()){
                        textField_2.setText("");
                        textField.setText(res.getString("name"));
                    }
                    else {
                        JTextArea aboutarea = new JTextArea();
                        aboutarea.setText("找不到用户！\n");
                        JOptionPane.showConfirmDialog(null,aboutarea,"Error!",JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel panel_10 = new JPanel();
        panel_6.add(panel_10, BorderLayout.SOUTH);
        panel_10.setLayout(new BorderLayout(0, 0));
        // 新增的按钮
        JButton btnNewButton_2 = new JButton("\u65B0\u589E");
        btnNewButton_2.setFont(new Font("����", Font.PLAIN, 16));
        panel_10.add(btnNewButton_2, BorderLayout.NORTH);
        // 销售保存按钮
        JButton btnNewButton_3 = new JButton("\u4FDD\u5B58");
        btnNewButton_3.setFont(new Font("����", Font.PLAIN, 16));
        panel_10.add(btnNewButton_3, BorderLayout.EAST);

        Box horizontalBox_1 = Box.createHorizontalBox();
        panel_10.add(horizontalBox_1, BorderLayout.WEST);

        // 共计的label
        JLabel lblNewLabel = new JLabel("    \u5171\u8BA1\uFF1A");
        lblNewLabel.setFont(new Font("����", Font.PLAIN, 16));
        horizontalBox_1.add(lblNewLabel);
        // 共计的显示textfield
        textField_1 = new JTextField();
        horizontalBox_1.add(textField_1);
        textField_1.setColumns(10);
        // 共计的单位“元“显示
        JLabel lblNewLabel_1 = new JLabel("\u5143");
        lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
        horizontalBox_1.add(lblNewLabel_1);

        JScrollPane scrollPane_2 = new JScrollPane();
        panel_6.add(scrollPane_2, BorderLayout.CENTER);

        // 显示添加的物品
        Vector<Object> name_2 = new Vector<Object>();
        name_2.add("name"); name_2.add("price/one"); name_2.add("num"); name_2.add("price/all");
        Vector<Vector<Object>> data_2 = new Vector<>();
        DefaultTableModel tableModel_2 = new DefaultTableModel(data_2, name_2);
        table_2 = new JTable();
        table_2.setModel(tableModel_2);
        scrollPane_2.setViewportView(table_2);

        // 销售保存按钮 监听
        btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameANDnum = "'";
                String priceAll = textField_1.getText();
                for (Vector<Object> x : data_2){
                    nameANDnum = nameANDnum + "<" + x.get(0) +","+ x.get(2) + ">";
                }
                nameANDnum = nameANDnum + "'";
                db.executeQuery( "ordermanager(Name, State, Items, price_all)",
                        "'" + textField.getText()+"',1,"+nameANDnum+","+priceAll);
                data_2.clear();
                table_2.setModel(new DefaultTableModel(data_2, name_2));
                textField_1.setText("");
                // 待审核刷新
                Vector<Vector<Object>> data_3 = returnVector.FromDBRead(db, "ordermanager", order_name, "1", "State");
                DefaultTableModel tablemodel_3 = new DefaultTableModel(data_3, order_name2);
                table_3 = new JTable();
                table_3.setModel(tablemodel_3);
                scrollPane_3.setViewportView(table_3);

                // 订单列表刷新
                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",order_name);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, order_name);
                table_8 = new JTable();
                table_8.setModel(tablemodel_8);
                scrollPane_8.setViewportView(table_8);
            }
        });

        // 新增按钮的button绑定
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                windowsToCreateOrders window_1 = new windowsToCreateOrders(db, table_2, data_2, name_2, textField_1);
                window_1.setVisible(true);
                window_1.setSize(400, 400);
            }
        });


        //待审核
        JPanel panel_5 = new JPanel();
        tabbedPane_1.addTab("待审核", null, panel_5, null);
        panel_5.setLayout(new BorderLayout(0, 0));
        panel_5.add(scrollPane_3, BorderLayout.CENTER);
        Vector<Vector<Object>> data_3 = returnVector.FromDBRead(db, "ordermanager", order_name, "1", "State");
        DefaultTableModel tablemodel_3 = new DefaultTableModel(data_3, order_name2);
        table_3 = new JTable();
        table_3.setModel(tablemodel_3);
        scrollPane_3.setViewportView(table_3);
        TableColumn tcm = table_3.getColumnModel().getColumn(order_name2.size()-1);
        boolean_button.boolean_button(table_3,data_3,order_name2.size()-1,tablemodel_3,db,tcm,"2","1");
        JButton btnNewButton = new JButton("确认");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> data_tmp = returnVector.FromDBRead(db, "ordermanager", order_name, "1", "State");
                DefaultTableModel tablemodel_tmp = new DefaultTableModel(data_tmp, order_name2);
                table_3.setModel(tablemodel_tmp);
                Vector<Vector<Object>> data_tmp_nextState = returnVector.FromDBRead(db, "ordermanager", order_name, "2", "State");
                DefaultTableModel tablemodel_tmp_nextState = new DefaultTableModel(data_tmp_nextState, order_name2);
                table_4.setModel(tablemodel_tmp_nextState);
                // flag
                TableColumn tcm_tmp_nextState = table_4.getColumnModel().getColumn(order_name2.size()-1);
                boolean_button.boolean_button(table_4, data_tmp_nextState,order_name2.size()-1,tablemodel_tmp_nextState,db,tcm_tmp_nextState,"3","2");

                TableColumn tcm = table_3.getColumnModel().getColumn(order_name2.size()-1);
                boolean_button.boolean_button(table_3,data_tmp,order_name2.size()-1,tablemodel_tmp,db,tcm,"2","1");

                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",order_name);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, order_name);
                table_8 = new JTable();
                table_8.setModel(tablemodel_8);
                scrollPane_8.setViewportView(table_8);
            }
        });
        panel_5.add(btnNewButton, BorderLayout.SOUTH);



        //待收款
        JPanel panel_7 = new JPanel();
        tabbedPane_1.addTab("待收款", null, panel_7, null);
        panel_7.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_4 = new JScrollPane();
        panel_7.add(scrollPane_4, BorderLayout.CENTER);
        Vector<Vector<Object>> data_4 = returnVector.FromDBRead(db, "ordermanager", order_name, "2", "State");
        DefaultTableModel tablemodel_4 = new DefaultTableModel(data_4, order_name2);
        table_4 = new JTable();
        table_4.setModel(tablemodel_4);
        scrollPane_4.setViewportView(table_4);
        TableColumn tcm2 = table_4.getColumnModel().getColumn(order_name2.size()-1);
        boolean_button.boolean_button(table_4,data_4,order_name2.size()-1,tablemodel_4,db,tcm2,"3","2");
        JButton btnNewButton_4 = new JButton("确认");
        btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> data_tmp = returnVector.FromDBRead(db, "ordermanager", order_name, "2", "State");
                DefaultTableModel tablemodel_tmp = new DefaultTableModel(data_tmp, order_name2);
                table_4.setModel(tablemodel_tmp);
                Vector<Vector<Object>> data_tmp_nextState = returnVector.FromDBRead(db, "ordermanager", order_name, "3", "State");
                DefaultTableModel tablemodel_tmp_nextState = new DefaultTableModel(data_tmp_nextState, order_name2);
                table_5.setModel(tablemodel_tmp_nextState);
                // flag
                TableColumn tcm_tmp_nextState = table_5.getColumnModel().getColumn(order_name2.size()-1);
                boolean_button.boolean_button(table_5, data_tmp_nextState,order_name2.size()-1,tablemodel_tmp_nextState,db,tcm_tmp_nextState,"4","3");

                TableColumn tcm2 = table_4.getColumnModel().getColumn(order_name2.size()-1);
                boolean_button.boolean_button(table_4,data_tmp,order_name2.size()-1,tablemodel_tmp,db,tcm2,"3","2");

                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",order_name);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, order_name);
                table_8 = new JTable();
                table_8.setModel(tablemodel_8);
                scrollPane_8.setViewportView(table_8);
            }
        });
        panel_7.add(btnNewButton_4, BorderLayout.SOUTH);


        //退货
        JPanel panel_8 = new JPanel();
        tabbedPane_1.addTab("\u9000\u8D27", null, panel_8, null);
        panel_8.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_5 = new JScrollPane();
        panel_8.add(scrollPane_5, BorderLayout.CENTER);
        Vector<Vector<Object>> data_5 = returnVector.FromDBRead(db, "ordermanager", order_name, "3", "State");
        DefaultTableModel tablemodel_5 = new DefaultTableModel(data_5, order_name2);
        table_5 = new JTable();
        table_5.setModel(tablemodel_5);
        scrollPane_5.setViewportView(table_5);
        TableColumn tcm3 = table_5.getColumnModel().getColumn(order_name2.size()-1);
        boolean_button.boolean_button(table_5,data_5,order_name2.size()-1,tablemodel_5,db,tcm3,"4","3");
        // 销售->退货->确认按钮->绑定监视器
        JButton btnNewButton_5 = new JButton("确认");
        btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> data_tmp = returnVector.FromDBRead(db, "ordermanager", order_name, "3", "State");
                DefaultTableModel tablemodel_tmp = new DefaultTableModel(data_tmp, order_name2);
                table_5.setModel(tablemodel_tmp);
                Vector<Vector<Object>> data_tmp_nextState = returnVector.FromDBRead(db, "ordermanager", order_name, "4", "State");
                DefaultTableModel tablemodel_tmp_nextState = new DefaultTableModel(data_tmp_nextState, order_name2);
                table_6.setModel(tablemodel_tmp_nextState);
                TableColumn tcm_tmp_nextState = table_6.getColumnModel().getColumn(order_name2.size()-1);
                boolean_button.boolean_button(table_6, data_tmp_nextState,order_name2.size()-1,tablemodel_tmp_nextState,db,tcm_tmp_nextState,"3","4");
                TableColumn tcm2 = table_5.getColumnModel().getColumn(order_name2.size()-1);
                boolean_button.boolean_button(table_5,data_tmp,order_name2.size()-1,tablemodel_tmp,db,tcm2,"4","3");

                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",order_name);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, order_name);
                table_8 = new JTable();
                table_8.setModel(tablemodel_8);
                scrollPane_8.setViewportView(table_8);
            }
        });
        panel_8.add(btnNewButton_5, BorderLayout.SOUTH);

        //已完成
        JPanel panel_12 = new JPanel();
        tabbedPane_1.addTab("已完成", null, panel_12, null);
        panel_12.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_6 = new JScrollPane();
        panel_12.add(scrollPane_6, BorderLayout.CENTER);
        Vector<Vector<Object>> data_6 = returnVector.FromDBRead(db, "ordermanager", order_name, "4", "State");
        DefaultTableModel tablemodel_6 = new DefaultTableModel(data_6, order_name2);
        table_6 = new JTable();
        table_6.setModel(tablemodel_6);
        scrollPane_6.setViewportView(table_6);
        TableColumn tcm4 = table_6.getColumnModel().getColumn(order_name2.size()-1);
        boolean_button.boolean_button(table_6,data_6,order_name2.size()-1,tablemodel_6,db,tcm4,"3","4");
        JButton btnNewButton_6 = new JButton("确认");
        btnNewButton_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> data_tmp = returnVector.FromDBRead(db, "ordermanager", order_name, "4", "State");
                DefaultTableModel tablemodel_tmp = new DefaultTableModel(data_tmp, order_name2);
                table_6.setModel(tablemodel_tmp);
                Vector<Vector<Object>> data_tmp_nextState = returnVector.FromDBRead(db, "ordermanager", order_name, "3", "State");
                DefaultTableModel tablemodel_tmp_nextState = new DefaultTableModel(data_tmp_nextState, order_name2);
                table_5.setModel(tablemodel_tmp_nextState);
                TableColumn tcm_tmp_nextState = table_5.getColumnModel().getColumn(order_name2.size()-1);
                boolean_button.boolean_button(table_5, data_tmp_nextState,order_name2.size()-1,tablemodel_tmp_nextState,db,tcm_tmp_nextState,"4","3");
                TableColumn tcm2 = table_6.getColumnModel().getColumn(order_name2.size()-1);
                boolean_button.boolean_button(table_6,data_tmp,order_name2.size()-1,tablemodel_tmp,db,tcm2,"3","4");

                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",order_name);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, order_name);
                table_8 = new JTable();
                table_8.setModel(tablemodel_8);
                scrollPane_8.setViewportView(table_8);
            }
        });
        panel_12.add(btnNewButton_6, BorderLayout.SOUTH);

        //订单列表
        JPanel panel_4 = new JPanel();
        tabbedPane_1.addTab("订单列表", null, panel_4, null);
        panel_4.setLayout(new BorderLayout(0, 0));
        panel_4.add(scrollPane_8, BorderLayout.CENTER);
        Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",order_name);
        DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, order_name);
        table_8 = new JTable();
        table_8.setModel(tablemodel_8);
        scrollPane_8.setViewportView(table_8);
        Box horizontalBox_2 = Box.createHorizontalBox();
        panel_4.add(horizontalBox_2, BorderLayout.NORTH);

        // 销售->订单列表->功能按钮部分->订单号tabel
        JLabel lblNewLabel_5 = new JLabel("订单号：");
        horizontalBox_2.add(lblNewLabel_5);

        // 销售->订单列表->功能按钮部分->订单号输入的textfield
        textField_3 = new JTextField();
        horizontalBox_2.add(textField_3);
        textField_3.setColumns(10);

        // 销售->订单列表->功能按钮部分->查询按钮
        JButton btnNewButton_7 = new JButton("查 询");
        btnNewButton_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table_8.setModel(new DefaultTableModel(returnVector.FromDBRead(db, "ordermanager", order_name, textField_3.getText(), "ID"), order_name));
            }
        });
        horizontalBox_2.add(btnNewButton_7);
        // TODO 修改按钮
        // 销售->订单列表->功能按钮部分->删除按钮
        JButton btnDel = new JButton("删 除");
        horizontalBox_2.add(btnDel);
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.executeDelete(textField_3.getText(), "ordermanager", "ID");
                // TODO 删除了之后。相关的table没有更新。删除成功的弹窗
            }
        });

        Component horizontalStrut_2 = Box.createHorizontalStrut(400);
        horizontalBox_2.add(horizontalStrut_2);

        Box horizontalBox_3 = Box.createHorizontalBox();
        panel_4.add(horizontalBox_3, BorderLayout.SOUTH);

        // 销售->订单列表->总利润部分->总利润label
        JLabel lblNewLabel_6 = new JLabel("总利润：");
        horizontalBox_3.add(lblNewLabel_6);

        // 销售->订单列表->总利润部分->总利润显示的textfield
        textField_4 = new JTextField();
        textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
        textField_4.setEditable(false);
        textField_4.setEnabled(false);
        horizontalBox_3.add(textField_4);
        textField_4.setColumns(1);

        // 销售->订单列表->总利润部分->总利润显示的单位
        JLabel lblNewLabel_7 = new JLabel("元");
        horizontalBox_3.add(lblNewLabel_7);

        Component horizontalStrut = Box.createHorizontalStrut(600);
        horizontalBox_3.add(horizontalStrut);



        // 库存
        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("\u5E93\u5B58", null, panel_3, null);
        panel_3.setLayout(new BorderLayout(0, 0));
        JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
        panel_3.add(tabbedPane_2, BorderLayout.CENTER);

        // 进货
        JPanel panel_AddIncomingOrders = new JPanel();
        tabbedPane_2.addTab("进货", null, panel_AddIncomingOrders, null);
        panel_AddIncomingOrders.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_IncomingGoodsList = new JScrollPane();
        panel_AddIncomingOrders.add(scrollPane_IncomingGoodsList, BorderLayout.CENTER);

        // 进货列表的tablemodel
        Vector<Object> name_7 = new Vector<Object>();
        name_7.add("货品");
        name_7.add("单价");
        name_7.add("数量");
        name_7.add("总价");
        Vector<Vector<Object>> data_7 = new Vector<Vector<Object>>();
        DefaultTableModel tablemodel_7 = new DefaultTableModel(data_7, name_7);

        // 进货列表的table
        table_IncomingGoodsList = new JTable();
        table_IncomingGoodsList.setModel(tablemodel_7);
        scrollPane_IncomingGoodsList.setViewportView(table_IncomingGoodsList);

        JPanel panel_11 = new JPanel();
        panel_AddIncomingOrders.add(panel_11, BorderLayout.SOUTH);
        panel_11.setLayout(new BorderLayout(0, 0));


        // 库存保存按钮
        JButton btnSave = new JButton("保存");
        panel_11.add(btnSave, BorderLayout.EAST);

        Box horizontalBox_totalIncomingPrice = Box.createHorizontalBox();
        panel_11.add(horizontalBox_totalIncomingPrice, BorderLayout.WEST);

        // 合计按钮
        JLabel lblNewLabel_3 = new JLabel("合计：");
        horizontalBox_totalIncomingPrice.add(lblNewLabel_3);
        // 合计显示价格的TextField
        totalPriceDisplay = new JTextField();
        totalPriceDisplay.setEnabled(false);
        totalPriceDisplay.setEditable(false);
        horizontalBox_totalIncomingPrice.add(totalPriceDisplay);
        totalPriceDisplay.setColumns(10);
        // 合计显示价格的单位
        JLabel lblNewLabel_4 = new JLabel("元");
        horizontalBox_totalIncomingPrice.add(lblNewLabel_4);

        // 新增货物按钮
        JButton btnAddIncomingGoods = new JButton("+ 新增货物");
        panel_11.add(btnAddIncomingGoods, BorderLayout.NORTH);
        btnAddIncomingGoods.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                windowsToCreateItem window_3 = new windowsToCreateItem(table_IncomingGoodsList, data_7, name_7, totalPriceDisplay);
                window_3.setVisible(true);
                window_3.setSize(400, 400);

            }
        });


        // 清点
        JPanel panel_13 = new JPanel();
        tabbedPane_2.addTab("清点", null, panel_13, null);
        panel_13.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_10 = new JScrollPane();
        panel_13.add(scrollPane_10, BorderLayout.CENTER);
        Vector<Object> name_10 = new Vector<>();
        name_10.add("id");
        name_10.add("name");
        name_10.add("num");
        Vector<Vector<Object>> data_10 = returnVector.FromDBReadAll(db,"itemmanager",name_10);
        DefaultTableModel tablemodel_10 = new DefaultTableModel(data_10, name_10);
        table_10 = new JTable();
        table_10.setModel(tablemodel_10);
        scrollPane_10.setViewportView(table_10);

        // 库存保存按钮的监听器
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Vector<Object> temp : data_7){
                    db.executeQuery("itemmanager(Name,OutPrice,Num,InPrice)",
                            "'"+temp.get(0)+"','"+temp.get(1)+"','" + temp.get(2) +"','"+temp.get(3)+"'");
                }
                Vector<Vector<Object>> data_10 = returnVector.FromDBReadAll(db,"itemmanager",name_10);
                DefaultTableModel tablemodel_10 = new DefaultTableModel(data_10, name_10);
                table_10.setModel(tablemodel_10);
                table_IncomingGoodsList.setModel(new DefaultTableModel(new Vector<Vector<Object>>(), name_7));
                totalPriceDisplay.setText("");
            }
        });
    }
}
