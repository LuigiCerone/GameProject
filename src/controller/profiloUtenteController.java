package controller;

import java.util.List;

import model.Livello;
import model.dao.UtenteDAO;

public class profiloUtenteController {
	
	/**
	 * Method used to fetch from the database all the infromation related to the user's levels.
	 * 
	 * @return mMatrix a matrix of objects.*/
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
