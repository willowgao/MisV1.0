/*
 * Login.java
 *
 * Created on __DATE__, __TIME__
 */

package ui.users;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bean.UserInfo;

import ui.main.Sys;
import constants.MisConstants;
import db.DbLogicServie;

/**
 * 
 * @author __USER__
 */
public class Login extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3552632321759425944L;
	private static Log log = LogFactory.getLog(Login.class);

	/** Creates new form Login */
	public Login() {
		log.info("物管MIS系统启动!");
		initComponents();
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		l_userName = new javax.swing.JLabel();
		l_userPwd = new javax.swing.JLabel();
		t_userName = new javax.swing.JTextField();
		t_userPwd = new javax.swing.JPasswordField();
		login = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u7cfb\u7edf\u767b\u5f55");

		jLabel1.setFont(new java.awt.Font("微软雅黑", 3, 30));
		jLabel1.setText("\u8d27\u7269Mis\u7ba1\u7406\u7cfb\u7edf");

		l_userName.setText("\u7528\u6237\u540d\uff1a");

		l_userPwd.setText("\u5bc6  \u7801\uff1a");

		t_userName.setColumns(18);

		t_userPwd.setColumns(18);

		login.setText("\u767b\u5f55");
		login.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				loginMouseClicked(evt);
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
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.LEADING)
																																.addGroup(
																																					layout
																																										.createSequentialGroup()
																																										.addContainerGap()
																																										.addComponent(
																																															jLabel1,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															307,
																																															javax.swing.GroupLayout.PREFERRED_SIZE))
																																.addGroup(
																																					layout
																																										.createSequentialGroup()
																																										.addGap(
																																															72,
																																															72,
																																															72)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING)
																																																				.addComponent(
																																																									l_userName)
																																																				.addComponent(
																																																									l_userPwd))
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING)
																																																				.addComponent(
																																																									t_userPwd,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE)
																																																				.addComponent(
																																																									t_userName,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									143,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE))
																																										.addGap(
																																															18,
																																															18,
																																															18)
																																										.addComponent(
																																															login)))
																						.addContainerGap(
																											26,
																											Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { t_userName, t_userPwd });

		layout
							.setVerticalGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addContainerGap()
																						.addComponent(jLabel1)
																						.addGap(18, 18, 18)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																					login,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					53,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addGroup(
																																					layout
																																										.createSequentialGroup()
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.BASELINE)
																																																				.addComponent(
																																																									l_userName)
																																																				.addComponent(
																																																									t_userName,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE))
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.BASELINE)
																																																				.addComponent(
																																																									l_userPwd)
																																																				.addComponent(
																																																									t_userPwd,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE))))
																						.addContainerGap(
																											58,
																											Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	@SuppressWarnings( { "deprecation", "unchecked" })
	private void loginMouseClicked(java.awt.event.MouseEvent evt) {
		String userName = this.t_userName.getText();
		String userPwd = this.t_userPwd.getText();
		String[] args = { userName, userPwd };
		List resluts = DbLogicServie.getUserInfo(args);
		if (null != resluts && resluts.size() > 0) {
			UserInfo userInfo = (UserInfo) resluts.get(0);
			JOptionPane.showMessageDialog(null, "【" + userInfo.getUserName() + "】,欢迎您登录系统!", "提示信息",
								JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			Sys sys = new Sys(userInfo.getUserType(), userInfo.getUserName());
			sys.setVisible(true);
			sys.setResizable(false);
			sys.setAlwaysOnTop(true);
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			int sysw = MisConstants.winW;
			int sysh = MisConstants.winH;
			sys.setBounds((d.width - sysw) / 2, (d.height - sysh) / 2, sysw, sysh);
			sys.setTitle("Mis管理系统，当前登录人【" + userInfo.getUserName() + "】");

		} else {
			JOptionPane.showMessageDialog(null, "登录失败！请检查用户名、密码是否输入正确!", "提示信息", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Login login = new Login();
				login.setLocation(500, 300);
				login.setVisible(true);
				login.setResizable(false);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel l_userName;
	private javax.swing.JLabel l_userPwd;
	private javax.swing.JButton login;
	private javax.swing.JTextField t_userName;
	private javax.swing.JPasswordField t_userPwd;
	// End of variables declaration//GEN-END:variables

}