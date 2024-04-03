package fr.emse.test;

public class Money implements IMoney{
	private int fAmount;
	private String fCurrency;
	public Money(int amount, String currency) {
		fAmount = amount;
		fCurrency = currency;
	}
	public int amount() {
		return fAmount;
	}
	public String currency() {
		return fCurrency;
	}
	
	@Override
	public boolean equals(Object m) {
		if(m == null || !(m instanceof Money)) {
			return false;
		}
		return amount() == ((Money)m).amount() && currency() == ((Money)m).currency();
	}
	@Override
	public IMoney add(IMoney m) {
		return m.addMoney(this);
	}
	
	public IMoney addMoney(IMoney aMoney) {
		if (((Money)aMoney).currency().equals(currency()))
			return new Money(amount() + ((Money)aMoney).amount(), currency());
		return new MoneyBag(this, ((Money)aMoney));
	}
	public IMoney addMoneyBag(IMoney aMoney) {
		((MoneyBag)aMoney).appendMoney(this);
		if(((MoneyBag)aMoney).getfMonies().size() == 1) {
			return ((MoneyBag)aMoney).getfMonies().get(0);
		}else {
			return aMoney;
		}
	}
}
