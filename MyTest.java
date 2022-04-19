package resources;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;

public class MyTest extends JFrame  {
	
   private JToolBar barre = new JToolBar();
   private JTextField txtField1;
   private  JTextField txtField2;
   private JScrollPane scrollTree;
   private static JTable table;
   private JScrollPane scrollTable;
   private JTree arbre;
   private DefaultMutableTreeNode racine;
   private String username;
   private HashMap<String, File> fileInfo;
   private String selectedData = null;
   private String pathFileSelected;
   private static String pathTreeSelected;
   private JPopupMenu menu = new JPopupMenu("Popup menu");
   private String Choix;
   private String autoPathFileSelected;

   
   
   private JButton copy = new JButton("Copy", new ImageIcon("src/resources/Copy.png")),
		   paste = new JButton("Paste", new ImageIcon("src/resources/Paste.png")),
		   delete = new JButton("Delete", new ImageIcon("src/resources/Delete.png")),
		   cut = new JButton("Cut", new ImageIcon("src/resources/Cut.png")),
		   copyPath = new JButton("Copy Path", new ImageIcon("src/resources/CopyPath.png")),
		   rename = new JButton("Rename", new ImageIcon("src/resources/Rename.png")),
		   newFolder = new JButton("New Folder", new ImageIcon("src/resources/NewFolder.png")),
		   sellectAll = new JButton("Select All", new ImageIcon("src/resources/SelectAll.png")),
		   content = new JButton("Content", new ImageIcon("src/resources/Content.png")),
		   details = new JButton("Details", new ImageIcon("src/resources/Details.png")),
		   groupBy = new JButton("Group By", new ImageIcon("src/resources/GroupBy.png")),
		   sort = new JButton("Sort By", new ImageIcon("src/resources/Sort.png")),
		   refrech = new JButton(new ImageIcon("src/resources/Refrech.png"));

   

   final String[] colHeads = { "File Name", "Date modified", "Read Only", "SIZE" };
   String[][] data = { { "", "", "", "", "" } };

   
			
   public MyTest() {  
	  setLayout(new BorderLayout(0, 20));  
	  setSize(800, 600);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setTitle("Windows Explorer");
      
      Image icon = Toolkit.getDefaultToolkit().getImage("src/resources/Icon.png");    
      setIconImage(icon); 
      
      
      
      Menu();
	  listRoot();
	  Display();
	  buttonAction();
	  popUp();
	  ToolBar.popUpSort();
      setVisible(true);
      
   }
   
   
   // ---------------------------------------------- Menu -----------------------------------------------------  
   // -------------- Home items
   public void Menu() {
	  barre.setFloatable(false);
      copy.setVerticalTextPosition(JButton.BOTTOM);
      copy.setHorizontalTextPosition(JButton.CENTER);
      
      paste.setVerticalTextPosition(JButton.BOTTOM);
      paste.setHorizontalTextPosition(JButton.CENTER);
      
      delete.setVerticalTextPosition(JButton.BOTTOM);
      delete.setHorizontalTextPosition(JButton.CENTER);
      
      cut.setVerticalTextPosition(JButton.BOTTOM);
      cut.setHorizontalTextPosition(JButton.CENTER);
      
      copyPath.setVerticalTextPosition(JButton.BOTTOM);
      copyPath.setHorizontalTextPosition(JButton.CENTER);
      
      rename.setVerticalTextPosition(JButton.BOTTOM);
      rename.setHorizontalTextPosition(JButton.CENTER);
      
      newFolder.setVerticalTextPosition(JButton.BOTTOM);
      newFolder.setHorizontalTextPosition(JButton.CENTER);
      
      sellectAll.setVerticalTextPosition(JButton.BOTTOM);
      sellectAll.setHorizontalTextPosition(JButton.CENTER);

      barre.add(copy);
      barre.add(paste);
      barre.add(cut);
      barre.add(copyPath);
      barre.addSeparator();
      barre.add(delete);
      barre.add(rename);
      barre.add(newFolder);    
      barre.addSeparator();
      barre.add(sellectAll);
      
      content.setVerticalTextPosition(JButton.BOTTOM);
      content.setHorizontalTextPosition(JButton.CENTER);
      
      details.setVerticalTextPosition(JButton.BOTTOM);
      details.setHorizontalTextPosition(JButton.CENTER);
      
      groupBy.setVerticalTextPosition(JButton.BOTTOM);
      groupBy.setHorizontalTextPosition(JButton.CENTER);
      
      sort.setVerticalTextPosition(JButton.BOTTOM);
      sort.setHorizontalTextPosition(JButton.CENTER);
      
      barre.add(content);
      barre.add(details);
      barre.addSeparator();
      barre.add(groupBy);
      barre.add(sort);
      add(barre, BorderLayout.NORTH);

      
   }
   
        
  
   
   
   // -------------------------------------------------- Tree ----------------------------------------------
   private void listRoot() {
		racine = new DefaultMutableTreeNode("This PC");
		
		DefaultMutableTreeNode desktop = new DefaultMutableTreeNode("Desktop");
		DefaultMutableTreeNode download = new DefaultMutableTreeNode("Downloads");
		DefaultMutableTreeNode document = new DefaultMutableTreeNode("Documents");
		DefaultMutableTreeNode music = new DefaultMutableTreeNode("Music");
		DefaultMutableTreeNode pictures = new DefaultMutableTreeNode("Pictures");
		DefaultMutableTreeNode videos = new DefaultMutableTreeNode("Videos");	
		DefaultMutableTreeNode disqueLocal = new DefaultMutableTreeNode("Local Disk (C:)");

		
		username = System.getProperty("user.name");

		File Desktop = new File("C:\\Users\\"+username+"\\Desktop");
		File Download = new File("C:\\Users\\"+username+"\\Downloads");
		File Documents = new File("C:\\Users\\"+username+"\\Documents");
		File Music = new File("C:\\Users\\"+username+"\\Music");
		File Pictures = new File("C:\\Users\\"+username+"\\Pictures");
		File Videos = new File("C:\\Users\\"+username+"\\Videos");
		File DisqueLocal = new File("C:\\");

		
		
		createNode(Desktop,desktop);
		createNode(Download,download);
		createNode(Documents,document);
		createNode(Music,music);
		createNode(Pictures,pictures);
		createNode(Videos,videos);
		createNode(DisqueLocal,disqueLocal);

		arbre = new JTree(this.racine);
		getContentPane().add(new JScrollPane(arbre));
	}
   
   
   
   // --------------- Create Node
	private void createNode(File file,DefaultMutableTreeNode node) {
		try {
			for (File nom : file.listFiles()) {
				if(nom.isDirectory()) {
					DefaultMutableTreeNode noeud = new DefaultMutableTreeNode(nom.getName() + "\\");
					node.add(listFile(nom, noeud));
				}
				
			}
		} catch (NullPointerException e) {
		}
		this.racine.add(node);
	}
	
	
	// --------- List File
	private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node) {
		int count = 0;
			File[] list = file.listFiles();
			if (list == null && file.isDirectory())
				return new DefaultMutableTreeNode(file.getName());
			for (File nom : list) {
				count++;
				if (count < 5) {
					DefaultMutableTreeNode subNode;
					if (nom.isDirectory()) {
						subNode = new DefaultMutableTreeNode(nom.getName() + "\\");
						node.add(subNode);

					} 
				}
			}
			return node;
		}

	
	
	// ------------------------------------------- TextField and tabble
	private void Display() {
		txtField1 = new JTextField();
		txtField2 = new JTextField();

		scrollTree = new JScrollPane(arbre);	
		
		final String[] colHeads = {"File Name", "Date modified", "Type", "SIZE"  };
		String[][] data = { { "", "", "", "", "" } };
		table = new JTable(data, colHeads);
		
		scrollTable = new JScrollPane(table);
		
		refrech.setBounds(535, 69, 16, 16);
		add(refrech);
		
		txtField1.setBounds(0, 67, 534, 20);
		add(txtField1);
		
		txtField2.setBounds(560, 67, 200, 20);
		txtField2.setText("Search");
		add(txtField2);
		
		add(scrollTree, BorderLayout.WEST);
		add(scrollTable, BorderLayout.CENTER);
		
		arbre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				doMouseClicked(me);
			}
		});	
		
		
		txtField2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				txtField2.setText("");
			}
		});	
		
		
		
		 txtField2.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent e) {
	           int key = e.getKeyCode();
	           if (key == KeyEvent.VK_ENTER) {
	        	   	Toolkit.getDefaultToolkit().beep();
	        		for(File f: new File(pathTreeSelected).listFiles()) {
	        			
	        			if(f.getName().contains(txtField2.getText())) {
	        				System.out.println(f);
	        				showFiles(String.valueOf(f));

	        			}
	        			else {
	              			System.out.println("no");

	        			}
	        		}  

	           }
	        }
	     });
	}
	
   
	// ------------------------------------- Tree Action
	void openFile(File file) {
		 try {
	            Desktop desktop = Desktop.getDesktop();
	            desktop.open(file);
	        } catch (IOException e) {
	        	System.out.println(e);
	        }
	}
	
	
	
	void doMouseClicked(MouseEvent me) {
		TreePath tp = arbre.getPathForLocation(me.getX(), me.getY());

		if (tp == null)
			return;
		
		pathTreeSelected = tp.toString();
		
		String s = tp.toString();
		s = s.replace("[", "");
		s = s.replace("This PC", "");
		s = s.replace("]", "");
		s = s.replace(", ", "\\");
		
		pathTreeSelected = pathTreeSelected.replace("[", "");
		pathTreeSelected = pathTreeSelected.replace("]", "");
		pathTreeSelected = pathTreeSelected.replace(", ", "\\");
		
		if (s.length()!=0) {
			if(pathTreeSelected.contains("Local Disk (C:)")) {
				pathTreeSelected = "C:\\";
				s = "C:\\";
				
				showFiles(pathTreeSelected);
				if (s.contains(".")) {
					s = "C:\\"+ s;
					File file = new File(s);
					openFile(file);
				}
			}
	
			pathTreeSelected = pathTreeSelected.replace("This PC", "C:\\" + "Users\\" + username);
			
			String pathDesign = "";
			pathDesign = pathTreeSelected.replace("\\", "").replace("C:", "This PC > ").replace("Users", "").replace(username, "");
			txtField1.setText(pathDesign);
			showFiles(pathTreeSelected);
			
			

			
			if (s.contains(".")) {
				s = "C:\\Users\\" + username + s;
				File file = new File(s);
				openFile(file);
			}
		}
	}
	
	
	
	private void DesignPath() {
		String pathDesign = "";
		
		pathDesign = pathFileSelected.replace("\\", " > ").replace("C:", "This PC");
		txtField1.setText(pathDesign);
	}
	
	
	//------------------------------POP UP--------------------------------	
	 private void popUp() {
				JMenuItem Open = new JMenuItem("Open");
				JMenuItem Copy = new JMenuItem("Copy");
				JMenuItem Cut = new JMenuItem("Cut");
				JMenuItem Delete = new JMenuItem("Delete");
				JMenuItem Paste = new JMenuItem("Paste");
				JMenuItem Rename = new JMenuItem("Rename");
			
				
				Open.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						File f = new File(pathFileSelected);
						
						if(f.isFile()) {
							openFile(f);
						}
						else {
							showFiles(pathFileSelected);
							DesignPath();
						}

					}
				});
				

				Copy.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						Choix = ToolBar.Copy(pathFileSelected);
						}catch(Exception ex) {
						}
					}
				});

				Cut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						Choix = ToolBar.Cut(pathFileSelected);
						}catch(Exception ex) {
						}
					}
				});
				Delete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						ToolBar.Delete(pathFileSelected);
						showFiles(pathTreeSelected);
						}catch(Exception ex) {
						}
					}
				});
				Paste.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						ToolBar.Paste(pathFileSelected, String.format("%s\\%s", pathTreeSelected, selectedData), Choix);
						showFiles(pathTreeSelected);
						}catch(Exception ex) {
						}
					}
				});
				Rename.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						System.out.println("Rename");
						}catch(Exception ex) {
						}
					}
				});

				menu.add(Open);
				menu.add(Cut);
				menu.add(Copy);
				menu.add(Paste);
				menu.addSeparator();
				menu.add(Delete);
				menu.add(Rename);
	 }
	 
	
	
	 void showFiles(String filename) {
			File temp = new File(filename);
			data = new String[][] { { "", "", "", "" } };
			remove(scrollTable);	

			table = new JTable(data, colHeads);
			table.setShowGrid(false);
			table.setShowHorizontalLines(false);
			table.setShowVerticalLines(false);
			
			scrollTable = new JScrollPane(table);
			add(scrollTable, BorderLayout.CENTER);
			setVisible(true);

			if (!temp.exists())
				return;
			if (!temp.isDirectory())
				return;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy hh:mm  a");
			fileInfo = new HashMap<String, File>();
			File[] filelist = temp.listFiles(); 
			int fileCounter = 0;
			data = new String[filelist.length][4];
			long tempi = 0;
			for (int i = 0; i < filelist.length; i++) {
				long tempo = filelist[i].lastModified();
				if (tempo>tempi) {
					tempi = tempo;
					autoPathFileSelected = filelist[i].getAbsolutePath();
				}
				data[fileCounter][0] = new String(filelist[i].getName());
				data[fileCounter][1] = new String(sdf.format(filelist[i].lastModified()) + "");
				data[fileCounter][2] = new String(!filelist[i].canWrite() + "");
				data[fileCounter][3] = new String(Math.round((double) filelist[i].length()/1024) + "  kb");
				fileInfo.put(data[fileCounter][0], filelist[i]);
				fileCounter++;
			}


		
		String dataTemp[][] = new String[fileCounter][4];
		for (int k = 0; k < fileCounter; k++)
			dataTemp[k] = data[k];
		data = dataTemp;
		
		remove(scrollTable);
		
		table = new JTable(data, colHeads);
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setCellSelectionEnabled(true);  
 
		
		scrollTable = new JScrollPane(table);
//		table.setEnabled(false);
		
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent e){
				table.setEnabled(false);

				
				if (e.getClickCount() == 2 ||(e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1)) {
					table.setEnabled(true);
					int[] row = table.getSelectedRows();
					int[] col = table.getSelectedColumns();
					
					
					for (int i = 0; i < row.length; i++) {
				          for (int j = 0; j < col.length; j++) {
				            selectedData = (String) table.getValueAt(row[i], col[j]);
				          }
				        }
				     try {
				    	 File f = new File(selectedData);
					        for(String fileName: fileInfo.keySet()) {
								if(selectedData.equals(fileName)) {
									File fileSelected = fileInfo.get(fileName);
									f = new File(String.valueOf(fileSelected));
									pathFileSelected = String.valueOf(f);
									selectedData = fileName;
								}
							}
					        if (e.getClickCount() == 2) {
					        	 if(f.isFile()) {
							        	openFile(f);
							        	table.setEnabled(true);
							        }
							        else {
							        	showFiles(String.valueOf(f));
							        	table.setEnabled(true);
							        }
					        }
				     }catch(Exception ex) {
				    	 System.out.println(ex);
				     }
			        
			       
			        
				}
		
				if (e.getButton() == MouseEvent.BUTTON3){
					menu.show(e.getComponent(), e.getX(), e.getY());
					
				}
		}
	});
			
		
		add(scrollTable, BorderLayout.CENTER);
		setVisible(true);

	}
	
	// --------------------------------------- ToolBar -------------------------------------
	void buttonAction() {
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Choix = ToolBar.Copy(pathFileSelected);
			}
		});
		
		
		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Choix = ToolBar.Cut(pathFileSelected);

			}
		});
		
		
		copyPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Choix = ToolBar.copyPath(pathFileSelected);
			}
		});
		
		
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolBar.Paste(pathFileSelected, String.format("%s\\%s", pathTreeSelected, selectedData), Choix);
				showFiles(pathTreeSelected);
			}
		});
			
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolBar.Delete(pathFileSelected);
				showFiles(pathTreeSelected);
			}
		});
		

		newFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ToolBar.newFolder(pathTreeSelected);
					showFiles(pathTreeSelected);
					pathFileSelected = autoPathFileSelected;
					renameFile();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		rename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renameFile();
				pathFileSelected = autoPathFileSelected;

			}
		});
		
		sellectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
					ToolBar.sellectAll(table);
			}
		});
		
		
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolBar.getSortList().show(sort, sort.getWidth()/2-26, sort.getHeight()/2+30);
								
		}
	});
		
		
		
//	groupBy.addActionListener(new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			ToolBar.getSortList().show(groupBy, groupBy.getWidth()/2-31, groupBy.getHeight()/2+30);
//		}
//	});
//		
	
		
		
	details.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			showFiles(pathTreeSelected);
		}
	});
	
	
	refrech.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			showFiles(pathTreeSelected);
		}
	});
	
	
	
	
		
	}
		
	
	public static String getpathTreeSelected(){
		return pathTreeSelected;
	}
	
	public static JTable getTable(){
		return table;
	}
	
	
	void renameFile() {
		if(pathFileSelected != null) {
		String pathFileSelected2 = pathFileSelected.replace("\\", "-");
		String file = "";
		String newPath = "";
		
		String tab[] = pathFileSelected2.split("-");
		for(int i =0; i<tab.length; i++) {
			file = tab[i];
		}
		String newArr[] = Arrays.copyOf(tab, tab.length - 1);
		
        File file1 = new File(pathFileSelected);
        String m = JOptionPane.showInputDialog("Rename", file);
       
        for(int i =0; i<newArr.length; i++) {
        	newPath += newArr[i];
        	newPath +="\\";
		}
        
        newPath = newPath+=m;
        File rename = new File(newPath);

        
        file1.renameTo(rename);
	  showFiles(pathTreeSelected);
	  table.setEnabled(true);
	  
		}
	}
	
	
   public static void main(String[] args) { new MyTest();}
}












