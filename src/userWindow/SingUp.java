package userWindow;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import database.InsertData;
import database.SelectData;
import logic.Moving;

import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class SingUp {

	protected Shell shell;
	private Text text;

	// Launch the application.
	public static void main(String[] args) {
		try {
			SingUp window = new SingUp();
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
	public void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Kalbos dežutė - Registruotis");

		Label newUserTitle = new Label(shell, SWT.WRAP | SWT.CENTER);
		newUserTitle.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		newUserTitle.setBounds(68, 21, 188, 49);
		newUserTitle.setText("Naujas vartotojas");

		Label newUserNameTitle = new Label(shell, SWT.NONE);
		newUserNameTitle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		newUserNameTitle.setBounds(41, 77, 70, 20);
		newUserNameTitle.setText("Vardas");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(173, 76, 140, 26);

		Label newWordsQuantityTitle = new Label(shell, SWT.NONE);
		newWordsQuantityTitle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		newWordsQuantityTitle.setBounds(41, 129, 150, 20);
		newWordsQuantityTitle.setText("Naujų žodžių skaičius");

		Label wordsGroupTitle = new Label(shell, SWT.NONE);
		wordsGroupTitle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		wordsGroupTitle.setBounds(41, 176, 108, 20);
		wordsGroupTitle.setText("Žodžių grupė");

		Label existingUserErrorTitle = new Label(shell, SWT.NONE);
		existingUserErrorTitle.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		existingUserErrorTitle.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		existingUserErrorTitle.setBounds(173, 108, 241, 15);
		existingUserErrorTitle.setText("Toks vartotojo vardas jau sukurtas !");
		existingUserErrorTitle.setVisible(false);

		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMinimum(1);
		spinner.setBounds(254, 129, 59, 26);

		Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] { "I Grupė", "II Grupė", "III Grupė" });
		combo.setBounds(234, 176, 97, 28);

		Button start = new Button(shell, SWT.NONE);
		start.setBounds(334, 221, 90, 30);
		start.setText("Pradėti");
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				SelectData sd = new SelectData();
				boolean isUser = sd.searchUsername(text.getText()); // iesko DB ar toks vartotojo vardas yra
				if (isUser) {
					existingUserErrorTitle.setVisible(true);
				} else {

					String name = text.getText();
					int wordAmount = Integer.parseInt(spinner.getText());
					String groupName = combo.getText();
					int groupId = sd.selectWordGroup(groupName); // suranda zodziu grupes id

					InsertData id = new InsertData();
					id.insertUserToDB(name, wordAmount, groupId); // iraso vartotoja i DB

					sd.selectUser(Boxes.all.getUser(), name); // sukuria vartotojo objekta (OBJ) ir ideda i lista
					int userId = Boxes.all.getUser().get(0).getUserId();

					sd.selectWordsForStart(Boxes.all.getUserList(), groupId); // sudedami zodziai i userlista

					Moving moving = new Moving();
					moving.autoSave(Boxes.all.getUserList(), userId, 0); // issaugoja vartotojo pasirinktus zodziu DB

					shell.close();
					Boxes boxes = new Boxes();
					boxes.open();
				}
			}
		});

		Button mainWindow = new Button(shell, SWT.NONE);
		mainWindow.setBounds(10, 221, 90, 30);
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
