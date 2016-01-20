/*
 * MerRetailQuery.java
 *
 * Created on __DATE__, __TIME__
 */

package ui.merchandises;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ui.model.MerchandiseRetailTabelModel;
import util.DateUtil;
import db.DbLogicServie;

/**
 * 
 * @author __USER__
 */
public class MerRetailQuery extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 653124814504824207L;

	/** Creates new form MerRetailQuery */
	public MerRetailQuery() {
		initComponents();

		this.tf_startTime.setText(DateUtil.getNowDateByFormat(DateUtil.YMD));
		this.tf_endTime.setText(DateUtil.getNowDateByFormat(DateUtil.YMD));
		// add listener on mouseDoubleClicked
		this.tb_total1.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String[] arg = { null, tb_total1.getValueAt(tb_total1.getSelectedRow(), 0).toString() };
					List recordsList = DbLogicServie.getMerRetailDetails(arg);
					MerchandiseRetailTabelModel m_table = new MerchandiseRetailTabelModel(recordsList);
					m_table.setData(recordsList);
					tb_detail.setModel(m_table);
				}
			}
		});
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane2 = new javax.swing.JScrollPane();
		tb_total = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		tf_startTime = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		tf_endTime = new javax.swing.JTextField();
		bt_query = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		tb_detail = new javax.swing.JTable();
		jScrollPane1 = new javax.swing.JScrollPane();
		tb_total1 = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		tb_total.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { { null, null, null, null },
							{ null, null, null, null }, { null, null, null, null }, { null, null, null, null } },
							new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane2.setViewportView(tb_total);

		jLabel1.setText("\u5f00\u59cb\u65f6\u95f4:");

		bt_query.setText("\u67e5\u8be2");
		bt_query.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt_queryActionPerformed(evt);
			}
		});

		tb_detail.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { { null, null, null, null },
							{ null, null, null, null }, { null, null, null, null }, { null, null, null, null } },
							new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane3.setViewportView(tb_detail);

		tb_total1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { { null, null, null, null },
							{ null, null, null, null }, { null, null, null, null }, { null, null, null, null } },
							new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(tb_total1);

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
																																										.addGap(
																																															49,
																																															49,
																																															49)
																																										.addComponent(
																																															jLabel1)
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																										.addComponent(
																																															tf_startTime,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addGap(
																																															18,
																																															18,
																																															18)
																																										.addComponent(
																																															jLabel2)
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addComponent(
																																															tf_endTime,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															131,
																																															javax.swing.GroupLayout.PREFERRED_SIZE))
																																.addGroup(
																																					layout
																																										.createSequentialGroup()
																																										.addContainerGap()
																																										.addComponent(
																																															jScrollPane2,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															436,
																																															Short.MAX_VALUE)))
																						.addGap(60, 60, 60)
																						.addComponent(bt_query).addGap(
																											634, 634,
																											634))
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addContainerGap()
																						.addComponent(
																											jScrollPane1,
																											javax.swing.GroupLayout.PREFERRED_SIZE,
																											504,
																											javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGap(26, 26, 26)
																						.addComponent(
																											jScrollPane3,
																											javax.swing.GroupLayout.PREFERRED_SIZE,
																											601,
																											javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addContainerGap(
																											56,
																											Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { tf_endTime, tf_startTime });

		layout
							.setVerticalGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addGap(26, 26, 26)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					jLabel1)
																																.addComponent(
																																					tf_startTime,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					jLabel2)
																																.addComponent(
																																					tf_endTime,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					bt_query))
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																											jScrollPane2,
																											javax.swing.GroupLayout.PREFERRED_SIZE,
																											0,
																											javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																					jScrollPane1,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					366,
																																					Short.MAX_VALUE)
																																.addComponent(
																																					jScrollPane3,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					366,
																																					Short.MAX_VALUE))
																						.addGap(86, 86, 86)));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	@SuppressWarnings("unchecked")
	public List queryList = new ArrayList();
	public String fileName = "";

	@SuppressWarnings("unchecked")
	private void bt_queryActionPerformed(java.awt.event.ActionEvent evt) {
		String[] arg = { DateUtil.string2Format(this.tf_startTime.getText(), DateUtil.YMD, DateUtil.YMD),
							DateUtil.string2Format(this.tf_endTime.getText(), DateUtil.YMD, DateUtil.YMD) };
		List list = DbLogicServie.getMerRetails(arg);
		this.queryList = list;
		this.fileName = this.tf_startTime.getText() + "-" + this.tf_endTime.getText() + "��Ʒ�����Ϣ.xls";
		MerchandiseRetailTabelModel m_table = new MerchandiseRetailTabelModel(queryList);
		m_table.colnumNames = new String[] { "����ID ", "�����ܽ��", "����ʱ��", "��¼��", "��ע" };
		m_table.fieldNames = new String[] { "merretailId", "merRetailTotalPrice", "merRetailCreateDt",
							"merRetailCreator", "merRetailDesc" };
		m_table.setData(list);
		this.tb_total1.setModel(m_table);
		TableColumnModel columnModel = this.tb_total1.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		column.setMinWidth(0);
		column.setMaxWidth(0);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MerRetailQuery().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton bt_query;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTable tb_detail;
	private javax.swing.JTable tb_total;
	private javax.swing.JTable tb_total1;
	private javax.swing.JTextField tf_endTime;
	private javax.swing.JTextField tf_startTime;
	// End of variables declaration//GEN-END:variables

}