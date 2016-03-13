package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

import grapheditor.controler.action.IdentifierAction;
import grapheditor.controler.mouse.GraphControlerFactory;
import grapheditor.view.main.PaintingPanel;

class WinOfAplication {

	private JFrame mainFrame;
	private JMenuBar menuBar;
	private PaintingPanel graphPanel;
	private JToolBar toolBar;

	public WinOfAplication() {
		mainFrame = loadFrame();
		menuBar = loadMenuBar();
		mainFrame.pack();
	}

	private JFrame loadFrame() {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(640, 360));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

	JMenuBar loadMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu currentMenu = new JMenu("����");

		JMenuItem menuItem = new JMenuItem("�������");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newGraph();
			}
		});
		currentMenu.add(menuItem);

		menuItem = new JMenuItem("�������");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFile();

			}
		});
		currentMenu.add(menuItem);

		menuItem = new JMenuItem("�������");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeFile();

			}
		});
		currentMenu.add(menuItem);

		currentMenu.addSeparator();

		menuItem = new JMenuItem("�����");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		currentMenu.add(menuItem);

		menuBar.add(currentMenu);
		JMenu menu = new JMenu("������");
		menuBar.add(menu);
		mainFrame.setJMenuBar(menuBar);
		return menuBar;
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();

		JButton but1 = new JButton(new ImageIcon("image/tool-select.png"));
		but1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Node_tool",
						graphPanel);
				graphPanel.changeMouseListener(listener);
			}
		});
		toolBar.add(but1);

		JButton but2 = new JButton(new ImageIcon("image/tool-pair.png"));
		but2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MouseInputListener listener = GraphControlerFactory.getInstance().getMouseInputListener("Arc_tool",
						graphPanel);
				graphPanel.changeMouseListener(listener);
			}
		});
		toolBar.add(but2);
		return toolBar;
	}

	public void newGraph() {
		graphPanel = new PaintingPanel();
		JScrollPane scrolPane = new JScrollPane(graphPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		mainFrame.add(scrolPane);
		toolBar = createToolBar();
		mainFrame.add(toolBar, BorderLayout.NORTH);
		mainFrame.pack();
		JMenu menu = menuBar.getMenu(1);
		menu.add(new JMenuItem(graphPanel.getActionEvent("CopyAction")));
		menu.add(new JMenuItem(graphPanel.getActionEvent("PasteAction")));
		menu.add(new JMenuItem(graphPanel.getActionEvent("CutAction")));
		menu.add(new JMenuItem(graphPanel.getActionEvent("DeleteAction")));
		menuBar.add(menu);
		menuBar.revalidate();
	}

	private void openFile() {
		// TODO
	}

	private void closeFile() {
		// TODO
	}

	static public void main(String[] args) {
		WinOfAplication f = new WinOfAplication();
	}
}