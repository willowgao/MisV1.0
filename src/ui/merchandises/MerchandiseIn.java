/*
 * MerchandiseIn.java
 *
 * Created on __DATE__, __TIME__
 */

package ui.merchandises;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ui.listener.MainWinListener;
import ui.model.MerchandiseTableModel;
import util.DateUtil;
import bean.ItemData;
import bean.Merchandise;
import bean.MerchandiseRecodes;
import constants.MisConstants;
import db.DbLogicServie;

/**
 * 
 * @author __USER__
 */
public class MerchandiseIn extends javax.swing.JFrame {
	private String userName;
	private Integer m_count;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates new form MerchandiseIn */
	@SuppressWarnings("unchecked")
	public MerchandiseIn(String userName) {
		this.userName = userName;
		initComponents();
		// init merdealtype for select
		ItemData dataIn = new ItemData(MisConstants.MERCHANDDISEIN, "入库");
		ItemData dataOut = new ItemData(MisConstants.MERCHANDDISEOUT, "出库");
		this.cb_MerDealType.addItem(dataIn);
		this.cb_MerDealType.addItem(dataOut);
		// init mertype for select
		List list = DbLogicServie.getMerchandiseInfo(null);
		if ((list != null) && (list.size() > 0)) {
			String[] items = new String[list.size()];
			for (int i = 0; i < list.size(); ++i) {
				Merchandise mer = (Merchandise) list.get(i);
				items[i] = mer.getMerchandiseName();
				ItemData data = new ItemData(mer.getMerchandiseId(), mer.getMerchandiseName());
				this.cb_MerType.addItem(data);
			}

		}
		this.ft_MerPrice.setEditable(false);

		// init merdate
		this.ft_MerInDate.setText(DateUtil.getNowDateByFormat("yyyy-MM-dd"));

		initTableInfo();
		// add listener on mouseDoubleClicked
		this.t_recordsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					List recordsList = DbLogicServie.getMerchandiseRecodes(null);
					MerchandiseTableModel tm = new MerchandiseTableModel(recordsList);
					MerchandiseRecodes mer = tm.getMer(t_recordsList.getSelectedRow());
					EditRecords eMer = new EditRecords(mer.getM_id());
					setWindowSize(eMer, "更新出入库记录");
				}
			}
		});

	}

	@SuppressWarnings("unchecked")
	public void initTableInfo() {
		// query merchandiserecodes for view
		List recordsList = DbLogicServie.getMerchandiseRecodes(null);
		if ((recordsList != null) && (recordsList.size() > 0)) {
			MerchandiseTableModel m_table = new MerchandiseTableModel(recordsList);
			m_table.setData(recordsList);
			this.t_recordsList.setModel(m_table);
			TableColumnModel columnModel = t_recordsList.getColumnModel();
			TableColumn column = columnModel.getColumn(0);
			column.setMinWidth(0);
			column.setMaxWidth(0);
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

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		cb_MerType = new javax.swing.JComboBox();
		jLabel2 = new javax.swing.JLabel();
		sp_MerCount = new javax.swing.JSpinner();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		b_cannel = new javax.swing.JButton();
		sb_desc = new javax.swing.JScrollPane();
		ta_desc = new javax.swing.JTextArea();
		b_next = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		t_recordsList = new javax.swing.JTable();
		ft_MerInDate = new javax.swing.JTextField();
		ft_MerPrice = new javax.swing.JTextField();
		ft_totalPrice = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		cb_MerDealType = new javax.swing.JComboBox();
		jLabel8 = new javax.swing.JLabel();
		l_Mcount = new javax.swing.JLabel();
		bt_del = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("\u8d27\u7269\u7c7b\u578b\uff1a");

		cb_MerType.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cb_MerTypeActionPerformed(evt);
			}
		});

		jLabel2.setText("\u8d27\u7269\u6570\u91cf\uff1a");

		sp_MerCount.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				sp_MerCountStateChanged(evt);
			}
		});

		jLabel3.setText("\u5907     \u6ce8\uff1a");

		jLabel4.setText("\u5355      \u4ef7\uff1a");

		jLabel5.setText("\u603b      \u4ef7\uff1a");

		jLabel6.setText("\u5165\u5e93\u65f6\u95f4\uff1a");

		b_cannel.setText("\u53d6\u6d88");
		b_cannel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				b_cannelActionPerformed(evt);
			}
		});

		ta_desc.setColumns(20);
		ta_desc.setRows(5);
		sb_desc.setViewportView(ta_desc);

		b_next.setText("\u7ee7\u7eed\u5f55\u5165");
		b_next.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				b_nextMouseClicked(evt);
			}
		});

	 
		jScrollPane2.setViewportView(t_recordsList);

		ft_MerInDate.setToolTipText("\u683c\u5f0f\"YYYY-MM-DD\"");

		ft_MerPrice.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ft_MerPriceActionPerformed(evt);
			}
		});
		ft_MerPrice.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				ft_MerPriceFocusLost(evt);
			}
		});

		jLabel7.setText("\u51fa\u5165\u5e93\uff1a");

		jLabel8.setText("\u5e93\u5b58\u5269\u4e0b:");

		l_Mcount.setFont(new java.awt.Font("微软雅黑", 1, 36));
		l_Mcount.setForeground(new java.awt.Color(255, 0, 0));

		bt_del.setText("\u5220\u9664");
		bt_del.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt_delActionPerformed(evt);
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
																						.addContainerGap()
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.LEADING)
																																.addGroup(
																																					layout
																																										.createSequentialGroup()
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING)
																																																				.addComponent(
																																																									jLabel4,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									76,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE)
																																																				.addComponent(
																																																									jLabel1,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									107,
																																																									Short.MAX_VALUE))
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING,
																																																									false)
																																																				.addComponent(
																																																									ft_MerPrice)
																																																				.addComponent(
																																																									cb_MerType,
																																																									0,
																																																									128,
																																																									Short.MAX_VALUE))
																																										.addGap(
																																															31,
																																															31,
																																															31)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.TRAILING)
																																																				.addComponent(
																																																									jLabel2,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									65,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE)
																																																				.addComponent(
																																																									jLabel6))
																																										.addPreferredGap(
																																															javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING,
																																																									false)
																																																				.addGroup(
																																																									layout
																																																														.createSequentialGroup()
																																																														.addComponent(
																																																																			sp_MerCount,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																			129,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE)
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																																														.addComponent(
																																																																			jLabel5))
																																																				.addGroup(
																																																									layout
																																																														.createSequentialGroup()
																																																														.addComponent(
																																																																			ft_MerInDate,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																			121,
																																																																			javax.swing.GroupLayout.PREFERRED_SIZE)
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																																																			javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																			Short.MAX_VALUE)
																																																														.addComponent(
																																																																			jLabel7)))
																																										.addGap(
																																															12,
																																															12,
																																															12)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.LEADING,
																																																									false)
																																																				.addComponent(
																																																									cb_MerDealType,
																																																									0,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									Short.MAX_VALUE)
																																																				.addComponent(
																																																									ft_totalPrice,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									75,
																																																									Short.MAX_VALUE))
																																										.addGap(
																																															37,
																																															37,
																																															37)
																																										.addComponent(
																																															jLabel8)
																																										.addGap(
																																															18,
																																															18,
																																															18)
																																										.addComponent(
																																															l_Mcount,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															133,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addGap(
																																															217,
																																															217,
																																															217))
																																.addGroup(
																																					layout
																																										.createSequentialGroup()
																																										.addComponent(
																																															jLabel3,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															55,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)
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
																																																																			b_next)
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																														.addComponent(
																																																																			bt_del)
																																																														.addPreferredGap(
																																																																			javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																														.addComponent(
																																																																			b_cannel))
																																																				.addComponent(
																																																									sb_desc,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									564,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE))
																																										.addGap(
																																															440,
																																															440,
																																															440))))
												.addGroup(
																	layout
																						.createSequentialGroup()
																						.addComponent(
																											jScrollPane2,
																											javax.swing.GroupLayout.PREFERRED_SIZE,
																											761,
																											javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addContainerGap(
																											336,
																											Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { b_cannel, b_next });

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { jLabel3, jLabel4 });

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { jLabel2, jLabel6 });

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
																																					javax.swing.GroupLayout.Alignment.TRAILING)
																																.addGroup(
																																					layout
																																										.createSequentialGroup()
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.BASELINE)
																																																				.addComponent(
																																																									jLabel1)
																																																				.addComponent(
																																																									cb_MerType,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE)
																																																				.addComponent(
																																																									jLabel6)
																																																				.addComponent(
																																																									ft_MerInDate,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE)
																																																				.addComponent(
																																																									jLabel7)
																																																				.addComponent(
																																																									cb_MerDealType,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE)
																																																				.addComponent(
																																																									jLabel8)
																																																				.addComponent(
																																																									l_Mcount,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									44,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE))
																																										.addGap(
																																															19,
																																															19,
																																															19)
																																										.addGroup(
																																															layout
																																																				.createParallelGroup(
																																																									javax.swing.GroupLayout.Alignment.BASELINE)
																																																				.addComponent(
																																																									jLabel4)
																																																				.addComponent(
																																																									ft_MerPrice,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE,
																																																									javax.swing.GroupLayout.DEFAULT_SIZE,
																																																									javax.swing.GroupLayout.PREFERRED_SIZE)))
																																.addGroup(
																																					layout
																																										.createParallelGroup(
																																															javax.swing.GroupLayout.Alignment.BASELINE)
																																										.addComponent(
																																															jLabel2)
																																										.addComponent(
																																															sp_MerCount,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addComponent(
																																															jLabel5)
																																										.addComponent(
																																															ft_totalPrice,
																																															javax.swing.GroupLayout.PREFERRED_SIZE,
																																															javax.swing.GroupLayout.DEFAULT_SIZE,
																																															javax.swing.GroupLayout.PREFERRED_SIZE)))
																						.addPreferredGap(
																											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																					jLabel3)
																																.addComponent(
																																					sb_desc,
																																					javax.swing.GroupLayout.PREFERRED_SIZE,
																																					62,
																																					javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGap(18, 18, 18)
																						.addGroup(
																											layout
																																.createParallelGroup(
																																					javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																					b_next)
																																.addComponent(
																																					b_cannel)
																																.addComponent(
																																					bt_del))
																						.addGap(28, 28, 28)
																						.addComponent(
																											jScrollPane2,
																											javax.swing.GroupLayout.PREFERRED_SIZE,
																											199,
																											javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addContainerGap(
																											43,
																											Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	@SuppressWarnings("unchecked")
	private void cb_MerTypeActionPerformed(java.awt.event.ActionEvent evt) {
		String[] arg = { ((ItemData) this.cb_MerType.getSelectedItem()).code };
		List list = DbLogicServie.getMerCount(arg);
		if (list != null) {
			MerchandiseRecodes merr = (MerchandiseRecodes) list.get(0);
			m_count = merr.getM_count();
			this.l_Mcount.setText(merr.getM_count().toString());
		}
		List merList = DbLogicServie.getMerchandiseInfo(arg);
		this.ft_MerPrice.setText(((Merchandise)merList.get(0)).getMerchandisePrice());
	}

	public List<String> merchandiseRetailIds = new ArrayList<String>();

	private void bt_delActionPerformed(java.awt.event.ActionEvent evt) {
		if (m_count.intValue() <= 0) {
			JOptionPane.showMessageDialog(null, "存库为零，请确认库存!", "提示信息", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int[] row = this.t_recordsList.getSelectedRows();
		for (int i = 0; i < row.length; i++) {
			merchandiseRetailIds.add(t_recordsList.getValueAt(row[i], 0).toString());
		}
		if (DbLogicServie.delMerchandise(merchandiseRetailIds)) {
			initTableInfo();
			cb_MerTypeActionPerformed(evt);
			JOptionPane.showMessageDialog(null, "剔除成功!", "提示信息", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void ft_MerPriceFocusLost(java.awt.event.FocusEvent evt) {
		setTotalPrice();
	}

	private void ft_MerPriceActionPerformed(java.awt.event.ActionEvent evt) {
		setTotalPrice();
	}

	/**
	 * @type MerchandiseIn.java
	 * @return void
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */
	void setTotalPrice() {
		if (!this.ft_MerPrice.getText().equals("")) {
			DecimalFormat df = new DecimalFormat("#.##");
			double value = Double.parseDouble(df.format(Double.valueOf(this.ft_MerPrice.getText()).doubleValue()
								* Double.valueOf(this.sp_MerCount.getValue().toString()).doubleValue()));
			this.ft_totalPrice.setText(value + "");
		}
	}

	private void sp_MerCountStateChanged(javax.swing.event.ChangeEvent evt) {
		setTotalPrice();
	}

	/**
	 * @type MerchandiseIn.java
	 * @return void
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */

	private void b_nextMouseClicked(java.awt.event.ActionEvent evt) {

		MerchandiseRecodes mer = new MerchandiseRecodes();
		mer.setM_price(new BigDecimal((String) this.ft_MerPrice.getText()));
		mer.setM_total(new BigDecimal((String) this.ft_totalPrice.getText()));
		mer.setM_date(DateUtil.string2SqlDate((String) this.ft_MerInDate.getText(), DateUtil.YMD));
		mer.setM_type(((ItemData) this.cb_MerType.getSelectedItem()).code);
		mer.setM_count((Integer) this.sp_MerCount.getValue());
		mer.setM_desc(this.ta_desc.getText());
		mer.setM_dealType(((ItemData) this.cb_MerDealType.getSelectedItem()).code);
		mer.setM_createDt(Timestamp.valueOf(DateUtil.getNowDateByFormat(DateUtil.YMDHMS)));
		mer.setM_creator(userName);
		if (m_count.intValue() <= 0 && mer.getM_dealType().equals(MisConstants.MERCHANDDISEOUT)) {
			JOptionPane.showMessageDialog(null, "存库为零，请确认库存!", "提示信息", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (DbLogicServie.insertMerchandise(mer)) {
			JOptionPane.showMessageDialog(null, "保存成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "保存失败", "提示信息", JOptionPane.ERROR_MESSAGE);
			return;
		}

		initTableInfo();
		cb_MerTypeActionPerformed(evt);
	}

	private void b_cannelActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MerchandiseIn("").setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton b_cannel;
	private javax.swing.JButton b_next;
	private javax.swing.JButton bt_del;
	private javax.swing.JComboBox cb_MerDealType;
	private javax.swing.JComboBox cb_MerType;
	private javax.swing.JTextField ft_MerInDate;
	private javax.swing.JTextField ft_MerPrice;
	private javax.swing.JTextField ft_totalPrice;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JLabel l_Mcount;
	private javax.swing.JScrollPane sb_desc;
	private javax.swing.JSpinner sp_MerCount;
	private javax.swing.JTable t_recordsList;
	private javax.swing.JTextArea ta_desc;
	// End of variables declaration//GEN-END:variables

}