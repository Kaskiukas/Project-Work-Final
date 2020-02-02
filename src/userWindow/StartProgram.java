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
		fr.isDBFile(); // tikrina ar yra DB ir esant poreikiui sukuria, nuskaito faila, sudeda zodzius i DB

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

		Label title = new Label(shell, SWT.WRAP | SWT.CENTER);
		title.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		title.setBounds(70, 32, 300, 65);
		title.setText("Anglų kalbos žodžių mokymosi programa");

		Button join = new Button(shell, SWT.NONE);
		join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
				LogIn logIn = new LogIn();
				logIn.open();
			}
		});
		join.setBounds(58, 115, 90, 30);
		join.setText("Prisijungti");

		Button register = new Button(shell, SWT.NONE);
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
				SingUp singUp = new SingUp();
				singUp.open();
			}
		});
		register.setBounds(256, 115, 90, 30);
		register.setText("Registruotis");

		Button statistics = new Button(shell, SWT.NONE);
		statistics.setBounds(256, 191, 90, 30);
		statistics.setText("Statistika");
		statistics.setVisible(false);

		Button rules = new Button(shell, SWT.NONE);
		rules.setBounds(58, 191, 90, 30);
		rules.setText("Taisyklės");
		rules.setVisible(false);

	}
}
