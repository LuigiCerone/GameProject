package util;

import java.awt.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Gioco;

public class ScrollCreator <T> {
	private JScrollPane mScrollPane;
	private JTable mTable;
	private int colonne;
	private List mList;
	private Object[][] mMatrix;
	
	public ScrollCreator(JScrollPane mScrollPane, JTable mTable) {
		this.mScrollPane = mScrollPane;
		this.mTable = mTable;
		this.mMatrix = new Object[mList.getItemCount()][colonne];
		//populateMatrix();
	}
	
	/*private void populateMatrix(){
		int i = 0;
		for(T g : mList){
			mMatrix[i][0] = (Object)g.getId();
			mMatrix[i][1] = (Object)g.getNome();
			mMatrix[i][2] = (Object)new Integer(g.getValoreXP());
			mMatrix[i][3] = (Object)new Float(g.getMedia());
			i++;
		}
	}*/
}
