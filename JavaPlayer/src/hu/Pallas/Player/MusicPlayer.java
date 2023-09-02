package hu.Pallas.Player;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MusicPlayer implements ActionListener {
	JFrame frame;
	JLabel szamNeve = new JLabel();
	JButton zeneValaszt = new JButton("MP3 zene választó");
	JButton lejatszasGomb = new JButton("Play");
	JButton pauseGomb = new JButton("Pause");
	JButton ujrainditGomb = new JButton("Resume");
	JButton stopGomb = new JButton("STOP");
	JFileChooser fileKivalaszto;
	String fileNev;
	String eleresiUtvonal;
	File kivalasztott = null;
	Player player;
	Thread playThread;
	
	
	MusicPlayer(){
		ablakKeszit();
		addActionEvents();
		playThread = new Thread(); 
		
	}

	public void ablakKeszit() {
		frame = new JFrame();
		//Ablak megnevezése
		frame.setTitle("Bazsi Pallas MP3 Player");
		//Ablakot nem engedi szétesni, arányosan mozgatja
		frame.getContentPane().setLayout(null);
		//Ablak háttérszínének megadása
		frame.getContentPane().setBackground(Color.ORANGE);
		//Ablak mérete px
		frame.setSize(500,220);
		//Ablak mindig a képernyő közepére kerüljön
		frame.setLocationRelativeTo(null);
		//Ablak látszódjon vagy ne booleannal beállítva
		frame.setVisible(true);
		//Ablak "X" gomb mit csináljon
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Gombok formázása elhelyezkedése és mérete
		zeneValaszt.setBounds(160, 30, 200, 30);
		frame.add(zeneValaszt);
		
		szamNeve.setBounds(160, 60 , 180, 30);
		frame.add(szamNeve);
		
		lejatszasGomb.setBounds(40, 120, 100, 30);
		frame.add(lejatszasGomb);
		
		pauseGomb.setBounds(140, 120, 100, 30);
		frame.add(pauseGomb);
		
		ujrainditGomb.setBounds(240, 120, 100, 30);
		frame.add(ujrainditGomb);
		
		stopGomb.setBounds(340, 120, 100, 30);
		frame.add(stopGomb);
	}
	
	public void addActionEvents() {
		zeneValaszt.addActionListener(this);
		lejatszasGomb.addActionListener(this);
		pauseGomb.addActionListener(this);
		ujrainditGomb.addActionListener(this);
		stopGomb.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == zeneValaszt) {
			fileKivalaszto = new JFileChooser();
			fileKivalaszto.setCurrentDirectory(new File("D:\\MP3\\"));
			fileKivalaszto.setDialogTitle("MP3 mappa");
			fileKivalaszto.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileKivalaszto.setFileFilter(new FileNameExtensionFilter("Zeneállományok", "mp3"));
			if (fileKivalaszto.showOpenDialog(zeneValaszt) == JFileChooser.APPROVE_OPTION) {
				kivalasztott = fileKivalaszto.getSelectedFile();
				fileNev = fileKivalaszto.getSelectedFile().getName();
				eleresiUtvonal = fileKivalaszto.getSelectedFile().getPath();
			}
		}
		if(e.getSource() == lejatszasGomb) {
			playThread.start();
			szamNeve.setText("Lejátszás: "+fileNev);
		}
		if(e.getSource() == stopGomb) {
			playThread.interrupt();;
			szamNeve.setText("");
		}
		
	}
	
}