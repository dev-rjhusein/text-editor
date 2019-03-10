package editor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import static java.lang.System.*;

public class Editor extends JFrame{

	private JTextArea textArea = new JTextArea(20, 60);
	private JFileChooser fc = new JFileChooser();

	public Editor(){
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		FileFilter txtFilter = new FileNameExtensionFilter("PlainText", "txt"); //add java
		fc.setFileFilter(txtFilter);

		add(scrollPane);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu file = new JMenu("File");
		menuBar.add(file);

		file.add(Open);
		file.add(Save);
		file.addSeparator();
		file.add(Exit);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	Action Open = new AbstractAction("Open File"){
		@Override
		public void actionPerformed(ActionEvent e){
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				openFile(fc.getSelectedFile().getAbsolutePath());
	}};

	Action Save = new AbstractAction("Save File"){
		@Override
		public void actionPerformed(ActionEvent e){
			saveFile();	
	}};

	Action Exit = new AbstractAction("Exit"){
		@Override
		public void actionPerformed(ActionEvent e){
		 exit(0);
	}};


	public void openFile(String fileDir){
		FileReader fr;
		try{
			fr = new FileReader(fileDir);
			textArea.read(fr, null);
			fr.close();
			setTitle(fileDir);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void saveFile(){
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			FileWriter fw;
			try{
				fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
				textArea.write(fw);
				fw.close();
			} catch (IOException e){
				e.printStackTrace();
			}
	}}

	public static void main(String[] args){
		new Editor();
	}
}
