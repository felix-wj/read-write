package cn.wangjie.learn.book.effective_java;


import java.util.HashMap;
import java.util.Map;

public class EnumTest {

	enum Operation{
		PLUS("+"){
			double apply(double x,double y){
				return x+y;
			}
		},
		MINUS("-"){
			double apply(double x,double y){
				return x-y;
			}
		},
		TIMES("*"){
			double apply(double x,double y){
				return x*y;
			}
		},
		DIVIDE("/"){
			double apply(double x,double y){
				return x/y;
			}
		}
		;
		private final String symbol;

		Operation(String symbol) {
			this.symbol = symbol;
		}

		@Override
		public String toString() {
			return symbol;
		}
		abstract double apply(double x,double y);

		private static final Map<String, Operation> stringToEnum = new HashMap<>();
		static{
			for (Operation operation : Operation.values()) {
				stringToEnum.put(operation.toString(),operation);
			}
		}
		public static Operation fromString(String symbol){
			return stringToEnum.get(symbol);
		}
	}

	public static void main(String[] args) {
		double x = 4;
		double y = 2;
		for (Operation symbol : Operation.values()) {
			System.out.println(String.format("%f%s%f=%f",x,symbol,y,symbol.apply(x,y)));
		}
	}
	enum PayrollDay{
		MONDAY(PayType.WEEKDAY),
		TUESDAY(PayType.WEEKDAY),
		WEDNESDAY(PayType.WEEKDAY),
		THURSDAY(PayType.WEEKDAY),
		FRIDAY(PayType.WEEKDAY),
		SATURDAY(PayType.WEEKEND),
		SUNDAY(PayType.WEEKEND);

		private final PayType payType;
		PayrollDay(PayType payType){this.payType = payType;}
		double pay(double hoursWorked,double payRate){
			return payType.overTimePay(hoursWorked,payRate);
		}

	}
	enum PayType{
		WEEKDAY{
			double overTimePay(double hours,double payRate){
				return hours<=HOURS_PRE_SHIFT?0:(hours-HOURS_PRE_SHIFT)*payRate/2;
			}
		},
		WEEKEND{
			double overTimePay(double hours,double payRate){
				return hours*payRate/2;
			}
		};

		private static final int HOURS_PRE_SHIFT=8;
		abstract double overTimePay(double hours,double payRate);
		double pay(double hoursWorked,double payRate){
			double basePay = hoursWorked*payRate;
			return basePay+overTimePay(hoursWorked,payRate);
		}
	}


}
