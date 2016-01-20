/*
 * UserManager.java
 *
 * Created on __DATE__, __TIME__
 */

package ui.merchandises;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import util.DateUtil;

import db.DbLogicServie;

import bean.ItemData;
import bean.UserInfo;

/**
 * 
 * @author __USER__
 */
public class UserManager extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 152645516047644276L;
	/** Creates new form UserManager */
	public UserManager() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		tf_userName = new javax.swing.JTextField();
		tf_userCode = new javax.swing.JTextField();
		pf_userPwd = new javax.swing.JPasswordField();
		tf_userFPwd = new javax.swing.JPasswordField();
		bt_Register = new javax.swing.JButton();
		jcb_userType = new javax.swing.JComboBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("\u7528\u6237\u59d3\u540d\uff1a");

		jLabel2.setText("\u7528\u6237\u767b\u5f55\u540d\uff1a");

		jLabel3.setText("\u7528\u6237\u5bc6\u7801\uff1a");

		jLabel4.setText("\u7528\u6237\u7c7b\u578b\uff1a");

		jLabel5.setText("\u7528\u6237\u786e\u8ba4\u5bc6\u7801\uff1a");

		bt_Register.setText("\u786e\u8ba4");
		bt_Register.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt_RegisterActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout
							.setHorizontalGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addGap(25, 25, 25)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.TRAILING)
																																.addComponent(
																																					jLabel5)
																																.addGroup(
																																					layout
																																										.createParallelGroup(
																																															javax.swing.GroupLayout.Alignment.LEADING,
																																															false)
																																										.addComponent(
																																															jLabel3,
																																															javax.swing.GroupLayout.Alignment.TRAILING,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															Short.MAX_VALUE)
																																										.addComponent(
																																															jLabel4,
																																															javax.swing.GroupLayout.Alignment.TRAILING,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															Short.MAX_VALUE)
																																										.addComponent(
																																															jLabel1,
																																															javax.swing.GroupLayout.Alignment.TRAILING,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															Short.MAX_VALUE)
																																										.addComponent(
																																															jLabel2,
																																															javax.swing.GroupLayout.Alignment.TRAILING,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															Short.MAX_VALUE)))
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																					bt_Register)
																																.addComponent(
																																					tf_userCode,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					pf_userPwd,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					tf_userFPwd,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					jcb_userType,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					111,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					tf_userName,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					152,
																																					javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addContainerGap(
																											134,
																											Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { pf_userPwd, tf_userCode,
							tf_userFPwd, tf_userName });

		layout
							.setVerticalGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addGap(33, 33, 33)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					jcb_userType,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					jLabel4))
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					tf_userName,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					jLabel1))
																						.addGap(14, 14, 14)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					jLabel2)
																																.addComponent(
																																					tf_userCode,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGap(12, 12, 12)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					pf_userPwd,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					jLabel3))
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					jLabel5)
																																.addComponent(
																																					tf_userFPwd,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGap(18, 18, 18)
																						.addComponent(bt_Register)
																						.addContainerGap(
																											39,
																											Short.MAX_VALUE)));

		pack();

		ItemData dataIn = new ItemData("1", "��ͨ�û�");
		ItemData dataOut = new ItemData("2", "����Ա�û�");
		jcb_userType.addItem(dataIn);
		jcb_userType.addItem(dataOut);

	}// </editor-fold>

	// GEN-END:initComponents
	@SuppressWarnings("deprecation")
	private void bt_RegisterActionPerformed(java.awt.event.ActionEvent evt) {

		if (!this.tf_userFPwd.getText().equals(this.pf_userPwd.getText())) {
			JOptionPane.showMessageDialog(null, "�����������벻һ�£���ȷ��!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String[] arg = { this.tf_userCode.getText() };
		if (DbLogicServie.checkUserInfo(arg).size() > 0) {
			JOptionPane.showMessageDialog(null, "�Ѿ����ڴ˵�¼�û�������ȷ��!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
			return;
		}

		UserInfo info = new UserInfo();

		info.setUserCode(this.tf_userCode.getText());
		info.setUserName(this.tf_userName.getText());
		info.setUserPwd(this.pf_userPwd.getText());
		info.setUserType(((ItemData) this.jcb_userType.getSelectedItem()).code);
		info.setRegisterDate(Timestamp.valueOf(DateUtil.getNowDateByFormat(DateUtil.YMDHMS)));

		if (DbLogicServie.insertUserInfo(info)) {
			JOptionPane.showMessageDialog(null, "ע��ɹ�", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UserManager().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton bt_Register;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JComboBox jcb_userType;
	private javax.swing.JPasswordField pf_userPwd;
	private javax.swing.JTextField tf_userCode;
	private javax.swing.JPasswordField tf_userFPwd;
	private javax.swing.JTextField tf_userName;
	// End of variables declaration//GEN-END:variables

}