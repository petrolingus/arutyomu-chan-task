package me.arutyomu.act;

import me.arutyomu.act.util.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Task2 extends JDialog {
	
	private final Map<Key, DataStructure<String>> dataStructureMap = new HashMap<>() {{
		put(new Key(DataStructureType.QUEUE, ImplementationType.CUSTOM), new CustomQueue<>());
		put(new Key(DataStructureType.QUEUE, ImplementationType.JDK), new JdkQueue<>());
		put(new Key(DataStructureType.STACK, ImplementationType.CUSTOM), new CustomStack<>());
		put(new Key(DataStructureType.STACK, ImplementationType.JDK), new JdkStack<>());
	}};
	
	private JPanel contentPane;
	private JButton chooseDirectoryButton;
	private JLabel listDataLabel;
	private JButton searchButton;
	private JRadioButton queueRadioButton;
	private JRadioButton stackRadioButton;
	private JRadioButton customRadioButton;
	private JRadioButton JDKRadioButton;
	private JTextField extensionTextField;
	private JList<String> foundFilesList;
	private JLabel chosenSearchDirectoryLabel;
	private JLabel modifiedListDataLabel;
	
	private final MyList<Integer> list = new MyLinkedList<>();
	
	private final DataStructureType[] dataStructureType = new DataStructureType[]{DataStructureType.QUEUE};
	private final ImplementationType[] implementationType = new ImplementationType[]{ImplementationType.JDK};
	
	@SuppressWarnings("ConstantConditions")
	public Task2() {
		setContentPane(contentPane);
		setModal(true);
		
		String currentDirectoryPath = System.getProperty("user.dir") + "/resources";
		chosenSearchDirectoryLabel.setText(currentDirectoryPath);
		
		chooseDirectoryButton.addActionListener(e -> {
			
			JFileChooser fileChooser = new JFileChooser(currentDirectoryPath);
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			fileChooser.showOpenDialog(Task2.this);
			File file = fileChooser.getSelectedFile();
			
			if (file!= null) {
				chosenSearchDirectoryLabel.setText(file.toString());
			}

		});
		
		queueRadioButton.addActionListener(__ -> dataStructureType[0] = DataStructureType.QUEUE);
		stackRadioButton.addActionListener(__ -> dataStructureType[0] = DataStructureType.STACK);
		customRadioButton.addActionListener(__ -> implementationType[0] = ImplementationType.CUSTOM);
		JDKRadioButton.addActionListener(__ -> implementationType[0] = ImplementationType.JDK);
		
		searchButton.addActionListener(__ -> {
			
			List<String> foundFiles = new ArrayList<>();
			
			DataStructure<String> dataStructure = dataStructureMap.get(new Key(dataStructureType[0], implementationType[0]));
			
			dataStructure.push(chosenSearchDirectoryLabel.getText());
			
			while (!dataStructure.isEmpty()) {
				
				File file = new File(dataStructure.pop());
				
				if (file.isDirectory()) {
					for (final File subfile : file.listFiles()) {
						dataStructure.push(subfile.toString());
					}
					System.out.println("Found dir: " + file);
				} else {
					final String fileName = file.getName();
					if (fileName.endsWith(extensionTextField.getText())) {
						foundFiles.add(fileName);
						System.out.println("\t\tFound filtered file: " + file);
					}else {
						System.out.println("\tFound file: " + file);
					}
				}
				
			}
			
			foundFilesList.setListData(foundFiles.toArray(String[]::new));
			
		});
		
		// Closed application when pressed X button
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});
		
		// Close application when pressed ESC
		contentPane.registerKeyboardAction(
				e -> onCancel(),
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
		);
	}
	
	private void onCancel() {
		dispose();
	}
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Task2 dialog = new Task2();
		dialog.pack();
		dialog.setTitle("Task 2");
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	enum DataStructureType {QUEUE, STACK}
	
	enum ImplementationType {CUSTOM, JDK}
	
	record Key(DataStructureType dataStructureType, ImplementationType implementationType) {}
	
}
