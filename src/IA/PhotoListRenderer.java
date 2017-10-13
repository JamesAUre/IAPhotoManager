package IA;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class PhotoListRenderer extends DefaultListCellRenderer{
@Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (renderer instanceof JLabel && value instanceof Photo) {
          String text = ((Photo) value).getName();
          if (text == null || text.isEmpty()){
          text = ((Photo) value).getPath();
          }
        ((JLabel) renderer).setText(text);
        }
        return renderer;
    }

}
