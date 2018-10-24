package org.warnotte.waxaudiomiditools.CControlers.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.warnotte.Swing.Component.CurveEditor.CurvePanel;
import org.warnotte.Swing.Component.WaxSlider.WFlatSlider;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCA_2ND;

public class Panel_VCA2ND extends JPanel
{

	private static final 
	long	serialVersionUID	= 1L;
	SignGen_VCA_2ND vca = new SignGen_VCA_2ND();
	private JPanel jPanel_CURVES = null;
	private JPanel jPanel_Lengths = null;
	private JTextField jTextField_ATTACK = null;
	private JTextField jTextField_SUSTAIN = null;
	private JTextField jTextField_RELEASE = null;
	private JLabel jLabel_ATTACK = null;
	private JLabel jLabel_SUSTAIn = null;
	private JLabel jLabel_RELEASE = null;
	//private JPanel jPanel = null;
	private JCheckBox jCheckBox_BYPASS = null;
	private JTextField jTextField_CC = null;
	private JCheckBox jCheckBox_INVERT = null;
	private JButton jButton_SELECT_CC = null;
	private JButton jButton_AJUST_SUSTAIN_LVL = null;
	private WFlatSlider jButton_jij = null;
	/**
	 * This method initializes jPanel_CURVES	
	 * 	
	 * @return javax.swing.JPanel	
	 * @throws Exception 
	 */
	private JPanel getJPanel_CURVES() throws Exception
	{
		if (jPanel_CURVES == null)
		{
			jPanel_CURVES = new JPanel();
			jPanel_CURVES.setLayout(new BoxLayout(getJPanel_CURVES(), BoxLayout.X_AXIS));
			
			CurvePanel cp1 = new CurvePanel(vca.getAtt_crv());
			cp1.setPreferredSize(new Dimension(100, 300));
			CurvePanel cp2 = new CurvePanel(vca.getAtt_sus());
			cp2.setPreferredSize(new Dimension(100, 300));
			CurvePanel cp3 = new CurvePanel(vca.getAtt_rel());
			cp3.setPreferredSize(new Dimension(100, 300));
			
			jPanel_CURVES.add(cp1);
			jPanel_CURVES.add(cp2);
			jPanel_CURVES.add(cp3);
			
		}
		return jPanel_CURVES;
	}

	/**
	 * This method initializes jPanel_Lengths	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_Lengths()
	{
		if (jPanel_Lengths == null)
		{
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 1;
			gridBagConstraints16.gridy = 2;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 1;
			gridBagConstraints15.fill = GridBagConstraints.BOTH;
			gridBagConstraints15.gridy = 3;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.fill = GridBagConstraints.BOTH;
			gridBagConstraints14.gridwidth = 2;
			gridBagConstraints14.gridy = 5;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 0;
			gridBagConstraints13.gridwidth = 1;
			gridBagConstraints13.fill = GridBagConstraints.BOTH;
			gridBagConstraints13.gridy = 4;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridy = 5;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.gridwidth = 1;
			gridBagConstraints12.gridx = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridwidth = 1;
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.gridy = 3;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridy = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 0.0;
			gridBagConstraints.gridx = 2;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = -1;
			gridBagConstraints5.gridwidth = 3;
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = -1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.weightx = 1.0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.weightx = 1.0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.weightx = 1.0;
			jPanel_Lengths = new JPanel();
			jPanel_Lengths.setLayout(new GridBagLayout());
			jPanel_Lengths.add(getJTextField_ATTACK(), gridBagConstraints1);
			jPanel_Lengths.add(getJTextField_SUSTAIN(), gridBagConstraints2);
			jPanel_Lengths.add(getJTextField_RELEASE(), gridBagConstraints3);
			jPanel_Lengths.add(getJLabel_RELEASE(), gridBagConstraints);
			jPanel_Lengths.add(getJLabel_ATTACK(), gridBagConstraints4);
			jPanel_Lengths.add(getJLabel_SUSTAIn(), gridBagConstraints6);
			jPanel_Lengths.add(getJCheckBox_BYPASS(), gridBagConstraints11);
			jPanel_Lengths.add(getJTextField_CC(), gridBagConstraints12);
			jPanel_Lengths.add(getJCheckBox_INVERT(), gridBagConstraints13);
			jPanel_Lengths.add(getJButton_SELECT_CC(), gridBagConstraints14);
			jPanel_Lengths.add(getJButton_AJUST_SUSTAIN_LVL(), gridBagConstraints15);
			jPanel_Lengths.add(getJButton_jij(), gridBagConstraints16);
		}
		return jPanel_Lengths;
	}

	/**
	 * This method initializes jTextField_ATTACK	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_ATTACK()
	{
		if (jTextField_ATTACK == null)
		{
			jTextField_ATTACK = new JTextField();
			jTextField_ATTACK.setText(""+vca.getSa_time());
			jTextField_ATTACK.setToolTipText("Change Attack time in ms");
			jTextField_ATTACK.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					long val = new Float(""+jTextField_ATTACK.getText()).longValue();
					vca.setSa_time(val);
					
				}
			});
		}
		return jTextField_ATTACK;
	}

	/**
	 * This method initializes jTextField_SUSTAIN	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_SUSTAIN()
	{
		if (jTextField_SUSTAIN == null)
		{
			jTextField_SUSTAIN = new JTextField();
			jTextField_SUSTAIN.setText(""+vca.getSs_time());
			jTextField_SUSTAIN.setToolTipText("Change Loop of Sustain time in ms");
			jTextField_SUSTAIN.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					long val = new Float(""+jTextField_SUSTAIN.getText()).longValue();
					vca.setSs_time(val);
					
				}
			});
		}
		return jTextField_SUSTAIN;
	}

	/**
	 * This method initializes jTextField_RELEASE	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_RELEASE()
	{
		if (jTextField_RELEASE == null)
		{
			jTextField_RELEASE = new JTextField();
			jTextField_RELEASE.setText(""+vca.getSr_time());
			jTextField_RELEASE.setToolTipText("Change Release time in ms");
			jTextField_RELEASE.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					long val = new Float(""+jTextField_RELEASE.getText()).longValue();
					vca.setSr_time(val);
					
				}
			});
		}
		return jTextField_RELEASE;
	}

	/**
	 * This method initializes jLabel_ATTACK	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel_ATTACK()
	{
		if (jLabel_ATTACK == null)
		{
			jLabel_ATTACK = new JLabel();
			jLabel_ATTACK.setText("Attack");
		}
		return jLabel_ATTACK;
	}

	/**
	 * This method initializes jLabel_SUSTAIn	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel_SUSTAIn()
	{
		if (jLabel_SUSTAIn == null)
		{
			jLabel_SUSTAIn = new JLabel();
			jLabel_SUSTAIn.setText("Sustain");
		}
		return jLabel_SUSTAIn;
	}

	/**
	 * This method initializes jLabel_RELEASE	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel_RELEASE()
	{
		if (jLabel_RELEASE == null)
		{
			jLabel_RELEASE = new JLabel();
			jLabel_RELEASE.setText("Release");
		}
		return jLabel_RELEASE;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	/*private JPanel getJPanel()
	{
		if (jPanel == null)
		{
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
		}
		return jPanel;
	}*/

	/**
	 * This method initializes jCheckBox_BYPASS	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_BYPASS()
	{
		if (jCheckBox_BYPASS == null)
		{
			jCheckBox_BYPASS = new JCheckBox();
			jCheckBox_BYPASS.setText("Bypass");
			jCheckBox_BYPASS.setSelected(vca.isBypassSend());
			jCheckBox_BYPASS.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					vca.setBypassSend(jCheckBox_BYPASS.isSelected());
				}
			});
		}
		return jCheckBox_BYPASS;
	}

	/**
	 * This method initializes jTextField_CC	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_CC() {
		if (jTextField_CC == null) {
			jTextField_CC = new JTextField();
			jTextField_CC.setText(""+vca.getCC());
			jTextField_CC.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int cc = new Integer(""+jTextField_CC.getText());
					vca.setCC(cc);
				}
			});
		}
		return jTextField_CC;
	}

	/**
	 * This method initializes jCheckBox_INVERT	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_INVERT() {
		if (jCheckBox_INVERT == null) {
			jCheckBox_INVERT = new JCheckBox();
			jCheckBox_INVERT.setText("Invert");
			jCheckBox_INVERT.setSelected(vca.isInvert());
			jCheckBox_INVERT.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					vca.setInvert(jCheckBox_INVERT.isSelected());
				}
			});
		}
		return jCheckBox_INVERT;
	}

	/**
	 * This method initializes jButton_SELECT_CC	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_SELECT_CC() {
		if (jButton_SELECT_CC == null) {
			jButton_SELECT_CC = new JButton();
			jButton_SELECT_CC.setText("MIDI CC");
			jButton_SELECT_CC.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					vca.configCC();
					jTextField_CC.setText(""+vca.getCC());
				}
			});
		}
		return jButton_SELECT_CC;
	}

	/**
	 * This method initializes jButton_AJUST_SUSTAIN_LVL	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_AJUST_SUSTAIN_LVL() {
		if (jButton_AJUST_SUSTAIN_LVL == null) {
			jButton_AJUST_SUSTAIN_LVL = new JButton();
			jButton_AJUST_SUSTAIN_LVL.setText("Ajust Sustain");
			jButton_AJUST_SUSTAIN_LVL
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									ajust_sustain();
								}
							});
							
						}
					});
		}
		return jButton_AJUST_SUSTAIN_LVL;
	}

	protected void ajust_sustain() {
		vca.ajust_sustain_lvl();
		repaint();
	}

	/**
	 * This method initializes jButton_jij	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private WFlatSlider getJButton_jij() {
		if (jButton_jij == null) {
			jButton_jij = new WFlatSlider(0, 10000);
			jButton_jij.setValue((int)vca.getSs_time());
			jButton_jij.setToolTipText("Change Loop of Sustain time in ms");
			jButton_jij.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					long val = new Float(jButton_jij.getValue()).longValue();
					vca.setSs_time(val);
					
				}
			});
		}
		return jButton_jij;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * This is the default constructor
	 * @throws Exception 
	 */
	public Panel_VCA2ND() throws Exception
	{
		super();
		initialize();
	}
	
	public Panel_VCA2ND(SignGen_VCA_2ND vca) throws Exception
	{
		super();
		this.vca=vca;
		initialize();
		
		
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws Exception 
	 */
	private void initialize() throws Exception
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(300, 200);
		setPreferredSize(new Dimension(320, 200));
		setMinimumSize(new Dimension(320, 200));
		this.add(getJPanel_CURVES(), null);
		this.add(getJPanel_Lengths(), null);

	
		
	}

	
}
