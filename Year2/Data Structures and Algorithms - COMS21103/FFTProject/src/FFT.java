
public class FFT {

	public static void main(String[] args){
		Polynomial f = new Polynomial("-3,0,1,0");
		Polynomial g = new Polynomial("0,-2,0,0");
		Polynomial fNew = FFTcalculate(f,4,false);
		Polynomial gNew = FFTcalculate(g,4,false);
		Polynomial res = multiplyPointWise(fNew, gNew);
		System.out.println(FFTInverse(res,4));
	}
	
	public static Polynomial FFTcalculate(Polynomial A, int N, boolean inverse){
		if(N==1)
			return A;
		ComplexNumber omegaAcc = new ComplexNumber(1,0);
		ComplexNumber omega1;
		if(!inverse)
			omega1   = new ComplexNumber(Math.cos((2.0/N)*Math.PI),Math.sin((2.0/N)*Math.PI));
		else
			omega1   = new ComplexNumber(Math.cos((-2.0/N)*Math.PI),Math.sin((-2.0/N)*Math.PI));
		Polynomial A0 = new Polynomial();
		Polynomial A1 = new Polynomial();
		A.splitPolynomial(A0, A1);
		Polynomial y0 = FFTcalculate(A0, N/2, inverse);
		Polynomial y1 = FFTcalculate(A1, N/2, inverse);
		Polynomial yRes = new Polynomial();
		for(int k=0;k<N/2;k++){
			yRes.setCoefficient(k, ComplexNumber.Add(y0.getCoefficient(k), ComplexNumber.Multiply(omegaAcc, y1.getCoefficient(k))));
			yRes.setCoefficient(k+N/2, ComplexNumber.Sub(y0.getCoefficient(k), ComplexNumber.Multiply(omegaAcc, y1.getCoefficient(k))));
			omegaAcc = ComplexNumber.Multiply(omegaAcc, omega1);
		}
		return yRes;
	}
	
	public static Polynomial FFTInverse(Polynomial A, int N){
		Polynomial temp = FFTcalculate(A,N,true);
		for(int i = 0;i<A.getBound();i++){
			temp.setCoefficient(i, ComplexNumber.DivByInt(temp.getCoefficient(i), N));
		}
		return temp;
	}
	
	public static Polynomial multiplyPointWise(Polynomial A, Polynomial B){
		Polynomial C = new Polynomial();
		for(int i = 0;i<Math.max(A.getBound(), B.getBound());i++){
			C.setCoefficient(i, ComplexNumber.Multiply(A.getCoefficient(i),B.getCoefficient(i)));
		}
		return C;
	}
	
}
