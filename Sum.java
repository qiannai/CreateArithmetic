package Operation;

import java.util.Random;
import java.util.regex.Pattern;

public class Sum {//实现加减乘除的运算
	
	public String createNum(int up){//生成0-up的数且不包括0跟up
		String num = "";
		Random rand = new Random();
		int saveRand = 0;
		saveRand = rand.nextInt(100);
		int temp = 0;
		int[] i1 = new int[2];//真分数保存
		int[] i2 = new int[3];//带分数保存
		int   i3 = 0;//整数保存
		if(saveRand<20){//20的概率是分数
			do{
				i1[0]=rand.nextInt(10)+1;
				i1[1]=rand.nextInt(10)+1;
			}while(i1[0]==i1[1]);	
			if(i1[0]>i1[1]){
				temp = i1[0];
				i1[0] = i1[1];
				i1[1] = temp;
			}
			num = this.trueFaction(i1);
		}else if(saveRand<50){//30的概率是带分数
			i2[0] = (int)(Math.random()*(up-1))+1;
			do{
				i2[1]=rand.nextInt(10)+1;
				i2[2]=rand.nextInt(10)+1;
			}while(i2[1]==i2[2]);	
			if(i2[1]>i2[2]){
				temp = i2[1];
				i2[1] = i2[2];
				i2[2] = temp;
			}
			i1[0] = i2[0]*i2[2]+i2[1];
			i1[1] = i2[2];
			num = this.trueFaction(i1);
		}else{//50的概率是产生一个整数
			i3 = (int)(Math.random()*(up-1))+1;
			num = "" + i3;
		}
		return num;
	}
	
	public String add_nan(String n1,String n2){//两个数相加的值
		String sum = "";
		if(Pattern.matches(".+\\/.+", n1)){//n1是分数
			if(Pattern.matches(".+\\/.+", n2)){//n1是分数，n2是分数
				sum = this.add_faf(n1, n2);
			}else{//n1是分数，n2是整数
				sum = this.add_faz(n1, n2);
			}
		}else{//n1是整数
			if(Pattern.matches(".+\\/.+", n2)){//n1是整数,n2是分数
				sum = this.add_faz(n2, n1);
			}else{//n1是整数,n2是整数
				sum = this.add_zaz(n1, n2);
			}
		}
		
		return sum;
	}
	
	public String sub_nan(String n1, String n2){//返回两个数相减的绝对值
		String sum = "";
		if(Pattern.matches(".+\\/.+", n1)){//n1是分数
			if(Pattern.matches(".+\\/.+", n2)){//n1是分数，n2是分数
				sum = this.sub_faf(n1, n2);
			}else{//n1是分数，n2是整数
				sum = this.sub_faz(n1, n2);
			}
		}else{//n1是整数
			if(Pattern.matches(".+\\/.+", n2)){//n1是整数,n2是分数
				sum = this.sub_faz(n2, n1);
			}else{//n1是整数,n2是整数
				sum = this.sub_zaz(n1, n2);
			}
		}
		return sum;
	}
	
	public String multi_nan(String n1,String n2){
		String sum = "";
		if(Pattern.matches(".+\\/.+", n1)){//n1是分数
			if(Pattern.matches(".+\\/.+", n2)){//n1是分数，n2是分数
				sum = this.multi_faf(n1, n2);
			}else{//n1是分数，n2是整数
				sum = this.multi_faz(n1, n2);
			}
		}else{//n1是整数
			if(Pattern.matches(".+\\/.+", n2)){//n1是整数,n2是分数
				sum = this.multi_faz(n2, n1);
			}else{//n1是整数,n2是整数
				sum = this.multi_zaz(n1, n2);
			}
		}
		return sum;
	}
	
	public String div_nan(String n1,String n2){//一定要注意除法是n1/n2，如果按照Computed中,必须是t[0]/r1或者是r2/r1;故输入为(t[0],r1)|(r2/r1)
		String sum = "";
		if(Pattern.matches(".+\\/.+", n1)){//n1是分数
			if(Pattern.matches(".+\\/.+", n2)){//n1是分数，n2是分数
				sum = this.div_faf(n1, n2);
			}else{//n1是分数，n2是整数
				sum = this.div_faz(n1, n2);
			}
		}else{//n1是整数
			if(Pattern.matches(".+\\/.+", n2)){//n1是整数,n2是分数
				sum = this.div_zaf(n1,n2);
			}else{//n1是整数,n2是整数
				sum = this.div_zaz(n1, n2);
			}
		}
		return sum;
	}
	
	public String add_faz(String f,String z){//分数与整数相加,返回一个String和
		String sum = "";
		int[] i1 = new int[2];
		int   i2 = Integer.parseInt(z);
		i1 = this.falseFaction(f);
		i1[0] = i2*i1[1]+i1[0];
		sum = this.trueFaction(i1);
		return sum;
	}
	
	public String add_faf(String r1,String r2){//两个分数相加
		int[] i1 = new int[2];
		int[] i2 = new int[2];
		int[] s = new int[2];
		String sum ="";
		i1 = this.falseFaction(r1);
		i2 = this.falseFaction(r2);
		s[0]=i1[0]*i2[1]+i2[0]*i1[1];
		s[1]=i1[1]*i2[1];
		sum = this.trueFaction(s);
		return sum;
	}
	
	public String add_zaz(String z1,String z2){//两个整数相加
		String sum ="";
		int s = 0;
		int i1 = Integer.parseInt(z1);
		int i2 = Integer.parseInt(z2);
		s = i1 + i2;
		sum+=s;
		return sum;
	}
	
	public String sub_zaz(String z1, String z2){//实现两个整数相减，返回绝对值
		String sum = "";
		int s = 0;
		int i1 = Integer.parseInt(z1);
		int i2 = Integer.parseInt(z2);
		if(this.is1max2(z1, z2)){
			s = i1 - i2;
		}else{
			s = i2 -i1;
		}
		sum +=s;
		return sum;
	}
	
	public String sub_faz(String f,String z){//分数跟整数相减，返回绝对值
		String sum = "";
		int[] i1 = new int[2];
		int   i2 = Integer.parseInt(z);
		i1 = this.falseFaction(f);
		if(this.is1max2(f, z)){//分数大于整数
			i1[0]-=i1[1]*i2;
		}else{//整数大于分数
			i1[0]=i1[1]*i2-i1[0];
		}
		sum = this.trueFaction(i1);
		return sum;
	}
	
	public String sub_faf(String r1,String r2){//返回两分数相减的绝对值
		String sum = "";
		int[] i1 = new int[2];
		int[] i2 = new int[2];
		int[] s = new int[2];
		i1 = this.falseFaction(r1);
		i2 = this.falseFaction(r2);
		if(this.is1max2(r1, r2)){//r1>r2
			s[0]=i1[0]*i2[1]-i2[0]*i1[1];
		}else{
			s[0]=i2[0]*i1[1]-i1[0]*i2[1];
		}
		s[1]=i1[1]*i2[1];
		sum=this.trueFaction(s);
		return sum;
	}
	
	public String multi_zaz(String z1,String z2){//两整数相乘
		String sum = "";
		int s = 0;
		int i1 = Integer.parseInt(z1);
		int i2 = Integer.parseInt(z2);
		s = i1 * i2;
		sum+=s;
		return sum;
	}
	
	public String multi_faz(String f,String z){//分数跟整数相乘
		String sum = "";
		int[] i1 = new int[2];
		int   i2 = Integer.parseInt(z);
		i1 = this.falseFaction(f);
		i1[0] = i2*i1[0];
		sum = this.trueFaction(i1);
		return sum;
	}
	
	public String multi_faf(String r1,String r2){//两分数相乘
		int[] i1 = new int[2];
		int[] i2 = new int[2];
		int[] s = new int[2];
		String sum ="";
		i1 = this.falseFaction(r1);
		i2 = this.falseFaction(r2);
		s[0]=i1[0]*i2[0];
		s[1]=i1[1]*i2[1];
		sum = this.trueFaction(s);
		return sum;
	}
	
	public String div_zaz(String z1,String z2){
		String sum = "";
		int[] i1 = new int[2];
		i1[0] = Integer.parseInt(z1);
		i1[1] = Integer.parseInt(z2);
		sum=this.trueFaction(i1);
		return sum;
	}
	
	public String div_faz(String f,String z){
		String sum = "";
		int[] i1 = new int[2];
		int   i2 = Integer.parseInt(z);
		i1 = this.falseFaction(f);
		i1[1] = i2*i1[1];
		sum = this.trueFaction(i1);
		return sum;
	}
	
	public String div_zaf(String z,String f){
		String sum = "";
		int[] i1 = new int[2];
		int temp=0;
		int   i2 = Integer.parseInt(z);
		i1 = this.falseFaction(f);
		temp=i1[0];//保存分子的值
		i1[0] = i2*i1[1];
		i1[1] = temp;
		sum = this.trueFaction(i1);
		return sum;
	}
	
	public String div_faf(String r1, String r2){
		int[] i1 = new int[2];
		int[] i2 = new int[2];
		int[] s = new int[2];
		String sum ="";
		i1 = this.falseFaction(r1);
		i2 = this.falseFaction(r2);
		s[0]=i1[0]*i2[1];
		s[1]=i1[1]*i2[0];
		sum = this.trueFaction(s);
		return sum;
	}
	 
	int[] falseFaction(String f){//将一个分数换成分子除分母的int型，0为分子，1为分母
		String[] cutF =new String[3];
		int[] fract = new int[3];
		int[] falseFac = new int[2];
		if(Pattern.matches(".*\\'.*",  f)){
			cutF = f.split("\\/|\\'");
			for(int i=0;i<cutF.length;i++){
				fract[i]=Integer.parseInt(cutF[i]);
			}
			falseFac[0]=fract[0]*fract[2]+fract[1];
			falseFac[1]=fract[2];
		}else{
			cutF = f.split("\\/");
			for(int i=0;i<cutF.length;i++){
				fract[i]=Integer.parseInt(cutF[i]);
			}
			falseFac[0]=fract[0];
			falseFac[1]=fract[1];
		}
		
		return falseFac;
	}
	
	String trueFaction(int[] flaFac){//化为真分数
		String s="";
		int k = 0;
		int fenzi = 0;
		int maxCom =1;
		fenzi =flaFac[0]%flaFac[1];
		maxCom = this.maxCommonFactor(fenzi, flaFac[1]);
		k=flaFac[0]/flaFac[1];
		fenzi/=maxCom;
		flaFac[1]/=maxCom;
		if(fenzi==0){
			s+=k;
		}else{
			if(k==0){
				s+=fenzi+"/"+flaFac[1];
			}else{
				s+=k+"'"+fenzi+"/"+flaFac[1];
			}
		}
		return s;
	}
	
	int maxCommonFactor(int r1,int r2){//求出最大公因数
		int num1 = r1<r2?r1:r2;//两数最小
		int num2 = r1<r2?r2:r1;//两数最大
		for(int i=num1;i>=1;i--){
			if(0==num1%i&&0==num2%i){
				return i;
			}
		}
		return 1;
	}
	
	boolean is1max2(String r1,String r2){//比较两个随机数的大小，如果r1>r2;则返回true
		int[] i1 = new int[2];
		int[] i2 = new int[2];
		double d1 = 0;
		double d2 = 0;
		if(Pattern.matches(".+\\/.+", r1)){//r1是分数
			i1=this.falseFaction(r1);//化为假分数
			d1=(double)i1[0]/(double)i1[1];//化成小数
		}else{//r1是整数
			d1 = Integer.parseInt(r1);
		}
		if(Pattern.matches(".+\\/.+", r2)){//r2是分数
			i2=this.falseFaction(r2);
			d2=(double)i2[0]/(double)i2[1];
		}else{//r1是整数
			d2 = Integer.parseInt(r2);
		}
		if(d1>d2){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Sum s = new Sum();
		/*int[] t = new int[2];
		t[0]=16;
		t[1]=6;
		/*String str="1'2/5";
		String z="2";
		System.out.println(s.is1max2(z,str));
		int[] t = new int[2];
		t[0]=12;
		t[1]=5;
		//t=s.falseFaction(str);
		str = s.trueFaction(t);
		System.out.println(str);
		System.out.println(s.add_faz(str, z));
		t=s.falseFaction(s.add_faz(str, z));
		System.out.println(t[0]+  "   "+t[1]);
		
		System.out.println(s.is1max2(z,str));
		System.out.println(s.add_faf("1/2", "1/3"));
		System.out.println(s.maxCommonFactor(9, 15));
		System.out.println(s.trueFaction(t));
		System.out.println(s.add_zaz("8", "14"));
		System.out.println(s.add_faz("3'1/2", "7"));
		System.out.println(s.add_nan("6", "2'1/3"));
		System.out.println(s.sub_zaz("9", "6"));
		System.out.println(s.sub_faz("4'3/4", "3"));
		System.out.println(s.sub_faf("1'1/4", "2'1/3"));
		System.out.println(s.sub_nan("9", "6"));
		System.out.println(s.multi_zaz("8", "9"));
		System.out.println(s.multi_faz("1'3/4", "3"));
		System.out.println(s.multi_faf("1'7/8", "2/3"));
		System.out.println(s.multi_nan("3/4", "4"));
		System.out.println(s.div_zaz("132", "8"));
		System.out.println(s.div_nan("1'1/4", "1'1/3"));
		System.out.println(s.createNum(1));*/
		//测试代码，
		String[][] str = {{"4","9"},{"3/4","5/9"},{"7","4/7"},{"4/9","3"},{"2'3/4","5'3/7"},{"3","4'2/3"},{"2'3/5","3"},{"3'2/3","2/3"}};
		//保存{整数跟整数，分数跟分数，整数跟分数，分数跟整数,带分数跟带分数，整数跟带分数，带分数跟整数，带分数跟分数}
		for(String[] sl:str){
			System.out.println(sl[0] + "+" + sl[1] + " : " + s.add_nan(sl[0], sl[1]));
			System.out.println(sl[0] + "*" + sl[1] + " : " + s.multi_nan(sl[0], sl[1]));
			System.out.println("|" + sl[0] + "-" + sl[1] + "|" + " : " + s.sub_nan(sl[0], sl[1]));
			System.out.println(sl[0] + " / " + sl[1] + " : " + s.div_nan(sl[0], sl[1]));
			System.out.println();
		}
		
	}
}
