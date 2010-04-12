package planetIce.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
    JFrame frame;
    JTextArea chatArea;
    JTextArea userArea;
    JTextField textField;
    JScrollPane chatScrollPane;
    JScrollPane userScrollPane;

    
    public GUI() {
	initGUI();
    }
    
    public void initGUI() {
	frame = new JFrame();

	frame.setPreferredSize(new Dimension(400, 300));

	chatArea = new JTextArea("");
	chatArea.setEditable(false);
	userArea = new JTextArea("");
	chatScrollPane = new JScrollPane(chatArea);
	userScrollPane = new JScrollPane(userArea);
	userScrollPane.setPreferredSize(new Dimension(120, frame.getHeight()));

	textField = new JTextField("");

	JPanel chatPanel = new JPanel();
	chatPanel.setLayout(new BorderLayout());
	chatPanel.add(chatScrollPane, BorderLayout.CENTER);
	chatPanel.add(textField, BorderLayout.SOUTH);

	frame.setLayout(new BorderLayout());

	frame.add(chatPanel, BorderLayout.CENTER);
	frame.add(userScrollPane, BorderLayout.EAST);
	frame.pack();
	frame.setVisible(true);

	textField.addActionListener(new actionListenerGUI());
    }

}
