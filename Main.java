import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Main implements ActionListener {
	
	private GuestList a = new GuestList();
	private  Scanner sc = new Scanner(System.in);
	private JFrame frame;
	private JPanel panel;
	private JButton button;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button10;
	private JButton button11;

	
	public Main () {
		
		frame = new JFrame();
		panel = new JPanel();
		
		frame.setSize(300,300);
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10 ,30));
		panel.setLayout(new GridLayout(11, 1));
		
		button = new JButton("Create List");
		button1 = new JButton("Add");
		button2 = new JButton("Check");
		button3 = new JButton("Remove");
		button4 = new JButton("Update");
		button5 = new JButton("Guests");
		button6 = new JButton("Waitlist");
		button7 = new JButton("Available");
		button8 = new JButton("Guests Number");
		button9 = new JButton("Waitlist Number");
		button10 = new JButton("Subscribe Number");
		button11 = new JButton("Search");

		button.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
		button10.addActionListener(this);
		button11.addActionListener(this);
	
		button1.setEnabled(false);
		button2.setEnabled(false);
		button3.setEnabled(false);
		button4.setEnabled(false);
		button5.setEnabled(false);
		button6.setEnabled(false);
		button7.setEnabled(false);
		button8.setEnabled(false);
		button9.setEnabled(false);
		button10.setEnabled(false);
		button11.setEnabled(false);
		
		panel.add(button);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		panel.add(button7);
		panel.add(button8);
		panel.add(button9);
		panel.add(button10);
		panel.add(button11);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Guest List App");
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public static void main (String [] args) {	
		new Main();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button) {
			
			int maxGuests = 10;
			
			System.out.println("What should be the number of guests ?");
			
			try {
				  maxGuests = sc.nextInt();
				}
				catch(Exception ex) {
					System.out.println("Are you sure that is a number ?");
				}
			
			a = new GuestList(maxGuests);
			
			button.setEnabled(false);
			button1.setEnabled(true);
			button2.setEnabled(true);
			button3.setEnabled(true);
			button4.setEnabled(true);
			button5.setEnabled(true);
			button6.setEnabled(true);
			button7.setEnabled(true);
			button8.setEnabled(true);
			button9.setEnabled(true);
			button10.setEnabled(true);
			button11.setEnabled(true);
		}
			
		if(e.getSource() == button1)
			a.addParticipant();
			
		
		if(e.getSource() == button2) {
			a.searchParticipant();
		}
		
		if(e.getSource() == button3) {
			a.deleteParticipant();
		}
		
		if(e.getSource() == button4) {
			a.updateParticipant();
		}
		
		if(e.getSource() == button5) {
			a.showGuestList();
		}
		
		if(e.getSource() == button6) {
			a.showWaitGuestList();
		}
		
		if(e.getSource() == button7) {
			a.spaceLeftOnGuestList();
		}
		
		if(e.getSource() == button8) {
			a.participantOnGuestList();
		}
		
		if(e.getSource() == button9) {
			a.participantOnWaitGuestList();
		}
		
		if(e.getSource() == button10) {
			a.allParticipants();
		}
		
		if(e.getSource() == button11) {
			a.showSearchInList();
		}


			
			
		
	}

}
