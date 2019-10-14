package Operation;

import java.util.Random;
import java.util.regex.Pattern;

public class Computed {
	Sum s = new Sum();
	Random rand = new Random();
	int saveRand = 0;
	//t�����ֵΪ"���"+"����"+"��ʽ"
	//r1Ϊ���ֵ������r2Ϊ�������
	String[] add(String r1,String r2,String[] t){
		String[] ans = new String[3]; //����ֵ,0="���";1="����";2="��ʽ"
		if(t[0]!=null){//��ʽ�ļӷ�
			ans[0] = s.add_nan(r1, t[0]);
			if(Pattern.matches(".+(\\+|\\-)", t[1])){
				saveRand = rand.nextInt(100);
				if(saveRand>=70){//r1 =1;t[0]=2+3;30�ĸ�����1+(2+3)�ı任��70�ĸ���Ϊ2+3+1
					ans[2] = r1 + " " + "+" + " " + "(" + t[2] + ")";
				}else{
					ans[2] = t[2] + " " + "+" + " " + r1;
				}
			}else{//����ǳ˷�����Ҫ�����ţ�ֱ�ӽ���˳��
				saveRand = rand.nextInt(100);
				if(saveRand>=70){
					ans[2] = r1 + " " + "+" + " " + t[2];
				}else{
					ans[2] = t[2] + " " + "+" + " " +r1;
				}
			}
			ans[1] =t[1] + " " + r1 + "+";
		}else{//r2��r1 �ļӷ�
			ans[0] = s.add_nan(r1, r2);
			if(s.is1max2(r1, r2)){//��ʶΪ�ߵ���
				ans[1]=r1 + " " + r2 + "+";
			}else{
				ans[1]=r2 + " " + r1 + "+";
			}
			ans[2]=r1 + " " + "+" + " " + r2;
		}
		return ans;
	}
	
	String[] sub(String r1,String r2,String[] t){
		String[] ans = new String[3]; //����ֵ,0="���";1="����";2="��ʽ"
		if(t[0]!=null){//��ʽ�ļ���
			ans[0] = s.sub_nan(r1,t[0]);
			if(Pattern.matches(".+(\\+|\\-)", t[1])){//�����һ�������ǼӼ��任��Ҫ������
				if(s.is1max2(r1, t[0])){
					ans[2]= r1 + " " + "-" + " " + "(" + t[2] + ")";
				}else{
					ans[2]= t[2] + " " + "-" + " " + r1;
				}
			}else{
				if(s.is1max2(r1, t[0])){//r1���ڶ�ʽ
					ans[2]= r1 + " " + "-" + " " + t[2];
				}else{
					ans[2]=t[2] + " " + "-" + " " +r1;
				}
			}
			ans[1] = t[1] + " " + r1 + "-";
		}else{//r1��r2�ļ���
			ans[0] = s.sub_nan(r1,r2);
			if(s.is1max2(r1, r2)){//r1>r2
				ans[1] = r1 + " "+ r2 +"-";
				ans[2] = r1 + " " + "-" + " " + r2;
			}else{
				ans[1] = r2 + " "+ r1 +"-";
				ans[2] = r2 + " " + "-" + " " + r1;
			}
		}
		return ans;
	}
	
	String[] multi(String r1,String r2,String[] t){
		String[] ans = new String[3]; //����ֵ,0="���";1="����";2="��ʽ"
		if(t[0]!=null){//��ʽ�ĳ˷�
			ans[0] = s.multi_nan(r1,t[0]);
			if(Pattern.matches(".+(\\+|\\-)", t[1])){//��һ�β����ǼӼ���
				saveRand = rand.nextInt(100);
				if(saveRand>=70){//r1 =1;t[0]=2*3;30�ĸ�����1*(2+3)�ı任��70�ĸ���Ϊ(2+3)*1
					ans[2] = r1+ " " + "*" + " " + "(" + t[2] + ")";
				}else{
					ans[2] = "(" + t[2] + ")" + " " + "*" + " " + r1;
				}
			}else{
				saveRand = rand.nextInt(100);
				if(saveRand>=70){//r1 =1;t[0]=2*3;30�ĸ�����1*(2/3)�ı任��70�ĸ���Ϊ2*3*1
					ans[2] = r1+ " " + "*" + " " + "(" + t[2] + ")";
				}else{
					ans[2] = t[2] + " " + "*" + " " + r1;
				}
			}
			
			ans[1] =t[1] + " " + r1 + "*";
		}else{//r1��r2�ĳ˷�
			ans[0] = s.multi_nan(r1, r2);
			if(s.is1max2(r1, r2)){//��ʶΪ�ߵ���
				ans[1]=r1 + " " + r2 + "*";
			}else{
				ans[1]=r2 + " " + r1 + "*";
			}
			ans[2]=r1 + " " + "*" + " " + r2;
		}
		return ans;
	}
	
	String[] div(String r1,String r2,String[] t){
		String[] ans = new String[3]; //����ֵ,0="���";1="����";2="��ʽ"
		if(t[0]!=null){//��ʽ�ĳ���
			ans[0] = s.div_nan(t[0],r1);
			if(Pattern.matches(".+(\\+|\\-)", t[1])){//��һ�β����ǼӼ���
				ans[2] = "(" + t[2] + ")" + " " + "/" + " " + r1;
			}else{//��һ�εĲ����ǳ˳�
				ans[2] = t[2] + " " + "/" + " " + r1;
			}
			ans[1] =t[1] + " " + r1 + "/";
		}else{//r2/r1(�����ĸ�ʽ)ֻ�����r2/r1��ֻ�豣֤r1�Ƿ�0�����Ͳ���Ҫ���ǳ�����������
			ans[0] = s.div_nan(r2,r1);
			ans[2] = r2 + " " + "/" + " " + r1;
			ans[1] = r2 + " " + r1 + "/";
		}
		return ans;
	}
	
	public static void main(String[] args) {
		Computed computed = new Computed();
		Sum s = new Sum();
		String[] str = new String[3]; 
		Random rand = new Random();
		int saveRand = rand.nextInt(4);
		int forNum = 0;
		String n1 = s.createNum(1);
		String n2 = s.createNum(1);
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
			n1 = s.createNum(1);
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
		System.out.println("str[0]:"+str[0]);
		System.out.println("str[1]:"+str[1]);
		System.out.println("str[2]:"+str[2]);
	}
	
}
