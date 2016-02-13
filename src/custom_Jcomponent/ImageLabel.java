
package custom_Jcomponent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ImageLabel extends JLabel {

	public ImageLabel(ImageIcon icon) {
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
		setIcon(icon);
		setIconTextGap(0);
		setBorder(null);
		setText(null);
		setOpaque(false);
	}

}
