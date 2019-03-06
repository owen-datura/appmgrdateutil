package io.datura.java.work.util.amdateutility;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class App {
	private static JTextField amDateField = null;
	private static JTextField gregDateField = null;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					createAndShowGui();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void createAndShowGui() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("AM Date Util");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		frame.setContentPane(content);

		/* Create a space for the fields. */
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		fieldPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		Font labelFont = new Font(Font.DIALOG, Font.BOLD, 14);
		Font fieldFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);

		JLabel amDateFieldLabel = new JLabel("Application Manager Date");
		amDateFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		amDateFieldLabel.setFont(labelFont);
		amDateFieldLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		fieldPanel.add(amDateFieldLabel);

		amDateField = new JTextField(8);
		amDateField.setAlignmentX(Component.CENTER_ALIGNMENT);
		amDateField.setFont(fieldFont);
		amDateField.setBorder(new EmptyBorder(5, 5, 5, 5));
		amDateField.setMaximumSize(amDateField.getPreferredSize());
		amDateField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// when focus is given to the app mgr field,
				// we'll clear the gregorian date
				gregDateField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		fieldPanel.add(amDateField);

		JLabel gregDateFieldLabel = new JLabel("Gregorian Date");
		gregDateFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gregDateFieldLabel.setFont(labelFont);
		gregDateFieldLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		fieldPanel.add(gregDateFieldLabel);

		gregDateField = new JTextField(8);
		gregDateField.setAlignmentX(Component.CENTER_ALIGNMENT);
		gregDateField.setFont(fieldFont);
		gregDateField.setBorder(new EmptyBorder(5, 5, 5, 5));
		gregDateField.setMaximumSize(gregDateField.getPreferredSize());
		gregDateField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				amDateField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		fieldPanel.add(gregDateField);

		content.add(fieldPanel);

		/* Create space for the buttons. */
		JPanel buttonPanel = new JPanel();

		Font buttonFont = new Font(Font.DIALOG, Font.PLAIN, 14);

		JButton calcTodayBtn = new JButton("Today");
		calcTodayBtn.setFont(buttonFont);
		calcTodayBtn.addActionListener(e -> {
			todayBtnClicked(e);
		});
		buttonPanel.add(calcTodayBtn);
		JButton calcEnteredDateBtn = new JButton("Calculate");
		calcEnteredDateBtn.setFont(buttonFont);
		calcEnteredDateBtn.addActionListener(e -> {
			calcBtnClicked(e);
		});
		buttonPanel.add(calcEnteredDateBtn);

		content.add(buttonPanel);

		/* Enable the main content window. */
		frame.setPreferredSize(new Dimension(300, 250));
		frame.pack();
		frame.setVisible(true);
	}

	private static void calcBtnClicked(ActionEvent e) {
		// TODO validation steps should be taken before trying to
		// parse these values
		if (fieldHasValue(amDateField)) {
			Long tam = Long.parseLong(amDateField.getText());
			Optional<LocalDate> tld = AppMgrDateUtil.fromAMDate(tam);
			if (tld.isPresent())
				gregDateField.setText(AppMgrDateUtil.formatDateForUI(tld.get()));
			else
				clearTextFields();
		} else if (fieldHasValue(gregDateField)) {
			Optional<LocalDate> tgd = AppMgrDateUtil.tryparse(gregDateField.getText());
			if (tgd.isPresent()) {
				LocalDate tld = tgd.get();
				gregDateField.setText(AppMgrDateUtil.formatDateForUI(tld));
				amDateField.setText(AppMgrDateUtil.toAMDate(tld).get().toString());
			} else
				clearTextFields();
		}
	}

	private static boolean fieldHasValue(JTextField f) {
		String v = f.getText();
		return v != null && v.trim().length() > 0;
	}

	private static void clearTextFields() {
		amDateField.setText("");
		gregDateField.setText("");
	}

	private static void todayBtnClicked(ActionEvent e) {
		LocalDate today = LocalDate.now();
		Optional<Number> on = AppMgrDateUtil.toAMDate(today);
		Number amToday = on.get();
		amDateField.setText(amToday.toString());
		gregDateField.setText(AppMgrDateUtil.formatDateForUI(today));
	}
}
