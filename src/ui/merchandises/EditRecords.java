package ui.merchandises;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;

import constants.MisConstants;

import bean.ItemData;
import bean.Merchandise;
import bean.MerchandiseRecodes;
import db.DbLogicServie;

/**
 * 
 * @author __USER__
 */
public class EditRecords extends javax.swing.JFrame {

	private static final long serialVersionUID = 1103973702843250915L;
	String merchandisId;

	/** Creates new form EdisMerchandiseRecords */
	public EditRecords(String merchandisId) {
		this.merchandisId = merchandisId;
		initComponents();
		initFrameValue(merchandisId);
	}

	@SuppressWarnings("unchecked")
	void initFrameValue(String merchandisId) {
		String[] arg = { merchandisId };
		List list = DbLogicServie.getMerchandiseRecodes(arg);
		if (list.size() > 0) {
			MerchandiseRecodes mer = (MerchandiseRecodes) list.get(0);
			List merchandiseInfo = DbLogicServie.getMerchandiseInfo(null);
			for (int i = 0; i < merchandiseInfo.size(); ++i) {
				String[] items = new String[merchandiseInfo.size()];
				Merchandise merInfo = (Merchandise) merchandiseInfo.get(i);
				items[i] = merInfo.getMerchandiseName();
				ItemData data = new ItemData(merInfo.getMerchandiseId(), merInfo.getMerchandiseName());
				this.jcb_MerType.addItem(data);
				if (merInfo.getMerchandiseId().equals(mer.getM_type())) {
					this.jcb_MerType.setSelectedIndex(i);
				}
			}

			this.jcb_MerDealType.addItem(new ItemData(MisConstants.MERCHANDDISEIN, "入库"));
			this.jcb_MerDealType.addItem(new ItemData(MisConstants.MERCHANDDISEOUT, "出库"));
			this.jcb_MerDealType.setSelectedIndex(mer.getM_dealType().equals("入库") ? 0 : 1);

			tf_MerCount.setText(mer.getM_count().toString());
			tf_MerPrice.setText(mer.getM_price().toString());
		}
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jcb_MerType = new javax.swing.JComboBox();
		jcb_MerDealType = new javax.swing.JComboBox();
		tf_MerCount = new javax.swing.JTextField();
		tf_MerPrice = new javax.swing.JTextField();
		bt_save = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("\u8d27\u7269\u7c7b\u578b\uff1a");

		jLabel2.setText("\u8d27\u7269\u4ef7\u683c\uff1a");

		jLabel3.setText("\u8d27\u7269\u6570\u91cf\uff1a");

		jLabel4.setText("\u51fa\u5165\u5e93\u7c7b\u578b\uff1a");

		bt_save.setText("\u4fdd\u5b58");
		bt_save.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt_saveActionPerformed(evt);
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
																						.addGap(30, 30, 30)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																					bt_save)
																																.addGroup(
																																					layout
																																										.createSequentialGroup()
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.TRAILING)
																																																				.addComponent(
																																																									jLabel2)
																																																				.addComponent(
																																																									jLabel1))
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING)
																																																				.addComponent(
																																																									jcb_MerType,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE)
																																																				.addComponent(
																																																									tf_MerPrice,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE))
																																										.addGap(
																																															82,
																																															82,
																																															82)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING)
																																																				.addGroup(
																																																									layout
																																																														.createSequentialGroup()
																																																														.addComponent(
																																																																			jLabel3)
																																																														.addGap(
																																																																			18,
																																																																			18,
																																																																			18)
																																																														.addComponent(
																																																																			tf_MerCount,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																			131,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE))
																																																				.addGroup(
																																																									layout
																																																														.createSequentialGroup()
																																																														.addComponent(
																																																																			jLabel4)
																																																														.addGap(
																																																																			18,
																																																																			18,
																																																																			18)
																																																														.addComponent(
																																																																			jcb_MerDealType,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																			javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE)))))
																						.addContainerGap(
																											javax.swing.GroupLayout.DEFAULT_SIZE,
																											Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { jcb_MerDealType, jcb_MerType,
							tf_MerCount, tf_MerPrice });

		layout
							.setVerticalGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addGap(27, 27, 27)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					jLabel1)
																																.addComponent(
																																					jLabel3)
																																.addComponent(
																																					jcb_MerType,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					tf_MerCount,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGap(49, 49, 49)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					jLabel2)
																																.addComponent(
																																					jLabel4)
																																.addComponent(
																																					jcb_MerDealType,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					tf_MerPrice,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(bt_save)
																						.addContainerGap(
																											76,
																											Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {
		MerchandiseRecodes merr = new MerchandiseRecodes();
		merr.setM_id(merchandisId);
		merr.setM_count(Integer.valueOf(this.tf_MerCount.getText()));
		merr.setM_price(new BigDecimal(this.tf_MerPrice.getText()));
		merr.setM_dealType(((ItemData) this.jcb_MerDealType.getSelectedItem()).code);
		merr.setM_type(((ItemData) this.jcb_MerType.getSelectedItem()).code);
		if (DbLogicServie.updateMerchandiseRecords(merr)) {
			JOptionPane.showMessageDialog(null, "更新成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new EditRecords("").setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton bt_save;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JComboBox jcb_MerDealType;
	private javax.swing.JComboBox jcb_MerType;
	private javax.swing.JTextField tf_MerCount;
	private javax.swing.JTextField tf_MerPrice;
	// End of variables declaration//GEN-END:variables

}