
package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import custom_Jcomponent.*;
import util.*;
import bankAccount.*;
import person.*;
import history.*;

public class MyTable extends JFrame implements ActionListener {

	JMenuItem menuItem;
	JTable table;
	final JTable fTable;
	DefaultTableModel mod;
	String[] COLUMN_NAMES = { "Account Number", "Account", "as" };
	int[] model;
	Object[][] ini;

	public void resetTable(Object[][] data) {
		System.out.println(table.getRowCount() + " " + table.getColumnCount());
		for (int i = 0; i < table.getRowCount(); i++)
			for (int j = 0; j < table.getColumnCount(); j++) {

				table.setValueAt(data[i][j], i, j);

			}

		// mod=new DefaultTableModel(data,COLUMN_NAMES);
		// table.setModel(mod);

		// ColumnResizer.adjustColumnPreferredWidths (table);
		table.revalidate();

	}

	public Object[][] getTableData() {
		Object[][] data = new Object[table.getRowCount()][table.getColumnCount()];

		for (int i = 0; i < table.getRowCount(); i++)
			for (int j = 0; j < table.getColumnCount(); j++) {

				data[i][j] = table.getValueAt(i, j);

			}

		return data;

	}

	public MyTable(Object[][] TABLE_DATA, String[] COLUMN_NAMES, String title, int[] model1) {

		this.model = model1;
		ini = TABLE_DATA;
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		JMenu menu = new JMenu("Edit");
		mb.add(menu);
		menuItem = new JMenuItem("save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		try {

			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		mod = new DefaultTableModel(TABLE_DATA, COLUMN_NAMES);
		table = new JTable(mod) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Object [][] data=getTableData();
		// SortArray.getSortArray(data,0);
		// resetTable(data);
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				Object[][] data = getTableData();

				final JTableHeader header = table.getTableHeader();
				int selIndex = 0;
				table.setRowSelectionAllowed(false);

				selIndex = header.columnAtPoint(e.getPoint());

				System.out.println("selIndex" + selIndex);
				SortArray.getSortArray(data, selIndex, model[selIndex]);
				if (selIndex < table.getColumnCount() - 1 && selIndex >= 0)
					resetTable(data);

				if (selIndex == table.getColumnCount() - 1)
					resetTable(ini);

			}
		});

		JScrollPane pane =

		new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		getContentPane().add(pane);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		pack();
		setVisible(true);

		fTable = table;
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				ColumnResizer.adjustColumnPreferredWidths(fTable);
				fTable.revalidate();
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		MyFileChooser Myc = new MyFileChooser();
		File file = Myc.getFile();
		TableModel model = fTable.getModel();

		try {
			FileWriter out = new FileWriter(file);
			for (int i = 0; i < model.getColumnCount(); i++) {
				out.write(model.getColumnName(i) + "\t");
			}
			out.write("\n");

			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					out.write(model.getValueAt(i, j).toString() + "\t");
				}
				out.write("\n");
			}

			out.close();

		}

		catch (Exception E) {

		}
		System.out.println("write out to: " + file);

	}

	public static void main(String[] args) {
		final Object[][] TABLE_DATA = { { new Integer(1), "ONJava", "http://www.onjava.com/" },
				{ new Integer(2), "Joshy's Site", "http://www.joshy.org/" },
				{ new Integer(3), "Anime Weekend Atlanta", "http://www.awa-con.com/" },
				{ new Integer(4), "QTJ book", "http://www.oreilly.com/catalog/quicktimejvaadn/" } };

		String[] COLUMN_NAMES = { "Account Number", "Account", "as" };
		String title = "mao";
		// new MyTable(TABLE_DATA ,COLUMN_NAMES,title);

	}
}
