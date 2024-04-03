package fr.emse.test;

import java.util.HashSet;
import java.util.Vector;

public class MoneyBag implements IMoney {
	private Vector<Money> fMonies = new Vector<Money>();
	MoneyBag(Money m1, Money m2) {
		appendMoney(m1); 
		appendMoney(m2);
	}
	MoneyBag(Money bag[]) {
		for (int i = 0; i < bag.length; i++)
		appendMoney(bag[i]);
	}
	public void appendMoney(Money m) {
		if (fMonies.isEmpty()) {
			fMonies.add(m);
		} else {
			int i = 0;
			while ((i < fMonies.size())&& (!(fMonies.get(i).currency().equals(m.currency()))))
				i++;
			if (i >= fMonies.size()) {
				fMonies.add(m);
			} else {
				if(fMonies.get(i).amount() +m.amount()==0) {
					fMonies.remove(i);
				}else {
					fMonies.set(i, new Money(fMonies.get(i).amount() +m.amount(),m.currency()));
				}
			}
		}
	}
	
	
	
	public Vector<Money> getfMonies() {
		return fMonies;
	}
	
	@Override
	public boolean equals(Object mb) {
		if(mb == null  || !(mb instanceof MoneyBag) || ((MoneyBag)mb).getfMonies().size() != getfMonies().size()) {
			return false;
		}else {
			HashSet<Money> intersection = new HashSet<>(getfMonies());
			intersection.retainAll(((MoneyBag)mb).getfMonies());
			return intersection.size() == getfMonies().size();
		}
	}
	@Override
	public IMoney add(IMoney m) {
		// TODO Auto-generated method stub
		return m.addMoneyBag(this);
	}
	
	public IMoney addMoney(IMoney aMoney) {
		this.appendMoney((Money)aMoney);
		if(this.fMonies.size() == 1) {
			return this.fMonies.get(0);
		}else {
			return this;
		}
	}
	public IMoney addMoneyBag(IMoney aMoney) {
		for(int i=0;i<((MoneyBag)aMoney).getfMonies().size();i++) {
			this.appendMoney(((MoneyBag)aMoney).getfMonies().get(i));
		}
		if(this.fMonies.size() == 1) {
			System.out.println(this.fMonies.get(0) instanceof Money);
			return this.fMonies.get(0);
		}else {
			return this;
		}
	}
}
