package ch.fdehedin.oca.ClassDesign.polymorphism;
public class SubClassFromAbstract extends AbstractClass{

	public String s = "subububusubsubsclass";
	
	@Override
	public void writeInstanceMethod(){
		System.out.println("instance sub class");
	}
	
	
	public static void writeStaticMethod(){
		System.out.println("static sub class");
	}
	

}