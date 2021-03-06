/*
 * MerchandisesQuery1.java
 *
 * Created on __DATE__, __TIME__
 */

package ui.merchandises;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ui.model.MerchandiseTableModel;
import util.DateUtil;
import util.FileUtil;
import bean.ItemData;
import bean.Merchandise;
import bean.MerchandiseRecodes;
import constants.MisConstants;
import db.DbLogicServie;

/**
 * 
 * @author __USER__
 */
public class MerchandisesQuery extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2936239605791455067L;

	/** Creates new form MerchandisesQuery1 */
	public MerchandisesQuery() {
		initComponents();
		coustomInit();

		this.jcb_MerDealType.insertItemAt(new ItemData("0", "----请选择----"), 0);
		this.jcb_MerDealType.setSelectedIndex(0);
		this.jcb_MerType.insertItemAt(new ItemData("0", "----请选择----"), 0);
		this.jcb_MerType.setSelectedIndex(0);
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
		tf_startTime = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		tf_endTime = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jcb_MerDealType = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		jcb_MerType = new javax.swing.JComboBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		t_MerRecords = new javax.swing.JTable();
		b_query = new javax.swing.JButton();
		bt_export = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("\u5f00\u59cb\u65f6\u95f4\uff1a");

		tf_startTime.setToolTipText("\u5f00\u59cb\u65f6\u95f4");

		jLabel2.setText("\u7ec8\u6b62\u65f6\u95f4\uff1a");

		tf_endTime.setToolTipText("\u7ec8\u6b62\u65f6\u95f4");

		jLabel3.setText("\u51fa\u5165\u5e93\u7c7b\u578b\uff1a");

		jLabel4.setText("\u5546\u54c1\u7c7b\u578b\uff1a");

		t_MerRecords.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { { null, null, null, null },
							{ null, null, null, null }, { null, null, null, null }, { null, null, null, null } },
							new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(t_MerRecords);

		b_query.setText("\u67e5\u8be2");
		b_query.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				b_queryMouseClicked(evt);
			}
		});

		bt_export.setText("\u5bfc\u51fa");
		bt_export.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt_exportActionPerformed(evt);
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
																						.addGap(47, 47, 47)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																					jScrollPane1,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					734,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addGroup(
																																					layout
																																										.createSequentialGroup()
																																										.addComponent(
																																															jLabel1)
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addComponent(
																																															tf_startTime,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															87,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addComponent(
																																															jLabel2)
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addComponent(
																																															tf_endTime,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															78,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addComponent(
																																															jLabel3)
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																										.addComponent(
																																															jcb_MerDealType,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															100,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addGap(
																																															18,
																																															18,
																																															18)
																																										.addComponent(
																																															jLabel4)
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addComponent(
																																															jcb_MerType,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															82,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addGap(
																																															18,
																																															18,
																																															18)
																																										.addComponent(
																																															b_query)
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															Short.MAX_VALUE)
																																										.addComponent(
																																															bt_export)))
																						.addContainerGap()));
		layout
							.setVerticalGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addContainerGap()
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
																																					tf_startTime,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					tf_endTime,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					jcb_MerDealType,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					jLabel4)
																																.addComponent(
																																					jcb_MerType,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					javax.swing.GroupLayout.DEFAULT_SIZE,
																																					javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																					b_query)
																																.addComponent(
																																					bt_export))
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																											jScrollPane1,
																											javax.swing.GroupLayout.PREFERRED_SIZE,
																											416,
																											javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addContainerGap(
																											javax.swing.GroupLayout.DEFAULT_SIZE,
																											Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	@SuppressWarnings("unchecked")
	public List queryList = new ArrayList();
	public String fileName = "";

	private void bt_exportActionPerformed(java.awt.event.ActionEvent evt) {

		if (queryList.size() > 0) {
		} else {
			String[] arg = { ((ItemData) this.jcb_MerType.getSelectedItem()).code,
								((ItemData) this.jcb_MerDealType.getSelectedItem()).code,
								DateUtil.string2Format(this.tf_startTime.getText(), DateUtil.YMD, DateUtil.YMD),
								DateUtil.string2Format(this.tf_endTime.getText(), DateUtil.YMD, DateUtil.YMD) };

			queryList = DbLogicServie.getMerchandiseRecodes(arg);
		}

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("导出文件");
		chooser.setSelectedFile(new File(fileName));
		int result = chooser.showDialog(this, "导出文件");
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				exportExcel(file);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void exportExcel(File file) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException,
						InvocationTargetException, IOException {
		OutputStream os = new FileOutputStream(file);
		HSSFWorkbook workbook = FileUtil.bean2Excel(MerchandiseTableModel.colnumNames,
							MerchandiseTableModel.fieldNames, queryList, MerchandiseRecodes.class, null);
		workbook.write(os);
		os.close();
	}

	@SuppressWarnings("unchecked")
	public void b_queryMouseClicked(java.awt.event.MouseEvent evt) {
		String[] arg = { ((ItemData) this.jcb_MerType.getSelectedItem()).code,
							((ItemData) this.jcb_MerDealType.getSelectedItem()).code,
							DateUtil.string2Format(this.tf_startTime.getText(), DateUtil.YMD, DateUtil.YMD),
							DateUtil.string2Format(this.tf_endTime.getText(), DateUtil.YMD, DateUtil.YMD) };
		fileName = this.tf_startTime.getText() + "-" + this.tf_endTime.getText() + "出入库明细导出.xls";
		queryList = DbLogicServie.getMerchandiseRecodes(arg);
		MerchandiseTableModel m_table = new MerchandiseTableModel(queryList);
		m_table.setData(queryList);
		t_MerRecords.setModel(m_table);

	}

	@SuppressWarnings("unchecked")
	public void coustomInit() {
		this.tf_startTime.setText(DateUtil.getNowDateByFormat(DateUtil.YMD));
		this.tf_endTime.setText(DateUtil.getNowDateByFormat(DateUtil.YMD));
		ItemData dataIn = new ItemData(MisConstants.MERCHANDDISEIN, "入库");
		ItemData dataOut = new ItemData(MisConstants.MERCHANDDISEOUT, "出库");
		this.jcb_MerDealType.addItem(dataIn);
		this.jcb_MerDealType.addItem(dataOut);
		List list = DbLogicServie.getMerchandiseInfo(null);
		if (list != null && list.size() > 0) {
			String[] items = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Merchandise mer = (Merchandise) list.get(i);
				items[i] = mer.getMerchandiseName();
				ItemData data = new ItemData(mer.getMerchandiseId(), mer.getMerchandiseName());
				this.jcb_MerType.addItem(data);
			}

		}

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MerchandisesQuery().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton b_query;
	private javax.swing.JButton bt_export;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	public javax.swing.JComboBox jcb_MerDealType;
	public javax.swing.JComboBox jcb_MerType;
	public javax.swing.JTable t_MerRecords;
	public javax.swing.JTextField tf_endTime;
	public javax.swing.JTextField tf_startTime;
	// End of variables declaration//GEN-END:variables

}