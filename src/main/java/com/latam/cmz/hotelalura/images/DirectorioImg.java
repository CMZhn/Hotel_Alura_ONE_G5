package com.latam.cmz.hotelalura.images;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;


public class DirectorioImg {
	/**
	 * Metodo para retornar la imagen como Image.
	 * La imagen de estar en el mismo paquete de esta clase.
	 * @param Image_name
	 * @return
	 */
	public static Image img(String Image_name) {
		return Toolkit.getDefaultToolkit().getImage(DirectorioImg.class.getResource(Image_name));
	}
	/**
	 * Metodo para retornar la imagen como ImageIcon.
	 * La imagen de estar en el mismo paquete de esta clase.
	 * @param Image_name
	 * @return
	 */
	public static ImageIcon ico(String Image_name) {
		return new ImageIcon (DirectorioImg.class.getResource(Image_name));
	}

}
