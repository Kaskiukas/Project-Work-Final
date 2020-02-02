package userWindow;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import database.DeleteData;
import database.SelectData;
import database.UpdateData;
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
	public static UpdateData ud = new UpdateData();
	public static DeleteData dd = new DeleteData();
	public static SelectData sd = new SelectData();
	public static Moving moving = new Moving();
	public static Word chekingWordOBJ;
	public String chekingWord;
	public String answerWord;
	public String userName;
	public int boxNr;
	public int primaryULsize;
	public int leftToLearn;
	public int leftInBox;
	public int learned;
	public int userId;
	public int wordId;
	public int newWordQuantity;
	public int wordGroupId;
	public int color;
	public ArrayList<Word> currentBox;
	public ArrayList<Word> currentMoveBox;

	// Launch the application.
	public static void main(String[] args) {

		try {
			Boxes window = new Boxes();
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

	//Create contents of the window.
	public void createContents() {

		shell = new Shell();
		shell.setSize(592, 463);
		shell.setText("Kalbos dežutė");

		userId = all.getUser().get(0).getUserId();
		userName = all.getUser().get(0).getName();
		newWordQuantity = all.getUser().get(0).getWordAmount();
		wordGroupId = all.getUser().get(0).getWordGroup();

		moving.notEmptyList().forEach((key, value) -> { //suranda pirma netuscia lista
			boxNr = key;
			currentBox = value;
        });
		currentMoveBox = all.getMovingUp2();

		if (!all.getUserList().isEmpty()) {		// papildo pirma dezute naujais zodziais
			moving.updateBox1ByNewWords(all.getUserList(), all.getMovingDown(),	all.getBox1(), newWordQuantity);
		} else {
			moving.updateBoxFromMovingList(all.getMovingDown(), all.getBox1());
		}
		
		primaryULsize = sd.countAllUserWord(userId);
		learned = sd.countAllLearnedUserWord(userId);
		leftToLearn = primaryULsize - learned;

		Label leftLearnWordValue = new Label(shell, SWT.CENTER);
		leftLearnWordValue.setBounds(95, 60, 50, 25);
		leftLearnWordValue.setText(Integer.toString(leftToLearn)); // likusiu ismokti zodziu kiekis
		leftInBox = currentBox.size();

		// Pirmasis zodis
		
		
		int index = moving.RandomGenerator(currentBox);	//moving.RandomGenerator(all.getBox1());
		chekingWordOBJ = currentBox.remove(index);		//all.getBox1().remove(index);
		chekingWord = chekingWordOBJ.getWord();
		wordId = chekingWordOBJ.getWordId();

		Label wordTranslationValue = new Label(shell, SWT.NONE);
		wordTranslationValue.setFont(SWTResourceManager.getFont("Courier New", 10, SWT.BOLD));
		wordTranslationValue.setBounds(245, 160, 260, 30);
		wordTranslationValue.setText(chekingWordOBJ.getTranslation());

		text = new Text(shell, SWT.BORDER | SWT.CENTER);
		text.setFont(SWTResourceManager.getFont("Courier New", 10, SWT.BOLD));
		text.setBounds(235, 200, 150, 30);

		Label lableIsTo = new Label(shell, SWT.NONE);
		lableIsTo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lableIsTo.setBounds(200, 200, 30, 30);
		lableIsTo.setText("to");
		lableIsTo.setVisible(false);

		if (chekingWordOBJ.getWithTo() == 1) {
			lableIsTo.setVisible(true);
		}

		Button mainWindow = new Button(shell, SWT.NONE);
		mainWindow.setBounds(25, 360, 90, 30);
		mainWindow.setText("Pagrindinis");
		mainWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				shell.close();
				StartProgram startProgram = new StartProgram();
				startProgram.open();
			}
		});
		
		Button btnNextWord = new Button(shell, SWT.NONE);
		btnNextWord.setBounds(420, 250, 90, 30);
		btnNextWord.setText("Kitas žodis");
		btnNextWord.setVisible(false);

		Button btnCheckWord = new Button(shell, SWT.NONE);
		btnCheckWord.setBounds(420, 200, 90, 30);
		btnCheckWord.setText("Tikrinti");

		Label learnedAllWordsTitle = new Label(shell, SWT.CENTER);
		learnedAllWordsTitle.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		learnedAllWordsTitle.setBounds(137, 310, 280, 85);
		learnedAllWordsTitle.setText("Jūs išmokote visus šios grupės žodžius");
		learnedAllWordsTitle.setVisible(false);

		Label lblPhonetic = new Label(shell, SWT.CENTER);
		lblPhonetic.setFont(SWTResourceManager.getFont("Courier New", 10, SWT.BOLD));
		lblPhonetic.setBounds(110, 235, 160, 30);
		lblPhonetic.setText("Tarimas");
		lblPhonetic.setVisible(false);
		lblPhonetic.setText(chekingWordOBJ.getPhonetic());

		Label lblENWord = new Label(shell, SWT.CENTER);
		lblENWord.setFont(SWTResourceManager.getFont("Courier New", 10, SWT.BOLD));
		lblENWord.setBounds(110, 275, 160, 30);
		lblENWord.setText("EN žodis");
		lblENWord.setVisible(false);
		lblENWord.setText(chekingWordOBJ.getWord());

		Label boxNrTitle = new Label(shell, SWT.NONE);
		boxNrTitle.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		boxNrTitle.setAlignment(SWT.CENTER);
		boxNrTitle.setBounds(110, 105, 340, 30);
		boxNrTitle.setVisible(true);
		boxNrTitle.setText("Dėžutės Nr. " + boxNr);

		Label enterENWordTitle = new Label(shell, SWT.NONE);
		enterENWordTitle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		enterENWordTitle.setBounds(25, 200, 170, 30);
		enterENWordTitle.setText("Įveskite anglišką žodį:");

		Label ltValueTitle = new Label(shell, SWT.NONE);
		ltValueTitle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		ltValueTitle.setBounds(25, 160, 150, 30);
		ltValueTitle.setText("Lietuviška reikšmė:");

		Label sayingTitle = new Label(shell, SWT.NONE);
		sayingTitle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		sayingTitle.setBounds(25, 235, 70, 30);
		sayingTitle.setText("Tarimas:");

		Label translationTitle = new Label(shell, SWT.NONE);
		translationTitle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		translationTitle.setBounds(25, 275, 70, 30);
		translationTitle.setText("Vertimas:");

		Label leftLearnTitle = new Label(shell, SWT.NONE);
		leftLearnTitle.setBounds(80, 30, 90, 25);
		leftLearnTitle.setText("Liko išmokti:");

		Label quantityInBoxTitle = new Label(shell, SWT.NONE);
		quantityInBoxTitle.setBounds(220, 30, 90, 25);
		quantityInBoxTitle.setText("Dėžutėje yra:");

		Label alreadyLearnedTitle = new Label(shell, SWT.NONE);
		alreadyLearnedTitle.setBounds(360, 30, 90, 25);
		alreadyLearnedTitle.setText("Jau išmokote:");

		Label learnedValue = new Label(shell, SWT.CENTER);
		learnedValue.setBounds(380, 60, 50, 25);
		learnedValue.setText(Integer.toString(learned));

		Label leftInBoxValue = new Label(shell, SWT.CENTER);
		leftInBoxValue.setBounds(240, 60, 50, 25);
		leftInBoxValue.setText(Integer.toString(leftInBox));

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
											boxNrTitle.setText("Pradėti nuo dėžutės Nr. 1");
											moving.updateBoxFromMovingList(all.getMovingDown(), all.getBox1()); 	// perkelia zodzius is tarpines dezutes i aukstesne
											moving.updateBoxFromMovingList(all.getMovingUp2(), all.getBox2());
											moving.updateBoxFromMovingList(all.getMovingUp3(), all.getBox3());
											moving.updateBoxFromMovingList(all.getMovingUp4(), all.getBox4());
											moving.updateBoxFromMovingList(all.getMovingUp5(), all.getBox5());
											moving.updateBoxFromMovingList(all.getMovingUp6(), all.getBox6());

											if (!all.getUserList().isEmpty()) {
												moving.updateBox1ByNewWords(all.getUserList(), all.getMovingDown(),		// papildo pirma dezute naujais zodziais
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
						leftInBoxValue.setText(Integer.toString(leftInBox));

						boxNrTitle.setText("Dėžutės Nr. " + boxNr);
						text.setText("");
						btnCheckWord.setVisible(true);
						btnNextWord.setVisible(false);
						lblPhonetic.setVisible(false);
						lblENWord.setVisible(false);
						lableIsTo.setVisible(false);

						int index = moving.RandomGenerator(currentBox);
						chekingWordOBJ = currentBox.remove(index);
						chekingWord = chekingWordOBJ.getWord();
						wordId = chekingWordOBJ.getWordId();
						wordTranslationValue.setText(chekingWordOBJ.getTranslation());
						lblPhonetic.setText(chekingWordOBJ.getPhonetic());
						lblENWord.setText(chekingWordOBJ.getWord());

						if (chekingWordOBJ.getWithTo() == 1) {
							lableIsTo.setVisible(true);
						}
					}

				} else {
					btnNextWord.setVisible(false);
					learnedAllWordsTitle.setVisible(true);
					boxNrTitle.setText("PABAIGA!");
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

				learnedValue.setText(Integer.toString(learned));
				answerWord = text.getText();

				if (answerWord.equalsIgnoreCase(chekingWord)) {
					ud.autoSave(userId, wordId, boxNr + 1);			// atnaujina zodzio dezutes numeri
					if (!(boxNr == 6)) {
						color = SWT.COLOR_DARK_GREEN;
						currentMoveBox.add(chekingWordOBJ);
						//ud.autoSave(userId, wordId, boxNr + 1); 		// atnaujina zodzio dezutes numeri
					} else {
						learned++;
						//dd.deleteAutoSave(userId, wordId, 6);		// istrina saugojimo informacija kaip zodis jau ismoktas
					//	ud.autoSave(userId, wordId, boxNr + 1);
					}
				} else {
					color = SWT.COLOR_RED;
					all.getMovingDown().add(chekingWordOBJ);
					ud.autoSave(userId, wordId, 1);					// atnaujina zodzio dezutes numeri
				}

				leftInBoxValue.setText(Integer.toString(currentBox.size()));
				learnedValue.setText(Integer.toString(learned));
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
