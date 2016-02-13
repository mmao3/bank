package frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SSNData extends JPanel {

	GridBagConstraints constraints;
	GridBagLayout gbaglayout;
	public JTextField SSN1;
	public JTextField SSN2;
	public JTextField SSN3;
	public JTextField SSN4;
	public JTextField SSN5;
	public JTextField SSN6;
	public JTextField SSN7;
	public JTextField SSN8;
	public JTextField SSN9;

	public String getText() {

		return SSN1.getText().trim() + SSN2.getText().trim() + SSN3.getText().trim() + SSN4.getText().trim()
				+ SSN5.getText().trim() + SSN6.getText().trim() + SSN7.getText().trim() + SSN8.getText().trim()
				+ SSN9.getText().trim();

	}

	public int getLength() {
		return SSN1.getText().trim().length() + SSN2.getText().trim().length() + SSN3.getText().trim().length()
				+ SSN4.getText().trim().length() + SSN5.getText().trim().length() + SSN6.getText().trim().length()
				+ SSN7.getText().trim().length() + SSN8.getText().trim().length() + SSN9.getText().trim().length();

	}

	public void setEnabled(boolean b) {
		if (b) {
			SSN1.setEnabled(true);
			SSN2.setEnabled(true);
			SSN3.setEnabled(true);
			SSN4.setEnabled(true);
			SSN5.setEnabled(true);
			SSN6.setEnabled(true);
			SSN7.setEnabled(true);
			SSN8.setEnabled(true);
			SSN9.setEnabled(true);

		} else {
			SSN1.setEnabled(false);
			SSN2.setEnabled(false);
			SSN3.setEnabled(false);
			SSN4.setEnabled(false);
			SSN5.setEnabled(false);
			SSN6.setEnabled(false);
			SSN7.setEnabled(false);
			SSN8.setEnabled(false);
			SSN9.setEnabled(false);

		}

	}

	public void setText(String text) {

		SSN1.setText(text.substring(0, 1));
		SSN2.setText(text.substring(1, 2));
		SSN3.setText(text.substring(2, 3));
		SSN4.setText(text.substring(3, 4));
		SSN5.setText(text.substring(4, 5));
		SSN6.setText(text.substring(5, 6));
		SSN7.setText(text.substring(6, 7));
		SSN8.setText(text.substring(7, 8));
		SSN9.setText(text.substring(8, 9));

	}

	public SSNData() {
		gbaglayout = new GridBagLayout();
		constraints = new GridBagConstraints();
		setLayout(gbaglayout);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.ipadx = 0;
		constraints.ipady = 0;
		SSN1 = new JTextField(1);
		SSN2 = new JTextField(1);
		SSN3 = new JTextField(1);
		SSN4 = new JTextField(1);
		SSN5 = new JTextField(1);
		SSN6 = new JTextField(1);
		SSN7 = new JTextField(1);
		SSN8 = new JTextField(1);
		SSN9 = new JTextField(1);

		SSN1.setDocument(new MyDocument(1));
		SSN1.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (SSN1.getText().trim().length() == 1)
					SSN1.setText("");
			}

			public void keyReleased(KeyEvent e) {

				SSN1.setText(SSN1.getText().replaceAll("[^1-9]", ""));
				if (SSN1.getText().trim().length() == 1)
					SSN2.requestFocus(true);
			}
		});

		SSN2.setDocument(new MyDocument(1));
		SSN2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				if (SSN2.getText().trim().length() == 1)
					SSN2.setText("");
			}

			public void keyReleased(KeyEvent e) {

				SSN2.setText(SSN2.getText().replaceAll("[^0-9]", ""));
				if (SSN2.getText().trim().length() == 1)
					SSN3.requestFocus(true);
			}
		});
		SSN3.setDocument(new MyDocument(1));
		SSN3.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (SSN3.getText().trim().length() == 1)
					SSN3.setText("");
			}

			public void keyReleased(KeyEvent e) {

				SSN3.setText(SSN3.getText().replaceAll("[^0-9]", ""));
				if (SSN3.getText().trim().length() == 1)
					SSN4.requestFocus(true);
			}
		});
		SSN4.setDocument(new MyDocument(1));
		SSN4.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (SSN4.getText().trim().length() == 1)
					SSN4.setText("");
			}

			public void keyReleased(KeyEvent e) {

				SSN4.setText(SSN4.getText().replaceAll("[^0-9]", ""));
				if (SSN4.getText().trim().length() == 1)
					SSN5.requestFocus(true);
			}
		});
		SSN5.setDocument(new MyDocument(1));
		SSN5.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (SSN5.getText().trim().length() == 1)
					SSN5.setText("");
			}

			public void keyReleased(KeyEvent e) {

				SSN5.setText(SSN5.getText().replaceAll("[^0-9]", ""));
				if (SSN5.getText().trim().length() == 1)
					SSN6.requestFocus(true);
			}
		});
		SSN6.setDocument(new MyDocument(1));
		SSN6.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (SSN6.getText().trim().length() == 1)
					SSN6.setText("");
			}

			public void keyReleased(KeyEvent e) {

				SSN6.setText(SSN6.getText().replaceAll("[^0-9]", ""));
				if (SSN6.getText().trim().length() == 1)
					SSN7.requestFocus(true);
			}
		});
		SSN7.setDocument(new MyDocument(1));
		SSN7.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (SSN7.getText().trim().length() == 1)
					SSN7.setText("");
			}

			public void keyReleased(KeyEvent e) {

				SSN7.setText(SSN7.getText().replaceAll("[^0-9]", ""));
				if (SSN7.getText().trim().length() == 1)
					SSN8.requestFocus(true);
			}
		});
		SSN8.setDocument(new MyDocument(1));
		SSN8.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (SSN8.getText().trim().length() == 1)
					SSN8.setText("");
			}

			public void keyReleased(KeyEvent e) {

				SSN8.setText(SSN8.getText().replaceAll("[^0-9]", ""));
				if (SSN8.getText().trim().length() == 1)
					SSN9.requestFocus(true);
			}
		});
		SSN9.setDocument(new MyDocument(1));
		SSN9.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (SSN9.getText().trim().length() == 1)
					SSN9.setText("");
			}

			public void keyReleased(KeyEvent e) {

				SSN9.setText(SSN9.getText().replaceAll("[^0-9]", ""));

			}
		});

		add(SSN1, constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(0, 2, 0, 0);
		add(SSN2, constraints);
		constraints.gridx = 2;
		add(SSN3, constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 3;
		add(new JLabel("--"), constraints);
		constraints.gridx = 4;
		add(SSN4, constraints);
		constraints.gridx = 5;
		constraints.insets = new Insets(0, 2, 0, 0);
		add(SSN5, constraints);
		constraints.gridx = 6;
		constraints.insets = new Insets(0, 0, 0, 0);
		add(new JLabel("--"), constraints);
		constraints.gridx = 7;

		add(SSN6, constraints);
		constraints.insets = new Insets(0, 2, 0, 0);
		constraints.gridx = 8;
		add(SSN7, constraints);

		constraints.gridx = 9;
		add(SSN8, constraints);
		constraints.gridx = 10;
		add(SSN9, constraints);

	}
}