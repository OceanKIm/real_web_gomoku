package com.ocean.realomuk.common;

import java.util.ArrayList;
import java.util.List;

public class PlayUtils {
	
	public static String Rule(int y, int x, int z, int[][] position, int boardSize) {
		String[] Check = new String[4];
		for (int i = 5; i > 0; i--) {
			if (x - i > -1 && y - i > -1)
				Check[2] += position[x - i][y - i] + "";
			if (x - i > -1 && y + i < boardSize)
				Check[3] += position[x - i][y + i] + "";
			if (y - i > -1)
				Check[0] += position[x][y - i] + "";
			if (x - i > -1)
				Check[1] += position[x - i][y] + "";
		}
		for (int i = 0; i < 6; i++) {
			if (x + i < boardSize && y + i < boardSize)
				Check[2] += position[x + i][y + i] + "";
			if (x + i < boardSize && y - i > -1)
				Check[3] += position[x + i][y - i] + "";
			if (y + i < boardSize)
				Check[0] += position[x][y + i] + "";
			if (x + i < boardSize)
				Check[1] += position[x + i][y] + "";
		}
		for (int i = 0; i < Check.length; i++)
			if ((Check[i].matches(".*[^1]" + "11111" + "[^1].*") && z == 1)
					|| (Check[i].contains("22222") && z == 2)
					|| (Check[i].contains("11111") && z == 1)) {
				return z == 1 ? "blackWin" : "whiteWin";
			}
		return "nothing";
	}

	public static List<String> RenJuRule(int y, int x, int z, int[][] position, int boardSize) {
		boolean is_six = false;
		List<String> list = new ArrayList<String>();
		String[] Check = new String[4];
		for (int i = 5; i > 0; i--) {
			if (x - i > -1 && y - i > -1)
				Check[2] += position[x - i][y - i] + "";
			if (x - i > -1 && y + i < boardSize)
				Check[3] += position[x - i][y + i] + "";
			if (y - i > -1)
				Check[0] += position[x][y - i] + "";
			if (x - i > -1)
				Check[1] += position[x - i][y] + "";
		}
		for (int i = 0; i < 6; i++) {
			if (x + i < boardSize && y + i < boardSize)
				Check[2] += position[x + i][y + i] + "";
			if (x + i < boardSize && y - i > -1)
				Check[3] += position[x + i][y - i] + "";
			if (y + i < boardSize)
				Check[0] += position[x][y + i] + "";
			if (x + i < boardSize)
				Check[1] += position[x + i][y] + "";
		}
		if (z == 1) {
			int Count33 = 0, Count44 = 0, Count66 = 0;
			for (int i = 0; i < Check.length; i++) {
				if (Check[i].contains("01110") || Check[i].contains("010110") || Check[i].contains("011010")) {
					if (Check[i].contains("0111010") || Check[i].contains("0101110")) {
					} else {
						Count33++;				
					}	
				}
				if (Check[i].contains("111111"))
					Count66++;				
				Count44 += ((Check[i].contains("1111") || Check[i].contains("11011") || Check[i].contains("11101")
						|| Check[i].contains("10111")) && !Check[i].contains("211112") && !Check[i].contains("11211")
						&& !Check[i].contains("12111") && !Check[i].contains("11121")) ? 1 : 0;
			}
			if (Count33 > 1 || Count44 > 1) {
				list.add("canNotPut");		
			}
			if (Count66 > 0) {
				list.add("canNotPut");
				System.out.println("육목!!!!!");
				is_six = true;
			}
		}
		for (int i = 0; i < Check.length; i++)
			if ((Check[i].matches(".*[^1]" + "11111" + "[^1].*") && z == 1)
					|| (Check[i].contains("22222") && z == 2)
					|| (Check[i].contains("11111") && z == 1)) {
				if(z == 1) {
					if (!is_six) {
						list.add("blackWin");						
					} else {
						list.add("noWin");	// 육목 체크를 위한 개같은 상황
					}
					return list;
				} 
			}
		list.add("noWin");
		return list;
	}
	
	
	
}
