public class User {
	protected String userid,password;
	protected String emailid,name;
	protected long phone;
	protected int wallet;
	
	public User() {}
	
	public User(String name,String userid,String emailid,String password,long phone,int wallet) {
		this.name = name;
		this.userid = userid;
		this.emailid = emailid;
		this.password = password;
		this.phone = phone;
		this.wallet = wallet;
	}
	
	public void register() {
		SqlConnector.DBConnectupdate("insert into user values ("+
		"'"+ this.userid + "','" + this.name + "','" + this.password + "','" + this.emailid + "'," + 0 + "," + this.phone +",'No'"+");");
	}
	
	public static Boolean uniqueid (String id) {
		try {
		String arr[]=SqlConnector.DBConnectgetname();
		int i=0;
		int n = arr.length;
        for(i=0;i<n;i++) {
        	if(arr[i] != null)
        if(arr[i].equals(id))
        	return false;
        }
		}
		catch(Exception e1)
		{
			System.out.println("Exception");
		}
		return true;
	}
	
	public static Boolean checkCredentials(String id,String passwd) {
		try {
			String arr[]=SqlConnector.DBConnectgetname();
			int i=0;
			String pass[]=SqlConnector.DBConnectgetpassword();
			int n= arr.length;
            for(i=0;i<n;i++) {
            	if(arr[i] != null) {
            		if(arr[i].equals(id) && pass[i].equals(passwd))
            			return true;
            }
            }
		}
		catch(Exception e1)
		{
			System.out.println("Exception");
		}
		return false;
		}
	
	public static User getUser(String id,String passwd) {
		String nameofuser = SqlConnector.getName(id);
		String emailid = SqlConnector.getEmailid(id);
		long phoneno = SqlConnector.getPhoneno(id);
		int balance = SqlConnector.getBalance(id);
		User u = new User(nameofuser,id,emailid,passwd,phoneno,balance);
		return u;
	}
	
	public Boolean checkWallet(double distance) {
		double costOfTrip = distance * 20;
		if(wallet>=300 && (wallet-costOfTrip)>=0)
			return true;
		return false;
	}
	
	public void updateWallet(double distance) {
		double costOfTrip = distance * 20;
		wallet = wallet - (int)costOfTrip;
		SqlConnector.updateBalance(userid,wallet);
	}
	
	public void addMoney(int money) {
		wallet = wallet + money;
		SqlConnector.updateBalance(userid,wallet);
	}
	public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
	public static double distanceCal(double x1,double y1,double x2,double y2) {
		/*
		 * double latDistance = Math.toRadians(x1 - x2); double lngDistance =
		 * Math.toRadians(y1 - y2);
		 * 
		 * double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
		 * Math.cos(Math.toRadians(x1)) * Math.cos(Math.toRadians(x2))
		 * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
		 * 
		 * double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		 * 
		 * return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
		 */
		return 10*(Math.round(Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1))));
		
	}
	
	public static double costCal(double distance) {
		return Math.round(distance*20);
	}
	
	public static double timeCal(double distance) {
		return Math.round(distance*3000);
	}
	
}

