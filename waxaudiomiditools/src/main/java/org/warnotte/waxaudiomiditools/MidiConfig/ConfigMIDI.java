package org.warnotte.waxaudiomiditools.MidiConfig;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfigMIDI extends JDialog
{

	private static final long	serialVersionUID	= 1L;

	private JPanel				jContentPane		= null;

	private JComboBox<retourMidiConfig>			jComboBox_IN		= null;
	private JComboBox<retourMidiConfig>			jComboBox_OUT		= null;

	private JButton				jButton_OK			= null;

	private JLabel				jLabel_IN			= null;

	private JLabel				jLabel_OUT			= null;

	/**
	 * @param owner
	 */
	public ConfigMIDI(Frame owner)
	{
		super(owner, "Configuration MIDI", true);
		initialize();

	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 1;
			jLabel_OUT = new JLabel();
			jLabel_OUT.setText("In");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 0;
			jLabel_IN = new JLabel();
			jLabel_IN.setText("Out");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(0, 0, 1, 0);
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.ipadx = 112;
			gridBagConstraints2.ipady = 76;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = -68;
			gridBagConstraints1.ipady = 61;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.ipadx = -3;
			gridBagConstraints.ipady = 61;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridx = 1;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJComboBox_OUT(), gridBagConstraints);
			jContentPane.add(getJComboBox(), gridBagConstraints1);
			jContentPane.add(getJButton_OK(), gridBagConstraints2);
			jContentPane.add(jLabel_IN, gridBagConstraints3);
			jContentPane.add(jLabel_OUT, gridBagConstraints4);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox<retourMidiConfig> getJComboBox()
	{
		if (jComboBox_IN == null)
		{
			Vector<retourMidiConfig> list = MidiCommon.listDevicesAndExit(true, false, false);
			jComboBox_IN = new JComboBox<retourMidiConfig>(list);
			jComboBox_IN.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER)
					{
						setVisible(false);
						dispose();
					}
				}
			});
		}
		return jComboBox_IN;
	}

	/**
	 * This method initializes jComboBox_OUT
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox<retourMidiConfig> getJComboBox_OUT()
	{
		if (jComboBox_OUT == null)
		{
			Vector<retourMidiConfig> list = MidiCommon.listDevicesAndExit(false, true, false);
			jComboBox_OUT = new JComboBox<retourMidiConfig>(list);
			jComboBox_OUT.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER)
					{	
						setVisible(false);
						dispose();
					}
				}
			});
		}
		return jComboBox_OUT;
	}

	void jButtonCANCEL_actionPerformed(ActionEvent e)
	{
		setVisible(false);
		dispose();
	}

	/**
	 * This method initializes jButton_OK
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_OK()
	{
		if (jButton_OK == null)
		{
			jButton_OK = new JButton();
			jButton_OK.setText("Accept");
			jButton_OK.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					setVisible(false);
					dispose();
				}
			});

		}
		return jButton_OK;
	}

	public static void main(String[] args)
	{
		ConfigMIDI md1 = new ConfigMIDI(new JFrame());
		md1.pack();
		md1.setVisible(true);
		Object[][] retour = md1.getDonnees();
		System.err.println("IN  : " + retour[0][0]);
		System.err.println("OUT : " + retour[1][0]);
		System.err.println("IN  : " + retour[0][1]);
		System.err.println("OUT : " + retour[1][1]);
	}

	public Object[][] getDonnees()
	{

		return new Object[][] {

		{ jComboBox_IN.getSelectedObjects()[0], jComboBox_IN.getSelectedIndex() + 1 }, { jComboBox_OUT.getSelectedObjects()[0], jComboBox_OUT.getSelectedIndex() + 1 } };
	}
}
