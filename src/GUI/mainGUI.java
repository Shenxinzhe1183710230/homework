package GUI;

import Bean.DBBean;
import op.returnVector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JButton btnNewButton_1 = new JButton("\u65B0\u589E\u5BA2\u6237");
        panel_1.add(btnNewButton_1, BorderLayout.NORTH);

        JScrollPane scrollPane_1 = new JScrollPane();
        panel_1.add(scrollPane_1, BorderLayout.CENTER);

        Vector<Object> name_1 = returnVector.getHeadName(db,"customermanager");
        Vector<Vector<Object>> data_1 = returnVector.FromDBReadAll(db,"customermanager",name_1);
        DefaultTableModel tablemodel_1 = new DefaultTableModel(data_1, name_1);
        table_1 = new JTable();
        table_1.setModel(tablemodel_1);
        scrollPane_1.setViewportView(table_1);

        // 新增用户按钮
        btnNewButton_1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // 弹出对话框
                // 填对话框
                // 填完转化成一项，放到model中，放到数据库中
                Vector<Object> temp = new Vector<Object>();
                temp.add("id");
                temp.add("name");
                temp.add("phone");
                tablemodel_1.addRow(temp);
            }

        });

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

        JLabel lblNewLabel_2 = new JLabel("\u5BA2\u6237");
        horizontalBox.add(lblNewLabel_2);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        horizontalBox.add(textField_2);

        JButton btnNewButton_2_1 = new JButton("\u641C\u7D22");
        horizontalBox.add(btnNewButton_2_1);

        Component horizontalStrut_1 = Box.createHorizontalStrut(450);
        horizontalBox.add(horizontalStrut_1);

        JLabel lblNewLabel_1_1 = new JLabel("\u8BA2\u5355\u5E8F\u53F7");
        horizontalBox.add(lblNewLabel_1_1);

        textField = new JTextField();
        textField.setEnabled(false);
        textField.setEditable(false);
        horizontalBox.add(textField);
        textField.setColumns(10);

        JPanel panel_10 = new JPanel();
        panel_6.add(panel_10, BorderLayout.SOUTH);
        panel_10.setLayout(new BorderLayout(0, 0));

        JButton btnNewButton_2 = new JButton("\u65B0\u589E");
        btnNewButton_2.setFont(new Font("����", Font.PLAIN, 16));
        panel_10.add(btnNewButton_2, BorderLayout.NORTH);

        JButton btnNewButton_3 = new JButton("\u4FDD\u5B58");
        btnNewButton_3.setFont(new Font("����", Font.PLAIN, 16));
        panel_10.add(btnNewButton_3, BorderLayout.EAST);

        Box horizontalBox_1 = Box.createHorizontalBox();
        panel_10.add(horizontalBox_1, BorderLayout.WEST);

        JLabel lblNewLabel = new JLabel("    \u5171\u8BA1\uFF1A");
        lblNewLabel.setFont(new Font("����", Font.PLAIN, 16));
        horizontalBox_1.add(lblNewLabel);

        textField_1 = new JTextField();
        horizontalBox_1.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u5143");
        lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
        horizontalBox_1.add(lblNewLabel_1);

        JScrollPane scrollPane_2 = new JScrollPane();
        panel_6.add(scrollPane_2, BorderLayout.CENTER);

        table_2 = new JTable();
        scrollPane_2.setViewportView(table_2);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                windowsToCreateOrders window_1 = new windowsToCreateOrders();
                window_1.setVisible(true);
                window_1.setSize(400, 400);
            }
        });


        Vector<Object> order_name = returnVector.getHeadName(db,"ordermanager");
        //待审核
        JPanel panel_5 = new JPanel();
        tabbedPane_1.addTab("待审核", null, panel_5, null);
        panel_5.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_3 = new JScrollPane();
        panel_5.add(scrollPane_3, BorderLayout.CENTER);
        Vector<Vector<Object>> data_3 = returnVector.FromDBRead(db, "ordermanager", order_name, "1", "State");
        DefaultTableModel tablemodel_3 = new DefaultTableModel(data_3, order_name);
        table_3 = new JTable();
        table_3.setModel(tablemodel_3);
        scrollPane_3.setViewportView(table_3);

        //待收款
        JPanel panel_7 = new JPanel();
        tabbedPane_1.addTab("待收款", null, panel_7, null);
        panel_7.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_4 = new JScrollPane();
        panel_7.add(scrollPane_4, BorderLayout.CENTER);
        Vector<Vector<Object>> data_4 = returnVector.FromDBRead(db, "ordermanager", order_name, "2", "State");
        DefaultTableModel tablemodel_4 = new DefaultTableModel(data_4, order_name);
        table_4 = new JTable();
        table_4.setModel(tablemodel_4);
        scrollPane_4.setViewportView(table_4);


        //待退货
        JPanel panel_8 = new JPanel();
        tabbedPane_1.addTab("\u9000\u8D27", null, panel_8, null);
        panel_8.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_5 = new JScrollPane();
        panel_8.add(scrollPane_5, BorderLayout.CENTER);
        Vector<Vector<Object>> data_5 = returnVector.FromDBRead(db, "ordermanager", order_name, "3", "State");
        DefaultTableModel tablemodel_5 = new DefaultTableModel(data_5, order_name);
        table_5 = new JTable();
        table_5.setModel(tablemodel_5);
        scrollPane_5.setViewportView(table_5);

        //已完成
        JPanel panel_12 = new JPanel();
        tabbedPane_1.addTab("已完成", null, panel_12, null);
        panel_12.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_6 = new JScrollPane();
        panel_12.add(scrollPane_6, BorderLayout.CENTER);
        Vector<Vector<Object>> data_6 = returnVector.FromDBRead(db, "ordermanager", order_name, "4", "State");
        DefaultTableModel tablemodel_6 = new DefaultTableModel(data_6, order_name);
        table_6 = new JTable();
        table_6.setModel(tablemodel_6);
        scrollPane_6.setViewportView(table_6);

        //订单列表
        JPanel panel_4 = new JPanel();
        tabbedPane_1.addTab("订单列表", null, panel_4, null);
        panel_4.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_8 = new JScrollPane();
        panel_4.add(scrollPane_8, BorderLayout.CENTER);
        Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",order_name);
        DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, order_name);
        table_8 = new JTable();
        table_8.setModel(tablemodel_8);
        scrollPane_8.setViewportView(table_8);

        //查看销售单
        JPanel panel_9 = new JPanel();
        tabbedPane_1.addTab("\u67E5\u770B\u9500\u552E\u5355", null, panel_9, null);
        panel_9.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_7 = new JScrollPane();
        panel_9.add(scrollPane_7, BorderLayout.CENTER);
        table_6 = new JTable();
        scrollPane_7.setViewportView(table_6);


        // 库存
        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("\u5E93\u5B58", null, panel_3, null);
        panel_3.setLayout(new BorderLayout(0, 0));
        JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
        panel_3.add(tabbedPane_2, BorderLayout.CENTER);

        // 进货
        JPanel panel_11 = new JPanel();
        tabbedPane_2.addTab("进货", null, panel_11, null);
        panel_11.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_9 = new JScrollPane();
        panel_11.add(scrollPane_9, BorderLayout.CENTER);
        Vector<String> name_7 = new Vector<String>();
        name_7.add("货品");
        name_7.add("数目");
        name_7.add("价格");
        //Vector<Vector<Object>> data_7 = op.FromDBReadALL(db, "进货"); 从数据读入
        Vector<Vector<Object>> data_7 = new Vector<Vector<Object>>();
        Vector<Object> temp_7 = new Vector<Object>();
        temp_7.add("name");
        temp_7.add("number");
        temp_7.add("price");
        data_7.add(temp_7);
        DefaultTableModel tablemodel_7 = new DefaultTableModel(data_7, name_7);
        table_7 = new JTable();
        table_7.setModel(tablemodel_7);
        scrollPane_9.setViewportView(table_7);

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


    }
}
