package userWindow;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class LogIn {

	protected Shell shlKalbosDezute;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogIn window = new LogIn();
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
		shlKalbosDezute.open();
		shlKalbosDezute.layout();
		while (!shlKalbosDezute.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlKalbosDezute = new Shell();
		shlKalbosDezute.setSize(450, 300);
		shlKalbosDezute.setText("Kalbos dežutė - Prisijungti");

		Label lblNewLabel = new Label(shlKalbosDezute, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel.setBounds(88, 91, 70, 20);
		lblNewLabel.setText("Vardas");

		text = new Text(shlKalbosDezute, SWT.BORDER);
		text.setBounds(278, 137, 78, 26);
		text.setVisible(false);

		Label lblNewLabel_1 = new Label(shlKalbosDezute, SWT.NONE);
		lblNewLabel_1.setBounds(278, 93, 96, 20);
		lblNewLabel_1.setText("Slaptažodis");
		lblNewLabel_1.setVisible(false);

		Button btnNewButton = new Button(shlKalbosDezute, SWT.NONE);
		btnNewButton.setBounds(334, 213, 90, 30);
		btnNewButton.setText("Pradėti");

		Combo combo = new Combo(shlKalbosDezute, SWT.NONE);
		combo.setBounds(87, 137, 97, 28);

		Button btnNewButton_1 = new Button(shlKalbosDezute, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shlKalbosDezute.close();
				StartProgram startProgram = new StartProgram();
				startProgram.open();
			}
		});
		btnNewButton_1.setBounds(10, 213, 90, 30);
		btnNewButton_1.setText("Pagrindinis");

		Label lblNewLabel_2 = new Label(shlKalbosDezute, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel_2.setBounds(124, 28, 191, 39);
		lblNewLabel_2.setText("Esamas vartotojas");

	}
}
