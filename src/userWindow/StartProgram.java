package userWindow;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import logic.FileReading;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class StartProgram {

	protected Shell shell;

	// Launch the application.

	public static void main(String[] args) {

		FileReading fr = new FileReading();
		fr.isDBFile();

		try {
			StartProgram window = new StartProgram();
			window.open();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// Open the window.

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

	// Create contents of the window.

	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Kalbos dėžutė");

		Label lblNewLabel = new Label(shell, SWT.WRAP | SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel.setBounds(70, 32, 300, 65);
		lblNewLabel.setText("Anglų kalbos žodžių mokymosi programa");

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
				LogIn logIn = new LogIn();
				logIn.open();
			}
		});
		btnNewButton.setBounds(58, 115, 90, 30);
		btnNewButton.setText("Prisijungti");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
				SingUp singUp = new SingUp();
				singUp.open();
			}
		});
		btnNewButton_1.setBounds(256, 115, 90, 30);
		btnNewButton_1.setText("Registruotis");

		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setBounds(256, 191, 90, 30);
		btnNewButton_2.setText("Statistika");
		btnNewButton_2.setVisible(false);

		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.setBounds(58, 191, 90, 30);
		btnNewButton_3.setText("Taisykles");
		btnNewButton_3.setVisible(false);

	}
}
