package Operation;

import java.util.Random;
import java.util.regex.Pattern;

public class Computed {
	Sum s = new Sum();
	Random rand = new Random();
	int saveRand = 0;
	//t数组的值为"结果"+"排序"+"格式"
	//r1为随机值整数，r2为随机整数
	String[] add(String r1,String r2,String[] t){
		String[] ans = new String[3]; //返回值,0="结果";1="排序";2="格式"
		if(t[0]!=null){//短式的加法
			ans[0] = s.add_nan(r1, t[0]);
			if(Pattern.matches(".+(\\+|\\-)", t[1])){
				saveRand = rand.nextInt(100);
				if(saveRand>=70){//r1 =1;t[0]=2+3;30的概率做1+(2+3)的变换，70的概率为2+3+1
					ans[2] = r1 + " " + "+" + " " + "(" + t[2] + ")";
				}else{
					ans[2] = t[2] + " " + "+" + " " + r1;
				}
			}else{//如果是乘法则不需要加括号，直接交换顺序
				saveRand = rand.nextInt(100);
				if(saveRand>=70){
					ans[2] = r1 + " " + "+" + " " + t[2];
				}else{
					ans[2] = t[2] + " " + "+" + " " +r1;
				}
			}
			ans[1] =t[1] + " " + r1 + "+";
		}else{//r2与r1 的加法
			ans[0] = s.add_nan(r1, r2);
			if(s.is1max2(r1, r2)){//标识为高到低
				ans[1]=r1 + " " + r2 + "+";
			}else{
				ans[1]=r2 + " " + r1 + "+";
			}
			ans[2]=r1 + " " + "+" + " " + r2;
		}
		return ans;
	}
	
	String[] sub(String r1,String r2,String[] t){
		String[] ans = new String[3]; //返回值,0="结果";1="排序";2="格式"
		if(t[0]!=null){//短式的减法
			ans[0] = s.sub_nan(r1,t[0]);
			if(Pattern.matches(".+(\\+|\\-)", t[1])){//如果上一次做的是加减变换需要加括号
				if(s.is1max2(r1, t[0])){
					ans[2]= r1 + " " + "-" + " " + "(" + t[2] + ")";
				}else{
					ans[2]= t[2] + " " + "-" + " " + r1;
				}
			}else{
				if(s.is1max2(r1, t[0])){//r1大于短式
					ans[2]= r1 + " " + "-" + " " + t[2];
				}else{
					ans[2]=t[2] + " " + "-" + " " +r1;
				}
			}
			ans[1] = t[1] + " " + r1 + "-";
		}else{//r1跟r2的减法
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
		String[] ans = new String[3]; //返回值,0="结果";1="排序";2="格式"
		if(t[0]!=null){//短式的乘法
			ans[0] = s.multi_nan(r1,t[0]);
			if(Pattern.matches(".+(\\+|\\-)", t[1])){//上一次操作是加减法
				saveRand = rand.nextInt(100);
				if(saveRand>=70){//r1 =1;t[0]=2*3;30的概率做1*(2+3)的变换，70的概率为(2+3)*1
					ans[2] = r1+ " " + "*" + " " + "(" + t[2] + ")";
				}else{
					ans[2] = "(" + t[2] + ")" + " " + "*" + " " + r1;
				}
			}else{
				saveRand = rand.nextInt(100);
				if(saveRand>=70){//r1 =1;t[0]=2*3;30的概率做1*(2/3)的变换，70的概率为2*3*1
					ans[2] = r1+ " " + "*" + " " + "(" + t[2] + ")";
				}else{
					ans[2] = t[2] + " " + "*" + " " + r1;
				}
			}
			
			ans[1] =t[1] + " " + r1 + "*";
		}else{//r1跟r2的乘法
			ans[0] = s.multi_nan(r1, r2);
			if(s.is1max2(r1, r2)){//标识为高到低
				ans[1]=r1 + " " + r2 + "*";
			}else{
				ans[1]=r2 + " " + r1 + "*";
			}
			ans[2]=r1 + " " + "*" + " " + r2;
		}
		return ans;
	}
	
	String[] div(String r1,String r2,String[] t){
		String[] ans = new String[3]; //返回值,0="结果";1="排序";2="格式"
		if(t[0]!=null){//短式的除法
			ans[0] = s.div_nan(t[0],r1);
			if(Pattern.matches(".+(\\+|\\-)", t[1])){//上一次操作是加减法
				ans[2] = "(" + t[2] + ")" + " " + "/" + " " + r1;
			}else{//上一次的操作是乘除
				ans[2] = t[2] + " " + "/" + " " + r1;
			}
			ans[1] =t[1] + " " + r1 + "/";
		}else{//r2/r1(不会变的格式)只会进行r2/r1，只需保证r1是非0的数就不需要考虑除法错误问题
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
			n1 = s.createNum(1);
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
		System.out.println("str[0]:"+str[0]);
		System.out.println("str[1]:"+str[1]);
		System.out.println("str[2]:"+str[2]);
	}
	
}
