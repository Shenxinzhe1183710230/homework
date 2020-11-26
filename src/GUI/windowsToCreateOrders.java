package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class windowsToCreateOrders extends JFrame{
	private JTextField goodsNameDisplay;
	private JTextField goodsPriceDisplay;
	private JTextField totalMoneyDisplay;
	private JTextField goodsNumberInput;
	public windowsToCreateOrders() {
		setTitle("新增商品");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_addGoods = new JPanel();
		panel_addGoods.setBorder(new EmptyBorder(40, 40, 40, 40));
		getContentPane().add(panel_addGoods, BorderLayout.CENTER);
		panel_addGoods.setLayout(new GridLayout(6, 2, 0, 20));
		
		JLabel goodsIDLabel = new JLabel("  商品序号  ");
		goodsIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_addGoods.add(goodsIDLabel);
		
		JComboBox goodsIDInput = new JComboBox();
		panel_addGoods.add(goodsIDInput);
		
		JLabel goodsNameLabel = new JLabel("  商品名称  ");
		goodsNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_addGoods.add(goodsNameLabel);
		
		goodsNameDisplay = new JTextField();
		goodsNameDisplay.setEnabled(false);
		goodsNameDisplay.setEditable(false);
		goodsNameDisplay.setColumns(10);
		panel_addGoods.add(goodsNameDisplay);
		
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
