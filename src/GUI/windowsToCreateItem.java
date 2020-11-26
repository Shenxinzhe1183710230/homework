package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class windowsToCreateItem extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public windowsToCreateItem() {
		setTitle("新增入库商品");
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(40, 40, 40, 40));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 2, 20, 20));
		
		JLabel lblNewLabel = new JLabel("商品名称");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("商品数量");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("商品单价");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("确 认");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取 消");
		panel.add(btnNewButton_1);
	}

}
