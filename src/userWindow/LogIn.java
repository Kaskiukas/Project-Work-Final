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

import database.SelectData;
import logic.AllLists;

public class LogIn {

	protected Shell shell;
	private Text text;

	// Launch the application.
	public static void main(String[] args) {
		try {
			LogIn window = new LogIn();
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
		shell.setText("Kalbos dežutė - Prisijungti");

		Label title = new Label(shell, SWT.NONE);
		title.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		title.setBounds(124, 28, 191, 39);
		title.setText("Esamas vartotojas");

		Label userNameTitle = new Label(shell, SWT.NONE);
		userNameTitle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		userNameTitle.setBounds(88, 91, 70, 20);
		userNameTitle.setText("Vardas");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(278, 137, 78, 26);
		text.setVisible(false);

		Label passwordTitle = new Label(shell, SWT.NONE); // nenaudojamas
		passwordTitle.setBounds(278, 93, 96, 20);
		passwordTitle.setText("Slaptažodis");
		passwordTitle.setVisible(false);

		Label selectUserErrorTitle = new Label(shell, SWT.NONE);
		selectUserErrorTitle.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		selectUserErrorTitle.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		selectUserErrorTitle.setBounds(88, 172, 191, 15);
		selectUserErrorTitle.setText("Jūs nepasirinkote vartotojo vardo");
		selectUserErrorTitle.setVisible(false);

		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(87, 137, 97, 28);

		AllLists al = new AllLists();
		SelectData sd = new SelectData();

		sd.allUsersList(al.getAllUsers()); // sudeta vartotoju vardus i lista

		for (String UserName : al.getAllUsers()) {
			combo.add(UserName);
		}

		Button start = new Button(shell, SWT.NONE);
		start.setBounds(334, 213, 90, 30);
		start.setText("Pradėti");
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				if (!combo.getText().equalsIgnoreCase("")) {

					sd.selectUser(Boxes.all.getUser(), combo.getText()); // sukuria vartotojo objekta (OBJ) ir ideda i lista
					int userId = Boxes.all.getUser().get(0).getUserId();

					sd.selectWordsOfUser(userId, Boxes.all.getUserList(), 0); // uzkrauna zodzius i lista po autosave
					sd.selectWordsOfUser(userId, Boxes.all.getMovingDown(), 1);
					sd.selectWordsOfUser(userId, Boxes.all.getBox2(), 2);
					sd.selectWordsOfUser(userId, Boxes.all.getBox3(), 3);
					sd.selectWordsOfUser(userId, Boxes.all.getBox4(), 4);
					sd.selectWordsOfUser(userId, Boxes.all.getBox5(), 5);
					sd.selectWordsOfUser(userId, Boxes.all.getBox6(), 6);

					shell.close();
					Boxes boxes = new Boxes();
					boxes.open();
					selectUserErrorTitle.setVisible(false);

				} else {
					selectUserErrorTitle.setVisible(true);
				}
			}
		});

		Button mainWindow = new Button(shell, SWT.NONE);
		mainWindow.setBounds(10, 213, 90, 30);
		mainWindow.setText("Pagrindinis");
		mainWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				shell.close();
				StartProgram startProgram = new StartProgram();
				startProgram.open();
			}
		});

	}

}
