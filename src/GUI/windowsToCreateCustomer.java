package GUI;

import Bean.DBBean;
import op.returnVector;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class windowsToCreateCustomer extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    public windowsToCreateCustomer a = this;

    public windowsToCreateCustomer(DBBean db, JTable table) {
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(40, 40, 40, 40));
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(3, 2, 0, 20));

        JLabel customerName = new JLabel("客户姓名");
        customerName.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(customerName);

        // 客户姓名输入框
        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);

        JLabel customerPhoneNumber = new JLabel("联系方式");
        customerPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(customerPhoneNumber);

        // 客户联系方式输入框
        textField_1 = new JTextField();
        panel.add(textField_1);
        textField_1.setColumns(10);

        JButton btnSvae = new JButton("保  存");
        panel.add(btnSvae);
        btnSvae.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("") || textField_1.getText().equals("")) {
                    JTextArea aboutarea = new JTextArea();
                    aboutarea.setText("输入不能为空！\n");
                    JOptionPane.showConfirmDialog(null, aboutarea, "Error!", JOptionPane.PLAIN_MESSAGE);
                } else {
                    int x = db.executeQuery("customermanager(Name,PhoneNum)", "\'" + textField.getText() + "\',\'" + textField_1.getText() + "\'");
                    if (x == 0) {
                        JTextArea aboutarea = new JTextArea();
                        aboutarea.setText("已经增加过此用户！\n");
                        JOptionPane.showConfirmDialog(null, aboutarea, "Error!", JOptionPane.PLAIN_MESSAGE);
                    }
                    Vector<Object> name_1 = null;
                    name_1 = returnVector.getHeadName(db, "customermanager");
                    Vector<Vector<Object>> data_1 = returnVector.FromDBReadAll(db, "customermanager", name_1);
                    DefaultTableModel temp = new DefaultTableModel_noEditable(data_1, name_1, 5);
                    table.setModel(temp);
                    a.setVisible(false);
                }
            }
        });

        JButton btnCancel = new JButton("取  消");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.setVisible(false);
            }
        });
        panel.add(btnCancel);
    }

}
