package GUI;

import Bean.DBBean;
import op.op;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class windowsToCreateOrders extends JFrame{
	private JTextField goodsPriceDisplay;
	private JTextField totalMoneyDisplay;
	private JTextField goodsNumberInput;
	private JTextField textField;
	private JTextField textField_1;
	private windowsToCreateOrders a = this;
	public windowsToCreateOrders(DBBean db, JTable table, Vector<Vector<Object>> data, Vector<Object> name, JTextField out) {
		setTitle("新增商品");
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_addGoods = new JPanel();
		panel_addGoods.setBorder(new EmptyBorder(60, 40, 20, 40));
		getContentPane().add(panel_addGoods, BorderLayout.CENTER);
		panel_addGoods.setLayout(new GridLayout(6, 2, 0, 20));

		JLabel goodsNameLabel = new JLabel("  商品名称  ");
		goodsNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_addGoods.add(goodsNameLabel);

		Box horizontalBox = Box.createHorizontalBox();
		panel_addGoods.add(horizontalBox);

		// 商品名称的Textfield
		textField = new JTextField();
		textField.setColumns(10);
		horizontalBox.add(textField);

		JButton btnSearch = new JButton("搜索");
		// 搜索监听器：找到物品并将outprice输出给输出价格的文本框goodsPriceDisplay
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet res = db.executeFind(textField.getText(), "itemmanager", "name");
				try {
					if (res.next()){
						goodsPriceDisplay.setText(String.valueOf(res.getObject("outprice")));
					}
					else {
						JTextArea aboutarea = new JTextArea();
						aboutarea.setText("未查找到！\n");
						JOptionPane.showConfirmDialog(null,aboutarea,"Error!",JOptionPane.PLAIN_MESSAGE);
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});
		horizontalBox.add(btnSearch);

		JLabel goodsPriceLabel = new JLabel("   单  价  ");
		goodsPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_addGoods.add(goodsPriceLabel);

		// 显示单价
		goodsPriceDisplay = new JTextField();
		goodsPriceDisplay.setEnabled(false);
		goodsPriceDisplay.setEditable(false);
		goodsPriceDisplay.setColumns(10);
		panel_addGoods.add(goodsPriceDisplay);

		JLabel goodsNumberInputLabel = new JLabel("  商品数量  ");
		goodsNumberInputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_addGoods.add(goodsNumberInputLabel);

		Box horizontalBox_1 = Box.createHorizontalBox();
		panel_addGoods.add(horizontalBox_1);

		// 输入数量的Textfield
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		horizontalBox_1.add(textField_1);

		// 计算总价的按钮
		JButton btnConfirm = new JButton("确认");
		horizontalBox_1.add(btnConfirm);

		JLabel totalMoneyLabel = new JLabel("  合  计  ");
		totalMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_addGoods.add(totalMoneyLabel);

		// 显示总价的Textfield
		totalMoneyDisplay = new JTextField();
		totalMoneyDisplay.setEnabled(false);
		totalMoneyDisplay.setEditable(false);
		totalMoneyDisplay.setColumns(10);
		panel_addGoods.add(totalMoneyDisplay);

		// 绑定计算总价的监听
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((!textField_1.getText().equals("")) &&  (!goodsPriceDisplay.getText().equals(""))){
					totalMoneyDisplay.setText(String.valueOf(Float.parseFloat(textField_1.getText()) * Float.parseFloat(goodsPriceDisplay.getText())));
				}
			}
		});

		// 确定按钮及监听
		JButton btnSave = new JButton("确  定");
		// 将商品加入到订单中
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<Object> temp = new Vector<>();
				temp.add(textField.getText()); temp.add(goodsPriceDisplay.getText());
				temp.add(textField_1.getText()); temp.add(totalMoneyDisplay.getText());
				data.add(temp);
				table.setModel(new DefaultTableModel(data, name));
				a.setVisible(false);
				out.setText(String.valueOf(op.caculateAllPrice(data)));
			}
		});
		panel_addGoods.add(btnSave);

		// 取消按钮及监听
		JButton btnCancel = new JButton("取  消");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				a.setVisible(false);
			}
		});
		panel_addGoods.add(btnCancel);
	}

}
