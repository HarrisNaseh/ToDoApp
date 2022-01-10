package toDoApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.junit.runner.manipulation.Sortable;

public class ToDoWindow implements ActionListener {
    private JFrame frame;
    private JButton button, submit, update;
    private JLabel label;
    private JTextField text;
    private JPanel topPanel, bottomPanel, centerPanel, topRightPanel,
        topLeftPanel;
    private DoublyLinkedList<JCheckBox> jList;
    private ArrayList<Message> list;
    private boolean darkMode = true;
    private JButton clear;
    private Color dark, light;
    private Font newFont, defaultFont, checkBoxFont;

    public ToDoWindow() {
        frame = new JFrame("To-Do App");
        frame.setLayout(new BorderLayout());

        Font font = new Font("Times New Roman", Font.PLAIN, 16);
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.STRIKETHROUGH,
            TextAttribute.STRIKETHROUGH_ON);
        newFont = new Font(attributes);

        defaultFont = new Font("Times New Roman", Font.PLAIN, 13);
        checkBoxFont = new Font("Times New Roman", Font.PLAIN, 16);
        

        dark = new Color(20, 4, 0);
        light = new Color(240, 230, 225);

        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100, 100));
        topPanel.setLayout(new BorderLayout());

        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(100, 50));
        bottomPanel.setLayout(new FlowLayout());

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        topRightPanel = new JPanel();
        topRightPanel.setLayout(new FlowLayout());
        topRightPanel.setBackground(light);

        topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new FlowLayout());
        topLeftPanel.setBackground(light);

        label = new JLabel("To-Do List");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(100, 100, 50, 50);
        label.setFont(defaultFont);

        text = new JTextField();
        text.setPreferredSize(new Dimension(150, 32));

        submit = new JButton("Submit");
        submit.setBackground(light);
        submit.setFont(defaultFont);
        submit.setFocusable(false);
        submit.addActionListener(this);

        button = new JButton("Dark-Mode");
        button.setFocusable(false);
        button.setBounds(0, 0, 100, 30);
        button.setBackground(light);
        button.addActionListener(this);
        button.setFont(defaultFont);

        update = new JButton("Update");
        update.setFocusable(false);
        update.addActionListener(this);
        update.setBackground(light);
        update.setFont(defaultFont);

        clear = new JButton("Clear");
        clear.addActionListener(this);
        clear.setBounds(new Rectangle(72, 30));
        clear.setBackground(light);
        clear.setFont(defaultFont);
        clear.setFocusable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width / 3, screenSize.width / 3);
        int y = (screenSize.height - frame.getHeight()) / 2;
        int x = (screenSize.width - frame.getWidth()) / 2;

        topLeftPanel.setPreferredSize(new Dimension(button.getWidth(), 15));
        topPanel.add(topLeftPanel, BorderLayout.WEST);
        topRightPanel.setPreferredSize(new Dimension(clear.getWidth(), 15));
        topPanel.add(topRightPanel, BorderLayout.EAST);

        label.setForeground(dark);
        frame.getContentPane().setBackground(light);
        frame.setLocation(x, y);
        // frame.setResizable(false);

        topPanel.setBackground(light);
        bottomPanel.setBackground(light);
        centerPanel.setBackground(light);
        frame.add(topPanel, BorderLayout.NORTH);
        topPanel.add(bottomPanel, BorderLayout.SOUTH);
        topLeftPanel.add(button);
        // topPanel.add(clear, BorderLayout.EAST);

        frame.add(centerPanel, BorderLayout.CENTER);
        topPanel.add(label);

        bottomPanel.add(text);
        bottomPanel.add(submit);
        topRightPanel.add(clear);
        bottomPanel.add(update);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("dd0.jpg");
        frame.setIconImage(image.getImage());
        // frame.getContentPane().setBackground(dark);

        // label.setForeground(dark);
        jList = new DoublyLinkedList<JCheckBox>();
        list = new ArrayList<Message>();

        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button && button.getText().equals("Light-Mode")) {
            darkMode();

        }
        else if (e.getSource() == button && button.getText().equals(
            "Dark-Mode")) {
            lightMode();

        }

        if (e.getSource() == submit) {
            submit();

        }

        if (e.getSource() == clear) {
            clear();
        }

        if (e.getSource() == update) {
            update();
        }

    }


    private void update() {
        for (int i = 0; i < jList.size(); i++) {
            JCheckBox box = jList.get(i);
            if (box.isSelected()) {
                box.setFont(newFont);
            }
            else {
                box.setFont(checkBoxFont);
            }
        }
    }


    private void clear() {
        for (int i = 0; i < jList.size(); i++) {
            centerPanel.remove(jList.get(i));
        }
        centerPanel.revalidate();
        centerPanel.repaint();
        jList.clear();
        list.clear();

    }


    private void lightMode() {
        frame.getContentPane().setBackground(dark);
        label.setForeground(light);
        button.setForeground(dark);
        button.setText("Light-Mode");
        button.setBackground(light);
        topPanel.setBackground(dark);
        bottomPanel.setBackground(dark);
        centerPanel.setBackground(dark);
        topRightPanel.setBackground(dark);
        topLeftPanel.setBackground(dark);
        if (jList.size() > 0) {
            for (int i = 0; i < jList.size(); i++) {
                jList.get(i).setForeground(light);
                jList.get(i).setBackground(dark);
            }
        }
        darkMode = true;

    }


    private void darkMode() {

        frame.getContentPane().setBackground(light);
        label.setForeground(dark);
        button.setText("Dark-Mode");
        button.setForeground(light);
        button.setBackground(dark);
        topPanel.setBackground(light);
        bottomPanel.setBackground(light);
        centerPanel.setBackground(light);
        topRightPanel.setBackground(light);
        topLeftPanel.setBackground(light);
        if (jList.size() > 0) {
            for (int i = 0; i < jList.size(); i++) {
                jList.get(i).setForeground(dark);
                jList.get(i).setBackground(light);
            }
        }
        darkMode = false;

    }


    private void submit() {
        if (!text.getText().equals("")) {
            Message addMessage = new Message(text.getText());
            list.add(addMessage);
            JCheckBox newLabel = new JCheckBox(addMessage.toString());
            text.setText("");
            newLabel.setAlignmentX(0.5f);
            if (darkMode) {
                newLabel.setForeground(light);
                newLabel.setBackground(dark);
            }
            else {
                newLabel.setForeground(dark);
                newLabel.setBackground(light);
            }
            newLabel.setFocusable(false);
            newLabel.setFont(checkBoxFont);
            centerPanel.add(newLabel);
            jList.add(newLabel);
            frame.setVisible(true);
        }
    }

}
