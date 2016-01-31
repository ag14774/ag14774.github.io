import java.util.ArrayList;
import java.util.List;


public class Polynomial {
	private List<ComplexNumber> coefficients;
	
	public Polynomial(String pol){
		coefficients = new ArrayList<ComplexNumber>();
		String[] coef = pol.split(",");
		for(int i = 0;i<coef.length;i++)
			coefficients.add(new ComplexNumber(Double.parseDouble(coef[i]),0.0));
	}
	
	public static void main(String args[]){
		Polynomial A = new Polynomial("2,4,6,7");
		System.out.println(A);
	}
	
	public Polynomial(){
		coefficients = new ArrayList<ComplexNumber>();
	}
	
	public int getBound(){
		return coefficients.size();
	}
	
	public void addCoefficient(ComplexNumber x){
		coefficients.add(x);
	}
	
	public void setCoefficient(int i, ComplexNumber x){
		while(i>=coefficients.size())
			coefficients.add(new ComplexNumber(0.0,0.0));
		coefficients.set(i, x);
	}
	
	public ComplexNumber getCoefficient(int i){
		if(i>=coefficients.size())
			return new ComplexNumber(0.0,0.0);
		return coefficients.get(i);
	}
	
	public void splitPolynomial(Polynomial A0, Polynomial A1){
		for(int i = 0;i<coefficients.size();i++){
			if(i%2==0)
				A0.addCoefficient(coefficients.get(i));
			else
				A1.addCoefficient(coefficients.get(i));
		}
	}
	
	@Override
	public String toString(){
		String res = "(";
		for(int i=0;i<coefficients.size()-1;i++){
			res = res + coefficients.get(i)+", ";
		}
		if(coefficients.isEmpty())
			res = res + ")";
		else
			res = res + coefficients.get(coefficients.size()-1) + ")";
		return res;
	}
	
}
