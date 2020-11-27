package GUI;

import Bean.DBBean;
import op.op;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * 库存中，新增货物按钮按下后的弹窗
 */
public class windowsToCreateItem extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private windowsToCreateItem a = this;
	public windowsToCreateItem(JTable table, Vector<Vector<Object>> data, Vector<Object> name, JTextField out) {
		setTitle("新增入库商品");
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(40, 40, 40, 40));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 20, 10));

		// 商品名称的label
		JLabel lblNewLabel = new JLabel("商品名称");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		// 商品名称的TestField
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		// 商品数量的label
		JLabel lblNewLabel_1 = new JLabel("商品数量");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		// 商品数量的Textfield
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);

		// 商品单价的label
		JLabel lblNewLabel_2 = new JLabel("商品单价");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		// 商品单价的textfield
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);

		// 商品售价的label
		JLabel lblNewLabel_3 = new JLabel("商品售价");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);
		// 商品售价的Textfield
		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("确 认");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("")){
					JTextArea aboutarea = new JTextArea();
					aboutarea.setText("输入不能为空！\n");
					JOptionPane.showConfirmDialog(null,aboutarea,"Error!",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					//db.executeQuery("itemmanager(Name,OutPrice,Num,InPrice)",
					//		"'"+textField.getText()+"','"+textField_2.getText()+"','" + textField_3.getText() +"','"+textField_1.getText()+"'");
					Vector<Object> temp = new Vector<>();
					temp.add(textField.getText()); temp.add(textField_2.getText());
					temp.add(textField_1.getText());
					temp.add(String.valueOf(Float.parseFloat(textField_2.getText()) * Float.parseFloat(textField_1.getText())));
					data.add(temp);
					table.setModel(new DefaultTableModel(data, name));
					a.setVisible(false);
					out.setText(String.valueOf(op.caculateAllPrice(data)));
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取 消");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				a.setVisible(false);
			}
		});
		panel.add(btnNewButton_1);
	}

}
