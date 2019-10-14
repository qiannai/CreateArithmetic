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
	String getPath= "";//���������·�������һ�㲻�޸ģ�Ĭ��ΪD:\\
	JFrame jf_GP;//·�����ô���
	 JTextArea jta;//jf�������ı���
	 JFrame jf;//���д���
	 boolean b = false;//����Ƿ��jf���壬����򿪣������µ�jf����֮ǰ��ر�֮ǰ�Ĵ���
	 File f;//��������·���ı�����
	 
	//��ȡ�ļ�����·�����ļ�������û�еĻ����д��������·���������󲻻ᱨ�������ں������й����л���ʾ����Ҫ�������޸�·�� 
	void visualGetPath(){//��ʼ���봰��
		//��������
		jf_GP = new JFrame();
		jf_GP.setTitle("��ӭ���������������������ý���");
		Container container = jf_GP.getContentPane();
		
		jf_GP.setBounds(200, 200, 800, 600);
		
		jf_GP.setLayout(new BorderLayout());
		JPanel jp_0 = new JPanel();
		JPanel jp_1 = new JPanel();
		JPanel jp_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
		container.add(jp_0,BorderLayout.NORTH);
		container.add(jp_1,BorderLayout.CENTER);
		container.add(jp_2,BorderLayout.SOUTH);
		
		//jp_0����
		JTextField jt_0 = new JTextField(17);
		jt_0.setText("��������������");
		jt_0.setEditable(false);
		jt_0.setFont(new Font("����", Font.BOLD, 80));
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
		JLabel jl_GP_0 = new JLabel("�����빤��·��:                           ");
		JLabel jl_GP_3 = new JLabel("�������ļ�����д���һ������·�����������ļ������Ҫ������λ�ñ����������������  ");
		JLabel jl_GP_4 = new JLabel(" Ȼ��ѡ��λ�ã������������봴��·�� ��                                                                                                       ");
		jl_GP_0.setFont(new Font("����", Font.BOLD, 25));
		jp_1_0.setLayout(new FlowLayout(0));
		JLabel jl_GP_1 = new JLabel("                           ");
		final JTextField jtf_GP = new JTextField(40);
		jtf_GP.setText("D:\\");
		jtf_GP.setFont(new Font("����", Font.BOLD, 25));
		JButton jb_GP_1 = new JButton("·��ѡ��");
		jp_1_0.add(jl_GP_0,BorderLayout.WEST);
		jp_1_3.add(jl_GP_3);
		jp_1_4.add(jl_GP_4);
		jp_1_1.add(jl_GP_1);
		jp_1_1.add(jtf_GP);
		jp_1_2.add(jb_GP_1);
		
		JButton jb_GP_2 = new JButton("����");
		JButton jb_GP_3 = new JButton("���");
		jp_2.add(jb_GP_2);
		jp_2.add(jb_GP_3);
		
		jf_GP.setVisible(true);
		jf_GP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//������������
		//·�����
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
		//���ؼ�����һ��ʼʱ�������ؼ�������ʾ�����������򿪵Ļ��������ؼ����ǹرմ���
		jb_GP_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Arithmetic.this.getPath==""){
					JOptionPane.showMessageDialog(null, "��û������·�������ܷ���");
				}else{
					Arithmetic.this.jf_GP.dispose();
				}				
			}
		});
		//��ɼ���������ɼ��󣬸�getPath��ֵ������Visual����
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
					JOptionPane.showMessageDialog(null, "��û��������ȷ·�����������");
				}
			}
		});
		
		
		
	}
	//���txt�ļ��������Ĵ𰸸���׼���Ƿ�һ��
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
						exeSave[1] = exeSave[1].replaceAll("[^(0-9|\'|\\/)]", "");//һ��ʼ��������ʽֻ�ǽ������ֵ�ȥ����û�п��ǵ�'��/����ʾ�ķ���
					}
					ansSave= ans.split(":");
					ansSave[1] = ansSave[1].replaceAll("\\s", "");
					/*System.out.println(exeSave[1]);
					System.out.println(ansSave[1]);
					���Ա��ʽ����ȽϽ��
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
	//����up���ڵ�������������
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
		case 2:str=computed.sub(n1, n2, str);break;//���ӷ�
		case 3:
		case 4:
		case 5:str=computed.sub(n1, n2, str);break;//������
		case 6:
		case 7:str=computed.multi(n1, n2, str);break;//���˷�
		case 8:
		case 9:str=computed.div(n1, n2, str);break;//������
		}
		forNum = rand.nextInt(3);
		for(int i = 0;i<forNum;i++){
			saveRand = rand.nextInt(10);
			n1 = s.createNum(up);
			switch(saveRand){
			case 0:
			case 1:
			case 2:str=computed.sub(n1, "", str);break;//���ӷ�
			case 3:
			case 4:
			case 5:str=computed.sub(n1, "", str);break;//������
			case 6:
			case 7:str=computed.multi(n1, "", str);break;//���˷�
			case 8:
			case 9:str=computed.div(n1, "", str);break;//������
			}
		}
		return str;
	}
	//�������������������Ľ��棬�ṩ���������еȹ���
	void visual(){
		jf = new JFrame();
		jf.setTitle("��������������");
		Container container = jf.getContentPane();
		
		jf.setBounds(200, 200, 800, 600);
		
		JPanel jp_1 = new JPanel();
		JPanel jp_2 = new JPanel();
		JPanel jp_3 = new JPanel();
		container.add(jp_1,BorderLayout.NORTH);
		container.add(jp_2,BorderLayout.EAST);
		container.add(jp_3,BorderLayout.CENTER);
		
		final JTextField jtf = new JTextField(30);
		JLabel jLabel_1 = new JLabel("����������:");
		JLabel jLabel_2 = new JLabel("��ʾ����:");
		final JButton jb_3 = new JButton("����");
		jp_1.setLayout(new BorderLayout());
		jp_1.add( jLabel_1,BorderLayout.WEST);
		jp_1.add( jLabel_2,BorderLayout.SOUTH);
		jp_1.add(jtf);
		jp_1.add(jb_3,BorderLayout.EAST);
		//�����Ҳఴť
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
			jta.append("�������·��������ͨ��Replace Path�ı�·��\n");
		}
		//���������г���ť
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
							jta.append("�ѻ�ȡnֵΪ"+n+"\n");
						}else{
							jta.append("�������nֵ���ڵ���1С��10000\n");
							jtf.setText("");
						}
					}else if(Pattern.matches("-r.+", getJtf)&&n!=0){
						getr=getJtf.replaceAll("[^0-9]", "");
						r = Integer.parseInt(getr);
						if(r>0&&r<1000){//ȡֵ̫�����׳���������Χ����������ͨ����int��Ϊlong�ͽ��
							jtf.setText("");
							jta.append("�ѻ�ȡrֵΪ"+r+"\n");
						}else{
							jta.append("�������rֵ���ڵ���1С��1000\n");
							jtf.setText("");
						}	
					}else{
						jta.append("���Ȱ��ա�-n �������ķ�ʽ��������\n");
						jtf.setText("");
					}
				}else{
					if(n==0){//�ж�������nֵû
						jta.append("�밴�ա�-n �������ķ�ʽ��������\n");
						jtf.setText("");
					}else if(r==0){
						jta.append("�밴�ա�-r ȡֵ���ķ�ʽ������Ŀ���ַ�Χ\n");
						jtf.setText("");
					}
				}
				
				if(n>0&&r>0){
					file_1 = new File(Arithmetic.this.getPath+"Exercises.txt");
					file_2 = new File(Arithmetic.this.getPath+"Answers.txt");
					
					if(!file_1.isFile()){
						try {
							file_1.createNewFile();
							jta.append("������ļ�������\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						file_1.delete();
						try {
							file_1.createNewFile();
							jta.append("������ļ�������\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					if(!file_2.isFile()){
						try {
							file_2.createNewFile();
							jta.append("�𰸵��ļ�������\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						file_2.delete();
						try {
							file_2.createNewFile();
							jta.append("�𰸵��ļ�������\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					try {
						fw_1 = new FileWriter(file_1);
						fw_2 = new FileWriter(file_2);
						jta.append("�ļ�����д����\n");
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
						System.out.println(j);//����������Щ��ͬ�ĵ�ʽ�б�
						str =Arithmetic.this.createArithmetic(r);
						for(String s:str_1){
							if(str[1].equals(s)){
								b=false;
							}
							
						}
						if(b){
							str_1[i]=str[1];
							try {
								jta.append("�ļ�����д����\n");
								fw_1.write("����������Ŀ  " + (i+1) + ":  " + str[2]+ " = " + "\r\n");
								fw_2.write("���������  " + (i+1) + ":  " + str[0]+ "\r\n");
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
		//����Ŀ��ť
		jb_1.addActionListener(new ActionListener() {
			File file_1;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				file_1 = new File(Arithmetic.this.getPath+"Exercises.txt");
				if(!file_1.isFile()){
					if(!Arithmetic.this.f.isDirectory()){
						jta.append("�������·��������ͨ��Replace Path�ı�·��\n");
					}else{
						jta.append("�ļ���δ������\n");
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
		//�򿪴𰸰�ť
		jb_2.addActionListener(new ActionListener() {
			File file_2;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				file_2 = new File(Arithmetic.this.getPath+"Answers.txt");
				if(!file_2.isFile()){
					if(!Arithmetic.this.f.isDirectory()){
						jta.append("�������·��������ͨ��Replace Path�ı�·��\n");
					}else{
						jta.append("�ļ���δ������\n");
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
		//�򿪳ɼ���ť
		jb_5.addActionListener(new ActionListener() {
			File file_3;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				file_3 = new File(Arithmetic.this.getPath+"Grade.txt");
				if(!file_3.isFile()){
					if(!Arithmetic.this.f.isDirectory()){
						jta.append("�������·��������ͨ��Replace Path�ı�·��\n");
					}else{
						jta.append("�ļ���δ������\n");
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
		//���ɴ𰸰�ť
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
						jta.append("�������·��������ͨ��Replace Path�ı�·��\n");
					}else{
						jta.append("�ļ���δ������\n");
					}
				}else{
					str = Arithmetic.this.checkAnswers(file_1, file_2);
					
					if(!file_3.isFile()){
						try {
							file_3.createNewFile();
							jta.append("����ļ�������\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						file_3.delete();
						try {
							file_3.createNewFile();
							jta.append("����ļ�������\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					try {
						fw_3 = new FileWriter(file_3);//�ļ�3��д��
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
		//����·����ť
		jb_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Arithmetic.this.visualGetPath();
			}
		});
		//�رճ���ť
		jb_7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		//������ť
		jb_8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "���ܽ��ܣ�\n " +
						"\r ���������룺'-n number'������������������0-10000��\n" +
						"\r ���������룺'-r number'��������������ʹ�õ����ķ�Χ��0-1000��\n" +
						"\r             �����������������������ķ�Χ��\n" +
						"\r    ���У�                     �����������������\n" +
						"\r    OPEN Exercises��  ������Դ���ϰ�ļ�\n" +
						"\r    OPEN Answers��      ������Դ򿪴��ļ�\n" +
						"\r    Check Answers��    ���������������һ����Ĵ𰸵ĳɼ�\n" +
						"\r    OPEN Grade��         ������Դ򿪳ɼ��ļ�\n" +
						"\r    Replace Path��     ������Ը������ɵĹ���·��\n" +
						"\r    Close EXE��          ������Թر���������\n" +
						"\r    HELP��                   ������Բ鿴����\n");
			}
		});
		
	}
	public static void main(String[] args) {
		Arithmetic ar = new Arithmetic();
		ar.visualGetPath();
		//ar.visual();
	}
}
