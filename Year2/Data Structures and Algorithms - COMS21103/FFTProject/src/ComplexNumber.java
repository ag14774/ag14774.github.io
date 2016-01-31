
public class ComplexNumber {
	private double realPart;
	private double imaginaryPart;
	
	public ComplexNumber(double r, double i){
		if(r>0 && r<(1.0/Math.pow(10, 15)))
			r=0.0;
		if(i>0 && i<(1.0/Math.pow(10, 15)))
			i=0.0;
		realPart = r;
		imaginaryPart = i;
	}
	
	public static void main(String[] args){
		ComplexNumber A = new ComplexNumber(-2,-3);
		ComplexNumber B = new ComplexNumber(-3,5);
		System.out.println(Multiply(A,B));
	}
	
	public double getReal(){
		return realPart;
	}
	
	public double getImaginary(){
		return imaginaryPart;
	}
	
	public static ComplexNumber Add(ComplexNumber A, ComplexNumber B){
		return new ComplexNumber(A.getReal()+B.getReal(),A.getImaginary()+B.getImaginary());
	}
	
	public static ComplexNumber Sub(ComplexNumber A, ComplexNumber B){
		return new ComplexNumber(A.getReal()-B.getReal(),A.getImaginary()-B.getImaginary());
	}
	
	public static ComplexNumber Multiply(ComplexNumber A, ComplexNumber B){
		return new ComplexNumber(A.getReal()*B.getReal()-A.getImaginary()*B.getImaginary(),A.getReal()*B.getImaginary()+B.getReal()*A.getImaginary());
	}
	
	public static ComplexNumber DivByInt(ComplexNumber A, int N){
		return new ComplexNumber(A.getReal()/N,A.getImaginary()/N);
	}
	
	@Override
	public String toString(){
		if(imaginaryPart>=0)
			return realPart +" + "+imaginaryPart+"i";
		return realPart +" - "+(-imaginaryPart)+"i";
	}
	
}
