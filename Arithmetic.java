package Operation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Arithmetic {
	String getPath= "";//保存输入的路径，如果一般不修改，默认为D:\\
	JFrame jf_GP;//路径配置窗体
	 JTextArea jta;//jf窗体中文本框
	 JFrame jf;//运行窗体
	 boolean b = false;//检查是否打开jf窗体，如果打开，生成新的jf窗体之前会关闭之前的窗体
	 File f;//创建工作路径的变量名
	 
	//获取文件保存路径的文件夹名，没有的化进行创建，如果路径输入有误不会报错，但是在后期运行过程中会提示报错，要求重新修改路径 
	void visualGetPath(){//起始进入窗口
		//创建窗体
		jf_GP = new JFrame();
		jf_GP.setTitle("欢迎来到四则运算生成器配置界面");
		Container container = jf_GP.getContentPane();
		
		jf_GP.setBounds(200, 200, 800, 600);
		
		jf_GP.setLayout(new BorderLayout());
		JPanel jp_0 = new JPanel();
		JPanel jp_1 = new JPanel();
		JPanel jp_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
		container.add(jp_0,BorderLayout.NORTH);
		container.add(jp_1,BorderLayout.CENTER);
		container.add(jp_2,BorderLayout.SOUTH);
		
		//jp_0设置
		JTextField jt_0 = new JTextField(17);
		jt_0.setText("四则运算生成器");
		jt_0.setEditable(false);
		jt_0.setFont(new Font("宋体", Font.BOLD, 80));
		jp_0.add(jt_0);
		
		JPanel jp_1_0 = new JPanel();
		JPanel jp_1_1 = new JPanel();
		JPanel jp_1_2 = new JPanel();
		JPanel jp_1_3 = new JPanel();
		JPanel jp_1_4 = new JPanel();
		jp_1.add(jp_1_0);
		jp_1.add(jp_1_3);
		jp_1.add(jp_1_4);
		jp_1.add(jp_1_1);
		jp_1.add(jp_1_2);
		JLabel jl_GP_0 = new JLabel("请输入工作路径:                           ");
		JLabel jl_GP_3 = new JLabel("将在您的计算机中创建一个工作路径保存您的文件，如果要在其他位置保存请点击“浏览”，  ");
		JLabel jl_GP_4 = new JLabel(" 然后选择位置，或者自行输入创建路径 。                                                                                                       ");
		jl_GP_0.setFont(new Font("宋体", Font.BOLD, 25));
		jp_1_0.setLayout(new FlowLayout(0));
		JLabel jl_GP_1 = new JLabel("                           ");
		final JTextField jtf_GP = new JTextField(40);
		jtf_GP.setText("D:\\");
		jtf_GP.setFont(new Font("宋体", Font.BOLD, 25));
		JButton jb_GP_1 = new JButton("路径选择");
		jp_1_0.add(jl_GP_0,BorderLayout.WEST);
		jp_1_3.add(jl_GP_3);
		jp_1_4.add(jl_GP_4);
		jp_1_1.add(jl_GP_1);
		jp_1_1.add(jtf_GP);
		jp_1_2.add(jb_GP_1);
		
		JButton jb_GP_2 = new JButton("返回");
		JButton jb_GP_3 = new JButton("完成");
		jp_2.add(jb_GP_2);
		jp_2.add(jb_GP_3);
		
		jf_GP.setVisible(true);
		jf_GP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//创建监视器。
		//路径浏览
		jb_GP_1.addActionListener(new ActionListener() {
			int state;
			File Path;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jf=new JFileChooser("d:\\");
				jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				state=jf.showOpenDialog(Arithmetic.this.jf_GP);
				Path=jf.getSelectedFile();
				if(Path!=null&&state==JFileChooser.APPROVE_OPTION){
					jtf_GP.setText(Path.getPath());   
				}
			}
		});
		//返回键，当一开始时，按返回键，则提示报错，如果后面打开的化，按返回键则是关闭窗口
		jb_GP_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Arithmetic.this.getPath==""){
					JOptionPane.showMessageDialog(null, "你没有设置路径，不能返回");
				}else{
					Arithmetic.this.jf_GP.dispose();
				}				
			}
		});
		//完成键，当按完成键后，给getPath赋值，并打开Visual窗口
		jb_GP_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(b==true){
					Arithmetic.this.jf.dispose();
				}
				Arithmetic.this.getPath = jtf_GP.getText();
				if(!(jtf_GP.getText().equals(""))){
					Arithmetic.this.getPath = jtf_GP.getText()+"/";
					Arithmetic.this.jf_GP.dispose();
					Arithmetic.this.visual();
					b=true;
				}else{
					JOptionPane.showMessageDialog(null, "你没有设置正确路径，不能完成");
				}
			}
		});
		
		
		
	}
	//检查txt文件中算术的答案跟标准答案是否一致
	String[] checkAnswers(File exercises,File answers){
		String str[] = new String[2];
		str[0] = "";
		str[1] = "";
		String[] exeSave = new String[2];
		String[] ansSave = new String[2];
		String exe = "";
		String ans = "";
		int i = 1;
		int correct = 0;
		int wrong = 0;
		try {
			InputStreamReader isr_1 = new InputStreamReader(new FileInputStream(exercises));
			InputStreamReader isr_2 = new InputStreamReader(new FileInputStream(answers));
			BufferedReader bR_1 = new BufferedReader(isr_1);
			BufferedReader bR_2 = new BufferedReader(isr_2);
			try {
				while((exe=bR_1.readLine())!=null&&(ans=bR_2.readLine())!=null){
					exeSave= exe.split("=");
					if(exeSave[1]!=null){
						exeSave[1] = exeSave[1].replaceAll("[^(0-9|\'|\\/)]", "");//一开始用正则表达式只是将非数字的去除，没有考虑到'和/所表示的分数
					}
					ansSave= ans.split(":");
					ansSave[1] = ansSave[1].replaceAll("\\s", "");
					/*System.out.println(exeSave[1]);
					System.out.println(ansSave[1]);
					测试表达式计算比较结果
					*/
					if(exeSave[1].equals(ansSave[1])){
						str[0] += "" + i + " " + "," + " ";
						correct++;
					}else{
						str[1] += "" + i + " " + "," + " ";
						wrong++;
					}
					i++;
				}
				str[0]= "Correct: " + correct + " ( " + str[0] + " );" ;
				str[1]= "Wrong: " + wrong + " ( " + str[1] + " );" ;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				bR_2.close();
				bR_1.close();
				isr_2.close();
				isr_1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	//创建up以内的数的四则运算
	String[] createArithmetic(int up){
		Computed computed = new Computed();
		Sum s = new Sum();
		String[] str = new String[3]; 
		Random rand = new Random();
		int saveRand = rand.nextInt(4);
		int forNum = 0;
		String n1 = s.createNum(up);
		String n2 = s.createNum(up);
		switch(saveRand){
		case 0:
		case 1:
		case 2:str=computed.sub(n1, n2, str);break;//做加法
		case 3:
		case 4:
		case 5:str=computed.sub(n1, n2, str);break;//做减法
		case 6:
		case 7:str=computed.multi(n1, n2, str);break;//做乘法
		case 8:
		case 9:str=computed.div(n1, n2, str);break;//做除法
		}
		forNum = rand.nextInt(3);
		for(int i = 0;i<forNum;i++){
			saveRand = rand.nextInt(10);
			n1 = s.createNum(up);
			switch(saveRand){
			case 0:
			case 1:
			case 2:str=computed.sub(n1, "", str);break;//做加法
			case 3:
			case 4:
			case 5:str=computed.sub(n1, "", str);break;//做减法
			case 6:
			case 7:str=computed.multi(n1, "", str);break;//做乘法
			case 8:
			case 9:str=computed.div(n1, "", str);break;//做除法
			}
		}
		return str;
	}
	//构建四则运算生成器的界面，提供命令行运行等功能
	void visual(){
		jf = new JFrame();
		jf.setTitle("四则运算生成器");
		Container container = jf.getContentPane();
		
		jf.setBounds(200, 200, 800, 600);
		
		JPanel jp_1 = new JPanel();
		JPanel jp_2 = new JPanel();
		JPanel jp_3 = new JPanel();
		container.add(jp_1,BorderLayout.NORTH);
		container.add(jp_2,BorderLayout.EAST);
		container.add(jp_3,BorderLayout.CENTER);
		
		final JTextField jtf = new JTextField(30);
		JLabel jLabel_1 = new JLabel("输入命令行:");
		JLabel jLabel_2 = new JLabel("提示窗口:");
		final JButton jb_3 = new JButton("运行");
		jp_1.setLayout(new BorderLayout());
		jp_1.add( jLabel_1,BorderLayout.WEST);
		jp_1.add( jLabel_2,BorderLayout.SOUTH);
		jp_1.add(jtf);
		jp_1.add(jb_3,BorderLayout.EAST);
		//创建右侧按钮
		final JButton jb_1 = new JButton("OPEN Exercises");
		final JButton jb_2 = new JButton("OPEN Answers");
		JButton jb_4 = new JButton("Check Answers");
		JButton jb_5 = new JButton("OPEN Grade");
		JButton jb_6 = new JButton("Replace Path");
		JButton jb_7 = new JButton("Close EXE");
		JButton jb_8 = new JButton("HELP");
		jp_2.setLayout(new GridLayout(8,1));
		jp_2.add(jb_1);
		jp_2.add(jb_2);
		jp_2.add(jb_4);
		jp_2.add(jb_5);
		jp_2.add(jb_6);
		jp_2.add(jb_7);
		jp_2.add(jb_8);
		
		jta = new JTextArea(28,60);
		jta.setEditable(false);
		JScrollPane jsp = new JScrollPane(jta);
		jp_3.add(jsp);
		
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f = new File(getPath);
		if(!f.isDirectory()){
			f.mkdirs();
		}
		if(!f.isDirectory()){
			jta.append("你输入的路径有误，请通过Replace Path改变路径\n");
		}
		//运行命令行程序按钮
		jb_3.addActionListener(new ActionListener() {
			int n=0;
			int r=0;
			File file_1;
			File file_2;
			String[] str = new String[3];
			FileWriter fw_1 ;
			FileWriter fw_2 ;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String getn = "";
				String getr = "";
				String getJtf  = jtf.getText();
				if(Pattern.matches("(-n|-r)\\s+[0-9]+", getJtf)){
					if(Pattern.matches("-n.+", getJtf)){
						getn=getJtf.replaceAll("[^0-9]", "");
						n = Integer.parseInt(getn);
						if(n>0&&n<=10000){
							jtf.setText("");
							jta.append("已获取n值为"+n+"\n");
						}else{
							jta.append("请输入的n值大于等于1小于10000\n");
							jtf.setText("");
						}
					}else if(Pattern.matches("-r.+", getJtf)&&n!=0){
						getr=getJtf.replaceAll("[^0-9]", "");
						r = Integer.parseInt(getr);
						if(r>0&&r<1000){//取值太大容易超出容量范围，后续可以通过改int型为long型解决
							jtf.setText("");
							jta.append("已获取r值为"+r+"\n");
						}else{
							jta.append("请输入的r值大于等于1小于1000\n");
							jtf.setText("");
						}	
					}else{
						jta.append("请先按照“-n 题数”的方式输入题数\n");
						jtf.setText("");
					}
				}else{
					if(n==0){//判断已输入n值没
						jta.append("请按照“-n 题数”的方式输入题数\n");
						jtf.setText("");
					}else if(r==0){
						jta.append("请按照“-r 取值”的方式输入题目数字范围\n");
						jtf.setText("");
					}
				}
				
				if(n>0&&r>0){
					file_1 = new File(Arithmetic.this.getPath+"Exercises.txt");
					file_2 = new File(Arithmetic.this.getPath+"Answers.txt");
					
					if(!file_1.isFile()){
						try {
							file_1.createNewFile();
							jta.append("问题的文件已生成\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						file_1.delete();
						try {
							file_1.createNewFile();
							jta.append("问题的文件已生成\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					if(!file_2.isFile()){
						try {
							file_2.createNewFile();
							jta.append("答案的文件已生成\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						file_2.delete();
						try {
							file_2.createNewFile();
							jta.append("答案的文件已生成\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					try {
						fw_1 = new FileWriter(file_1);
						fw_2 = new FileWriter(file_2);
						jta.append("文件正在写入中\n");
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					int i =0;
					String[] str_1 = new String[10000];
					boolean b = true;
					int j=0;
					while(i<n){
						b = true;
						j++;
						System.out.println(j);//留待测试那些相同的等式判别。
						str =Arithmetic.this.createArithmetic(r);
						for(String s:str_1){
							if(str[1].equals(s)){
								b=false;
							}
							
						}
						if(b){
							str_1[i]=str[1];
							try {
								jta.append("文件正在写入中\n");
								fw_1.write("四则运算题目  " + (i+1) + ":  " + str[2]+ " = " + "\r\n");
								fw_2.write("四则运算答案  " + (i+1) + ":  " + str[0]+ "\r\n");
								fw_1.flush();
								fw_2.flush();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							i++;
						}
						
					}
					
					
					n=0;
					r=0;
				}
				
			}
		});
		//打开题目按钮
		jb_1.addActionListener(new ActionListener() {
			File file_1;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				file_1 = new File(Arithmetic.this.getPath+"Exercises.txt");
				if(!file_1.isFile()){
					if(!Arithmetic.this.f.isDirectory()){
						jta.append("你输入的路径有误，请通过Replace Path改变路径\n");
					}else{
						jta.append("文件还未被创建\n");
					}
				}else{
					try {
						Desktop.getDesktop().open(file_1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		//打开答案按钮
		jb_2.addActionListener(new ActionListener() {
			File file_2;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				file_2 = new File(Arithmetic.this.getPath+"Answers.txt");
				if(!file_2.isFile()){
					if(!Arithmetic.this.f.isDirectory()){
						jta.append("你输入的路径有误，请通过Replace Path改变路径\n");
					}else{
						jta.append("文件还未被创建\n");
					}
				}else{
					try {
						Desktop.getDesktop().open(file_2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		//打开成绩按钮
		jb_5.addActionListener(new ActionListener() {
			File file_3;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				file_3 = new File(Arithmetic.this.getPath+"Grade.txt");
				if(!file_3.isFile()){
					if(!Arithmetic.this.f.isDirectory()){
						jta.append("你输入的路径有误，请通过Replace Path改变路径\n");
					}else{
						jta.append("文件还未被创建\n");
					}
				}else{
					try {
						Desktop.getDesktop().open(file_3);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		//生成答案按钮
		jb_4.addActionListener(new ActionListener() {
			String[] str = new String[2];
			File file_1;
			File file_2;
			File file_3;
			FileWriter fw_3 ;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				file_1 = new File(Arithmetic.this.getPath+"Exercises.txt");
				file_2 = new File(Arithmetic.this.getPath+"Answers.txt");
				file_3 = new File(Arithmetic.this.getPath+"Grade.txt");
				if(!file_1.isFile()&&!(file_2.isFile())){
					if(!Arithmetic.this.f.isDirectory()){
						jta.append("你输入的路径有误，请通过Replace Path改变路径\n");
					}else{
						jta.append("文件还未被创建\n");
					}
				}else{
					str = Arithmetic.this.checkAnswers(file_1, file_2);
					
					if(!file_3.isFile()){
						try {
							file_3.createNewFile();
							jta.append("检查文件已生成\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						file_3.delete();
						try {
							file_3.createNewFile();
							jta.append("检查文件已生成\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					try {
						fw_3 = new FileWriter(file_3);//文件3的写入
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					for(int i = 0;i<str.length;i++){
						try {
							fw_3.write(str[i] + "\r\n");
							fw_3.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		//更换路径按钮
		jb_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Arithmetic.this.visualGetPath();
			}
		});
		//关闭程序按钮
		jb_7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		//帮助按钮
		jb_8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "功能介绍：\n " +
						"\r 命令行输入：'-n number'可以设置生成题数（0-10000）\n" +
						"\r 命令行输入：'-r number'可以设置生成题使用的数的范围（0-1000）\n" +
						"\r             请先输入题数，再输入数的范围。\n" +
						"\r    运行：                     点击可以运行命令行\n" +
						"\r    OPEN Exercises：  点击可以打开练习文件\n" +
						"\r    OPEN Answers：      点击可以打开答案文件\n" +
						"\r    Check Answers：    点击可以生成你上一次填的答案的成绩\n" +
						"\r    OPEN Grade：         点击可以打开成绩文件\n" +
						"\r    Replace Path：     点击可以更换生成的工作路径\n" +
						"\r    Close EXE：          点击可以关闭整个程序\n" +
						"\r    HELP：                   点击可以查看帮助\n");
			}
		});
		
	}
	public static void main(String[] args) {
		Arithmetic ar = new Arithmetic();
		ar.visualGetPath();
		//ar.visual();
	}
}
