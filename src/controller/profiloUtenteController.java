package controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.Livello;
import model.dao.UtenteDAO;

public class profiloUtenteController {
	public static Object[][] timeLine() {
		List<Livello> timeLineList = new UtenteDAO().timeLineList(loginController.mObject.getID());
		Object[][] mMatrix = new Object[timeLineList.size()][2];
		
		int i = 0;
		for(Livello l : timeLineList){
			mMatrix[i][0] = l.getData();
			mMatrix[i][1] = l.getLivello();
			i++;
		}
		return mMatrix;
	}
	
}
