package detection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import metadata.PathAnalyzer;

public class FaceDetection{
	
	private static final String TITLE = "Face Detection and Tracking";
	private static final String LABEL_TEXT = "Search path";
	private static final String CBOX_TEXT = "Include subdirectories";
	private static final String BTTN_TEXT1 = "Search";
	private static final String BTTN_TEXT2 = "Clear";
	
	private static final int TEXTBOX_COLUMNS = 50;
	private static final int TEXTAREA_ROWS = 28;
	private static final int TEXTAREA_COLUMNS = 100;
	private static final Font TEXTAREA_FONT = new Font("Consolas", Font.PLAIN, 12);
	
	private static JFrame frame;
	private static JPanel panel, panel_one, panel_two, panel_three, panel_container;
	private static JLabel label;
	private static JTextField textField;
	private static JCheckBox checkBox;
	private static JTextArea textArea;
	private static JScrollPane scrollPane;
	private static JButton button, submitBttn;
	
	private static PathAnalyzer analyzer;
	
	public static JTextArea getTextArea(){
		return textArea;
	}
	
	public static void main(String[] args){
		initialize();
	}
	
	/**
	 * Initialize the awt components of the window application.
	 */
	private static void initialize(){
		analyzer = new PathAnalyzer();
		
		frame = new JFrame(TITLE);
		frame.setResizable(false);
		frame.setBounds(300, 100, 800, 600);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel = new JPanel();
		panel_one = new JPanel();
		panel_two = new JPanel();
		panel_three = new JPanel();
		panel_container = new JPanel();
		panel_container.setLayout(new BoxLayout(panel_container, BoxLayout.PAGE_AXIS));
		
		label = new JLabel(LABEL_TEXT);
		textField = new JTextField();
		checkBox = new JCheckBox(CBOX_TEXT);
		textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
		scrollPane = new JScrollPane(textArea);        
		button = new JButton(BTTN_TEXT1);
		submitBttn = new JButton(BTTN_TEXT2);
		
		textArea.setEditable(false);
		textField.setColumns(TEXTBOX_COLUMNS);
		textArea.setFont(TEXTAREA_FONT);

		button.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent mev){
				analyzer.getPath(textField.getText());
				if (checkBox.isSelected()){
					// ricerca ricorsiva
				}
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
		});
		
		submitBttn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent mev){
				textArea.setText("");
			}
		});
		
		frame.getContentPane().add(panel_container);
		panel_container.add(panel);
		panel_container.add(panel_one);
		frame.getContentPane().add(panel_two);
		frame.getContentPane().add(panel_three);
		panel.add(label);
		panel.add(textField);
		panel_one.add(checkBox);
		panel_one.add(button);
		panel_two.add(scrollPane);
		panel_three.add(submitBttn);
	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}