package GUI;

import Bean.DBBean;
import op.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private JTable table_product;
    private JTable table_allClient;
    private JTextField textField_makeSellOrders_inputClientName;
    private JTextField textField_makeSellOrders_findres;
    private JTextField textField_makeSellOrders_total;
    private JTable table_makeSellOrders_items;
    private JTable table_unchecked;
    private JTable table_unpaid;
    private JTable table_return;
    private JTable table_finish;
    private JTable table_7;
    private JTable table_9;
    private JTable table_allOrder;
    private JTable table_checkStock;
    private JTable table_inStock_addProduct;
    private JTextField textField_inStock_totalProfit;
    private JTextField textField_allOrder_inputOrderID;
    private JTextField textField_allOrder_totalProfit;
    private JScrollPane scrollPane_allOrder = new JScrollPane();
    private JScrollPane scrollPane_unchecked = new JScrollPane();
    private DBBean db=new DBBean();
    Vector<Object> name_checkStock = new Vector<>();

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
        // 设置总面板属性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 794, 510);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);

        // ****************************************货品**************************************
        JPanel panel_product = new JPanel();
        tabbedPane.addTab("货品(product)", null, panel_product, null);
        panel_product.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_product = new JScrollPane();
        scrollPane_product.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel_product.add(scrollPane_product, BorderLayout.CENTER);
        Vector<Object> name_product = new Vector<Object>();
        name_product.add("id");
        name_product.add("name");
        name_product.add("outprice");
        Vector<Vector<Object>> data_product = returnVector.FromDBReadAll(db,"itemmanager",name_product);
        DefaultTableModel tablemodel_product = new DefaultTableModel(data_product, name_product);
        table_product = new JTable();
        table_product.setModel(tablemodel_product);
        scrollPane_product.setViewportView(table_product);


        //*******************************客户************************************************
        JPanel panel_client = new JPanel();
        tabbedPane.addTab("客户(client)", null, panel_client, null);
        panel_client.setLayout(new BorderLayout(0, 0));

        // 新增用户按钮
        JButton btnNewButton_client_addNewClient = new JButton("\u65B0\u589E\u5BA2\u6237(btnNewButton_client_addNewClient)");
        panel_client.add(btnNewButton_client_addNewClient, BorderLayout.NORTH);
        // 新增用户按钮绑定
        btnNewButton_client_addNewClient.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // 弹出对话框
                windowsToCreateCustomer window_2 = new windowsToCreateCustomer(db, table_allClient);
                window_2.setVisible(true);
                window_2.setSize(400, 400);
            }
        });

        // 用户列表
        JScrollPane scrollPane_allClient = new JScrollPane();
        panel_client.add(scrollPane_allClient, BorderLayout.CENTER);
        Vector<Object> name_Of_AllClientTable = returnVector.getHeadName(db,"customermanager");
        Vector<Vector<Object>> data_Of_AllClientTable = returnVector.FromDBReadAll(db,"customermanager",name_Of_AllClientTable);
        DefaultTableModel tablemodel_AllClient = new DefaultTableModel(data_Of_AllClientTable, name_Of_AllClientTable);
        table_allClient = new JTable();
        table_allClient.setModel(tablemodel_AllClient);
        scrollPane_allClient.setViewportView(table_allClient);


        //**************************************销售****************************
        Vector<Object> name_sell_noStateChange = returnVector.getHeadName(db,"ordermanager");
        Vector<Object> name_sell_haveStateChaneg = returnVector.getHeadName(db,"ordermanager");
        name_sell_haveStateChaneg.add("stateChange");
        // 销售
        JPanel panel_sell = new JPanel();
        tabbedPane.addTab("销售(sell)", null, panel_sell, null);
        panel_sell.setLayout(new BorderLayout(0, 0));

        //二级选项卡
        JTabbedPane tabbedPane_sell = new JTabbedPane(JTabbedPane.TOP);
        panel_sell.add(tabbedPane_sell, BorderLayout.CENTER);

        //------------------开销售单---------------------------------------
        JPanel panel_makeSellOrders = new JPanel();
        tabbedPane_sell.addTab("开销售单(makeSellOrders)", null, panel_makeSellOrders, null);
        panel_makeSellOrders.setLayout(new BorderLayout(0, 0));
        // 顶部二级容器
        Box box_makeSellOrders_up = Box.createHorizontalBox();
        panel_makeSellOrders.add(box_makeSellOrders_up, BorderLayout.NORTH);
        // 客户确认按钮  显示的label
        JLabel lblNewLabel_makeSellOrders_clientConfirm = new JLabel("确认");
        box_makeSellOrders_up.add(lblNewLabel_makeSellOrders_clientConfirm);
        // 客户确认按钮  输入客户编号的文本框，通过姓名查
        textField_makeSellOrders_inputClientName = new JTextField();
        textField_makeSellOrders_inputClientName.setText("input name");
        textField_makeSellOrders_inputClientName.setColumns(10);
        box_makeSellOrders_up.add(textField_makeSellOrders_inputClientName);
        // 客户确认按钮  按钮大小等属性
        JButton button_makeSellOrders_check = new JButton("确认");
        box_makeSellOrders_up.add(button_makeSellOrders_check);
        //占位符
        Component placeholder_makeSellOrders_1 = Box.createHorizontalStrut(450);
        box_makeSellOrders_up.add(placeholder_makeSellOrders_1);

        // 查找到的客户 label
        JLabel label_makeSellOrders_findres = new JLabel("查找到的客户");
        box_makeSellOrders_up.add(label_makeSellOrders_findres);
        // 查找到的客户 客户名的打印的textfield
        textField_makeSellOrders_findres = new JTextField();
        textField_makeSellOrders_findres.setEnabled(false);
        textField_makeSellOrders_findres.setEditable(false);
        box_makeSellOrders_up.add(textField_makeSellOrders_findres);
        textField_makeSellOrders_findres.setColumns(10);

        // 客户确认按钮  按钮绑定鉴定器
        button_makeSellOrders_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet res = db.executeFind(textField_makeSellOrders_inputClientName.getText(), "customermanager", "name");
                try {
                    if (res.next()){
                        textField_makeSellOrders_inputClientName.setText("");
                        textField_makeSellOrders_findres.setText(res.getString("name"));
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
        // 底部按钮所在的panel todo
        JPanel panel_makeSellOrders_down = new JPanel();
        panel_makeSellOrders.add(panel_makeSellOrders_down, BorderLayout.SOUTH);
        panel_makeSellOrders_down.setLayout(new BorderLayout(0, 0));
        // 新增的按钮
        JButton button_makeSellOrders_new = new JButton("\u65B0\u589E");
        button_makeSellOrders_new.setFont(new Font("����", Font.PLAIN, 16));
        panel_makeSellOrders_down.add(button_makeSellOrders_new, BorderLayout.NORTH);
        // 销售保存按钮
        JButton button_makeSellOrders_save = new JButton("\u4FDD\u5B58");
        button_makeSellOrders_save.setFont(new Font("����", Font.PLAIN, 16));
        panel_makeSellOrders_down.add(button_makeSellOrders_save, BorderLayout.EAST);
        // 底部二级容器
        Box box_makeSellOrders_down = Box.createHorizontalBox();
        panel_makeSellOrders_down.add(box_makeSellOrders_down, BorderLayout.WEST);

        // 共计的label
        JLabel label_makeSellOrders_total = new JLabel("    \u5171\u8BA1\uFF1A");
        label_makeSellOrders_total.setFont(new Font("����", Font.PLAIN, 16));
        box_makeSellOrders_down.add(label_makeSellOrders_total);
        // 共计的显示textfield
        textField_makeSellOrders_total = new JTextField();
        box_makeSellOrders_down.add(textField_makeSellOrders_total);
        textField_makeSellOrders_total.setColumns(10);
        // 共计的单位“元“显示
        JLabel label_makeSellOrders_unit = new JLabel("\u5143");
        label_makeSellOrders_unit.setFont(new Font("����", Font.PLAIN, 16));
        box_makeSellOrders_down.add(label_makeSellOrders_unit);

        // 订单中物品的scrollPane
        JScrollPane scrollPane_makeSellOrders_items = new JScrollPane();
        panel_makeSellOrders.add(scrollPane_makeSellOrders_items, BorderLayout.CENTER);
        // 显示添加的物品
        Vector<Object> name_makeSellOrders_items = new Vector<Object>();
        name_makeSellOrders_items.add("name"); name_makeSellOrders_items.add("price/one"); name_makeSellOrders_items.add("num"); name_makeSellOrders_items.add("price/all");
        Vector<Vector<Object>> data_makeSellOrders_items = new Vector<>();
        DefaultTableModel model_makeSellOrders_items = new DefaultTableModel(data_makeSellOrders_items, name_makeSellOrders_items);
        table_makeSellOrders_items = new JTable();
        table_makeSellOrders_items.setModel(model_makeSellOrders_items);
        scrollPane_makeSellOrders_items.setViewportView(table_makeSellOrders_items);

        // 销售保存按钮 监听
        button_makeSellOrders_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameANDnum = "'";
                String priceAll = textField_makeSellOrders_total.getText();
                for (Vector<Object> x : data_makeSellOrders_items){
                    nameANDnum = nameANDnum + "<" + x.get(0) +","+ x.get(2) + ">";
                }
                nameANDnum = nameANDnum + "'";
                db.executeQuery( "ordermanager(Name, State, Items, price_all)",
                        "'" + textField_makeSellOrders_findres.getText()+"',1,"+nameANDnum+","+priceAll);
                data_makeSellOrders_items.clear();
                table_makeSellOrders_items.setModel(new DefaultTableModel(data_makeSellOrders_items, name_makeSellOrders_items));
                textField_makeSellOrders_total.setText("");
                // 待审核刷新
                Vector<Vector<Object>> data_3 = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "1", "State");
                DefaultTableModel tablemodel_3 = new DefaultTableModel(data_3, name_sell_haveStateChaneg);
                table_unchecked = new JTable();
                table_unchecked.setModel(tablemodel_3);
                scrollPane_unchecked.setViewportView(table_unchecked);

                // 订单列表刷新
                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",name_sell_noStateChange);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, name_sell_noStateChange);
                table_allOrder = new JTable();
                table_allOrder.setModel(tablemodel_8);
                scrollPane_allOrder.setViewportView(table_allOrder);
            }
        });

        // 新增按钮的button绑定
        button_makeSellOrders_new.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                windowsToCreateOrders window_1 = new windowsToCreateOrders(db, table_makeSellOrders_items, data_makeSellOrders_items, name_makeSellOrders_items, textField_makeSellOrders_total);
                window_1.setVisible(true);
                window_1.setSize(400, 400);
            }
        });



        //------------------待审核---------------------------------------
        // 待审核总的panel
        JPanel panel_unchecked = new JPanel();
        tabbedPane_sell.addTab("待审核(unchecked)", null, panel_unchecked, null);
        panel_unchecked.setLayout(new BorderLayout(0, 0));
        // 待审核订单的scrollpanel
        panel_unchecked.add(scrollPane_unchecked, BorderLayout.CENTER);
        Vector<Vector<Object>> data_unchecked = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "1", "State");
        DefaultTableModel model_unchecked = new DefaultTableModel(data_unchecked, name_sell_haveStateChaneg);
        table_unchecked = new JTable();
        table_unchecked.setModel(model_unchecked);
        scrollPane_unchecked.setViewportView(table_unchecked);
        TableColumn tcm_unchecked = table_unchecked.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
        boolean_button.boolean_button(table_unchecked,data_unchecked,name_sell_haveStateChaneg.size()-1,model_unchecked,db,tcm_unchecked,"2","1");
        JButton button_unchecked_check = new JButton("确认");
        button_unchecked_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> data_tmp = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "1", "State");
                DefaultTableModel tablemodel_tmp = new DefaultTableModel(data_tmp, name_sell_haveStateChaneg);
                table_unchecked.setModel(tablemodel_tmp);
                Vector<Vector<Object>> data_tmp_nextState = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "2", "State");
                DefaultTableModel tablemodel_tmp_nextState = new DefaultTableModel(data_tmp_nextState, name_sell_haveStateChaneg);
                table_unpaid.setModel(tablemodel_tmp_nextState);
                // flag
                TableColumn tcm_tmp_nextState = table_unpaid.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
                boolean_button.boolean_button(table_unpaid, data_tmp_nextState,name_sell_haveStateChaneg.size()-1,tablemodel_tmp_nextState,db,tcm_tmp_nextState,"3","2");

                TableColumn tcm = table_unchecked.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
                boolean_button.boolean_button(table_unchecked,data_tmp,name_sell_haveStateChaneg.size()-1,tablemodel_tmp,db,tcm,"2","1");

                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",name_sell_noStateChange);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, name_sell_noStateChange);
                table_allOrder = new JTable();
                table_allOrder.setModel(tablemodel_8);
                scrollPane_allOrder.setViewportView(table_allOrder);
            }
        });
        panel_unchecked.add(button_unchecked_check, BorderLayout.SOUTH);


        //------------------待收款---------------------------------------
        JPanel panel_unpaid = new JPanel();
        tabbedPane_sell.addTab("待收款(unpaid)", null, panel_unpaid, null);
        panel_unpaid.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_unpaid = new JScrollPane();
        panel_unpaid.add(scrollPane_unpaid, BorderLayout.CENTER);
        Vector<Vector<Object>> data_unpaid = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "2", "State");
        DefaultTableModel model_unpaid = new DefaultTableModel(data_unpaid, name_sell_haveStateChaneg);
        table_unpaid = new JTable();
        table_unpaid.setModel(model_unpaid);
        scrollPane_unpaid.setViewportView(table_unpaid);
        TableColumn tcm_unpaid = table_unpaid.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
        boolean_button.boolean_button(table_unpaid,data_unpaid,name_sell_haveStateChaneg.size()-1,model_unpaid,db,tcm_unpaid,"3","2");
        JButton button_unpaid_check = new JButton("确认");
        button_unpaid_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> data_tmp = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "2", "State");
                DefaultTableModel tablemodel_tmp = new DefaultTableModel(data_tmp, name_sell_haveStateChaneg);
                table_unpaid.setModel(tablemodel_tmp);
                Vector<Vector<Object>> data_tmp_nextState = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "3", "State");
                DefaultTableModel tablemodel_tmp_nextState = new DefaultTableModel(data_tmp_nextState, name_sell_haveStateChaneg);
                table_return.setModel(tablemodel_tmp_nextState);
                // flag
                TableColumn tcm_tmp_nextState = table_return.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
                boolean_button.boolean_button(table_return, data_tmp_nextState,name_sell_haveStateChaneg.size()-1,tablemodel_tmp_nextState,db,tcm_tmp_nextState,"4","3");

                TableColumn tcm2 = table_unpaid.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
                boolean_button.boolean_button(table_unpaid,data_tmp,name_sell_haveStateChaneg.size()-1,tablemodel_tmp,db,tcm2,"3","2");

                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",name_sell_noStateChange);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, name_sell_noStateChange);
                table_allOrder = new JTable();
                table_allOrder.setModel(tablemodel_8);
                scrollPane_allOrder.setViewportView(table_allOrder);
            }
        });
        panel_unpaid.add(button_unpaid_check, BorderLayout.SOUTH);


        //------------------退货---------------------------------------
        JPanel panel_return = new JPanel();
        tabbedPane_sell.addTab("\u9000\u8D27(return)", null, panel_return, null);
        panel_return.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_return = new JScrollPane();
        panel_return.add(scrollPane_return, BorderLayout.CENTER);
        Vector<Vector<Object>> data_return = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "3", "State");
        DefaultTableModel model_return = new DefaultTableModel(data_return, name_sell_haveStateChaneg);
        table_return = new JTable();
        table_return.setModel(model_return);
        scrollPane_return.setViewportView(table_return);
        TableColumn tcm_return = table_return.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
        boolean_button.boolean_button(table_return,data_return,name_sell_haveStateChaneg.size()-1,model_return,db,tcm_return,"4","3");
        // 销售->退货->确认按钮->绑定监视器
        JButton button_return_check = new JButton("确认");
        button_return_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> data_tmp = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "3", "State");
                DefaultTableModel tablemodel_tmp = new DefaultTableModel(data_tmp, name_sell_haveStateChaneg);
                table_return.setModel(tablemodel_tmp);
                Vector<Vector<Object>> data_tmp_nextState = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "4", "State");
                DefaultTableModel tablemodel_tmp_nextState = new DefaultTableModel(data_tmp_nextState, name_sell_haveStateChaneg);
                table_finish.setModel(tablemodel_tmp_nextState);
                TableColumn tcm_tmp_nextState = table_finish.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
                boolean_button.boolean_button(table_finish, data_tmp_nextState,name_sell_haveStateChaneg.size()-1,tablemodel_tmp_nextState,db,tcm_tmp_nextState,"3","4");
                TableColumn tcm2 = table_return.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
                boolean_button.boolean_button(table_return,data_tmp,name_sell_haveStateChaneg.size()-1,tablemodel_tmp,db,tcm2,"4","3");

                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",name_sell_noStateChange);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, name_sell_noStateChange);
                table_allOrder = new JTable();
                table_allOrder.setModel(tablemodel_8);
                scrollPane_allOrder.setViewportView(table_allOrder);
            }
        });
        panel_return.add(button_return_check, BorderLayout.SOUTH);

        //------------------已完成---------------------------------------
        JPanel panel_finish = new JPanel();
        tabbedPane_sell.addTab("已完成(finish)", null, panel_finish, null);
        panel_finish.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_finish = new JScrollPane();
        panel_finish.add(scrollPane_finish, BorderLayout.CENTER);
        Vector<Vector<Object>> data_finish = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "4", "State");
        DefaultTableModel model_finish = new DefaultTableModel(data_finish, name_sell_haveStateChaneg);
        table_finish = new JTable();
        table_finish.setModel(model_finish);
        scrollPane_finish.setViewportView(table_finish);
        TableColumn tcm_finish = table_finish.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
        boolean_button.boolean_button(table_finish,data_finish,name_sell_haveStateChaneg.size()-1,model_finish,db,tcm_finish,"3","4");
        JButton button_finish_check = new JButton("确认");
        button_finish_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> data_tmp = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "4", "State");
                DefaultTableModel tablemodel_tmp = new DefaultTableModel(data_tmp, name_sell_haveStateChaneg);
                table_finish.setModel(tablemodel_tmp);
                Vector<Vector<Object>> data_tmp_nextState = returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, "3", "State");
                DefaultTableModel tablemodel_tmp_nextState = new DefaultTableModel(data_tmp_nextState, name_sell_haveStateChaneg);
                table_return.setModel(tablemodel_tmp_nextState);
                TableColumn tcm_tmp_nextState = table_return.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
                boolean_button.boolean_button(table_return, data_tmp_nextState,name_sell_haveStateChaneg.size()-1,tablemodel_tmp_nextState,db,tcm_tmp_nextState,"4","3");
                TableColumn tcm2 = table_finish.getColumnModel().getColumn(name_sell_haveStateChaneg.size()-1);
                boolean_button.boolean_button(table_finish,data_tmp,name_sell_haveStateChaneg.size()-1,tablemodel_tmp,db,tcm2,"3","4");

                Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",name_sell_noStateChange);
                DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, name_sell_noStateChange);
                table_allOrder = new JTable();
                table_allOrder.setModel(tablemodel_8);
                scrollPane_allOrder.setViewportView(table_allOrder);
            }
        });
        panel_finish.add(button_finish_check, BorderLayout.SOUTH);

        //------------------订单列表---------------------------------------
        JPanel panel_allOrder = new JPanel();
        tabbedPane_sell.addTab("订单列表(allOrder)", null, panel_allOrder, null);
        panel_allOrder.setLayout(new BorderLayout(0, 0));
        panel_allOrder.add(scrollPane_allOrder, BorderLayout.CENTER);
        Vector<Vector<Object>> data_allOrder = returnVector.FromDBReadAll(db,"ordermanager",name_sell_noStateChange);
        DefaultTableModel model_allOrder = new DefaultTableModel(data_allOrder, name_sell_noStateChange);
        table_allOrder = new JTable();
        table_allOrder.setModel(model_allOrder);
        scrollPane_allOrder.setViewportView(table_allOrder);
        Box box_allOrder = Box.createHorizontalBox();
        panel_allOrder.add(box_allOrder, BorderLayout.NORTH);

        // 订单号的label
        JLabel label_allOrder_orderID = new JLabel("订单号：");
        box_allOrder.add(label_allOrder_orderID);

        // 销售->订单列表->功能按钮部分->订单号输入的textfield
        textField_allOrder_inputOrderID = new JTextField();
        box_allOrder.add(textField_allOrder_inputOrderID);
        textField_allOrder_inputOrderID.setColumns(10);

        // 销售->订单列表->功能按钮部分->查询按钮
        JButton button_allOrder_findOrder = new JButton("查 询");
        button_allOrder_findOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel temp;
                if (textField_allOrder_inputOrderID.getText().equals("")){
                    temp = new DefaultTableModel(returnVector.FromDBReadAll(db,"ordermanager",name_sell_noStateChange), name_sell_noStateChange);
                }
                else {
                    temp = new DefaultTableModel(returnVector.FromDBRead(db, "ordermanager", name_sell_noStateChange, textField_allOrder_inputOrderID.getText(), "ID"), name_sell_noStateChange);
                }
                table_allOrder.setModel(temp);
            }
        });
        box_allOrder.add(button_allOrder_findOrder);
        // TODO 修改按钮
        // 销售->订单列表->功能按钮部分->删除按钮
        JButton button_allOrder_delete = new JButton("删 除");
        box_allOrder.add(button_allOrder_delete);
        button_allOrder_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.executeDelete(textField_allOrder_inputOrderID.getText(), "ordermanager", "ID");
                // TODO 删除了之后。相关的table没有更新。删除成功的弹窗
            }
        });

        Component horizontalStrut_2 = Box.createHorizontalStrut(400);
        box_allOrder.add(horizontalStrut_2);

        Box horizontalBox_3 = Box.createHorizontalBox();
        panel_allOrder.add(horizontalBox_3, BorderLayout.SOUTH);


        // 销售->订单列表->总利润部分->总利润label
        JLabel label_allOrder_totalProfit = new JLabel("总利润：");
        horizontalBox_3.add(label_allOrder_totalProfit);

        // 销售->订单列表->总利润部分->总利润显示的textfield
        textField_allOrder_totalProfit = new JTextField();
        textField_allOrder_totalProfit.setHorizontalAlignment(SwingConstants.RIGHT);
        textField_allOrder_totalProfit.setEditable(false);
        textField_allOrder_totalProfit.setEnabled(false);
        horizontalBox_3.add(textField_allOrder_totalProfit);
        textField_allOrder_totalProfit.setColumns(1);

        // 销售->订单列表->总利润部分->总利润显示的单位
        JLabel label_allOrder_totalProfit_showYuan = new JLabel("元");
        horizontalBox_3.add(label_allOrder_totalProfit_showYuan);

        Component horizontalStrut = Box.createHorizontalStrut(600);
        horizontalBox_3.add(horizontalStrut);



        // **************************库存**************************
        JPanel panel_stock = new JPanel();
        tabbedPane.addTab("库存(stock)", null, panel_stock, null);
        panel_stock.setLayout(new BorderLayout(0, 0));
        JTabbedPane tabbedPane_stock = new JTabbedPane(JTabbedPane.TOP);
        panel_stock.add(tabbedPane_stock, BorderLayout.CENTER);

        // ------------------进货------------------------------------
        JPanel panel_inStock = new JPanel();
        tabbedPane_stock.addTab("进货", null, panel_inStock, null);
        panel_inStock.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_inStock_addProduct = new JScrollPane();
        panel_inStock.add(scrollPane_inStock_addProduct, BorderLayout.CENTER);

        // 进货列表的tablemodel
        Vector<Object> name_inStock_addProduct = new Vector<Object>();
        name_inStock_addProduct.add("货品");
        name_inStock_addProduct.add("单价");
        name_inStock_addProduct.add("数量");
        name_inStock_addProduct.add("总价");
        Vector<Vector<Object>> data_inStock_addProduct = new Vector<Vector<Object>>();
        DefaultTableModel tablemodel_inStock_addProduct = new DefaultTableModel(data_inStock_addProduct, name_inStock_addProduct);

        // 进货列表的table
        table_inStock_addProduct = new JTable();
        table_inStock_addProduct.setModel(tablemodel_inStock_addProduct);
        scrollPane_inStock_addProduct.setViewportView(table_inStock_addProduct);

        //进货保存，合计面板
        JPanel panel_inStock_saveAndTotal = new JPanel();
        panel_inStock.add(panel_inStock_saveAndTotal, BorderLayout.SOUTH);
        panel_inStock_saveAndTotal.setLayout(new BorderLayout(0, 0));
        
        // 进货保存按钮
        JButton button_inStock_save = new JButton("保存");
        panel_inStock_saveAndTotal.add(button_inStock_save, BorderLayout.EAST);

        // 库存保存按钮的监听器
        button_inStock_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Vector<Object> temp : data_inStock_addProduct){
                    db.executeQuery("itemmanager(Name,OutPrice,Num,InPrice)",
                            "'"+temp.get(0)+"','"+temp.get(1)+"','" + temp.get(2) +"','"+temp.get(3)+"'");
                }
                Vector<Vector<Object>> data_10 = returnVector.FromDBReadAll(db,"itemmanager",name_checkStock);
                DefaultTableModel tablemodel_10 = new DefaultTableModel(data_10, name_checkStock);
                table_checkStock.setModel(tablemodel_10);
                table_inStock_addProduct.setModel(new DefaultTableModel(new Vector<Vector<Object>>(), name_inStock_addProduct));
                textField_inStock_totalProfit.setText("");
            }
        });

        Box horizontalBox_totalIncomingPrice = Box.createHorizontalBox();
        panel_inStock_saveAndTotal.add(horizontalBox_totalIncomingPrice, BorderLayout.WEST);

        // 合计按钮
        JLabel label_inStock_totalProfit = new JLabel("合计：");
        horizontalBox_totalIncomingPrice.add(label_inStock_totalProfit);
        // 合计显示价格的TextField
        textField_inStock_totalProfit = new JTextField();
        textField_inStock_totalProfit.setEnabled(false);
        textField_inStock_totalProfit.setEditable(false);
        horizontalBox_totalIncomingPrice.add(textField_inStock_totalProfit);
        textField_inStock_totalProfit.setColumns(10);
        // 合计显示价格的单位
        JLabel label_inStock_showYUAN = new JLabel("元");
        horizontalBox_totalIncomingPrice.add(label_inStock_showYUAN);

        // 新增货物按钮
        JButton button_inStock_addNewGoods = new JButton("+ 新增货物");
        panel_inStock_saveAndTotal.add(button_inStock_addNewGoods, BorderLayout.NORTH);
        button_inStock_addNewGoods.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                windowsToCreateItem window_3 = new windowsToCreateItem(table_inStock_addProduct, data_inStock_addProduct, name_inStock_addProduct, textField_inStock_totalProfit);
                window_3.setVisible(true);
                window_3.setSize(400, 400);

            }
        });


        // -----------------------清点-----------------------------------------
        JPanel panel_checkStock = new JPanel();
        tabbedPane_stock.addTab("清点库存(checkStock)", null, panel_checkStock, null);
        panel_checkStock.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_checkStock = new JScrollPane();
        panel_checkStock.add(scrollPane_checkStock, BorderLayout.CENTER);

        name_checkStock = new Vector<>();
        name_checkStock.add("id");
        name_checkStock.add("name");
        name_checkStock.add("num");
        name_checkStock.add("inprice");
        Vector<Vector<Object>> data_checkStock = returnVector.FromDBReadAll(db,"itemmanager",name_checkStock);
        DefaultTableModel tablemodel_checkStock = new DefaultTableModel(data_checkStock, name_checkStock);
        table_checkStock = new JTable();
        table_checkStock.setModel(tablemodel_checkStock);
        scrollPane_checkStock.setViewportView(table_checkStock);
    }
}
