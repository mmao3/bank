package custom_Jcomponent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ImageButton extends JButton {
	public ImageButton(ImageIcon icon) {
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
		setIcon(icon);
		setMargin(new Insets(0, 0, 0, 0));
		setIconTextGap(0);
		setBorderPainted(false);
		setBorder(null);
		setText(null);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
}
