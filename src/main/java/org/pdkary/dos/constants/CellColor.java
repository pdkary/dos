package org.pdkary.dos.constants;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.IndexedColors;

public class CellColor {
	private static Map<Integer,Short> colorMap = new HashMap<>();
	
	static {
		colorMap.put(1, IndexedColors.RED.getIndex());
		colorMap.put(2, IndexedColors.ORANGE.getIndex());
		colorMap.put(3, IndexedColors.YELLOW.getIndex());
		colorMap.put(4, IndexedColors.GREEN.getIndex());
		colorMap.put(5, IndexedColors.BLUE.getIndex());
		colorMap.put(6, IndexedColors.PLUM.getIndex());
		colorMap.put(7, IndexedColors.PINK.getIndex());
		colorMap.put(8, IndexedColors.GREY_25_PERCENT.getIndex());
	}
	
	public static short get(Integer id) {
		return CellColor.colorMap.get(id);
	}
}
