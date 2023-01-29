package api;

import java.awt.Image;

import javax.swing.ImageIcon;

public enum SlotFigures {
	BELL("/beLl.png"), CHERRY("/cherry.jpg"), WATERMELON("/watermelon.jpg"), BAR("/bar.jpg"), APPLE("/apple.png");
	
	private final Image img;
	
	private SlotFigures(String url) {
		img = new ImageIcon(this.getClass().getResource(url)).getImage();
	}
	
	public Image getImg() {
		return img;
	}
}
