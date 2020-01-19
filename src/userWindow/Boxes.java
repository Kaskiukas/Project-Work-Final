package userWindow;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import database.SelectData;
import logic.AllLists;
import logic.Moving;
import logic.Word;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class Boxes {

	public Shell shell;
	public Text text;
	public static AllLists all = new AllLists(); // sukuriami visi reikalingi listai
	public static SelectData sd = new SelectData();
	public static Moving moving = new Moving();
	public static SingUp user = new SingUp();
	public static Word chekingWordOBJ;
	public String chekingWord;
	public String answerWord;
	public int boxNr;
	public int primaryULsize;
	public int leftToLearn;
	public int leftInBox;
	public int learned;
	public String userName;
	public int newWordQuantity;
	public int wordGroupId;
	public ArrayList<Word> currentBox;
	public ArrayList<Word> currentMoveBox;
	public int color;

	// Launch the application.

	public static void main(String[] args) {

		try {
			Boxes window = new Boxes();
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
		shell.setSize(592, 463);
		shell.setText("Kalbos dežutė");

		userName = all.getUser().get(0).getName();
		newWordQuantity = all.getUser().get(0).getWordAmount();
		wordGroupId = all.getUser().get(0).getWordLevel();

		currentBox = all.getBox1();
		currentMoveBox = all.getMovingUp2();

		sd.selectWordsForStart(all.getUserList(), wordGroupId); // sudedami zodziai i userlista
		boxNr = 1;
		primaryULsize = all.getUserList().size();
		learned = 0;
		leftToLearn = primaryULsize - learned;

		Button btnNextBox = new Button(shell, SWT.NONE);

		btnNextBox.setBounds(427, 360, 137, 30);
		btnNextBox.setText("Naujas ciklas");
		btnNextBox.setVisible(false);

		Label leftLearnWordValue = new Label(shell, SWT.CENTER);
		leftLearnWordValue.setBounds(95, 60, 50, 25);
		leftLearnWordValue.setText(Integer.toString(leftToLearn)); // likusiu ismokti zodziu kiekis

		moving.updateBox1ByNewWords(all.getUserList(), all.getMovingDown(), all.getBox1(), newWordQuantity);
		leftInBox = all.getBox1().size();

		int index = moving.RandomGenerator(all.getBox1());
		chekingWordOBJ = all.getBox1().remove(index);
		chekingWord = chekingWordOBJ.getWord();

		Label wordTranslationValue = new Label(shell, SWT.NONE);
		wordTranslationValue.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		wordTranslationValue.setBounds(245, 160, 260, 30);
		wordTranslationValue.setText(chekingWordOBJ.getTranslation());

		text = new Text(shell, SWT.BORDER | SWT.CENTER);
		text.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		text.setBounds(235, 200, 150, 30);

		Label lableIsTo = new Label(shell, SWT.NONE);
		lableIsTo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lableIsTo.setBounds(200, 200, 30, 30);
		lableIsTo.setText("to");
		lableIsTo.setVisible(false);

		if (chekingWordOBJ.getWithTo() == 1) {
			lableIsTo.setVisible(true);
		}

		Button btnMainWindow = new Button(shell, SWT.NONE);
		btnMainWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				shell.close();
				StartProgram startProgram = new StartProgram();
				startProgram.open();
			}
		});
		btnMainWindow.setBounds(25, 360, 90, 30);
		btnMainWindow.setText("Pagrindinis");

		Label lblNewLabel = new Label(shell, SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel.setBounds(137, 310, 280, 85);
		lblNewLabel.setText("Jūs išmokote visus šios grupės žodžius");
		lblNewLabel.setVisible(false);

		Button btnNextWord = new Button(shell, SWT.NONE);
		btnNextWord.setBounds(420, 250, 90, 30);
		btnNextWord.setText("Kitas žodis");
		btnNextWord.setVisible(false);

		Button btnCheckWord = new Button(shell, SWT.NONE);
		btnCheckWord.setBounds(420, 200, 90, 30);
		btnCheckWord.setText("Tikrinti");

		Label lblPhonetic = new Label(shell, SWT.CENTER);
		lblPhonetic.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblPhonetic.setBounds(110, 235, 160, 30);
		lblPhonetic.setText("Tarimas");
		lblPhonetic.setVisible(false);
		lblPhonetic.setText(chekingWordOBJ.getPhonetic());

		Label lblENWord = new Label(shell, SWT.CENTER);
		lblENWord.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblENWord.setBounds(110, 275, 160, 30);
		lblENWord.setText("EN žodis");
		lblENWord.setVisible(false);
		lblENWord.setText(chekingWordOBJ.getWord());

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setBounds(110, 105, 340, 30);
		lblNewLabel_1.setVisible(true);
		lblNewLabel_1.setText("Dėžutės Nr. 1");

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel_2.setBounds(25, 200, 170, 30);
		lblNewLabel_2.setText("Įveskite anglišką žodį:");

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel_3.setBounds(25, 160, 150, 30);
		lblNewLabel_3.setText("Lietuviška reikšmė:");

		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel_4.setBounds(25, 235, 70, 30);
		lblNewLabel_4.setText("Tarimas:");

		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel_5.setBounds(25, 275, 70, 30);
		lblNewLabel_5.setText("Vertimas:");

		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setBounds(80, 30, 90, 25);
		lblNewLabel_6.setText("Liko išmokti:");

		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setBounds(220, 30, 90, 25);
		lblNewLabel_7.setText("Dėžutėje yra:");

		Label lblNewLabel_8 = new Label(shell, SWT.NONE);
		lblNewLabel_8.setBounds(360, 30, 90, 25);
		lblNewLabel_8.setText("Jau išmokote:");

		Label lblNewLabel_9 = new Label(shell, SWT.CENTER);
		lblNewLabel_9.setBounds(380, 60, 50, 25);
		lblNewLabel_9.setText(Integer.toString(learned));

		Label lblNewLabel_10 = new Label(shell, SWT.CENTER);
		lblNewLabel_10.setBounds(240, 60, 50, 25);
		lblNewLabel_10.setText(Integer.toString(leftInBox));

		btnNextWord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				lblPhonetic.setText("");
				lblENWord.setText("");
				text.setText("");
				wordTranslationValue.setText("");
				lblPhonetic.setVisible(false);
				lblENWord.setVisible(false);
				lableIsTo.setVisible(false);

				if (!all.getUserList().isEmpty() || !all.getBox1().isEmpty() || !all.getBox2().isEmpty()
						|| !all.getBox3().isEmpty() || !all.getBox4().isEmpty() || !all.getBox5().isEmpty()
						|| !all.getBox6().isEmpty() || !all.getMovingDown().isEmpty() || !all.getMovingUp2().isEmpty()
						|| !all.getMovingUp3().isEmpty() || !all.getMovingUp4().isEmpty()
						|| !all.getMovingUp5().isEmpty() || !all.getMovingUp6().isEmpty()) {

					if (!all.getBox1().isEmpty()) {
						currentBox = all.getBox1();
						boxNr = 1;
						currentMoveBox = all.getMovingUp2();
					} else {
						if (!all.getBox2().isEmpty()) {
							currentBox = all.getBox2();
							boxNr = 2;
							currentMoveBox = all.getMovingUp3();
						} else {
							if (!all.getBox3().isEmpty()) {
								currentBox = all.getBox3();
								boxNr = 3;
								currentMoveBox = all.getMovingUp4();
							} else {
								if (!all.getBox4().isEmpty()) {
									currentBox = all.getBox4();
									boxNr = 4;
									currentMoveBox = all.getMovingUp5();
								} else {
									if (!all.getBox5().isEmpty()) {
										currentBox = all.getBox5();
										boxNr = 5;
										currentMoveBox = all.getMovingUp6();
									} else {
										if (!all.getBox6().isEmpty()) {
											currentBox = all.getBox6();
											boxNr = 6;
										} else {
											currentBox = all.getBoxNA();
											// boxNr = 1;
											lblNewLabel_1.setText("Pradėti nuo dėžutės Nr. 1");
											moving.updateBoxFromMovingList(all.getMovingDown(), all.getBox1());
											moving.updateBoxFromMovingList(all.getMovingUp2(), all.getBox2());
											moving.updateBoxFromMovingList(all.getMovingUp3(), all.getBox3());
											moving.updateBoxFromMovingList(all.getMovingUp4(), all.getBox4());
											moving.updateBoxFromMovingList(all.getMovingUp5(), all.getBox5());
											moving.updateBoxFromMovingList(all.getMovingUp6(), all.getBox6());

											if (!all.getUserList().isEmpty()) {
												moving.updateBox1ByNewWords(all.getUserList(), all.getMovingDown(),
														all.getBox1(), newWordQuantity);
											}

										}
									}
								}
							}
						}
					}

					if (!currentBox.isEmpty()) {
						leftInBox = currentBox.size();
						lblNewLabel_10.setText(Integer.toString(leftInBox));

						lblNewLabel_1.setText("Dėžutės Nr. " + boxNr);
						text.setText("");
						btnCheckWord.setVisible(true);
						btnNextWord.setVisible(false);
						lblPhonetic.setVisible(false);
						lblENWord.setVisible(false);
						lableIsTo.setVisible(false);

						int index = moving.RandomGenerator(currentBox);
						chekingWordOBJ = currentBox.remove(index);

						chekingWord = chekingWordOBJ.getWord();
						wordTranslationValue.setText(chekingWordOBJ.getTranslation());
						lblPhonetic.setText(chekingWordOBJ.getPhonetic());
						lblENWord.setText(chekingWordOBJ.getWord());

						if (chekingWordOBJ.getWithTo() == 1) {
							lableIsTo.setVisible(true);
						}
					}

				} else {

					btnNextWord.setVisible(false);
					lblNewLabel.setVisible(true);
					lblNewLabel_1.setText("PABAIGA!");
					lblPhonetic.setVisible(false);
					lblENWord.setVisible(false);
					text.setVisible(false);

					wordTranslationValue.setVisible(false);
					lableIsTo.setVisible(false);
				}
			}
		});

		btnCheckWord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				lblNewLabel_9.setText(Integer.toString(learned));
				answerWord = text.getText();

				if (answerWord.equalsIgnoreCase(chekingWord)) {
					if (!(boxNr == 6)) {
						color = SWT.COLOR_DARK_GREEN;
						currentMoveBox.add(chekingWordOBJ);
					} else {
						learned++;
					}
				} else {
					color = SWT.COLOR_RED;
					all.getMovingDown().add(chekingWordOBJ);
				}

				lblNewLabel_10.setText(Integer.toString(currentBox.size()));
				lblNewLabel_9.setText(Integer.toString(learned));
				leftLearnWordValue.setText(Integer.toString(primaryULsize - learned));
				lblPhonetic.setForeground(SWTResourceManager.getColor(color));
				lblENWord.setForeground(SWTResourceManager.getColor(color));
				lblPhonetic.setVisible(true);
				lblENWord.setVisible(true);
				btnCheckWord.setVisible(false);
				btnNextWord.setVisible(true);

			}
		});

	}
}
