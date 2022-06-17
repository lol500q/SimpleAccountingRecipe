import java.util.*;
import java.io.*;
public class lab10{
	static Scanner c=new Scanner(System.in);
	public static void main(String[]args) throws Exception{
		Scanner c=new Scanner(System.in);
		ArrayList<Item> items=new ArrayList<Item>();
		readfromfile(items);
		
		while(true){
		
	
	System.out.println("1. Create Receipt ");

	System.out.println("2. Add Item");
	System.out.println("3. Edit Item");
	System.out.println("4. Delete Item");
	System.out.println("5. Exit");
	System.out.print("Enter a Selection from all above ");
	int choice=c.nextInt();
	switch(choice){
		case 1:
		createReceipt(items);
		break;
		case 2:
		addItem(items);
		break;
		case 3:
		editItem(items);
		break;
		case 4:
		deleteItem(items);
		break;
		case 5:
		System.exit(0);
		
	}
	
	}
	}
	public static void createReceipt(ArrayList<Item> items){
		ArrayList<Item> sa=new ArrayList<>();
		ArrayList<Integer> number=new ArrayList<>();
		System.out.print("Enter Item Code: ");
		int code=c.nextInt();
		
		while(code!=0){
			boolean d=false;
			int s=0;
			for(int i=0;i<items.size();i++){
				if(items.get(i).code==code){
					sa.add(items.get(i));
					System.out.print("Enter the Qty :");
					int y=c.nextInt();
					number.add(y);
					d=true;
				}
			}
			if(!d){
				System.out.println("not Found ");
			}
			System.out.print("Enter Item Code: ");
			code=c.nextInt();
		}
		System.out.println("---------Receipt---------");
		System.out.println("ITEM NAME     Qty   PRICE    TOTAL ");
		double total=0;
		for(int i=0;i<sa.size();i++){
			System.out.printf("%10s%4d%8.2f%8.2f\n",sa.get(i).getName(),number.get(i),sa.get(i).getPrice(),sa.get(i).getPrice()*number.get(i));
			total+=number.get(i)*sa.get(i).getPrice();
		}
		System.out.println("----------");
		System.out.printf("Total: $%4.2f\n",total);
		
		
		
		
		
		
	}
	public static void addItem(ArrayList<Item> items) throws Exception{
		Item item=new Item();
		System.out.print("Enter Item Code: ");
		int code=c.nextInt();
		item.setCode(code);
		c.nextLine();
		System.out.print("Enter Item New Name : (If empty leave it as it is): ");
		String name=c.nextLine();
		item.setName(name);
		System.out.print("Enter Item New Price : ");
		double price=c.nextDouble();
		item.setPrice(price);
		items.add(item);
		System.out.println("Item saved! ");
		writeOnFile(items);
	}
	public static void editItem(ArrayList<Item> items) throws Exception{
		System.out.print("Enter Item Code : ");
		int code=c.nextInt();
		c.nextLine();
		boolean is=false;
		for(int i=0;i<items.size();i++){
			if(code==items.get(i).getCode()){
				is=true;
				
				System.out.print("Enter Item New Name : (If empty leave it as it is) :  ");
				String name=c.nextLine();
				items.get(i).setName(name);
				System.out.print("Enter Item New Price : ");
				double price=c.nextDouble();
				items.get(i).setPrice(price);
			}
		}
		if(!is){
			System.out.print("code not found :( ");
			
		}
		else{
			System.out.print("Item saved! ");
		}
		writeOnFile(items);
	}
	public static void deleteItem(ArrayList<Item> items) throws Exception{
		System.out.print("Enter item code : ");
		int code=c.nextInt();
		for(int i=0;i<items.size();i++){
			if(code==items.get(i).getCode()){
				items.remove(i);
			}
		}
		System.out.println("Item deleted ");
		writeOnFile(items);
	}
	
	
	
	
public static	void writeOnFile(ArrayList<Item> items) throws Exception{
	File file=new File("account.txt");
	PrintWriter output=new PrintWriter(file);
	for(int i=0;i<items.size();i++){
		output.print(items.get(i).getCode()+" "+items.get(i).getPrice()+" ");
		output.println(items.get(i).getName());
	}
	output.close();
	
}
public static void readfromfile(ArrayList<Item> items) throws Exception{
	File file=new File("account.txt");
	Scanner x=new Scanner(file);
	while(x.hasNext()){
		Item item=new Item();
		int code=x.nextInt();
		item.setCode(code);
		double price=x.nextDouble();
		item.setPrice(price);
		String name=x.nextLine();
		item.setName(name);
		items.add(item);
		
	}
	
}

}
class Item{
	String name;
	double  price;
	int code;
	Item(){
	}
	void setName(String name){
		this.name=name;
	}
	void setCode(int code){
		this.code=code;
	}
	void setPrice(double price){
		this.price=price;
	}
	String getName(){
		return name;
	}
	double getPrice(){
		return price;
	}
	int getCode(){
		return code;
	}
}