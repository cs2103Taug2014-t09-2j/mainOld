package org.eclipse.wb.swt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Draft1 {

	private JFrame frame;
	private JTextField commandBox;
	private final String fileName = "test.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Draft1 window = new Draft1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Draft1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIdo = new JLabel("iDO++");
		lblIdo.setForeground(Color.ORANGE);
		lblIdo.setFont(new Font("Bauhaus 93", Font.PLAIN, 24));
		lblIdo.setBounds(10, 11, 90, 36);
		frame.getContentPane().add(lblIdo);
		
		JLabel lblMissingThis = new JLabel("Missing This?");
		lblMissingThis.setBounds(412, 16, 97, 14);
		frame.getContentPane().add(lblMissingThis);
		
		JLabel lblGeneralTask = new JLabel("General Task");
		lblGeneralTask.setBounds(412, 204, 97, 14);
		frame.getContentPane().add(lblGeneralTask);
		
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 99, 71));
		textArea.setBounds(406, 11, 168, 182);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBackground(new Color(30, 144, 255));
		textArea_1.setBounds(406, 198, 168, 208);
		frame.getContentPane().add(textArea_1);
		
		final JTextArea date1 = new JTextArea();
		date1.setBackground(new Color(255, 215, 0));
		date1.setLineWrap(true);
		date1.setBounds(20, 59, 123, 114);
		frame.getContentPane().add(date1);
		
		JTextArea date2 = new JTextArea();
		date2.setBounds(147, 59, 123, 114);
		frame.getContentPane().add(date2);
		
		JTextArea date3 = new JTextArea();
		date3.setBackground(new Color(255, 215, 0));
		date3.setBounds(273, 59, 123, 114);
		frame.getContentPane().add(date3);
		
		commandBox = new JTextField();
		commandBox.addActionListener(new ActionListener() {
			private BufferedWriter bw;

			public void actionPerformed(ActionEvent arg0) {
				String input = commandBox.getText();
		        String arrString[] = input.split(" ",2);
		        String command = arrString[0];
		        String theRest = arrString[1];
		        
		        switch (command) {
		            case "start":
					try {
						displayPost1();break;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		                
		            case "edit":
		                String arrString2[] = theRest.split(" ",3);
		                String date = arrString2[0];
		                String number = arrString2[1];
		                String modification = arrString2[2];
					try {
						editTask(date,number,modification);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}   break;
		            
		            
		        }
			}

			private void editTask(String date, String number,
					String modification) throws IOException {
				// TODO Auto-generated method stub
				 ArrayList<String> currDateTask;
			        currDateTask = new ArrayList<>();
			        //fileName=setFileName(date);
			        //read the content of the file, put in the list
			       BufferedReader br = null;
					try {
						String curr;
						br = new BufferedReader(new FileReader(fileName));
						while ((curr = br.readLine()) != null) {
							currDateTask.add(curr);
						}
					} catch (IOException ee) {
						ee.printStackTrace();
					}
			                //delete the specific task in the date
			                int position = Integer.parseInt(number);
			                String toBeRemoved = currDateTask.get(position-1);
			                currDateTask.remove(toBeRemoved);
			                
			                //insert the modification into the arrayList
			                currDateTask.add(position-1, modification);
			                
			                //clear the file
			                clearFile(fileName);
			                
			                //write to the file
			                writeToFile(currDateTask,fileName);
			                
			                //then display in the UI
			                displayPost1();
			                
			                //set the command box to be empty
			                commandBox.setText("");
			                
			        
			}

			private void writeToFile(ArrayList<String> currDateTask,
					String fileName) throws IOException {
				// TODO Auto-generated method stub
				try {
		            bw = new BufferedWriter(new FileWriter(new File("C:/Users/MadeRaditya/workspace/V0.0/test.txt"), true));
		            for (int i = 0; i < currDateTask.size(); i++) {
		            
		                bw.write(currDateTask.get(i) + "\n");
		            }

				} catch (IOException ee) {
					ee.printStackTrace();
				} finally {
					//close the writer so that it can write to the file
		            bw.close(); 
		        }
		        System.out.println("DONE!");
				
			}

			private void clearFile(String fileName) {
				// TODO Auto-generated method stub
				try {
					File file = new File(fileName);
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("");
					bw.close();
				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}

			private void displayPost1() throws IOException {
				// TODO Auto-generated method stub
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
		        date1.read(reader, this);
			}
		});
		commandBox.setBounds(94, 21, 302, 25);
		frame.getContentPane().add(commandBox);
		commandBox.setColumns(10);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setLineWrap(true);
		textArea_2.setBounds(20, 176, 123, 114);
		frame.getContentPane().add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(273, 176, 123, 114);
		frame.getContentPane().add(textArea_3);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBackground(new Color(255, 215, 0));
		textArea_4.setBounds(147, 176, 123, 114);
		frame.getContentPane().add(textArea_4);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setBackground(new Color(255, 215, 0));
		textArea_5.setLineWrap(true);
		textArea_5.setBounds(20, 292, 123, 114);
		frame.getContentPane().add(textArea_5);
		
		JTextArea textArea_6 = new JTextArea();
		textArea_6.setBackground(new Color(255, 215, 0));
		textArea_6.setBounds(273, 292, 123, 114);
		frame.getContentPane().add(textArea_6);
		
		JTextArea textArea_7 = new JTextArea();
		textArea_7.setBounds(147, 292, 123, 114);
		frame.getContentPane().add(textArea_7);
	}
	
	
}
