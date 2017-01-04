package space;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ACalculusGame {
	
	int input1, input2, input3, input4, input5, input6, input7, input8, input9, input10;
	int questionNumber = 1;
	List<Integer> correct = new ArrayList<Integer>();
	List<Integer> wrong = new ArrayList<Integer>();
	ImageIcon i1 = null;
	ImageIcon i2 = null;
	ImageIcon i3 = null;
	ImageIcon i4 = null;
	ImageIcon i5 = null; 
	ImageIcon i6 = null;
	ImageIcon i7 = null;
	ImageIcon i8 = null;
	ImageIcon i9 = null;
	ImageIcon i10 = null;
	ImageIcon answers = null;
	
	public static void main(String[] args){
		ACalculusGame a = new ACalculusGame();
		a.showSet1();
		a.showSet2();
		a.showSet3();
		a.showSet4();
		a.showSet5();
		a.showAnswers();
	}
	
	public ACalculusGame(){		
		try {i1 = new ImageIcon(ACalculusGame.class.getResource("/q1.gif"));} catch (Exception e) {}
		try {i2 = new ImageIcon(ACalculusGame.class.getResource("/q2.gif"));} catch (Exception e) {}
		try {i3 = new ImageIcon(ACalculusGame.class.getResource("/q3.gif"));} catch (Exception e) {}
		try {i4 = new ImageIcon(ACalculusGame.class.getResource("/q4.gif"));} catch (Exception e) {}
		try {i5 = new ImageIcon(ACalculusGame.class.getResource("/q5.gif"));} catch (Exception e) {}
		try {i6 = new ImageIcon(ACalculusGame.class.getResource("/q6.gif"));} catch (Exception e) {}
		try {i7 = new ImageIcon(ACalculusGame.class.getResource("/q7.gif"));} catch (Exception e) {}
		try {i8 = new ImageIcon(ACalculusGame.class.getResource("/q8.gif"));} catch (Exception e) {}
		try {i9 = new ImageIcon(ACalculusGame.class.getResource("/q9.gif"));} catch (Exception e) {}
		try {i10 = new ImageIcon(ACalculusGame.class.getResource("/q10.gif"));} catch (Exception e) {}
		try {answers = new ImageIcon(ACalculusGame.class.getResource("/answers.gif"));} catch (Exception e) {}

	}
	
	public void computeScore() {
		int total = questionNumber-1;
		double percent = (double) correct.size()*100/total;
		String correctAns = "";
		String wrongAns = "";
		
		for (int i=0; i<correct.size(); i++)
			correctAns+=correct.get(i)+" ";
		for (int j=0; j<wrong.size(); j++)
			wrongAns+=wrong.get(j)+" ";
		if(correctAns.isEmpty())
			correctAns = "none";
		if(wrongAns.isEmpty())
			wrongAns = "none";
		
		JOptionPane.showMessageDialog(null,"Percent: "+percent+"\nCorrect: "+correctAns+"\nWrong: "+wrongAns,
		"Game Over", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void showSet1(){
		
		JOptionPane.showMessageDialog(null, "Welcome! You answer a pair of questions and then play a game. "
		+ "\nAfter the second pair, if your score is less than 50% you lose.", "Calculus Practice",
		JOptionPane.INFORMATION_MESSAGE);
		
		Object[] options1 = {"A", "B", "C", "D"};
		input1 = JOptionPane.showOptionDialog(null, "", "Question 1",
		JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, i1, options1, null);
		this.add(3, input1, questionNumber);
		questionNumber++;
		
		Object[] options2 = {"A", "B", "C", "D"};
		input2 = JOptionPane.showOptionDialog(null, "", "Question 2",
		JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, i2, options2, null);
		this.add(2, input2, questionNumber);
		questionNumber++;
		
		this.computeScore();
	}
	
	public void showSet2(){
		
		Object[] options3 = {"A", "B", "C", "D"};
		input3 = JOptionPane.showOptionDialog(null, "", "Question 3",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, i3, options3, null);
		this.add(3, input3, questionNumber);
		questionNumber++;

		Object[] options4 = {"A", "B", "C", "D"};
		input4 = JOptionPane.showOptionDialog(null, "", "Question 4",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, i4, options4, null);
		this.add(0, input4, questionNumber);
		questionNumber++;
		
		this.computeScore();


	}
	
	public void showSet3(){
		
		Object[] options5 = {"A", "B", "C", "D"};
		input5 = JOptionPane.showOptionDialog(null, "", "Question 5",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, i5, options5, null);
		this.add(1, input5, questionNumber);
		questionNumber++;

		Object[] options6 = {"A", "B", "C", "D"};
		input6 = JOptionPane.showOptionDialog(null, "", "Question 6",
		JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, i6, options6, null);
		this.add(0, input6, questionNumber);
		questionNumber++;
		
		this.computeScore();


	}
	
	public void showSet4(){
		Object[] options7 = {"A", "B", "C", "D"}; 
		input7 = JOptionPane.showOptionDialog(null, "", "Question 7",
		JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, i7, options7, null);
		this.add(0, input7, questionNumber); 
		questionNumber++;

		Object[] options8 = {"A", "B", "C", "D"}; 
		input8 = JOptionPane.showOptionDialog(null, "", "Question 8",
		JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, i8, options8, null);
		this.add(0, input8, questionNumber); 
		questionNumber++;
		
		this.computeScore();

	}
	
	public void showSet5(){	
		
		Object[] options9 = {"A", "B", "C", "D"}; 
		input9 = JOptionPane.showOptionDialog(null, "", "Question 9",
		JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, i9, options9, null);
		this.add(0, input9, questionNumber); 
		questionNumber++;

		Object[] options10 = {"A", "B", "C", "D"}; 
		input10 = JOptionPane.showOptionDialog(null, "", "Question 10",
		JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, i10, options10, null);
		this.add(0, input10, questionNumber); 
		questionNumber++;
		
		this.computeScore();
		
	}
	
	private void add(int answer, int input, int questionNumber) {
		if(input==answer)
			correct.add(questionNumber);
		else
			wrong.add(questionNumber);
	}
	
	public double findPercent(){
		int total = questionNumber-1;
		double percent = (double) correct.size()*100/total;
		return percent;
	}
	
	public int getQuestionNumber(){
		return this.questionNumber;
	}
	
	public void showAnswers(){
		JOptionPane.showMessageDialog(null, "Review the Questions.\nThanks for Playing!", "Review",JOptionPane.DEFAULT_OPTION, answers);
	}
	
}

//	JOptionPane.showMessageDialog(null,new JLabel("String", icon, JLabel.LEFT),
//    "Title", JOptionPane.INFORMATION_MESSAGE);
