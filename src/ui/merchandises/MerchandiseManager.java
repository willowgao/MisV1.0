/*
 * MerchandiseManager.java
 *
 * Created on __DATE__, __TIME__
 */

package ui.merchandises;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;

import ui.listener.MainWinListener;
import ui.model.MerchandiseInfoTableModel;
import bean.Merchandise;
import constants.MisConstants;
import db.DbLogicServie;

/**
 * 
 * @author __USER__
 */
public class MerchandiseManager extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6017387493301362842L;

	/** Creates new form MerchandiseManager */
	public MerchandiseManager() {
		initComponents();
		initTbMerInfo();
		this.tb_MerInfo.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					List recordsList = DbLogicServie.getMerchandiseInfo(null);
					MerchandiseInfoTableModel tm = new MerchandiseInfoTableModel(recordsList);
					Merchandise mer = tm.getMer(tb_MerInfo.getSelectedRow());
					EditMerchandise eMer = new EditMerchandise(mer.getMerchandiseId());
					setWindowSize(eMer, "商品信息更新");
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public void initTbMerInfo() {
		List list = DbLogicServie.getMerchandiseInfo(null);
		if (list != null && list.size() > 0) {
			MerchandiseInfoTableModel m_table = new MerchandiseInfoTableModel(list);
			m_table.setData(list);
			tb_MerInfo.setModel(m_table);
		}

	}

	public void setWindowSize(JFrame jframe, String title) {
		this.setAlwaysOnTop(false);
		this.setEnabled(false);
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setLocation(300, 150);
		jframe.addWindowListener(new MainWinListener(this));
		jframe.setTitle(title);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int sysw = MisConstants.winW;
		int sysh = MisConstants.winH;
		jframe.setBounds((d.width - sysw) / 2, (d.height - sysh) / 2, sysw, sysh);
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
		tf_MerType = new javax.swing.JTextField();
		tf_MerName = new javax.swing.JTextField();
		tf_MerTan = new javax.swing.JTextField();
		tf_MerBrand = new javax.swing.JTextField();
		bt_insert = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		tb_MerInfo = new javax.swing.JTable();
		jLabel5 = new javax.swing.JLabel();
		tf_MerPrice = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("\u5546\u54c1\u7c7b\u578b\uff1a");

		jLabel2.setText("\u5546\u54c1\u540d\u79f0\uff1a");

		jLabel3.setText("\u5546\u54c1\u89c4\u683c\uff1a");

		jLabel4.setText("\u5546\u54c1\u54c1\u724c\uff1a");

		bt_insert.setText("\u5f55\u5165");
		bt_insert.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt_insertActionPerformed(evt);
			}
		});

		tb_MerInfo.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { { null, null, null, null },
							{ null, null, null, null }, { null, null, null, null }, { null, null, null, null } },
							new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		tb_MerInfo.setName("");
		jScrollPane1.setViewportView(tb_MerInfo);

		jLabel5.setText("\u5546\u54c1\u5355\u4ef7\uff1a");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout
							.setHorizontalGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addGap(28, 28, 28)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.TRAILING)
																																.addComponent(
																																					jScrollPane1,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					682,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
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
																																																														.addComponent(
																																																																			jLabel1)
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																														.addComponent(
																																																																			tf_MerType,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																			158,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE))
																																																				.addGroup(
																																																									layout
																																																														.createSequentialGroup()
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																														.addComponent(
																																																																			jLabel4)
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																														.addComponent(
																																																																			tf_MerBrand,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																			javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE)))
																																										.addGap(
																																															27,
																																															27,
																																															27)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING)
																																																				.addGroup(
																																																									layout
																																																														.createSequentialGroup()
																																																														.addComponent(
																																																																			jLabel2)
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																														.addComponent(
																																																																			tf_MerName,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																			131,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE))
																																																				.addGroup(
																																																									layout
																																																														.createSequentialGroup()
																																																														.addComponent(
																																																																			jLabel5)
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																														.addComponent(
																																																																			tf_MerPrice,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																			158,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE)))
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING)
																																																				.addGroup(
																																																									layout
																																																														.createSequentialGroup()
																																																														.addComponent(
																																																																			jLabel3)
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																														.addComponent(
																																																																			tf_MerTan,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																			javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE))
																																																				.addComponent(
																																																									bt_insert,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									85,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE))))
																						.addContainerGap(
																											65,
																											Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { tf_MerBrand, tf_MerName,
							tf_MerTan, tf_MerType });

		layout
							.setVerticalGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addGap(23, 23, 23)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					jLabel1)
																																.addComponent(
																																					jLabel2)
																																.addComponent(
																																					jLabel3)
																																.addComponent(
																																					tf_MerType,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					tf_MerName,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					tf_MerTan,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.TRAILING)
																																.addGroup(
																																					layout
																																										.createParallelGroup(
																																															javax.swing.GroupLayout.Alignment.BASELINE)
																																										.addComponent(
																																															tf_MerBrand,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addComponent(
																																															jLabel4)
																																										.addComponent(
																																															bt_insert))
																																.addGroup(
																																					layout
																																										.createParallelGroup(
																																															javax.swing.GroupLayout.Alignment.BASELINE)
																																										.addComponent(
																																															jLabel5)
																																										.addComponent(
																																															tf_MerPrice,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)))
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																											jScrollPane1,
																											javax.swing.GroupLayout.PREFERRED_SIZE,
																											353,
																											javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addContainerGap(
																											36,
																											Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	private void bt_insertActionPerformed(java.awt.event.ActionEvent evt) {
		Merchandise mer = new Merchandise();
		mer.setMerchandiseName(this.tf_MerName.getText());
		mer.setMerchandiseBrand(this.tf_MerBrand.getText());
		mer.setMerchandiseTandard(this.tf_MerTan.getText());
		mer.setMerchandiseType(this.tf_MerType.getText());
		mer.setMerchandisePrice(this.tf_MerPrice.getText());
		DbLogicServie.insertMerchandiseInfo(mer);
		initTbMerInfo();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MerchandiseManager().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton bt_insert;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tb_MerInfo;
	private javax.swing.JTextField tf_MerBrand;
	private javax.swing.JTextField tf_MerName;
	private javax.swing.JTextField tf_MerPrice;
	private javax.swing.JTextField tf_MerTan;
	private javax.swing.JTextField tf_MerType;
	// End of variables declaration//GEN-END:variables

}