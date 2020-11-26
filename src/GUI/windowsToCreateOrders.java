package GUI;

import Bean.DBBean;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class windowsToCreateOrders extends JFrame{
	private JTextField goodsPriceDisplay;
	private JTextField totalMoneyDisplay;
	private JTextField goodsNumberInput;
	private JTextField textField;
	public windowsToCreateOrders(DBBean db) {
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

		goodsPriceDisplay = new JTextField();
		goodsPriceDisplay.setEnabled(false);
		goodsPriceDisplay.setEditable(false);
		goodsPriceDisplay.setColumns(10);
		panel_addGoods.add(goodsPriceDisplay);

		JLabel goodsNumberInputLabel = new JLabel("  商品数量  ");
		goodsNumberInputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_addGoods.add(goodsNumberInputLabel);

		goodsNumberInput = new JTextField();
		panel_addGoods.add(goodsNumberInput);
		goodsNumberInput.setColumns(10);

		JLabel totalMoneyLabel = new JLabel("  合  计  ");
		totalMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_addGoods.add(totalMoneyLabel);

		totalMoneyDisplay = new JTextField();
		totalMoneyDisplay.setEnabled(false);
		totalMoneyDisplay.setEditable(false);
		totalMoneyDisplay.setColumns(10);
		panel_addGoods.add(totalMoneyDisplay);

		JButton btnSave = new JButton("确  定");
		panel_addGoods.add(btnSave);

		JButton btnCancel = new JButton("取  消");
		panel_addGoods.add(btnCancel);
	}

}
