package userWindow;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import database.InsertData;
import database.SelectData;
import logic.Users;

import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class SingUp {

	protected Shell shell;
	private Text text;
	public String name;
	public int wordAmount;
	public int levelId;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SingUp window = new SingUp();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	public void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Kalbos dezute - Registruotis");

		Label lblNewLabel = new Label(shell, SWT.WRAP | SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel.setBounds(68, 21, 188, 49);
		lblNewLabel.setText("Naujas vartotojas");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(41, 77, 70, 20);
		lblNewLabel_1.setText("Vardas");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(173, 76, 78, 26);

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel_2.setBounds(41, 149, 150, 20);
		lblNewLabel_2.setText("Naujų žodžių skaičius");

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel_3.setBounds(41, 196, 108, 20);
		lblNewLabel_3.setText("Žodžių grupė");

		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMinimum(1);
		spinner.setBounds(254, 149, 59, 26);

		Combo combo = new Combo(shell, SWT.NONE);

		combo.setItems(new String[] { "I Grupė", "II Grupė", "III Grupė" });
		combo.setBounds(234, 196, 97, 28);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				InsertData insertNewUser = new InsertData();
				String name = text.getText();
				int wordAmount = Integer.parseInt(spinner.getText());

				SelectData sd = new SelectData();
				String groupName = combo.getText();
				int levelId = sd.selectWordGroup(groupName);

				insertNewUser.insertUserToDB(name, wordAmount, levelId);

				Users user = new Users(name, wordAmount, levelId);

				Boxes.all.getUser().add(user);

				shell.close();
				Boxes box01 = new Boxes();
				box01.open();
			}
		});
		btnNewButton.setBounds(318, 21, 90, 30);
		btnNewButton.setText("Pradėti");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				shell.close();
				StartProgram startProgram = new StartProgram();
				startProgram.open();
			}
		});
		btnNewButton_1.setBounds(10, 221, 90, 30);
		btnNewButton_1.setText("Pagrindinis");

	}
}
