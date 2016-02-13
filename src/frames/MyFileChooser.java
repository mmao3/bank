package frames;

import javax.swing.JFileChooser;
import java.util.*;
import java.io.*;

public class MyFileChooser extends JFileChooser {
	MyFileFilter filter;
	File file;
	File newFile;

	public MyFileChooser() {
		MyFileFilter txtFilter, excelFilter, docFilter;
		txtFilter = new MyFileFilter(".txt", "txt file (*.txt)");
		excelFilter = new MyFileFilter(".xls", "excel file (*.xls)");
		docFilter = new MyFileFilter(".doc", "doc file (*.doc)");
		addChoosableFileFilter(txtFilter);
		addChoosableFileFilter(excelFilter);
		addChoosableFileFilter(docFilter);
		setAcceptAllFileFilterUsed(false);
		int fresult;
		fresult = showSaveDialog(this);
		if (fresult == JFileChooser.APPROVE_OPTION) {
			file = getSelectedFile();
			filter = (MyFileFilter) getFileFilter();
			String ends = filter.getEnds();
			newFile = null;
			if (file.getAbsolutePath().toUpperCase().endsWith(ends.toUpperCase())) {
				newFile = file;
			} else {

				newFile = new File(file.getAbsolutePath() + ends);
			}

		}

	}

	public File getFile() {

		return newFile;

	}

	public static void main(String[] args) {

		new MyFileChooser();

	}

}